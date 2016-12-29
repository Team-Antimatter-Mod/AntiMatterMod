package antimattermod.core.fileIO;


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
public enum DeveloperBossTexture {
    Raitichan,
    C6H2Cl2,
    kojin15,
    sora_suke,
    
    ;
    
    /**
     * このプレイヤーのスキンへのパス
     */
    private String TexturePath;
    
    /**
     * スキンへのパスをセットします。
     *
     * @param texturePath スキンへのパス
     */
    private void setTexturePath(String texturePath) {
        this.TexturePath = texturePath;
    }
    
    /**
     * このプレイヤーのスキンへのパスを取得します。
     *
     * @return スキンへのパス
     */
    public String getTexturePath() {
        return this.TexturePath;
    }
    
    /**
     * 開発者のスキンをダウンロードします。
     */
    public static void downloadTexture() {
        for (DeveloperBossTexture developer : DeveloperBossTexture.values()) {
            String string = developer.toString();
            String filePath = DownloadFiles.download(DownloadFiles.SKIN_URL_LOCATION + string + ".png",
                    AMMFiles.DEVELOPER_BOSS_TEXTURE_DIRECTORY + string + ".png");
            developer.setTexturePath(filePath);
        }
    }
}
