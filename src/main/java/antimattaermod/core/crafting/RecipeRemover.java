package antimattaermod.core.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 西村　航 on 2016/09/22.
 */
public class RecipeRemover {

    /**
     * 指定したアイテムのレシピを消去します。<br>
     * 複数レシピがあった場合すべて消去します。<br>
     * メタデータを持ったアイテムのレシピを利用する場合{@link RecipeRemover#removeRecipe(Item, int)}を利用してください。
     * @param item 消去するアイテム
     */
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

}
