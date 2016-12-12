package antimattermod.core.Energy

/**
 * @author C6H2Cl2
 */
enum class APType {
    Provider,Receiver,Transfer,ぬるぽ;
    fun getAPTypeFromOrdinal(ordinal : Int):APType{
        return when(ordinal){
            0 -> Provider
            1 -> Receiver
            2 -> Transfer
            else -> ぬるぽ
        }
    }
}