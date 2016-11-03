package test;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Raiti-chan on 2016/11/02.
 * 爆発テストブロック
 * @author Raiti-chan
 */
public class ExplosionTestBlock extends Block{
	
	public ExplosionTestBlock() {
		super(Material.wood);
		this.setBlockName("ExplosionTestBlock");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.tnt.getIcon(side,meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int px, float py, float pz, float f) {
		world.createExplosion(player,x,y,z,100,true);
		return true;
	}
}
