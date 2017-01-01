package antimattermod.core.Energy.GUI

import antimattermod.core.AntiMatterModCore
import antimattermod.core.Energy.GUI.AlloySmelterContainer
import antimattermod.core.Energy.TileEntity.Machine.TileAlloySmelter
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation

/**
 * @author C6H2Cl2
 */
class AlloySmelterGuiContainer(private val x: Int, private val y: Int, private val z: Int, private val tileAlloySmelter: TileAlloySmelter, private val inventoryPlayer: InventoryPlayer) : GuiContainer(AlloySmelterContainer(x, y, z, tileAlloySmelter, inventoryPlayer)) {

    private val Texture_2Slot = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/alloy_furnace_2.png")
    private val Texture_4Slot = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/alloy_furnace_4.png")
    private val Texture_9Slot = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(), "textures/gui/alloy_furnace_9.png")

    init {

    }

    override fun initGui() {
        super.initGui()
    }

    override fun drawGuiContainerForegroundLayer(x: Int, p_146979_2_: Int) {
        super.drawGuiContainerForegroundLayer(x, p_146979_2_)
        when (tileAlloySmelter.getSlotSize()) {
            2, 4 -> fontRendererObj.drawString("Alloy Smelter", xSize / 2 - 26, 8, 4210752, false)
            9 -> fontRendererObj.drawString("Alloy Smelter", xSize / 2 - 6, 8, 4210752, false)
        }
    }

    override fun drawGuiContainerBackgroundLayer(p_146976_1_: Float, p_146976_2_: Int, p_146976_3_: Int) {
        val texture = when (tileAlloySmelter.getSlotSize()) {
            2 -> Texture_2Slot
            4 -> Texture_4Slot
            9 -> Texture_9Slot
            else -> Texture_2Slot
        }
        this.mc.renderEngine.bindTexture(texture)
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize)
    }
}