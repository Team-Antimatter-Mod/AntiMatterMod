package antimattermod.core.Energy.MultiBlock

import antimattermod.core.AntiMatterModCore
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

/**
 * Created by kojin15.
 */
class MultiControllerGui(private val tile: MultiControllerTile, private val world: World, private val player: EntityPlayer, private val x: Int, private val y: Int, private val z: Int) : GuiContainer(MultiControllerContainer()) {

    private val mainGui = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/guimulticontroller.png")
    private var controlButton: GuiButton? = null
    private var showButton: GuiButton? = null

    private var NEIButton: GuiButton? = null

    override fun initGui() {
        super.initGui()
        controlButton = AMMButton(0, (guiLeft - 14) + 181, guiTop + 7, 16, 16)
        showButton = AMMButton(0, (guiLeft - 14) + 181, guiTop + 25, 16, 16)
        NEIButton = AMMButton(0, (guiLeft - 14) + 181, guiTop + 97, 16, 16)
        buttonList.add(controlButton)
        buttonList.add(showButton)
        buttonList.add(NEIButton)

    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseZ: Int) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseZ)

    }

    override fun drawGuiContainerBackgroundLayer(partialTick: Float, mouseX: Int, mouseZ: Int) {
        this.mc.renderEngine.bindTexture(mainGui)
        this.drawTexturedModalRect(guiLeft - 14, guiTop, 0, 0, 204, 129)
        this.drawTexturedModalRect(guiLeft +167, guiTop + 7, 204, 0, 16, 16)
        when (tile.isShowAssist) {
            false -> this.drawTexturedModalRect(guiLeft +167, guiTop + 25, 204, 16, 16, 16)
            true -> this.drawTexturedModalRect(guiLeft +167, guiTop + 25, 220, 16, 16, 16)
        }

        this.drawTexturedModalRect(guiLeft +167, guiTop + 97, 204, 80, 16, 16)
    }

    override fun actionPerformed(button: GuiButton?) {
        super.actionPerformed(button)
        when (button) {
            showButton -> {
                when (tile.isShowAssist) {
                    false -> tile.isShowAssist = true
                    true -> tile.isShowAssist = false
                }
            }
        }
    }

    override fun doesGuiPauseGame(): Boolean {
        return false
    }
}
class AMMButton(id: Int, xPosition: Int, yPosition: Int, width: Int, height: Int) : GuiButton(id, xPosition, yPosition,  width, height, null) {

    override fun drawButton(p_146112_1_: Minecraft?, p_146112_2_: Int, p_146112_3_: Int) {

    }
}