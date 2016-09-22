/*
 * 
 */
package antimattaermod.core.Block.Ores;

import java.util.List;
import java.util.Random;

import antimattaermod.core.Render.OverlayBlockRender;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/** <h1>CrystalOreBlock</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class CrystalOreBlock extends OreBlock{

	private static Random random = new Random();
	
	private int[] doropmeta;
	
	private Item doropitems;
	
	public CrystalOreBlock(Material material, String name, String textureName, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels, Item doropitems, int[] doropmeta) {
		super(material,name,textureName,overlayTextureName,tabs,maxMeta,herdness,harvestLevels);
		this.doropmeta = doropmeta;
		this.doropitems = doropitems;
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


}
