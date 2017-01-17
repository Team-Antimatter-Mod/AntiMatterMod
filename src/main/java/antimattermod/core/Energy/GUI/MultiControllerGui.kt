package antimattermod.core.Energy.GUI

import antimattermod.core.AntiMatterModCore
import antimattermod.core.Util.Gui.ClearButton
import antimattermod.core.Energy.MultiBlock.TileMultiController
import antimattermod.core.Util.Gui.GuiHelper
import antimattermod.core.Util.Gui.TextureButton
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.Tessellator
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.StatCollector
import net.minecraft.world.World
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11

/**
 * Created by kojin15.
 */
class MultiControllerGui(private val tileMultiController: TileMultiController, private val world: World, private val player: EntityPlayer, private val x: Int, private val y: Int, private val z: Int) : GuiContainer(MultiControllerContainer()) {

    private val mainGui = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/guimulticontroller.png")
    private var controlButton: TextureButton? = null
    private var showButton: TextureButton? = null
    private var NEIButton: TextureButton? = null

    private val subGui = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/guimulticontroller2.png")
    private var backButton: ClearButton? = null

    override fun initGui() {
        super.initGui()
        controlButton = TextureButton(0, (guiLeft - 14) + 181, guiTop + 7, 16, 16)
        //showButton = ClearButton(1, (guiLeft - 14) + 181, guiTop + 25, 16, 16)
        showButton = TextureButton(1, (guiLeft - 14) + 181, guiTop + 25, 16, 16)
        NEIButton = TextureButton(2, (guiLeft - 14) + 181, guiTop + 97, 16, 16)

        backButton = ClearButton(3, (guiLeft - 14) + 184, guiTop + 11, 9, 9)

    }

    override fun updateScreen() {
        super.updateScreen()
        when (tileMultiController.page) {
            0 -> {
                buttonList.clear()
                buttonList.add(controlButton)
                buttonList.add(showButton)
                buttonList.add(NEIButton)
            }
            1 -> {
                buttonList.clear()
                buttonList.add(backButton)
            }
        }

    }

    override fun mouseClicked(p_73864_1_: Int, p_73864_2_: Int, p_73864_3_: Int) {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_)
        val i = Mouse.getEventX() * this.width / this.mc.displayWidth
        val j = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1
        System.out.println("${i} :: ${j}")

    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseZ: Int) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseZ)
        when (tileMultiController.page) {
            0 -> {
                this.fontRendererObj.drawString(StatCollector.translateToLocal(tileMultiController.coreBlockName + ".name"),-3, 11, 0xFFFFFF )

            }
            1 -> {

            }
        }

    }

    override fun drawGuiContainerBackgroundLayer(partialTick: Float, mouseX: Int, mouseZ: Int) {
        when (tileMultiController.page) {
            0 -> {
                this.mc.renderEngine.bindTexture(mainGui)
                this.drawTexturedModalRect(guiLeft - 14, guiTop, 0, 0, 204, 129)
                GuiHelper.drawTextureButton(controlButton, 204, 0, 1.0, false)
                GuiHelper.drawTextureButton(showButton, 204, 16, 1.0, tileMultiController.isShowAssist)
                GuiHelper.drawTextureButton(NEIButton, 204, 80, 1.0, false)
            }
            1 -> {
                this.mc.renderEngine.bindTexture(subGui)
                this.drawTexturedModalRect(guiLeft - 14, guiTop, 0, 0, 204, 129)
            }
        }
    }

    override fun actionPerformed(button: GuiButton?) {
        super.actionPerformed(button)
        when (button) {
            controlButton -> tileMultiController.page = 1
            showButton -> {
                when (tileMultiController.isShowAssist) {
                    false -> tileMultiController.isShowAssist = true
                    true -> tileMultiController.isShowAssist = false
                }
            }
            backButton -> tileMultiController.page = 0
        }
    }

    override fun doesGuiPauseGame(): Boolean {
        return false
    }
}