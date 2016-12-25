package antimattermod.core.Render.ItemRender

import antimattermod.core.Item.tool.AMMTool
import net.minecraft.client.renderer.ItemRenderer
import net.minecraft.item.ItemStack
import net.minecraftforge.client.IItemRenderer
import org.lwjgl.opengl.GL11
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraftforge.client.MinecraftForgeClient.registerItemRenderer
import net.minecraftforge.client.MinecraftForgeClient
import net.minecraftforge.client.MinecraftForgeClient.registerItemRenderer






/**
 * Created by kojin15.
 */
class ToolDamageRender : IItemRenderer {

    override fun handleRenderType(item: ItemStack?, type: IItemRenderer.ItemRenderType?): Boolean {
        when (type) {
            IItemRenderer.ItemRenderType.INVENTORY -> return true
            else -> return false
        }
    }

    override fun shouldUseRenderHelper(type: IItemRenderer.ItemRenderType?, item: ItemStack?, helper: IItemRenderer.ItemRendererHelper?): Boolean {
        return false
    }

    override fun renderItem(type: IItemRenderer.ItemRenderType?, item: ItemStack?, vararg data: Any?) {
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {

            GL11.glEnable(3042)
            GL11.glColor3f(1.0F, 1.0F, 1.0F)
            GL11.glBlendFunc(770, 771)

            val icon = item!!.iconIndex
            this.renderItemIcon(icon, 16.0, 0.001, 0.0F, 0.0F, -1.0F)

            val damageIcon = AMMTool.icons
            val damage: Int = item.itemDamage
            val maxDamage = item.maxDamage
            val baseDamage = maxDamage.div(8)
            val typeD : Array<Int> = arrayOf(0, baseDamage.times(1), baseDamage.times(2), baseDamage.times(3), baseDamage.times(4), baseDamage.times(5), baseDamage.times(6), baseDamage.times(7), baseDamage.times(8), maxDamage)

            when {
                damage > typeD[8] -> this.renderItemIcon(damageIcon[0], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[7] -> this.renderItemIcon(damageIcon[1], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[6] -> this.renderItemIcon(damageIcon[2], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[5] -> this.renderItemIcon(damageIcon[3], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[4] -> this.renderItemIcon(damageIcon[4], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[3] -> this.renderItemIcon(damageIcon[5], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[2] -> this.renderItemIcon(damageIcon[6], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[1] -> this.renderItemIcon(damageIcon[7], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
                damage > typeD[0] -> this.renderItemIcon(damageIcon[8], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            }
            /*if (damage > typeD[8]) {
                this.renderItemIcon(damageIcon[0], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[7]) {
                this.renderItemIcon(damageIcon[1], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[6]) {
                this.renderItemIcon(damageIcon[2], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[5]) {
                this.renderItemIcon(damageIcon[3], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[4]) {
                this.renderItemIcon(damageIcon[4], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[3]) {
                this.renderItemIcon(damageIcon[5], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[2]) {
                this.renderItemIcon(damageIcon[6], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[1]) {
                this.renderItemIcon(damageIcon[7], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            } else if (damage > typeD[0]) {
                this.renderItemIcon(damageIcon[8], 16.0, 0.001, 0.0F, 0.0F, -1.0F)
            }*/


            GL11.glDisable(3042)

        }
    }

    fun renderItemIcon(icon: IIcon, size: Double, z: Double, nx: Float, ny: Float, nz: Float) {
        renderItemIcon(icon, 0.0, 0.0, size, size, z, nx, ny, nz)
    }

    fun renderItemIcon(icon: IIcon?, xStart: Double, yStart: Double, xEnd: Double, yEnd: Double, z: Double, nx: Float, ny: Float, nz: Float) {
        if (icon == null) {
            return
        }
        Tessellator.instance.startDrawingQuads()
        Tessellator.instance.setNormal(nx, ny, nz)
        if (nz > 0.0f) {
            Tessellator.instance.addVertexWithUV(xStart, yStart, z, icon.minU.toDouble(), icon.minV.toDouble())
            Tessellator.instance.addVertexWithUV(xEnd, yStart, z, icon.maxU.toDouble(), icon.minV.toDouble())
            Tessellator.instance.addVertexWithUV(xEnd, yEnd, z, icon.maxU.toDouble(), icon.maxV.toDouble())
            Tessellator.instance.addVertexWithUV(xStart, yEnd, z, icon.minU.toDouble(), icon.maxV.toDouble())
        } else {
            Tessellator.instance.addVertexWithUV(xStart, yEnd, z, icon.minU.toDouble(), icon.maxV.toDouble())
            Tessellator.instance.addVertexWithUV(xEnd, yEnd, z, icon.maxU.toDouble(), icon.maxV.toDouble())
            Tessellator.instance.addVertexWithUV(xEnd, yStart, z, icon.maxU.toDouble(), icon.minV.toDouble())
            Tessellator.instance.addVertexWithUV(xStart, yStart, z, icon.minU.toDouble(), icon.minV.toDouble())
        }
        Tessellator.instance.draw()
    }
}