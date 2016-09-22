package antimattaermod.core.Block;

import antimattaermod.core.Render.OverlayBlockRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

/**
 * Created by Raiti on 2016/09/22.
 */
public abstract class OverlayBlockBase extends Block{

    protected OverlayBlockBase(Material material){
        super(material);
    }

    public abstract IIcon getOverlayIcon(int par, int meta);




    @Override
    public int getRenderType() {
        return OverlayBlockRender.RenderID;
    }



}
