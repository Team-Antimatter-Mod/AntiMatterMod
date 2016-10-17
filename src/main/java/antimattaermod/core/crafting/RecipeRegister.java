package antimattaermod.core.crafting;

import antimattaermod.core.AntiMatterModRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by kojin15 on 2016/10/14.
 * Changed by Raiti-chan on 2016/10/16.
 * レシピ登録関数クラス
 * @author kojin15 Raiti-chan
 */
public class RecipeRegister {

    /**
     * レシピを登録
     * @param event FMLイベント
     */
    public static void RecipeRegisterInit(FMLInitializationEvent event){

        //レシピの登録 =================================================================================================
        /*耐久値有のRecipeを登録するときはメタ値のところにOreDictionary.WILDCARD_VALUE
	     * レシピで鉱石辞書の使用の際は、
	     * 登録名を入れるところに
	     * OreDictionaryRegister.plateIron や
	     * OreDictionaryRegister.craftingTookWrench;
	     * で、RecipeRegisterクラスのメソッド shapedOreRecipe や shapelessOreRecipe
	     * を通すこと
    	 * IDEAなので候補が出てきます。
    	 * その方が効率いいでしょ
    	 */

        //プレート
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.industrialplate_01,1,0),"A","B","B",'A',OreDictionaryRegister.craftingToolHardHammer,'B',new ItemStack(Items.iron_ingot,1,0)));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.plate_01,1,1),"A","B","B",'A',OreDictionaryRegister.craftingToolHardHammer,'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,1)));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.plate_01,1,2),"A","B","B",'A',OreDictionaryRegister.craftingToolHardHammer,'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,2)));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.plate_01,1,3),"A","B","B",'A',OreDictionaryRegister.craftingToolHardHammer,'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,3)));

        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.crystalplate_01,1,0),"AB ","CC ","CC ",
                'A',OreDictionaryRegister.craftingToolFile,'B',OreDictionaryRegister.craftingToolHardHammer,'C',new ItemStack(AntiMatterModRegistry.crystal_01,1,6)));

        //ロッド
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.rod_01,1,0),"   ","A  "," B ", 'A',OreDictionaryRegister.craftingToolFile,'B',new ItemStack(AntiMatterModRegistry.ingot_01,1,3)));

        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.motormagnet_01,1,0)," BA"," A ","AC ",
                'A',new ItemStack(AntiMatterModRegistry.magnetizedingot_01,1,0),'B',OreDictionaryRegister.craftingToolHardHammer,'C',OreDictionaryRegister.craftingToolWrench));

        //ネジ
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.screw_01,2,0),"AB",
                'A',new ItemStack(AntiMatterModRegistry.rod_01,0,0),'B',OreDictionaryRegister.craftingToolFile));

        //その他
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.motorparts_01,1,0),"AAA","AB ","AAA",'A', new ItemStack(AntiMatterModRegistry.plate_01,1,1),'B',OreDictionaryRegister.craftingToolHardHammer));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.motorparts_01,1,1)," AA"," BA"," AA",'A',OreDictionaryRegister.plateIron,'B',OreDictionaryRegister.craftingToolHardHammer));
        //筐体
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.furnaceGenerator),"ABA","CDC","EFE",
                'A',new ItemStack(AntiMatterModRegistry.motor_01,1,0), 'B',new ItemStack(AntiMatterModRegistry.turbine_01,1,0),
                'C',new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0), 'D',new ItemStack(AntiMatterModRegistry.tier1_machinecasing,1,0),
                'E',new ItemStack(Blocks.furnace,1,0),'F',OreDictionaryRegister.craftingToolWrench));

        //ケーブル
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.cable,2,0),"ABA","BCB","ABA",
                'A',new ItemStack(AntiMatterModRegistry.wire,1,0),
                'B',new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0),
                'C',OreDictionaryRegister.craftingToolWrench));

        //ナゲット→インゴット  \\なんでクラフトでできるんだ！！！ by Raiti//
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
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.wire,1,0)," S ","SCS"," S ",
                'S',new ItemStack(AntiMatterModRegistry.plate_01,1,2),'C',OreDictionaryRegister.craftingToolWrench));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.gear_01,1,0),"ABA","BCB","ABA",
                'A',new ItemStack(AntiMatterModRegistry.rod_01,1,0),'B',new ItemStack(AntiMatterModRegistry.plate_01,1,3),
                'C',OreDictionaryRegister.craftingToolWrench));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.turbineblade_01,1,0)," H ","SSS","   ",
                'S',new ItemStack(AntiMatterModRegistry.gear_01,1,0),'H',OreDictionaryRegister.craftingToolHardHammer));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.shaft_01,1,0),"  S"," S ","S  ",
                'S',new ItemStack(AntiMatterModRegistry.rod_01,1,0));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.turbine_01,1,0),"  C","ABA","D  ",
		        'A',new ItemStack(AntiMatterModRegistry.turbineblade_01,1,0),'B',new ItemStack(AntiMatterModRegistry.shaft_01,1,0),
                'C',OreDictionaryRegister.craftingToolHardHammer,'D',OreDictionaryRegister.craftingToolWrench));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.tier1_machinecasing,1,0),"AAA","ABA","ACA",
                'A',new ItemStack(AntiMatterModRegistry.plate_01,1,1),'B',new ItemStack(AntiMatterModRegistry.gear_01,1,0),
                'C',OreDictionaryRegister.craftingToolWrench));
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0),"ABA","BCB","ABA",
                'A',new ItemStack(AntiMatterModRegistry.crystalplate_01,1,0),'B',new ItemStack(AntiMatterModRegistry.powder_01,1,0),
                'C',new ItemStack(Blocks.glass_pane,1,0));
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.motor_01,1,0),"ABC","DEF","ABC",
                'A',OreDictionaryRegister.plateIron,'B',new ItemStack(AntiMatterModRegistry.wire,1,0),
                'C',new ItemStack(AntiMatterModRegistry.conductivematerial_01,1,0),'D',new ItemStack(AntiMatterModRegistry.motormagnet_01,1,0),
                'E',new ItemStack(AntiMatterModRegistry.motorparts_01,1,0),'F',new ItemStack(AntiMatterModRegistry.motorparts_01,1,1)));

        //ハンマー
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.hammer_01,1,0)," AA","BBC"," AA",
                'A', OreDictionaryRegister.ingotIron,'B',Items.stick,'C',Blocks.iron_block));

        //レンチ
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.wrench_01,1,0),"ABA","ACA"," A ",
                'A',OreDictionaryRegister.plateIron,'B',OreDictionaryRegister.craftingToolHardHammer,'C',new ItemStack(AntiMatterModRegistry.plate_01,1,1)));

        //ヤスリ
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.file_01,1,0),"  A"," AB","C  ",
                'A',OreDictionaryRegister.plateIron,'B',OreDictionaryRegister.craftingToolHardHammer,'C',OreDictionaryRegister.stickWood));


        //ワイヤーカッター
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(AntiMatterModRegistry.wirecutter_01,1,0),"ADA","EBF","C C",
                'A',OreDictionaryRegister.plateIron,'B',new ItemStack(AntiMatterModRegistry.screw_01,0,0),'C',OreDictionaryRegister.stickWood,
                'D',OreDictionaryRegister.craftingScrewdriver,'E',OreDictionaryRegister.craftingToolHardHammer,'F',OreDictionaryRegister.craftingToolFile));


        //バケツ
        GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(Items.bucket,1,0),"I I","IHI"," I ",
                'H',OreDictionaryRegister.craftingToolHardHammer,'I',OreDictionaryRegister.plateIron));
	    //大釜
	    GameRegistry.addRecipe(shapedOreRecipe(new ItemStack(Items.cauldron,1,0),"I I","IHI","III",'H',OreDictionaryRegister.craftingToolHardHammer,'I',OreDictionaryRegister.plateIron));

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
	
	/**
	 * {@link RecipeRegister#RecipeRegisterInit(FMLInitializationEvent)}が実行される前に実行されるレシピ消去
	 * @param event FMLイベント
	 */
    public static void beforeRemoveRecipeinit(FMLInitializationEvent event){
	    //Recipe削除 ===================================================================================================
	    RecipeRemover.removeRecipe(Items.stick);//木の棒
	    RecipeRemover.removeShapedRecipe(new ItemStack(Items.bucket), "I I", " I ", 'I', Items.iron_ingot);//バケツ
	    RecipeRemover.removeShapedRecipe(new ItemStack(Items.cauldron, 1),"# #", "# #", "###", '#', Items.iron_ingot);//大釜
    }
	
	/**
	 * {@link RecipeRegister#RecipeRegisterInit(FMLInitializationEvent)}が実行された後に実行されるレシピ消去
	 * @param event FMLイベント
	 */
    public static void afterRemoveRecipeinit(FMLInitializationEvent event){
    }


    /**
     * {@link OreDictionaryRegister}を使用した場合その参照をStringに変換した
     * {@link ShapedOreRecipe}を生成します
     * @param stack 完成品 {@link ItemStack}
     * @param objects レシピ配列
     * @return 生成された {@link ShapedOreRecipe}インスタンス
     */
    private static ShapedOreRecipe shapedOreRecipe(ItemStack stack, Object... objects){
        Object[] newObjects = new Object[objects.length];

        for(int i = 0; i < newObjects.length; i++){
            if(objects[i] instanceof OreDictionaryRegister){
                newObjects[i] = objects[i].toString();
            }else {
                newObjects[i] = objects[i];
            }
        }

        return new ShapedOreRecipe(stack,newObjects);
    }

    /**
     * {@link OreDictionaryRegister}を使用した場合その参照をStringに変換した
     * {@link ShapelessOreRecipe}を生成します
     * @param stack 完成品 {@link ItemStack}
     * @param objects レシピ配列
     * @return 生成された {@link ShapelessOreRecipe}インスタンス
     */
    public static ShapelessOreRecipe shapelessOreRecipe(ItemStack stack, Object... objects){
        Object[] newObjects = new Object[objects.length];

        for(int i = 0; i < newObjects.length; i++){
            if(objects[i] instanceof OreDictionaryRegister){
                newObjects[i] = objects[i].toString();
            }else {
                newObjects[i] = objects[i];
            }
        }

        return new ShapelessOreRecipe(stack,newObjects);
    }
}
