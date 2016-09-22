package antimattaermod.core.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 西村　航 on 2016/09/22.
 */
public class RecipeRemover {

    public static void removeRecipe(){
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

        Iterator<IRecipe> remover = recipes.iterator();
        while (remover.hasNext()){
            ItemStack itemStack = remover.next().getRecipeOutput();
            if(itemStack != null && itemStack.getItem() == Items.stick){
                remover.remove();
            }
        }
    }
}
