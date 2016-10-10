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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raiti on 2016/09/22.
 */
public class OreBlock extends OverlayBlockBase implements AMMOreBlock{

    private String overlayTextureName;

    private IIcon[] Overlaytextures;
    
    private String[] baseTextureNames;
    
    private IIcon[] baseTextures;

    private float[] herdness;

    private byte[] harvestLevels;

    private short handle;

    public OreBlock(Material material, String name, String baseTextureName, int handle, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels){
        super(material);
        this.setBlockName(name);
        if(baseTextureName != null)this.baseTextureNames = new String[]{(AntiMatterModCore.MOD_ID+":"+baseTextureName)};
        this.setOverlayTextureName(AntiMatterModCore.MOD_ID+":"+overlayTextureName);
        if(tabs != null)this.setCreativeTab(tabs);
        this.setResistance(1.0F);
        this.setStepSound(Block.soundTypeStone);
        if(maxMeta > 16)throw new ArrayIndexOutOfBoundsException("メタ値の最大は16までです！！");
        this.Overlaytextures = new IIcon[maxMeta];
        this.herdness = herdness;
        this.harvestLevels = harvestLevels;
        this.handle = (short) handle;
        if(baseTextureName != null) baseTextures = new IIcon[baseTextureNames.length];
    }
    
    public OreBlock(Material material, String name, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels){
        this(material,name,null,0,overlayTextureName,tabs,maxMeta,herdness,harvestLevels);
    }
    
    public OreBlock(Material material, String name, String baseTextureNames[], String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels) {
        this(material, name, null, 0, overlayTextureName, tabs, maxMeta, herdness, harvestLevels);
        this.baseTextureNames = baseTextureNames;
        this.baseTextures = new IIcon[baseTextureNames.length];
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        return this.herdness[world.getBlockMetadata(x,y,z)];
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return this.herdness[world.getBlockMetadata(x,y,z)];
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
        if(this.baseTextures == null)return;
        for (int i = 0; i < this.baseTextures.length; i++) {
            this.baseTextures[i] = iiconregister.registerIcon(baseTextureNames[i]);
        }
    }

    public void setOverlayTextureName(String overlayTextureName) {
        this.overlayTextureName = overlayTextureName;
    }

    public String getOverlayTextureName() {
        return overlayTextureName;
    }
    
    @Override
    public IIcon getBaseIcon(IBlockAccess world, int x, int y, int z) {
        return getBaseIcon(world, x, y, z, null, 0, new ArrayList<>());
    }
    
    public IIcon getBaseIcon(IBlockAccess world, int x, int y, int z, ForgeDirection chain, int count, ArrayList<Point> poitList) {
        if (this.baseTextures != null){
            int meta = world.getBlockMetadata(x,y,z);
            if(this.handle != 0){
                String binary = Integer.toBinaryString(this.handle);
                if (binary.length() > meta && binary.charAt(meta) == '1'){
                    return this.baseTextures[0];
                }
            }
        }
        IIcon icon = getTouchingBlockIcon(world,x,y,z,isTouchingBlock(world,x,y,z,ReversalForgeDirection(chain),poitList),count,poitList);
        return icon != null ? icon : Blocks.stone.getIcon(0,0);
    }
    
    public static ForgeDirection isTouchingBlock(IBlockAccess world, int x, int y, int z, ForgeDirection chain,ArrayList<Point> pointList){
        ForgeDirection oreBlocktouvhing = null;
        if(world.getBlock(x,y+1,z) != Blocks.air){
            if(!(world.getBlock(x,y+1,z) instanceof OreBlock)) {
                return ForgeDirection.UP;
            }
            if (chain != ForgeDirection.UP && !Point.isOnPoint(pointList,x,y+1,z))oreBlocktouvhing = ForgeDirection.UP;
        }
        if(world.getBlock(x,y,z-1) != Blocks.air){
            if(!(world.getBlock(x,y,z-1) instanceof OreBlock)) {
                return ForgeDirection.NORTH;
            }
            if (chain != ForgeDirection.NORTH && !Point.isOnPoint(pointList,x,y,z-1))oreBlocktouvhing = ForgeDirection.NORTH;
        }
        if(world.getBlock(x,y,z+1) != Blocks.air){
            if(!(world.getBlock(x,y,z+1) instanceof OreBlock)) {
                return ForgeDirection.SOUTH;
            }
            if (chain != ForgeDirection.SOUTH && !Point.isOnPoint(pointList,x,y,z+1))oreBlocktouvhing = ForgeDirection.SOUTH;
        }
        if(world.getBlock(x-1,y,z) != Blocks.air){
            if(!(world.getBlock(x-1,y,z) instanceof OreBlock)) {
                return ForgeDirection.WEST;
            }
            if (chain != ForgeDirection.WEST && !Point.isOnPoint(pointList,x-1,y,z))oreBlocktouvhing = ForgeDirection.WEST;
        }
        if(world.getBlock(x+1,y,z) != Blocks.air){
            if(!(world.getBlock(x+1,y,z) instanceof OreBlock)) {
                return ForgeDirection.EAST;
            }
            if (chain != ForgeDirection.EAST && !Point.isOnPoint(pointList,x+1,y,z))oreBlocktouvhing = ForgeDirection.EAST;
        }
        if(world.getBlock(x,y-1,z) != Blocks.air){
            if(!(world.getBlock(x,y-1,z) instanceof OreBlock)) {
                return ForgeDirection.DOWN;
            }
            if (chain != ForgeDirection.DOWN && !Point.isOnPoint(pointList,x,y-1,z))oreBlocktouvhing = ForgeDirection.DOWN;
        }
        
        return oreBlocktouvhing;
    }
    
    
    public static IIcon getTouchingBlockIcon(IBlockAccess world, int x, int y, int z, ForgeDirection dir, int count, ArrayList<Point> pointList){
        if(dir == null) return null;
        Block block = world.getBlock(x+dir.offsetX,y+dir.offsetY,z+dir.offsetZ);
        if(block == Blocks.stone || block == Blocks.netherrack || block == Blocks.end_stone){
            return block.getIcon(0,0);
        }else if(block instanceof OreBlock){
            count++;
            pointList.add(new Point(x,y,z));
            return ((OreBlock)block).getBaseIcon(world, x+dir.offsetX, y+dir.offsetY, z+dir.offsetZ,dir, count, pointList);
        }
        return null;
    }
    
    @Override
    public IIcon getBaseIcon(int meta) {
        if(this.baseTextureNames != null){
            if(this.handle != 0){
                String binary = Integer.toBinaryString(this.handle);
                if(binary.length() > meta && binary.charAt(meta) == '1'){
                    return this.baseTextures[0];
                }
            }
        }
        return Blocks.stone.getIcon(0,0);
    }
    
    public static ForgeDirection ReversalForgeDirection(ForgeDirection dir){
        if (dir == null) return null;
        switch (dir){
            case UP:
                return ForgeDirection.DOWN;
            case DOWN:
                return ForgeDirection.UP;
            case EAST:
                return ForgeDirection.WEST;
            case WEST:
                return ForgeDirection.EAST;
            case NORTH:
                return ForgeDirection.SOUTH;
            case SOUTH:
                return ForgeDirection.NORTH;
            default:
                return null;
        }
    }
    
    @Override
    public IIcon getIcon(int par, int meta) {
        return Overlaytextures[meta];
    }
    
    @Override
    public int getMaxMetadate() {
        return Overlaytextures.length;
    }
}

class Point{
    
    public int x,y,z;
    
    public Point(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static boolean isOnPoint(ArrayList<Point> pointList,int x,int y,int z){
        for(Point point:pointList){
            if(point.x == x && point.y == y && point.z == z){
                return true;
            }
        }
        return false;
    }
    
}
