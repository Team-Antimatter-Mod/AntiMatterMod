package antimattaermod.core;

import antimattaermod.core.Energy.Machine.GUI.AlloySmelterContainer;
import antimattaermod.core.Energy.Machine.GUI.AlloySmelterGuiContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author C6H2Cl2
 */
public class AMMGuiHandler implements IGuiHandler {
    public static final int GuiID_AlloySmelter = 334;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GuiID_AlloySmelter: return new AlloySmelterContainer(x, y, z);
            default:return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GuiID_AlloySmelter: return new AlloySmelterGuiContainer(x, y, z);
            default:return null;
        }
    }
}
