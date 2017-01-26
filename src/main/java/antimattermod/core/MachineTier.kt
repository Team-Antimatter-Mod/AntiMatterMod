package antimattermod.core

import antimattermod.core.Energy.APVoltage
import antimattermod.core.Energy.APVoltage.*
import antimattermod.core.Energy.EnergyGroup
import antimattermod.core.Energy.EnergyGroup.*

/**
 * @author C6H2Cl2
 */
enum class MachineTier(val voltage: APVoltage, val group: EnergyGroup, val efficiency: Double) {
    NoTier(ZeroVoltage, NonGroup, 0.0),
    Tier1(HV, Low, 0.90), Tier2(MV, Low, 0.9028), Tier3(HV, Low, 0.9104),
    Tier4(HV, Middle, 0.9216), Tier5(EV, Middle, 0.9352), Tier6(EV, Middle, 0.95),
    Tier7(IV, High, 0.9648), Tier8(IV, High, 0.9784), Tier9(LuV, High, 0.9896),
    Tier10(LuV, SuperHigh, 0.9972), Tier11(ZPMV, SuperHigh, 1.0), Tier12(ZPMV, SuperHigh, 1.0),
    Tier13(UV, UltraHigh, 1.0), Tier14(UV, UltraHigh, 1.0), Tier15(MaxV, Ultimate, 1.0)
}