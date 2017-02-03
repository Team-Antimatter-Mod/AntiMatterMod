package antimattermod.core.Energy

import antimattermod.core.Energy.APVoltage.*
import antimattermod.core.Energy.EnergyGroup.*
import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
enum class MachineTier(val voltage: APVoltage, val group: EnergyGroup, val efficiency: Double, val maxConnect: Int) {
    NoTier(ZeroVoltage, NonGroup, 0.0, 0),
    Tier1(HV, Low, 0.90, 10), Tier2(MV, Low, 0.9028, 15), Tier3(HV, Low, 0.9104, 20),
    Tier4(HV, Middle, 0.9216, 50), Tier5(EV, Middle, 0.9352, 60), Tier6(EV, Middle, 0.95, 75),
    Tier7(IV, High, 0.9648, 100), Tier8(IV, High, 0.9784, 120), Tier9(LuV, High, 0.9896, 150),
    Tier10(LuV, SuperHigh, 0.9972, 200), Tier11(ZPMV, SuperHigh, 1.0, 225), Tier12(ZPMV, SuperHigh, 1.0, 250),
    Tier13(UV, UltraHigh, 1.0, 300), Tier14(UV, UltraHigh, 1.0, 500), Tier15(MaxV, Ultimate, 1.0, Int.MAX_VALUE);

    fun writeToNBT(tagCompound: NBTTagCompound): NBTTagCompound {
        tagCompound.setInteger(TIER, ordinal)
        return tagCompound
    }

    fun readFromNBT(tagCompound: NBTTagCompound): MachineTier {
        return getFromTier(tagCompound.getInteger(TIER))
    }

    companion object {
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