package antimattermod.core

import antimattermod.core.crafting.AlloySmelterRecipe
import net.minecraft.item.ItemStack
import java.util.*

/**
 * @author C6H2Cl2
 */
//RecipeRegistryKT.INSTANCE
val INSTANCE : RecipeRegistry = RecipeRegistry()
class RecipeRegistry {
    private val alloySmelterRecipes : HashMap<String, AlloySmelterRecipe> = HashMap()

    public fun addAlloySmelterRecipe(name : String,alloySmelterRecipe: AlloySmelterRecipe){
        alloySmelterRecipes.put(name,alloySmelterRecipe)
    }

    //ForgeのGameRegistry.addRecipe()形式でレシピ受付したいなぁ
    public fun addAlloySmelterRecipe(name: String,tier : Int,isShapeless:Boolean,vararg recipeShape : Any){
        val size = recipeShape.size
        var numRecipe = 0
        val recipeShapeString = arrayOfNulls<String?>(3)
        //numRecipeは、RecipeのStringの数と同じ
        recipeShape.forEachIndexed { i, obj ->
            if (obj !is String) {
                numRecipe = i
                return@forEachIndexed
            }
        }
        //Stringが一つもなかったら例外投げる。サイズがおかしくても例外投げる。
        if (numRecipe == 0 || numRecipe > 3 || size-numRecipe%2 == 1){
            throw IllegalArgumentException()
        }
        //String移す
        for (i in 0..2){
            if(i <= numRecipe-1){
                recipeShapeString[i] = recipeShape[i] as String
            }
        }
        //CharArrayに変換
        val shape : CharArray= CharArray(9)//kotlin.arrayOfNulls<Char?>(numRecipe+1)
        for (i in 0..recipeShapeString.size-1){
            (recipeShapeString[i] as String).toCharArray().forEachIndexed { j, c ->
                shape[i*3+j] = c
            }
        }
        //文字とItemStackの変換表
        val recipeStacks : HashMap<Char, ItemStack> = HashMap()
        //後半部分のチェック
        for (i in 0..(recipeShape.size-numRecipe)/2){
            //Char・ItemStackのペアでなければ例外
            if (recipeShape[i+numRecipe] !is Char || recipeShape[i+numRecipe+1] !is ItemStack){
                throw IllegalArgumentException()
            }
            recipeStacks.put(recipeShape[i+numRecipe] as Char,recipeShape[i+numRecipe+1] as ItemStack)
        }
        //CharArrayから参照して置き換え作業
        val recipe : LinkedList<ItemStack> = LinkedList()
        shape.forEachIndexed({ i, c ->
            val itemStack = recipeStacks[c] ?: throw IllegalArgumentException()
            recipe[i] = itemStack
        })
        alloySmelterRecipes[name] = AlloySmelterRecipe(recipe.toTypedArray(),tier,!isShapeless)
    }

    //ラッパ達
    //不定形
    public fun addAlloySmelterRecipeShapeless(name: String,tier: Int,vararg recipeShape: Array<out Any>){
        addAlloySmelterRecipe(name,tier,true,recipeShape)
    }
    //定形
    public fun addAlloySmelterRecipeShaped(name: String,tier: Int,vararg recipeShape: Array<out Any>){
        addAlloySmelterRecipe(name,tier,false,recipeShape)
    }
}