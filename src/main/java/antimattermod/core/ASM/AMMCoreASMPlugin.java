package antimattermod.core.ASM;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

/**
 * @author C6H2Cl2
 */
//ASMの対象から除外する名前空間
@IFMLLoadingPlugin.TransformerExclusions({"antimattermod","java","org","com","net.minecraftforge","tv","cpw","io","ic2","codechicken"})
public class AMMCoreASMPlugin implements IFMLLoadingPlugin{
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"antimattermod.core.ASM.AMMCoreTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return "antimattermod.core.ASM.AMMCoreDummyContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
