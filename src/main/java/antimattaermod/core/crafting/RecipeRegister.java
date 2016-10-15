package antimattaermod.core.crafting;

import antimattaermod.core.AntiMatterModRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by kojin15 on 2016/10/14.
 */
public class RecipeRegister {

    public static void RecipeRegisterInit(FMLInitializationEvent event){

        //レシピの登録 =================================================================================================
        //耐久値有のRecipeを登録するときはメタ値のところにOreDictionary.WILDCARD_VALUE

        //プレート
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.industrialplate_01,1,0),"A","B","B",'A',"craftingToolHardHammer",'B',new ItemStack(Items.iron_ingot,1,0)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.plate_01,1,1),"A","B","B",'A',"craftingToolHardHammer",'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.plate_01,1,2),"A","B","B",'A',"craftingToolHardHammer",'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,2)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.plate_01,1,3),"A","B","B",'A',"craftingToolHardHammer",'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,3)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.crystalplate_01,1,0),"AB ","CC ","CC ",
                'A',"craftingToolFile",'B',"craftingToolHardHammer",'C',new ItemStack(AntiMatterModRegistry.crystal_01,1,6)));

        //ロッド
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.rod_01,1,0),"   ","A  "," B ",'A',"craftingToolFile",'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,3)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.motormagnet_01,1,0)," BA"," A ","AC ",
                'A',new ItemStack(AntiMatterModRegistry.magnetizedingot_01,1,0),'B',"craftingToolHardHammer",'C',"craftingToolFile"));

        //その他
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.motorparts_01,1,0),"AAA","AB ","AAA",'A',new ItemStack(AntiMatterModRegistry.plate_01,1,1),'B',"craftingToolHardHammer"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.motorparts_01,1,1)," AA"," BA"," AA",'A',"plateIron",'B',"craftingToolHardHammer"));

        //筐体
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.furnaceGenerator),"ABA","CDC","EFE",
                'A',new ItemStack(AntiMatterModRegistry.motor_01,1,0), 'B',new ItemStack(AntiMatterModRegistry.turbine_01,1,0),
                'C',new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0), 'D',new ItemStack(AntiMatterModRegistry.tier1_machinecasing,1,0),
                'E',new ItemStack(Blocks.furnace,1,0),'F',"craftingToolWrench"));

        //ケーブル
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.cable,2,0),"ABA","BCB","ABA",
                'A',new ItemStack(AntiMatterModRegistry.wire,1,0),
                'B',new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0),
                'C',"craftingToolWrench"));

        //ナゲット→インゴット
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,1),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,0));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,2),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,1));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,3),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,2));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,4),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,3));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,5),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,4));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,6),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,5));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,7),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,6));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,8),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,7));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,9),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,8));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,10),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,9));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,11),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,10));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,12),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,11));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,13),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,12));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,14),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,13));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,15),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,14));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.ingot_01,1,16),"AAA","AAA","AAA",
                'A',new ItemStack(AntiMatterModRegistry.nugget_01,1,15));

        //その他
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.wire,1,0)," S ","SCS"," S ",
                'S',new ItemStack(AntiMatterModRegistry.plate_01,1,2),'C',"craftingToolWireCutter"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.gear_01,1,0),"ABA","BCB","ABA",
                'A',new ItemStack(AntiMatterModRegistry.rod_01,1,0),'B',new ItemStack(AntiMatterModRegistry.plate_01,1,3),
                'C',"craftingToolWrench"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.turbineblade_01,1,0)," H ","SSS","   ",
                'S',new ItemStack(AntiMatterModRegistry.gear_01,1,0),'H',"craftingToolHardHammer"));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.shaft_01,1,0),"  S"," S ","S  ",
                'S',new ItemStack(AntiMatterModRegistry.rod_01,1,0));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.turbine_01,1,0),"  C","ABA","D  ",
		        'A',new ItemStack(AntiMatterModRegistry.turbineblade_01,1,0),'B',new ItemStack(AntiMatterModRegistry.shaft_01,1,0),
                'C',"craftingToolHardHammer",'D',"craftingToolWrench"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.tier1_machinecasing,1,0),"AAA","ABA","ACA",
                'A',new ItemStack(AntiMatterModRegistry.plate_01,1,1),'B',new ItemStack(AntiMatterModRegistry.gear_01,1,0),
                'C',"craftingToolWrench"));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0),"ABA","BCB","ABA",
                'A',new ItemStack(AntiMatterModRegistry.crystalplate_01,1,0),'B',new ItemStack(AntiMatterModRegistry.powder_01,1,0),
                'C',new ItemStack(Blocks.glass_pane,1,0));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.motor_01,1,0),"ABC","DEF","ABC",
                'A',"plateIron",'B',new ItemStack(AntiMatterModRegistry.wire,1,0),
                'C',new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0),'D',new ItemStack(AntiMatterModRegistry.motormagnet_01,1,0),
                'E',new ItemStack(AntiMatterModRegistry.motorparts_01,1,0),'F',new ItemStack(AntiMatterModRegistry.motorparts_01,1,1)));

        //ハンマー
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.hammer_01,1,0)," AA","BBC"," AA",'A',"ingotIron",'B',Items.stick,'C',Blocks.iron_block));

        //レンチ
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AntiMatterModRegistry.wrench_01,1,0),"ABA","ACA"," A ",'A',"plateIron",'B',"craftingToolHardHammer",'C',new ItemStack(AntiMatterModRegistry.plate_01,1,1)));
        
        
        //バケツ
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.bucket),"I I","IHI"," I ",'H',"craftingToolHardHammer",'I',"plateIron"));

        //精錬
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,1),new ItemStack(AntiMatterModRegistry.nugget_01,3,0),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,2),new ItemStack(AntiMatterModRegistry.nugget_01,3,1),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,3),new ItemStack(AntiMatterModRegistry.nugget_01,3,2),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,4),new ItemStack(AntiMatterModRegistry.nugget_01,3,4),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,5),new ItemStack(AntiMatterModRegistry.nugget_01,3,5),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,6),new ItemStack(AntiMatterModRegistry.nugget_01,3,6),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,7),new ItemStack(AntiMatterModRegistry.nugget_01,3,7),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,8),new ItemStack(AntiMatterModRegistry.nugget_01,3,8),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,9),new ItemStack(AntiMatterModRegistry.nugget_01,3,9),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,10),new ItemStack(AntiMatterModRegistry.nugget_01,3,10),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,11),new ItemStack(AntiMatterModRegistry.nugget_01,3,11),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,12),new ItemStack(AntiMatterModRegistry.nugget_01,3,12),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,13),new ItemStack(AntiMatterModRegistry.nugget_01,3,13),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,14),new ItemStack(AntiMatterModRegistry.nugget_01,3,14),0.7f);
        GameRegistry.addSmelting(new ItemStack(AntiMatterModRegistry.oreBlock_1,1,15),new ItemStack(AntiMatterModRegistry.nugget_01,3,15),0.7f);

    }
}
