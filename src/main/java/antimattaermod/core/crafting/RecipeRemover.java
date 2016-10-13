package antimattaermod.core.crafting;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RecipeRemover {

    /**
     * 指定したアイテムのレシピを消去します。<br>
     * 複数レシピがあった場合すべて消去します。<br>
     * メタデータを持ったアイテムのレシピを利用する場合{@link RecipeRemover#removeRecipe(Item, int)}を利用してください。
     * @param item 消去するアイテム
     */
    @SuppressWarnings("unchecked")
    public static void removeRecipe(Item item){
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();//全レシピを取得
        Iterator<IRecipe> remover = recipes.iterator();//レシピリストのイテレーター取得

        while (remover.hasNext()){
            ItemStack outItem = remover.next().getRecipeOutput();
            if(outItem != null && outItem.getItem() == item){
                remover.remove();
            }
        }

    }

    /**
     * 指定したアイテム、メタデータのレシピを消去します。<br>
     * 複数レシピがあった場合すべて消去します。
     * @param item 消去するレシピのアイテム
     * @param meta 消去するレシピのアイテムのメタデータ
     */
    @SuppressWarnings("unchecked")
    public  static  void removeRecipe(Item item,int meta){
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();//全レシピを取得
	    Iterator<IRecipe> remover = recipes.iterator();//レシピリストのイテレーター取得
	    
	    while (remover.hasNext()){
            ItemStack outItem = remover.next().getRecipeOutput();
            if(outItem != null && outItem.getItem() == item && outItem.getItemDamage() == meta){
                remover.remove();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void removeShapedRecipe(ItemStack itemStack,Object ... par){
	
	    ItemStack[] itemStacks;
	    {//ItemStackの配列の生成
		    String s = new String();
		    int i = 0;
		    int j = 0;
		    int k = 0;
		
		    if (par[i] instanceof String[]) {
			    String[] astring = (String[]) ((String[]) par[i++]);
			    for (int l = 0; l < astring.length; ++l) {
				    String s1 = astring[l];
				    k++;
				    j = s1.length();
				    s = s + s1;
			    }
		    } else {
			    while (par[i] instanceof String) {
				    String s2 = (String) par[i++];
				    k++;
				    j = s2.length();
				    s = s + s2;
			    }
		    }
		
		    HashMap<Character, ItemStack> hashMap = new HashMap<>();
		    for (; i < par.length; i += 2) {
			    if (!(par[i] instanceof Character)) throw new IllegalArgumentException("パラメータがcharではありません");
			    Character character = (Character) par[i];
			    ItemStack itemStack1;
			    Object parB = par[i + 1];
			    if (parB instanceof Item) {
				    itemStack1 = new ItemStack((Item) parB);
			    } else if (parB instanceof Block) {
				    itemStack1 = new ItemStack((Block) parB);
			    } else if (parB instanceof ItemStack) {
				    itemStack1 = (ItemStack) parB;
			    } else throw new IllegalArgumentException("パラメーターBがItem、Block、ItemStackのどれかではありません");
			    hashMap.put(character, itemStack1);
		    }
		
		    itemStacks = new ItemStack[j * k];
		
		    for (i = 0; i < j * k; i++) {
			    char c = s.charAt(i);
			    if (hashMap.containsKey(Character.valueOf(c))) {
				    itemStacks[i] = (hashMap.get(Character.valueOf(c))).copy();
			    } else {
				    itemStacks[i] = null;
			    }
		    }
	    }
	
	    List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();//全レシピを取得
	    Iterator<IRecipe> remover = recipes.iterator();//レシピリストのイテレーター取得
	
	    a:while (remover.hasNext()){
		    IRecipe iRecipe = remover.next();
		    if (iRecipe instanceof ShapedRecipes) {
			    ShapedRecipes recipe = (ShapedRecipes) iRecipe;
			    ItemStack outItem = recipe.getRecipeOutput();
			    if (outItem != null && outItem.isItemEqual(itemStack)) {
				    if (itemStacks.length != recipe.recipeItems.length) continue a;
				
				    for (int i = 0; i < itemStacks.length; i++) {
					    if (!itemStacks[i].isItemEqual(recipe.recipeItems[i])) continue a;
				    }
				    remover.remove();
			    }
		    }else if (iRecipe instanceof ShapedOreRecipe){
			    ShapedOreRecipe recipe = (ShapedOreRecipe) iRecipe;
			    ItemStack outItem = recipe.getRecipeOutput();
			    if (outItem != null && outItem.isItemEqual(itemStack)) {
				    if (itemStacks.length != recipe.getInput().length) continue a;
				    for (int i = 0; i < itemStacks.length; i++){
					    if (recipe.getInput()[i] instanceof ItemStack){
						    if (!itemStacks[i].isItemEqual((ItemStack) recipe.getInput()[i])) continue a;
						}else if (recipe.getInput()[i] instanceof String){
						    int[] ids = OreDictionary.getOreIDs(itemStacks[i]);
						    if (ids.length < 1) continue a;
						    for (int id:ids) {
							    if (!OreDictionary.getOreName(id).equals(recipe.getInput()[i])) continue a;
						    }
					    }
				    }
				    remover.remove();
			    }
		    }
	    }
	    
    }

}
