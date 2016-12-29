package antimattermod.core.Energy.Item.Wrench

/**
 * Created by kojin15
 */
enum class WrenchMode {
    /**
     * Block : 向き変更や撤去など
     * Transceiver : エネルギーの送受信機の設定
     */
    Block,
    Transceiver,


    ぬるぽ;

    companion object {
        fun getWrenchModeFromOrdinal(ordinal : Int):WrenchMode{
            return when(ordinal){
                0 -> Block
                1 -> Transceiver

                else -> ぬるぽ
            }
        }
    }

}