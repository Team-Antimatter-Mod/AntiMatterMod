package antimattermod.core.fileIO

import java.io.File

/**
 * 開発者のスキン管理Enum<br>
 * Enumインスタンス名はプレイヤーID<br>
 * このEnumインスタンスの中にスキンへのパスが格納されています。
 * <br>Created by Raiti-chan on 2016/12/29.
 *
 * @author Raiti-chan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
enum class DeveloperBossTexture {
    Raitichan,
    C6H2Cl2,
    kojin15,
    sora_suke,
    Gaku_Minecraft,
    ;

    private var _initialized: Boolean = false
    private var _texturePath: File? = null
    /**
     * このプレイヤーのスキンへのパス
     */
    val texturePath: File?
        get() {
            if (!_initialized) {
                _initialized = true
                val string = toString()
                val local = File(AMMFiles.DEVELOPER_BOSS_TEXTURE_DIRECTORY + string + ".png")
                if (local.exists())
                    _texturePath = local
                else
                    DownloadFiles.download(
                            DownloadFiles.SKIN_URL_LOCATION + string + ".png",
                            local,
                            DownloadFiles.DownloadCallback { path -> _texturePath = path }
                    )
            }
            return _texturePath
        }

    /**
     * 開発者のスキンをダウンロードします。
     * @Deprecated 使用時に自動的にダウンロードされます
     */
    companion object {
        @Deprecated("使用時に自動的にダウンロードされます")
        fun downloadTexture() {
            /*
                    for (DeveloperBossTexture developer : DeveloperBossTexture.values()) {
            String string = developer.toString();
            */
            /*
             * とりあえずMOJANG APIから、GakuのUUIDを落とすようにしてみました。安全確保のためコメントアウトしてまする。
             * String filePath = DownloadFiles.download(DownloadFiles.MOJANG_API_UUID_URL_LOCATION + "25165cda7bff4963bfcaa152bc8f11ef" + ".json", AMMFiles.MOJANG_API_UUID_JSON_DIRECTORY + "25165cda7bff4963bfcaa152bc8f11ef" + ".json");
             */
            /*
            String filePath = DownloadFiles.download(DownloadFiles.SKIN_URL_LOCATION + string + ".png",
                    AMMFiles.DEVELOPER_BOSS_TEXTURE_DIRECTORY + string + ".png");
            developer.setTexturePath(filePath);
             */

            //AbstractClientPlayer.getDownloadImageSkin(new ResourceLocation("skins/texture/raitichan.png"), "Raitichan");
        }
    }
}
