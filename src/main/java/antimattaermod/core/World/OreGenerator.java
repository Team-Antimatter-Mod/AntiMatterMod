package antimattaermod.core.World;

import antimattaermod.core.AntiMatterModRegistry;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.*;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by Raiti on 2016/09/22.
 */
public class OreGenerator implements IWorldGenerator {
	
	
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        if (world.provider instanceof WorldProviderSurface){
            oreGenerateOverworld(random,chunkX << 4,chunkZ,world,chunkGenerator,chunkProvider);
        }else if (world.provider instanceof WorldProviderHell){
            oreGenerateHell(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
        }else if (world.provider instanceof WorldProviderEnd){
            oreGenerateEnd(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
        }



    }

    private  void oreGenerateOverworld(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){

        for(int i = 0; i < 16; i++) {
            int genX = x + random.nextInt(16);
            int genY = 16 + random.nextInt(15);
            int genZ = z + random.nextInt(16);
            System.out.println(new WorldGenMinable(AntiMatterModRegistry.crystalOreBlock_1, 0, 5, Blocks.stone).generate(world, random, genX, genY, genZ)+":"+genX+","+genY+","+genZ);
        }

    }

    private  void oreGenerateHell(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){

    }

    private  void oreGenerateEnd(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){

    }


}


