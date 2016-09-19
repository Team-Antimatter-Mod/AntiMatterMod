package antimattaermod.core.Energy;

/**
 * @author C6H2Cl2
 */

public interface IAPMachine {
    int getMaxStoreEnergy();
    boolean canSendEnergy();
    boolean canReciveEnergy();
    APVoltage getSendVoltage();
    APVoltage getReciveVoltage();

}
