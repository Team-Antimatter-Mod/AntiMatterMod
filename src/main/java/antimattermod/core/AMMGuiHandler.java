package antimattermod.core;

import antimattermod.core.Energy.Filler.FillerContainer;
import antimattermod.core.Energy.Filler.FillerGuiContainer;
import antimattermod.core.Energy.GUI.*;
import antimattermod.core.Energy.MultiBlock.TileMultiController;
import antimattermod.core.Energy.TileEntity.Machine.TileAlloySmelter;
import antimattermod.core.Energy.Filler.TileFiller;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author C6H2Cl2
 */
public class AMMGuiHandler implements IGuiHandler {
    public static final int GuiID_AlloySmelter = 334;
    public static final int GuiID_MultiController = 335;
    public static final int GuiID_Filler = 336;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GuiID_AlloySmelter: return new AlloySmelterContainer(x, y, z,(TileAlloySmelter)(world.getTileEntity(x,y,z)),player.inventory);
            case GuiID_MultiController: return new MultiControllerContainer();
            case GuiID_Filler: return new FillerContainer(x, y, z, (TileFiller)(world.getTileEntity(x, y, z)), player.inventory);
            default:return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GuiID_AlloySmelter: return new AlloySmelterGuiContainer(x, y, z,(TileAlloySmelter)(world.getTileEntity(x,y,z)),player.inventory);
            case GuiID_MultiController: return new MultiControllerGui((TileMultiController)(world.getTileEntity(x, y, z)), world, player, x, y, z);
            case GuiID_Filler: return new FillerGuiContainer(x, y, z, (TileFiller)(world.getTileEntity(x, y, z)), player.inventory);
            default:return null;
        }
    }
}
