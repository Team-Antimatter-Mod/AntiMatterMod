package antimattermod.core.Util.Gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.Tessellator
import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import org.lwjgl.opengl.GL11

/**
 * Created by kojin15.
 */
object GuiHelper {

    fun drawTexturedClear(x: Int, y: Int, u: Int, v: Int, width: Int, height: Int, alpha: Double, zLevel: Float) {
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glColor4d(2.0, 2.0, 2.0, alpha)
        val f = 0.00390625f
        val f1 = 0.00390625f
        val tessellator = Tessellator.instance
        tessellator.startDrawingQuads()
        tessellator.addVertexWithUV((x + 0).toDouble(), (y + height).toDouble(), zLevel.toDouble(), ((u + 0).toFloat() * f).toDouble(), ((v + height).toFloat() * f1).toDouble())
        tessellator.addVertexWithUV((x + width).toDouble(), (y + height).toDouble(), zLevel.toDouble(), ((u + width).toFloat() * f).toDouble(), ((v + height).toFloat() * f1).toDouble())
        tessellator.addVertexWithUV((x + width).toDouble(), (y + 0).toDouble(), zLevel.toDouble(), ((u + width).toFloat() * f).toDouble(), ((v + 0).toFloat() * f1).toDouble())
        tessellator.addVertexWithUV((x + 0).toDouble(), (y + 0).toDouble(), zLevel.toDouble(), ((u + 0).toFloat() * f).toDouble(), ((v + 0).toFloat() * f1).toDouble())
        tessellator.draw()
        GL11.glDisable(GL11.GL_BLEND)
    }

    /**
     * ボタンの描画
     * @param button 描画するボタン
     * @param isPush 押されているかどうか
     */
    fun drawTextureButton(button: TextureButton?, textureU: Int, textureV: Int, alpha: Double, isPush: Boolean) {
        if (button != null) {
            when (isPush) {
                false -> GuiHelper.drawTexturedClear(button.xPosition, button.yPosition, textureU, textureV, button.width, button.height, alpha, button.zTLevel)
                true -> GuiHelper.drawTexturedClear(button.xPosition, button.yPosition, textureU + button.width, textureV, button.width, button.height, alpha, button.zTLevel)
            }
        }

    }

}

/**
 * 描画を変更する時のボタン
 */
class TextureButton(id: Int, xPosition: Int, yPosition: Int, width: Int, height: Int) : GuiButton(id, xPosition, yPosition, width, height, null) {

    val zTLevel: Float = zLevel

    override fun drawButton(p_146112_1_: Minecraft?, p_146112_2_: Int, p_146112_3_: Int) {

    }

}

/**
 * 何も描画しないボタン
 */
class ClearButton(id: Int, xPosition: Int, yPosition: Int, width: Int, height: Int) : GuiButton(id, xPosition, yPosition, width, height, null) {

    override fun drawButton(p_146112_1_: Minecraft?, p_146112_2_: Int, p_146112_3_: Int) {

    }
}

class SlotBase(inventory: IInventory, slotIndex: Int, xDisplayPosition: Int, yDisplayPosition: Int, private val canIn: Boolean, private val canOut: Boolean) : Slot(inventory, slotIndex, xDisplayPosition, yDisplayPosition) {

    override fun putStack(p_75215_1_: ItemStack?) {
        if (canIn) super.putStack(p_75215_1_)
    }

    override fun decrStackSize(p_75209_1_: Int): ItemStack? {
        if (canOut) {
            return super.decrStackSize(p_75209_1_)
        } else return null

    }
}