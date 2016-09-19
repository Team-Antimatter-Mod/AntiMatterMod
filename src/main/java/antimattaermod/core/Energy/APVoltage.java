package antimattaermod.core.Energy;

/**
 * @author C6H2Cl2
 */

public enum APVoltage {
    ULV(8),LV(32),MV(128),HV(512),EV(2048),IV(8192),LuV(32768),ZPMV(131072),UV(524288),MaxV(Integer.MAX_VALUE);
    private int maxEnergy;

    APVoltage(int maxEnergy){
        this.maxEnergy = maxEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }
}
