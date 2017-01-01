package antimattermod.core.fileIO;


/**
 * AMMの各ファイルパスの保管場所？<br>
 * もしかすると実装を追加するかも
 * <br>Created by Raiti-chan on 2016/12/28.
 *
 * @author Raiti-chan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class AMMFiles {

    /**
     * このMODのConfig用のパス
     */
    public static final String CONFIG_DIRECTORY = "config/AntiMatterMod/";

    /**
     * 開発者BOSSテクスチャのダウンロード場所
     */
    public static final String DEVELOPER_BOSS_TEXTURE_DIRECTORY = CONFIG_DIRECTORY + "developerBossTexture/";

    /**
     * MOJANG APIで取得したプレイヤーのJSONファイルのダウンロード場所
     */
    public static final String MOJANG_API_UUID_JSON_DIRECTORY = CONFIG_DIRECTORY + "developerBossUUID/";


}
