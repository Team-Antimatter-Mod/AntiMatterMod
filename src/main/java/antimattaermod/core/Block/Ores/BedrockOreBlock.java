package antimattaermod.core.Block.Ores;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * Created by Raiti on 2016/10/09.
 */
public class BedrockOreBlock extends Block {
	
	
	private Block dropBlock = null;
	
	public BedrockOreBlock(String name, @NotNull Block block){
		super(Material.rock);
		if(!(block instanceof AMMOreBlock))throw new IllegalArgumentException("渡されたBlockインスタンスはAMMOreBlockをimportしていません");
		this.setBlockName(name);
		this.setBlockTextureName(AntiMatterModCore.MOD_ID+":ore/bedrockOre");
		this.setCreativeTab(AntiMatterModRegistry.tabOreBlock);
		this.setResistance(1.0F);
		this.setHardness(3.0F);
		this.setHarvestLevel("pickaxe",3);
		this.setStepSound(Block.soundTypeStone);
		dropBlock = block;
		
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
			return dropBlock.getItemDropped(meta,random,fortune);
	}
	
	@Override
	public int damageDropped(int meta) {
		return dropBlock.damageDropped(meta);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < ((AMMOreBlock)dropBlock).getMaxMetadate(); i++){
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		if(player.capabilities.isCreativeMode){
			return super.removedByPlayer(world, player, x, y, z, willHarvest);
		}else if(hasSilkTouch(player.getHeldItem())){
			return super.removedByPlayer(world, player, x, y, z, willHarvest);
		} else{
			if (world.rand.nextInt(100) >= 90+world.rand.nextInt(5)){
				return super.removedByPlayer(world, player, x, y, z, willHarvest);
			}else{
				return true;
			}
		}
	}
	
	public static boolean hasSilkTouch(ItemStack item){
		NBTTagList list = item.getEnchantmentTagList();
		if (list == null) return false;
		
		for (int i = 0; i < list.tagCount(); i++){
			if(list.getCompoundTagAt(i).getShort("id")==33) return true;
		}
		return false;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random){
		int j = random.nextInt(fortune + 1) - 1;
		
		if (j < 0)
		{
			j = 0;
		}
		
		return this.quantityDropped(random) * (j + 1);
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		Item item = getItem(world, x, y, z);
		
		if (item == null)
		{
			return null;
		}
		return new ItemStack(item, 1, world.getBlockMetadata(x,y,z));
	}
	
	@Override
	public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
		return this.dropBlock.getExpDrop(world, metadata, fortune);
	}
}
