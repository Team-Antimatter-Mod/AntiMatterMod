package antimattaermod.core.Item;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by  on 2016/09/22.
 */
public class IngotBase extends Item{

    private IIcon[] iicon;

    public IngotBase(String name,String texture,int Maxmeta){
        setCreativeTab(AntiMatterModRegistry.tabMaterials);
        setUnlocalizedName(name);
        setTextureName(AntiMatterModCore.MOD_ID+":"+"ingot/"+texture);
        setMaxDamage(0);
        setHasSubtypes(true);
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
