package antimattaermod.core.Render

import antimattaermod.core.AntiMatterModCore
import antimattaermod.core.Energy.Transfer.TileEntityCable
import antimattaermod.core.Model.CableModel
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.util.ForgeDirection
import org.lwjgl.opengl.GL11

class CableRender : TileEntitySpecialRenderer() {

    override fun renderTileEntityAt(tileEntity: TileEntity, x: Double, y: Double, z: Double, f: Float) {
        val model = CableModel()
        val tileEntityCable : TileEntityCable = tileEntity as TileEntityCable
        var isConnected : Boolean = false
        if(tileEntityCable.getConnect(ForgeDirection.UP)){
            if (tileEntityCable.getConnect(ForgeDirection.DOWN)){
                model.connectUpToDown()
            }else{
                model.connectUp()
            }
            isConnected = true
        }else if (tileEntityCable.getConnect(ForgeDirection.DOWN)){
            model.connectDown()
            isConnected = true
        }
        if(tileEntityCable.getConnect(ForgeDirection.NORTH)){
            if (tileEntityCable.getConnect(ForgeDirection.SOUTH)){
                model.connectNorthToSouth()
            }else{
                model.connectNorth()
            }
            isConnected = true
        }else if (tileEntityCable.getConnect(ForgeDirection.SOUTH)){
            model.connectSouth()
            isConnected = true
        }
        if (tileEntityCable.getConnect(ForgeDirection.EAST)){
            if (tileEntityCable.getConnect(ForgeDirection.WEST)){
                model.connectEastToWest()
            }else{
                model.connectEast()
            }
            isConnected = true
        }else if (tileEntityCable.getConnect(ForgeDirection.WEST)){
            model.connectWest()
            isConnected = true
        }
        if(!isConnected){
//            model.nonConnect()
        }
        model.nonConnect()
        GL11.glPushMatrix()
        GL11.glTranslatef(x.toFloat() + 0.5f, y.toFloat() + 1.5f, z.toFloat() + 0.5f)
        GL11.glRotatef(180f, 0f, 0f, 1f)

        this.bindTexture(texture)

        GL11.glPushMatrix()
        model.renderModel(0.0625f)
        GL11.glPopMatrix()

        GL11.glPopMatrix()
    }

    companion object {

        private val texture = ResourceLocation(AntiMatterModCore.MOD_ID + ":" + "textures/models/Cable.png")
    }
}
