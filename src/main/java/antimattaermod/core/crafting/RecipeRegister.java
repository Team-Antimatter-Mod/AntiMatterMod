package antimattaermod.core.crafting;

import antimattaermod.core.AntiMatterModRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by nisimura-t on 2016/10/14.
 */
public class RecipeRegister {

    public static void RecipeRegisterInit(FMLInitializationEvent event){

        //レシピの登録 =================================================================================================
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.wire,1,0)," S ","S S"," S ",'S',new ItemStack(AntiMatterModRegistry.plate_01,1,1));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.gear_01,1,0),"ABA","B B","ABA",'A',new ItemStack(AntiMatterModRegistry.rod_01,1,0),'B',new ItemStack(AntiMatterModRegistry.plate_01,1,2));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.turbineblade_01,1,0),"SSS",'S',new ItemStack(AntiMatterModRegistry.gear_01,1,0));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.shaft_01,1,0),"  S"," S ","S  ",'S',new ItemStack(AntiMatterModRegistry.rod_01,1,0));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.turbine_01,1,0),"   ","ABA","   ",'A',new ItemStack(AntiMatterModRegistry.turbineblade_01,1,0),'B',new ItemStack(AntiMatterModRegistry.shaft_01,1,0));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.tier1_machinecasing,1,0),"AAA","ABA","AAA",'A',new ItemStack(AntiMatterModRegistry.plate_01,1,0),'B',new ItemStack(AntiMatterModRegistry.gear_01,1,0));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0),"ABA","BCB","ABA",'A',new ItemStack(AntiMatterModRegistry.crystalplate_01,1,0),'B',new ItemStack(AntiMatterModRegistry.powder_01,1,0),'C',new ItemStack(Blocks.glass_pane,1,0));


    }
}
