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
 * 岩盤鉱石ジェネレーター
 * @author Raiti-chan
 */
public class BedrockGenerator extends WorldGenerator{
	
	public BedrockOreBlock[] blocks = {
			(BedrockOreBlock)AntiMatterModRegistry.bedrockOreBlock_1,
			(BedrockOreBlock)AntiMatterModRegistry.bedrockCrystalOreBlock_1,
	};
	
	
	@Override
	public boolean generate(World world, Random random, int px, int py, int pz) {
		for (int i = 0; i < 1; i++) {
			int x = ((px >> 4) << 4) + random.nextInt(16);
			int z = ((pz >> 4) << 4) + random.nextInt(16);
			int rad = random.nextInt(blocks.length);
			int meta;
			if(rad == 0 || rad == 1) {
				meta = random.nextInt(blocks[rad].getMaxMetadate()-1)+1;
			} else {
				meta = random.nextInt(blocks[rad].getMaxMetadate());
			}
			if (!Block.isEqualTo(world.getBlock(x, py, z), Blocks.air)) world.setBlock(x, py, z, blocks[rad], meta, 2);
		}
		return true;
	}
}
