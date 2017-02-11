package antimattermod.core.Energy

import antimattermod.core.Energy.APVoltage.*
import antimattermod.core.Energy.EnergyGroup.*
import net.minecraft.nbt.NBTTagCompound

/**
 * # 日本語
 * このmodで追加される機械の段階を管理するEnumです。
 * @property voltage 段階における電圧[APVoltage]です。原則、各機械の電圧はその機械の段階によって決定されます。
 * @property group 段階が所属するランク[EnergyGroup]です。各ランクによって特性があります。
 * @property efficiency エネルギーの伝送効率です。1ブロックごとのエネルギー伝送量の割合を0~1で指定してください。
 * @property maxConnect その段階に所属するコントローラー[IAPController]の最大接続可能数です。原則、コントローラーの最大接続可能数はその機械の段階によって決定されます。
 *
 * # English
 * @property voltage voltage[APVoltage] of machines of the tier.
 * @property group rank[EnergyGroup] in the tier.
 * @property efficiency The rate of energy transmission efficiency. min:0, max:1
 * @property maxConnect The number of max connection of controller[IAPController] in ther iter.
 * # 共通 Common
 * @author C6H2Cl2
 */
enum class MachineTier(val voltage: APVoltage, val group: EnergyGroup, val efficiency: Double, val maxConnect: Int) {
    NoTier(ZeroVoltage, NonGroup, 0.0, 0),
    Tier1(HV, Low, 0.90, 10), Tier2(MV, Low, 0.9028, 15), Tier3(HV, Low, 0.9104, 20),
    Tier4(HV, Middle, 0.9216, 50), Tier5(EV, Middle, 0.9352, 60), Tier6(EV, Middle, 0.95, 75),
    Tier7(IV, High, 0.9648, 100), Tier8(IV, High, 0.9784, 120), Tier9(LuV, High, 0.9896, 150),
    Tier10(LuV, SuperHigh, 0.9972, 200), Tier11(ZPMV, SuperHigh, 1.0, 225), Tier12(ZPMV, SuperHigh, 1.0, 250),
    Tier13(UV, UltraHigh, 1.0, 300), Tier14(UV, UltraHigh, 1.0, 500), Tier15(MaxV, Ultimate, 1.0, Int.MAX_VALUE);

    /**
     * # 日本語
     * EnumをNBTに書き込みます。主にTileEntityのwriteToNBTから呼び出すことを想定しています。
     * @param tagCompound 情報を書き込むための[NBTTagCompound]です。
     * @return 引数[tagCompound]に情報を書き込んだ[NBTTagCompound]を返します。
     *
     * # English
     * write the information of this to NBT. Please call this from [net.minecraft.tileentity.TileEntity.writeToNBT]
     * @param tagCompound [NBTTagCompound] to write information.
     * @return [NBTTagCompound] with information written.
     */
    fun writeToNBT(tagCompound: NBTTagCompound): NBTTagCompound {
        tagCompound.setInteger(TIER, ordinal)
        return tagCompound
    }

    /**
     * # 日本語
     * EnumをNBTから読み取ります。主にTileEntityのreadFromNBTから呼び出すことを想定しています。
     * @param tagCompound 情報が書き込まれた[NBTTagCompound]です。[writeToNBT]で書き込んだNBTを使ってください。
     * @return [tagCompound]から読み取ったEnumを返します。
     *
     * # English
     * @param tagCompound [NBTTagCompound] with information written by [writeToNBT]
     * @return Enum read from [tagCompound]
     */
    fun readFromNBT(tagCompound: NBTTagCompound): MachineTier {
        return getFromTier(tagCompound.getInteger(TIER))
    }

    companion object {
        /**
         * # 日本語
         * Tierの値から、Enumを返します。
         * @param tier 機械のTierです。
         * @return 引数[tier]に対応するEnumです。
         *
         * # English
         * @param tier the tier of machine
         * @return Enum of [tier]
         */
        fun getFromTier(tier: Int): MachineTier {
            return when (tier) {
                1 -> Tier1
                2 -> Tier2
                3 -> Tier3
                4 -> Tier4
                5 -> Tier5
                6 -> Tier6
                7 -> Tier7
                8 -> Tier8
                9 -> Tier9
                10 -> Tier10
                11 -> Tier11
                12 -> Tier12
                13 -> Tier13
                14 -> Tier14
                15 -> Tier15
                else -> NoTier
            }
        }
    }
}