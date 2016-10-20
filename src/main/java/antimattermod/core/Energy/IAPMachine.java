package antimattermod.core.Energy;

/**
 * @author C6H2Cl2
 */

public interface IAPMachine {
    int getMaxStoreEnergy();
    boolean canSendEnergy();
    boolean canReceiveEnergy();
    APVoltage getSendVoltage();
    APVoltage getReceiveVoltage();
    int getStoredEnergy();
}
