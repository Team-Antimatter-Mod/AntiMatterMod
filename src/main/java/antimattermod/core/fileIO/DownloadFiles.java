package antimattermod.core.fileIO;


import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * ファイルをURL上からダウンロードします。
 * <br>Created by Raiti-chan on 2016/12/28.
 *
 * @author Raiti-chan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DownloadFiles {
    
    /**
     * ダウンロードに使用するバッファsize
     */
    private static int bufferSize = 2048;
    /**
     * ダウンロードに使用するバッファ配列
     */
    private static byte[] buffer = new byte[bufferSize];
    
    /**
     * ダウンロードに使用するバッファーのサイズを変更します。
     *
     * @param bufferSize バッファサイズ
     */
    public static void setBufferSize(int bufferSize) {
        DownloadFiles.bufferSize = bufferSize;
        DownloadFiles.buffer = new byte[bufferSize];
    }
    
    /**
     * 現在のバッファサイズを取得します。
     *
     * @return 現在のバッファサイズ
     */
    public static int getBufferSize() {
        return DownloadFiles.buffer.length;
    }
    
    /**
     * ファイルを指定されたURLからダウンロードしてきます。
     *
     * @param url  ファイルのURL
     * @param path 保存するパスへの{@link File}オブジェクト
     * @return ダウンロードしたファイルへのパス文字列
     */
    public static String download(String url, File path) {
        boolean flag;//URLへの接続が成功した場合trueになるフラグ
        URLConnection connection = null;//URLへの接続オブジェクト
        try {
            File parent;//親ディレクトリオブジェクト
            if (!(parent = path.getParentFile()).isDirectory()) {
                //noinspection ResultOfMethodCallIgnored
                parent.mkdirs();//親ディレクトリが存在しなかった場合生成
            }
            //接続処理
            URL u = new URL(url);
            System.out.println("DownloadURL:" + u.toString());
            System.out.println("Connection...");
            connection = u.openConnection();
            flag = true;
            
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }
        //ここより下は、接続ができた場合
        if (flag) {
            //try-with-resource文によるリソース解放の定義
            try (DataInputStream dataIS = new DataInputStream(connection.getInputStream());
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path), 8192)) {
                int ret;//読み込みできたサイズ
                while (true) {
                    ret = dataIS.read(DownloadFiles.buffer);//バイト配列に読み込み
                    if (ret <= 0) break;//もし1バイトも読み込んでなかったらループを抜ける
                    bufferedOutputStream.write(DownloadFiles.buffer, 0, ret);//読み込んだバイトをバッファに書き込み
                }
                //ダウンロード終了
                System.out.println("Finish!!");
                return path.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
        
    }
    
    /**
     * ファイルを指定されたURLからダウンロードしてきます。
     *
     * @param url  ファイルのURL
     * @param path 保存するパスへの{@link File}オブジェクト
     * @return ダウンロードしたファイルへのパス文字列
     */
    public static String download(String url, String path) {
        return DownloadFiles.download(url, new File(path));
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
