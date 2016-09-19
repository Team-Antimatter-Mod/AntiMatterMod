/*
 * 
 */
package antimattaermod.core.Block.Ores;

import java.util.List;
import java.util.Random;

import antimattaermod.core.Render.OreBlockRender;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/** <h1>CrystalOreBlock</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class CrystalOreBlock extends Block{
	
	private static Random random = new Random();
	
	private String textureName;
	
	private IIcon[] textures;
	
	private IIcon baseTextures;
	
	private float[] herdnesses;
	
	private byte[] harvestLevels;
	
	private int[] doropmeta;
	
	private Item doropitems;
	
	public CrystalOreBlock(Material material, String name, String textureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels, Item doropitems, int[] doropmeta) {
		super(material);
		this.setBlockName(name);
		this.setBlockTextureName("stone");
		if(tabs != null)this.setCreativeTab(tabs);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundTypeStone);
		if(maxMeta > 16)throw new ArrayIndexOutOfBoundsException("メタ値が有効範囲を超えています");
		this.textures = new IIcon[maxMeta];
		this.textureName = textureName;
		this.herdnesses = herdness;
		this.harvestLevels = harvestLevels;
		this.doropmeta = doropmeta;
		this.doropitems = doropitems;
	}
	
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return herdnesses[world.getBlockMetadata(x, y, z)];
	}
	
	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX,
			double explosionY, double explosionZ) {
		return herdnesses[world.getBlockMetadata(x, y, z)];
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
	public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
		return MathHelper.getRandomIntegerInRange(random, 7, 10+(fortune*3));
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return this.doropitems;
	}
	
	@Override
	public int damageDropped(int meta) {
		return this.doropmeta[meta];
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		int i = random.nextInt(fortune + 2) - 1;
		if(i < 0) i = 0;
		return this.quantityDropped(random) * (i + 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < this.textures.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iiconregister) {
		for(int i = 0; i < this.textures.length; i++) {
			this.textures[i] = iiconregister.registerIcon(textureName + "_" + i);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par, int meta) {
		return Blocks.stone.getIcon(par, meta);
		//return textures[meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side){
		return getIcon(side, blockAccess.getBlockMetadata(x, y, z));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getOverlayIcon(int par, int meta) {
		return textures[meta];
	}
	
	@Override
	public int getRenderType() {
		return OreBlockRender.RenderID;
	}
	
}
