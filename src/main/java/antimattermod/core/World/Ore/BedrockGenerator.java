package antimattermod.core.World.Ore;

import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.Ores.BedrockOreBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by Raiti on 2016/10/11.
 */
public class BedrockGenerator extends WorldGenerator{
	
	public BedrockOreBlock[] blocks = {
			(BedrockOreBlock)AntiMatterModRegistry.bedrockOreBlock_1,
			(BedrockOreBlock)AntiMatterModRegistry.bedrockCrystalOreBlock_1,
	};
	
	
	@Override
	public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
		for(int i =0 ; i < 1; i++){
			int x = ((p_76484_3_ >> 4) << 4) + p_76484_2_.nextInt(16);
			int z = ((p_76484_5_ >> 4) << 4) + p_76484_2_.nextInt(16);
			int rad = p_76484_2_.nextInt(blocks.length);
			if(!Block.isEqualTo(p_76484_1_.getBlock(x,p_76484_4_,z), Blocks.air))p_76484_1_.setBlock(x,p_76484_4_,z,blocks[rad],p_76484_2_.nextInt(blocks[rad].getMaxMetadate()),2);
		}
		return true;
	}
}
