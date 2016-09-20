package antimattaermod.core.Item;

import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.item.Item;

/**
 * Created by 西村　航 on 2016/09/20.
 */
public class JacketedCable extends Item{

    public JacketedCable(){
        super();
        setCreativeTab(AntiMatterModRegistry.tabMaterials);
        setTextureName("antimattermodcore:jacketed_cable");
        setUnlocalizedName("jacketed_cable");
    }
}
