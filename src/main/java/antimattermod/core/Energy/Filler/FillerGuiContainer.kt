package antimattermod.core.Energy.Filler

import antimattermod.core.AntiMatterModCore
import antimattermod.core.Energy.Filler.TileFiller
import antimattermod.core.Util.Gui.ClearButton
import antimattermod.core.Util.Gui.GuiHelper
import antimattermod.core.Util.Gui.TextureButton
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.OpenGlHelper
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation
import org.jetbrains.kotlin.backend.jvm.intrinsics.ArrayOf
import org.lwjgl.opengl.GL11

/**
 * @author kojin15.
 */
class FillerGuiContainer(private val x: Int, private val y: Int, private val z: Int, private val tile: TileFiller, private val playerInventory: InventoryPlayer) : GuiContainer(FillerContainer(x, y, z, tile, playerInventory)) {
    init {
        xSize += 88
        ySize = 190
    }

    private val mainGui = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/filler.png")
    private val itemSlot = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/itemslot_4_6.png")

    private var active: GuiButton? = null

    private val modeChangeButton: Array<GuiButton?> = arrayOfNulls(2)

    private val rangeXPlusButton: Array<GuiButton?> = arrayOfNulls(3)
    private val rangeYPlusButton: Array<GuiButton?> = arrayOfNulls(3)
    private val rangeZPlusButton: Array<GuiButton?> = arrayOfNulls(3)
    private val rangeXMinusButton: Array<GuiButton?> = arrayOfNulls(3)
    private val rangeYMinusButton: Array<GuiButton?> = arrayOfNulls(3)
    private val rangeZMinusButton: Array<GuiButton?> = arrayOfNulls(3)
    private val rangeStr: Array<String> = arrayOf(">", ">>", ">>>", "<", "<<", "<<<")

    override fun initGui() {
        super.initGui()
        for (i in 0..2) {
            rangeXPlusButton[i] = PictureButton(i, guiLeft + 100 + i * 18, guiTop + 55, 14, 12, rangeStr[i], 0, 190, mainGui)
            rangeYPlusButton[i] = PictureButton(i + 3, guiLeft + 100 + i * 18, guiTop + 73, 14, 12, rangeStr[i], 0, 190, mainGui)
            rangeZPlusButton[i] = PictureButton(i + 6, guiLeft + 100 + i * 18, guiTop + 91, 14, 12, rangeStr[i], 0, 190, mainGui)
            rangeXMinusButton[i] = PictureButton(i + 9, guiLeft + 62 - i * 18, guiTop + 55, 14, 12, rangeStr[i + 3], 0, 190, mainGui)
            rangeYMinusButton[i] = PictureButton(i + 12, guiLeft + 62 - i * 18, guiTop + 73, 14, 12, rangeStr[i + 3], 0, 190, mainGui)
            rangeZMinusButton[i] = PictureButton(i + 15, guiLeft + 62 - i * 18, guiTop + 91, 14, 12, rangeStr[i + 3], 0, 190, mainGui)
        }
        modeChangeButton[0] = PictureButton(18, guiLeft + 60, guiTop + 16, 12, 12, "<", 0, 202, mainGui)
        modeChangeButton[1] = PictureButton(19, guiLeft + 104, guiTop + 16, 12, 12, ">", 0, 202, mainGui)
        active = PictureButton(20, guiLeft + 153, guiTop + 89, 16, 16, "S", 0, 214, mainGui)
        addButton()
    }

    private fun addButton() {
        for (i in 0..2) {
            buttonList.add(rangeXPlusButton[i])
            buttonList.add(rangeYPlusButton[i])
            buttonList.add(rangeZPlusButton[i])
            buttonList.add(rangeXMinusButton[i])
            buttonList.add(rangeYMinusButton[i])
            buttonList.add(rangeZMinusButton[i])
        }
        for (i in 0..1) {
            buttonList.add(modeChangeButton[i])
        }
        buttonList.add(active)
    }

    override fun drawGuiContainerForegroundLayer(p_146979_1_: Int, p_146979_2_: Int) {
        this.fontRendererObj.drawString("${tile.xRange}", 80, 57, 0xFFFFFF)
        this.fontRendererObj.drawString("${tile.yRange}", 80, 75, 0xFFFFFF)
        this.fontRendererObj.drawString("${tile.zRange}", 80, 93, 0xFFFFFF)
    }

    override fun drawGuiContainerBackgroundLayer(partialTick: Float, x: Int, y: Int) {
        this.mc.renderEngine.bindTexture(mainGui)
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 190)
        this.mc.renderEngine.bindTexture(itemSlot)
        this.drawTexturedModalRect(guiLeft + 178, guiTop, 0, 0, 86, 122)

    }

    override fun actionPerformed(button: GuiButton?) {
        super.actionPerformed(button)
        when (button) {
            active -> tile.startStop()
            modeChangeButton[0] -> tile.modeDown()
            modeChangeButton[1] -> tile.modeUp()
            else -> {
                if (!tile.isStart) {
                    when (button) {
                        rangeXPlusButton[0] -> tile.xRange++
                        rangeXPlusButton[1] -> tile.xRange += 10
                        rangeXPlusButton[2] -> tile.xRange += 100

                        rangeYPlusButton[0] -> tile.yRange++
                        rangeYPlusButton[1] -> tile.yRange += 10
                        rangeYPlusButton[2] -> tile.yRange += 100

                        rangeZPlusButton[0] -> tile.zRange++
                        rangeZPlusButton[1] -> tile.zRange += 10
                        rangeZPlusButton[2] -> tile.zRange += 100

                        rangeXMinusButton[0] -> if (tile.xRange != 1) tile.xRange--
                        rangeXMinusButton[1] -> if (tile.xRange > 10) tile.xRange -= 10
                        rangeXMinusButton[2] -> if (tile.xRange > 100) tile.xRange -= 100

                        rangeYMinusButton[0] -> if (tile.yRange != 1) tile.yRange--
                        rangeYMinusButton[1] -> if (tile.yRange > 10) tile.yRange -= 10
                        rangeYMinusButton[2] -> if (tile.yRange > 100) tile.yRange -= 100

                        rangeZMinusButton[0] -> if (tile.zRange != 1) tile.zRange--
                        rangeZMinusButton[1] -> if (tile.zRange > 10) tile.zRange -= 10
                        rangeZMinusButton[2] -> if (tile.zRange > 100) tile.zRange -= 100
                    }
                }

            }
        }

    }
    companion object {

        class PictureButton(private var id: Int, private var x: Int, private var y: Int, private var w: Int, private var h: Int, private var str: String, private var u: Int, private var v: Int, private var resource: ResourceLocation) : GuiButton(id, x, y, w, h, str) {

            override fun drawButton(minecraft: Minecraft?, x: Int, y: Int) {
                if (visible) {
                    val fontRenderer = minecraft?.fontRenderer
                    minecraft?.textureManager?.bindTexture(resource)
                    GL11.glColor4d(1.0, 1.0, 1.0, 1.0)
                    field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + width && y < this.yPosition + this.height
                    var i = getHoverState(field_146123_n)
                    GL11.glEnable(GL11.GL_BLEND)
                    OpenGlHelper.glBlendFunc(770, 771, 1, 0)
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
                    if (!field_146123_n) {
                        this.drawTexturedModalRect(xPosition, yPosition, u, v, w, h)
                    } else {
                        this.drawTexturedModalRect(xPosition, yPosition, u + w, v, w, h)
                    }

                    mouseDragged(minecraft, x, y)
                    var l = 14737632
                    if (packedFGColour != 0) {
                        l = packedFGColour
                    } else if (!this.enabled) {
                        l = 10526880
                    } else if (this.field_146123_n) {
                        l = 16777120
                    }
                    this.drawCenteredString(fontRenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l)


                }
            }
        }

    }
}