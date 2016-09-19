package antimattaermod.core.Energy;

/**
 * Created by C6H2Cl2 on 2016/09/19.
 */
public enum APVoltage {
    LV(128),MV(512),HV(2048),GV(10240),TV(40960),UV(Integer.MAX_VALUE);
    private int maxEnergy;

    APVoltage(int maxEnergy){
        this.maxEnergy = maxEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }
}
