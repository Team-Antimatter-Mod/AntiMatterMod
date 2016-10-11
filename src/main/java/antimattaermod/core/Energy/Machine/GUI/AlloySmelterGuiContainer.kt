package antimattaermod.core.Energy.Machine.GUI

import antimattaermod.core.AntiMatterModCore
import antimattaermod.core.Energy.Machine.TileAlloySmelter
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.util.ResourceLocation

/**
 * @author C6H2Cl2
 */
class AlloySmelterGuiContainer(private val x:Int, private val y:Int, private val z:Int,private val tileAlloySmelter: TileAlloySmelter) :GuiContainer(AlloySmelterContainer(x, y, z,tileAlloySmelter)){

    private val Texture_2Slot = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(),"textures/gui/alloy_furnace_2.png")
    private val Texture_4Slot = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(),"textures/gui/alloy_furnace_4.png")
    private val Texture_9Slot = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(),"textures/gui/alloy_furnace_9.png")

    init {

    }

    override fun initGui() {
        super.initGui()
    }

    override fun drawGuiContainerBackgroundLayer(p_146976_1_: Float, p_146976_2_: Int, p_146976_3_: Int) {
        val texture = Texture_2Slot
        when(tileAlloySmelter.getSlotSize()){
            2 -> Texture_2Slot
            4 -> Texture_4Slot
            9 -> Texture_9Slot
        }
        this.mc.renderEngine.bindTexture(texture)
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize)
    }
}