package test;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * Created by Raiti-chan on 2016/11/02.
 * 爆発テストブロック
 *
 * @author Raiti-chan
 */
public class ExplosionTestBlock extends Block {
	
	public ExplosionTestBlock() {
		super(Material.wood);
		this.setBlockName("ExplosionTestBlock");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.tnt.getIcon(side, meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int px, float py, float pz, float f) {
		AMMExplosion explosion = new AMMExplosion(world, player, x, y, z, 10, 100);
		explosion.isFlaming = false;
		explosion.isSmoking = true;
		if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(world, explosion)) return true;
		explosion.doExplosionA();
		explosion.doExplosionB(false);
		return true;
	}
}

class AMMExplosion extends Explosion {
	
	private final World worldObj;
	
	private float power;
	
	AMMExplosion(World world, Entity entity, double x, double y, double z, float r, float power) {
		super(world, entity, x, y, z, r);
		this.worldObj = world;
		this.power = power;
	}
	
	@Override
	public void doExplosionA() {
		
		for (int x = (int) (this.explosionX - this.explosionSize); x <= (int) (this.explosionX + this.explosionSize); x++) {
			for (int y = (int) (this.explosionY - this.explosionSize); y <= (int) (this.explosionY + this.explosionSize); y++) {
				for (int z = (int) (this.explosionZ - this.explosionSize); z <= (int) (this.explosionZ + this.explosionSize); z++) {
					
					double dis = ((this.explosionX - x) * (this.explosionX - x) + ((this.explosionZ - z) * (this.explosionZ - z)) + ((this.explosionY - y) * (this.explosionY - y)));
					
					if (dis < this.explosionSize * this.explosionSize) {
						/*
						double px = 1 - (x - (this.explosionX - this.explosionSize)) / (this.explosionSize * 2);
						double py = 1 - (y - (this.explosionY - this.explosionSize)) / (this.explosionSize * 2);
						double pz = 1 - (z - (this.explosionZ - this.explosionSize)) / (this.explosionSize * 2);
						
						double xyz = px * py * pz;
						*/
						if (this.worldObj.getBlock(x, y, z).getExplosionResistance(this.exploder) < this.power) {
							this.worldObj.setBlock(x, y, z, Blocks.air);
						}
					}
				}
			}
		}
		
	}
	
	@Override
	public void doExplosionB(boolean spawnEffect) {
	}
}





