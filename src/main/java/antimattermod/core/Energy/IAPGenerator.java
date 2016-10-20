package antimattermod.core.Energy;

/**
 * @author C6H2Cl2
 */
public interface IAPGenerator extends IAPMachine{
    float getMaxFuelValue();
    float getFuelValue();
    int getCurrentGenerate();
    boolean isFuelMax();
}
