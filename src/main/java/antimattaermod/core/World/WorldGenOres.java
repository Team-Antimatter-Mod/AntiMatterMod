package antimattaermod.core.World;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by Raiti on 2016/09/25.
 */
public class WorldGenOres extends WorldGenerator{
	
	private int blockMata;
	
	private Block genBlock;
	
	public WorldGenOres(Block genBlock,int blockMata){
		this.genBlock = genBlock;
		this.blockMata = blockMata;
	}
	
	
	@Override
	public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
		return false;
	}
}
