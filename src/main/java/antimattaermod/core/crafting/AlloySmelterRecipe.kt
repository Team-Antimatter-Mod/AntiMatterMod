package antimattaermod.core.crafting

import net.minecraft.item.ItemStack

/**
 * @author C6H2Cl2
 */
class AlloySmelterRecipe {
    private var materials : Array<ItemStack?> = kotlin.arrayOfNulls<ItemStack>(9)
    private var requiredTier : Int = 0
    private var isShaped : Boolean = false

    constructor(alloyMaterials: Array<ItemStack?>,tier : Int){
        materials = alloyMaterials
        requiredTier = tier
    }

    constructor(alloyMaterials: Array<ItemStack?>, tier: Int, isShapedRecipe : Boolean){
        materials = alloyMaterials
        requiredTier = tier
        isShaped = isShapedRecipe
    }

    public fun getMaterials() : Array<ItemStack?> = materials
    public fun getRequiredTier() : Int = requiredTier
    public fun isShapedRecipe() : Boolean = isShaped

    //レシピ比較用に、equalsをオーバーライドしておいた
    override fun equals(other: Any?): Boolean {
        if (other !is AlloySmelterRecipe){
            return false
        }
        val target : AlloySmelterRecipe = other
        if(target.requiredTier != requiredTier){
            return false
        }
        if (isShaped){
            materials.forEachIndexed { i, itemStack ->
                if (target.materials[i] != itemStack){
                    return false
                }
            }
        }else{
            materials.forEachIndexed { i, itemStack ->
                if(!target.materials.contains(itemStack)){
                    return false
                }
            }
        }
        return true
    }
}