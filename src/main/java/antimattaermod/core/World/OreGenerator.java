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
            oreGenerateOverworld(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
        }else if (world.provider instanceof WorldProviderHell){
            oreGenerateHell(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
        }else if (world.provider instanceof WorldProviderEnd){
            oreGenerateEnd(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
        }



    }

    private  void oreGenerateOverworld(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
        
        for(OreGeneratorEntry entry:OreGeneratorEntry.ORE_GENERATOR_ENTRIES){
            for (int i = 0; i < entry.getLoop(); i++){
                if (entry.isGeneratOverworld() == false) continue;
                int genX = (x << 4) + random.nextInt(16);
                int genY = (entry.getMinYOverworld() + random.nextInt(entry.getMaxYOverworld()-entry.getMinYOverworld()));
                int genZ = (z << 4) + random.nextInt(16);
                entry.getGenMinable().generate(world,random,genX,genY,genZ);
            }
            
        }

    }

    private  void oreGenerateHell(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
        for(OreGeneratorEntry entry:OreGeneratorEntry.ORE_GENERATOR_ENTRIES){
            if (entry.isGeneratHell() == false) continue;
            for (int i = 0; i < entry.getLoop(); i++){
                int genX = (x << 4) + random.nextInt(16);
                int genY = (entry.getMinYHell() + random.nextInt(entry.getMaxYHell()-entry.getMinYHell()));
                int genZ = (z << 4) + random.nextInt(16);
                entry.getGenMinable().generate(world,random,genX,genY,genZ);
                
            }
        }
    }

    private  void oreGenerateEnd(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
        for(OreGeneratorEntry entry:OreGeneratorEntry.ORE_GENERATOR_ENTRIES){
            if (entry.isGeneratEnd() == false) continue;
            for (int i = 0; i < entry.getLoop(); i++){
                int genX = (x << 4) + random.nextInt(16);
                int genY = (entry.getMinYEnd() + random.nextInt(entry.getMaxYEnd()-entry.getMinYEnd()));
                int genZ = (z << 4) + random.nextInt(16);
                entry.getGenMinable().generate(world,random,genX,genY,genZ);
            }
        }
    }


}


