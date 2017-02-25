package antimattermod.core.crafting;

import antimattermod.core.AMMRegistry;
import antimattermod.core.AntiMatterModRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by kojin15 on 2016/10/14.
 * Changed by Raiti-chan on 2016/10/16.
 * レシピ登録関数クラス
 *
 * @author kojin15 Raiti-chan
 */
@SuppressWarnings("UnusedParameters")
public class RecipeRegister {
	
	/**
	 * レシピを登録
	 *
	 * @param event FMLイベント
	 */
	public static void RecipeRegisterInit(FMLInitializationEvent event) {
		
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

		
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.crystalblock_01, 1, 0), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.crystal_01, 1, 0));
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.crystalblock_01, 1, 1), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.crystal_01, 1, 1));
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.crystalblock_01, 1, 2), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.crystal_01, 1, 2));
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.crystalblock_01, 1, 3), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.crystal_01, 1, 3));
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.crystalblock_01, 1, 4), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.crystal_01, 1, 4));
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.crystalblock_01, 1, 5), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.crystal_01, 1, 5));
		
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.crystalblock_02, 1, 0), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.crystal_02, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.powderblock_01, 1, 0), "AAA", "AAA", "AAA",
				'A', new ItemStack(AntiMatterModRegistry.powder_01, 1, 0));
		

	}
	
	/**
	 * {@link RecipeRegister#RecipeRegisterInit(FMLInitializationEvent)}が実行される前に実行されるレシピ消去
	 *
	 * @param event FMLイベント
	 */
	public static void beforeRemoveRecipeinit(FMLInitializationEvent event) {
		//Recipe削除 ===================================================================================================
		RecipeRemover.removeRecipe(Items.stick);//木の棒
		RecipeRemover.removeShapedRecipe(new ItemStack(Items.bucket), "I I", " I ", 'I', Items.iron_ingot);//バケツ
		RecipeRemover.removeShapedRecipe(new ItemStack(Items.cauldron, 1), "# #", "# #", "###", '#', Items.iron_ingot);//大釜
	}
	
	/**
	 * {@link RecipeRegister#RecipeRegisterInit(FMLInitializationEvent)}が実行された後に実行されるレシピ消去
	 *
	 * @param event FMLイベント
	 */
	public static void afterRemoveRecipeinit(FMLInitializationEvent event) {
	}
	
	
	/**
	 * {@link OreDictionaryRegister}を使用した場合その参照をStringに変換した
	 * {@link ShapedOreRecipe}を生成します
	 *
	 * @param stack   完成品 {@link ItemStack}
	 * @param objects レシピ配列
	 * @return 生成された {@link ShapedOreRecipe}インスタンス
	 */
	private static ShapedOreRecipe shapedOreRecipe(ItemStack stack, Object... objects) {
		Object[] newObjects = new Object[objects.length];
		
		for (int i = 0; i < newObjects.length; i++) {
			if (objects[i] instanceof OreDictionaryRegister) {
				newObjects[i] = objects[i].toString();
			} else {
				newObjects[i] = objects[i];
			}
		}
		
		return new ShapedOreRecipe(stack, newObjects);
	}
	
	/**
	 * {@link OreDictionaryRegister}を使用した場合その参照をStringに変換した
	 * {@link ShapelessOreRecipe}を生成します
	 *
	 * @param stack   完成品 {@link ItemStack}
	 * @param objects レシピ配列
	 * @return 生成された {@link ShapelessOreRecipe}インスタンス
	 */
	@SuppressWarnings("unused")
	public static ShapelessOreRecipe shapelessOreRecipe(ItemStack stack, Object... objects) {
		Object[] newObjects = new Object[objects.length];
		
		for (int i = 0; i < newObjects.length; i++) {
			if (objects[i] instanceof OreDictionaryRegister) {
				newObjects[i] = objects[i].toString();
			} else {
				newObjects[i] = objects[i];
			}
		}
		
		return new ShapelessOreRecipe(stack, newObjects);
	}
}
