package antimattaermod.core.Energy.Machine.GUI

import antimattaermod.core.AntiMatterModCore
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.util.ResourceLocation

/**
 * @author C6H2Cl2
 */
class AlloySmelterGuiContainer(x:Int,y:Int,z:Int) :GuiContainer(AlloySmelterContainer(x, y, z)){

    private val TextureName = ResourceLocation(AntiMatterModCore.MOD_ID.toLowerCase(),"textures/gui/null.png")
    private var x = 0
    private var y = 0
    private var z = 0

    init {
        this.x = x
        this.y = y
        this.z = z
    }

    override fun drawGuiContainerBackgroundLayer(p_146976_1_: Float, p_146976_2_: Int, p_146976_3_: Int) {
        this.mc.renderEngine.bindTexture(TextureName)
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize)
    }
}