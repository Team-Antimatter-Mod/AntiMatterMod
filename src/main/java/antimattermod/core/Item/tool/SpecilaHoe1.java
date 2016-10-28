package antimattermod.core.Item.tool;

import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.item.ItemHoe;

/**
 * Created by worldofthetakumi on 2016/10/28.
 */
public class SpecilaHoe1 extends ItemHoe {

    public SpecilaHoe1(){
        super(ToolMaterial.EMERALD);
        setTextureName("");
        setNoRepair();
        setUnlocalizedName("A man's hoe");
        setMaxDamage(1000);
        setMaxStackSize(1);
    }
}
