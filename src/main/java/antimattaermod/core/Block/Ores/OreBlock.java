package antimattaermod.core.Block.Ores;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.Block.OverlayBlockBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import java.util.List;

/**
 * Created by Raiti on 2016/09/22.
 */
public class OreBlock extends OverlayBlockBase{

    private String overlayTextureName;

    private IIcon[] Overlaytextures;

    private float[] herdness;

    private byte[] harvestLevels;



    public OreBlock(Material material, String name, String textureName, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels){
        super(material);
        this.setBlockName(name);
        this.setBlockTextureName(textureName);
        this.setOverlayTextureName(AntiMatterModCore.MOD_ID+":"+overlayTextureName);
        if(tabs != null)this.setCreativeTab(tabs);
        this.setResistance(1.0F);
        this.setStepSound(Block.soundTypeStone);
        if(maxMeta > 16)throw new ArrayIndexOutOfBoundsException("メタ値の最大は16までです！！");
        this.Overlaytextures = new IIcon[maxMeta];
        this.herdness = herdness;
        this.harvestLevels = harvestLevels;

    }


    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        return this.herdness[world.getBlockMetadata(x,y,z)];
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        //return this.herdness[world.getBlockMetadata(x,y,z)];
        return 100000.0F;// TODO : 鉱石生成デバッグ時、TNTが使えるよう爆破耐久値を底上げ、リリース時忘れないように
    }

    @Override
    public int getHarvestLevel(int metadata) {
        return harvestLevels[metadata];
    }

    @Override
    public String getHarvestTool(int metadata) {
        return "pickaxe";
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.Overlaytextures.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister iiconregister) {
        super.registerBlockIcons(iiconregister);
        for (int i = 0; i < this.Overlaytextures.length; i++) {
            this.Overlaytextures[i] = iiconregister.registerIcon(this.getOverlayTextureName()+"_"+i);
        }
    }

    public void setOverlayTextureName(String overlayTextureName) {
        this.overlayTextureName = overlayTextureName;
    }

    public String getOverlayTextureName() {
        return overlayTextureName;
    }

    @Override
    public IIcon getOverlayIcon(int par, int meta) {
        return Overlaytextures[meta];
    }



}
