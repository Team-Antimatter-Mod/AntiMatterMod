package antimattaermod.core.crafting;

import antimattaermod.core.AntiMatterModRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by kojin15 on 2016/10/15.
 */
public class OreDictionaryRegister {

    public static void OreDictionaryRegisterPreInit(FMLPreInitializationEvent event){

        //鉱石辞書登録
        OreDictionary.registerOre("plateIron",new ItemStack(AntiMatterModRegistry.industrialplate_01,1,0));
        OreDictionary.registerOre("craftingToolHardHammer",new ItemStack(AntiMatterModRegistry.hammer_01,1,OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("craftingToolWrench",new ItemStack(AntiMatterModRegistry.wrench_01,1,OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("craftingToolWireCutter",new ItemStack(AntiMatterModRegistry.wirecutter_01,1,OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("craftingToolFile",new ItemStack(AntiMatterModRegistry.file_01,1,OreDictionary.WILDCARD_VALUE));
    }
}
