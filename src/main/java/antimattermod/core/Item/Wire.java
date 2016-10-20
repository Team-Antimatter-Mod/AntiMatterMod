package antimattermod.core.Item;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by worldofthetakumi on 2016/09/24.
 */
public class Wire extends Item {

    private IIcon[] iicon;

    public Wire(String name,String texture,int Maxmeta){
        super();
        setUnlocalizedName(name);
        setTextureName(AntiMatterModCore.MOD_ID+":"+texture);
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(AntiMatterModRegistry.tabMaterials);
        this.iicon =  new IIcon[Maxmeta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        for (int i=0;i<iicon.length;i++){
            this.iicon[i] = register.registerIcon(this.getIconString()+"_"+i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta){
        return iicon[meta];
    }

    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list){
        for (int i=0;i<iicon.length;i++){
            list.add(new ItemStack(this,1,i));
        }
    }

    @Override
    public int getMetadata(int meta){
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack){
        return super.getUnlocalizedName()+"_"+itemStack.getItemDamage();
    }
}
