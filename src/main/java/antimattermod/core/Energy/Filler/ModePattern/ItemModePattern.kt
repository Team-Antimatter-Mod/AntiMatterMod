package antimattermod.core.Energy.Filler.ModePattern

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Util.MetaItemBase
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon

/**
 * @author kojin15.
 */
class ItemModePattern : Item() {
    init {
        unlocalizedName = "fillerMode"
    }

    var icon: Array<IIcon?> = arrayOfNulls(3)
    val textureDomain = "${AntiMatterModCore.MOD_ID}:fillerpattern/"

    override fun getUnlocalizedName(itemStack: ItemStack?): String {
        return "${super.getUnlocalizedName()}_${itemStack?.itemDamage}"
    }

    override fun registerIcons(register: IIconRegister?) {
        icon[0] = register!!.registerIcon(textureDomain + "filler_place")
        icon[1] = register.registerIcon(textureDomain + "filler_destruction")
        icon[2] = register.registerIcon(textureDomain + "filler_erase")
    }

    override fun getIconFromDamage(meta: Int): IIcon? {
        return icon[meta]
    }
}