package antimattermod.core.Energy.GUI

import antimattermod.core.AntiMatterModCore
import antimattermod.core.Energy.GUI.AMMButton
import antimattermod.core.Energy.MultiBlock.MultiControllerTile
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.StatCollector
import net.minecraft.world.World

/**
 * Created by kojin15.
 */
class MultiControllerGui(private val tile: MultiControllerTile, private val world: World, private val player: EntityPlayer, private val x: Int, private val y: Int, private val z: Int) : GuiContainer(MultiControllerContainer()) {

    private val mainGui = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/guimulticontroller.png")
    private var controlButton: GuiButton? = null
    private var showButton: GuiButton? = null
    private var NEIButton: GuiButton? = null

    private val subGui = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/guimulticontroller2.png")
    private var backButton: GuiButton? = null

    override fun initGui() {
        super.initGui()
        controlButton = AMMButton(0, (guiLeft - 14) + 181, guiTop + 7, 16, 16)
        showButton = AMMButton(1, (guiLeft - 14) + 181, guiTop + 25, 16, 16)
        NEIButton = AMMButton(2, (guiLeft - 14) + 181, guiTop + 97, 16, 16)

        backButton = AMMButton(3, (guiLeft - 14) + 184, guiTop + 11, 9, 9)

    }

    override fun updateScreen() {
        when (tile.page) {
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

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseZ: Int) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseZ)
        when (tile.page) {
            0 -> {
                this.fontRendererObj.drawString(StatCollector.translateToLocal(tile.coreBlockName + ".name"),-3, 11, 0xFFFFFF )
            }
            1 -> {

            }
        }

    }

    override fun drawGuiContainerBackgroundLayer(partialTick: Float, mouseX: Int, mouseZ: Int) {
        when (tile.page) {
            0 -> {
                this.mc.renderEngine.bindTexture(mainGui)
                this.drawTexturedModalRect(guiLeft - 14, guiTop, 0, 0, 204, 129)
                this.drawTexturedModalRect(guiLeft +167, guiTop + 7, 204, 0, 16, 16)
                when (tile.isShowAssist) {
                    false -> this.drawTexturedModalRect(guiLeft +167, guiTop + 25, 204, 16, 16, 16)
                    true -> this.drawTexturedModalRect(guiLeft +167, guiTop + 25, 220, 16, 16, 16)
                }

                this.drawTexturedModalRect(guiLeft +167, guiTop + 97, 204, 80, 16, 16)
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
            controlButton -> tile.page = 1
            showButton -> {
                when (tile.isShowAssist) {
                    false -> tile.isShowAssist = true
                    true -> tile.isShowAssist = false
                }
            }
            backButton -> tile.page = 0
        }
    }

    override fun doesGuiPauseGame(): Boolean {
        return false
    }
}