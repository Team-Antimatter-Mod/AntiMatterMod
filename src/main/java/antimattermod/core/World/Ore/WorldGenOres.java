package antimattermod.core.World.Ore;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by Raiti-chan on 2016/09/25.
 * 鉱石生成基本アルゴリズム
 * @author Raiti-chan
 */
public class WorldGenOres extends WorldGenerator{
	
	private int blockMata;
	
	private int number;
	
	private Block genBlock;
	
	/**
	 * 鉱石生成関数を生成
	 * @param genBlock 生成するブロック
	 * @param blockMata ブロックのメタデータ
	 * @param number 生成密度
	 */
	public WorldGenOres(Block genBlock,int blockMata,int number){
		this.genBlock = genBlock;
		this.blockMata = blockMata;
		this.number = number;
	}
	
	/**
	 * 生成処理
	 * @param world 生成するワールド
	 * @param random Random
	 * @param X 起点となるx座標
	 * @param Y 起点となるy座標
	 * @param Z 起点となるz座標
	 * @return 生成できた場合true
	 */
	@Override
	public boolean generate(World world, Random random, int X, int Y, int Z){
		float f = random.nextFloat() * (float)Math.PI;
		double d0 = (double)((float)(X + 8) + MathHelper.sin(f) * (float)this.number / 8.0F);
		double d1 = (double)((float)(X + 8) - MathHelper.sin(f) * (float)this.number / 8.0F);
		double d2 = (double)((float)(Z + 8) + MathHelper.cos(f) * (float)this.number / 8.0F);
		double d3 = (double)((float)(Z + 8) - MathHelper.cos(f) * (float)this.number / 8.0F);
		double d4 = (double)(Y + random.nextInt(3) - 2);
		double d5 = (double)(Y + random.nextInt(3) - 2);
		
		for (int l = 0; l <= this.number; ++l) {
			double d6 = d0 + (d1 - d0) * (double)l / (double)this.number;
			double d7 = d4 + (d5 - d4) * (double)l / (double)this.number;
			double d8 = d2 + (d3 - d2) * (double)l / (double)this.number;
			double d9 = random.nextDouble() * (double)this.number / 16.0D;
			double d10 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.number) + 1.0F) * d9 + 1.0D;
			double d11 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.number) + 1.0F) * d9 + 1.0D;
			int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
			int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
			int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
			int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
			int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
			int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);
			
			for (int k2 = i1; k2 <= l1; ++k2) {
				double d12 = ((double)k2 + 0.5D - d6) / (d10 / 2.0D);
				
				if (d12 * d12 < 1.0D) {
					for (int l2 = j1; l2 <= i2; ++l2) {
						double d13 = ((double)l2 + 0.5D - d7) / (d11 / 2.0D);
						
						if (d12 * d12 + d13 * d13 < 1.0D) {
							for (int i3 = k1; i3 <= j2; ++i3) {
								double d14 = ((double)i3 + 0.5D - d8) / (d10 / 2.0D);
								
								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && isReplaceableOreGen(world.getBlock(k2, l2, i3), world , k2, l2, i3)) {
									world.setBlock(k2, l2, i3, this.genBlock, blockMata, 2);
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	//ブロックが置き換え可能かのcheckメソッド
	private static boolean isReplaceableOreGen(Block block, World world,int k2, int l2, int i3){
		if(block.isReplaceableOreGen(world, k2, l2, i3, Blocks.stone)){
			return true;
		}else if (block.isReplaceableOreGen(world, k2, l2, i3, Blocks.netherrack)){
			return true;
		}else if (block.isReplaceableOreGen(world, k2, l2, i3, Blocks.end_stone)){
			return true;
		}
		return false;
	}
	
}
