package antimattermod.core.fileIO;


import antimattermod.core.AntiMatterModCore;
import antimattermod.core.Log;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * ファイルをURL上からダウンロードします。
 * <br>Created by Raiti-chan on 2016/12/28.
 * <br>Modified by Kamesuta on 2017/1/28.
 *
 * @author Raiti-chan
 * @author Kamesuta
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DownloadFiles {
    /**
     * ダウンロードスレッドプール
     */
    public static final ExecutorService processor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("AMM-DL-%d").build());

    /**
     * ダウンローダー
     */
    public static final HttpClient client;

    static {
        final int timeouttime = 10 * 1000;

        Registry<ConnectionSocketFactory> registry = null;
        try {
            final SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(
                            null,
                            new TrustSelfSignedStrategy())
                    .loadTrustMaterial(
                            null, (@Nullable X509Certificate[] chain, final @Nullable String authType) -> true)
                    .build();
            final SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER) {
                @Override
                protected void prepareSocket(final @Nullable SSLSocket socket) throws IOException {
                    if (socket != null) {
                        final String[] enabledCipherSuites = socket.getEnabledCipherSuites();

                        final List<String> asList = Lists.newArrayList(enabledCipherSuites);

                        asList.remove("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
                        asList.remove("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
                        asList.remove("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");

                        final String[] array = asList.toArray(new String[0]);
                        socket.setEnabledCipherSuites(array);
                    }
                }
            };

            registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory)
                    .build();
        } catch (final NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            //
        }

        final PoolingHttpClientConnectionManager manager;
        if (registry != null)
            manager = new PoolingHttpClientConnectionManager(registry);
        else
            manager = new PoolingHttpClientConnectionManager();

        final RequestConfig.Builder requestConfig = RequestConfig.custom();
        requestConfig.setConnectTimeout(timeouttime);
        requestConfig.setSocketTimeout(timeouttime);

        final List<Header> headers = Lists.newArrayList();
        headers.add(new BasicHeader("Accept-Charset", "utf-8"));
        headers.add(new BasicHeader("Accept-Language", "ja, en;q=0.8"));
        headers.add(new BasicHeader("User-Agent", AntiMatterModCore.MOD_ID));

        client = HttpClientBuilder.create()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(requestConfig.build())
                .setDefaultHeaders(headers)
                .build();
    }

    /**
     * 現在のスレッドでファイルを指定されたURLからダウンロードしてきます。
     *
     * @param url  ファイルのURL
     * @param path 保存するパスへの{@link File}オブジェクト
     * @return ダウンロードしたファイルへのパス文字列
     */
    public static
    @Nullable
    File downloadBlocking(String url, File path) {
        InputStream input = null;
        OutputStream output = null;
        try {
            File parent;//親ディレクトリオブジェクト
            if (!(parent = path.getParentFile()).isDirectory()) {
                //noinspection ResultOfMethodCallIgnored
                parent.mkdirs();//親ディレクトリが存在しなかった場合生成
            }
            //接続処理
            URI u = new URI(url);
            Log.dev.info("DownloadURL:" + u.toString());
            Log.dev.info("Connection...");
            final HttpUriRequest req = new HttpGet(u);
            final HttpResponse response = client.execute(req);
            final HttpEntity entity = response.getEntity();
            input = entity.getContent();
            output = new BufferedOutputStream(new FileOutputStream(path));
            IOUtils.copyLarge(input, output);
            Log.dev.info("Finish!!");
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
        return null;
    }

    /**
     * ファイルを指定されたURLからダウンロードするキューを追加します。
     *
     * @param url      ファイルのURL
     * @param path     保存するパスへの{@link File}オブジェクト
     * @param callback コールバック
     */
    public static void download(@NotNull String url, @NotNull File path, @Nullable DownloadCallback callback) {
        processor.execute(() -> {
            File f = downloadBlocking(url, path);
            if (callback != null)
                callback.onDone(f);
        });
    }

    /**
     * ファイルを指定されたURLからダウンロードするキューを追加します。
     *
     * @param url  ファイルのURL
     * @param path 保存するパスへの{@link File}オブジェクト
     */
    public static void download(@NotNull String url, @NotNull File path) {
        download(url, path, null);
    }

    /**
     * ダウンロードコールバック
     */
    public interface DownloadCallback {
        void onDone(@Nullable File path);
    }

    /**
     * MinecraftのプレイヤースキンのロケーションURL
     */
    public static final String SKIN_URL_LOCATION = "http://skins.minecraft.net/MinecraftSkins/";

    /**
     * MOJANG APIを使用し、UUIDから情報を取得するURL
     */
    public static final String MOJANG_API_UUID_URL_LOCATION = "https://sessionserver.mojang.com/session/minecraft/profile/";
}
