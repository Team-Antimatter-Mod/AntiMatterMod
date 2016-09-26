package antimattaermod.core.World;

import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;

/**
 * Created by Raiti on 2016/09/23.
 */
public class OreGeneratorEntry {
	
	public static final int PROPERTY_OVERWORLD = 0;
	
	public static final int PROPERTY_HELL = 1;
	
	public static final int PROPERTY_END = 2;
	
	public static final ArrayList<OreGeneratorEntry> ORE_GENERATOR_ENTRIES = new ArrayList<>();
	
	static {
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 1, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 2, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 3, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 4, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 5, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 6, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 7, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 8, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 9, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 10, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 11, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 12, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 13, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 14, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 15, 10),5,true,true,true,0,10,100,1,10,200,2,10,200));
	}
	
	
	private int loop;
	
	private boolean isGeneratOverworld;
	
	private boolean isGeneratHell;
	
	private boolean isGeneratEnd;
	
	private int minYOverworld;
	
	private int maxYOverworld;
	
	private int minYHell;
	
	private int maxYHell;
	
	private int minYEnd;
	
	private int maxYEnd;
	
	private WorldGenerator genMinable;
	
	public OreGeneratorEntry(WorldGenerator genMinable, int loop, boolean isGeneratOverworld, boolean isGeneratHell, boolean isGeneratEnd, int ... limiter){
		this.loop = loop;
		this.isGeneratOverworld = isGeneratOverworld;
		this.isGeneratHell = isGeneratHell;
		this.isGeneratEnd = isGeneratEnd;
		this.genMinable = genMinable;
		for (int i = 0; i < limiter.length;){
			switch (limiter[i++]) {
				case 0:
					this.minYOverworld = limiter[i++];
					this.maxYOverworld = limiter[i++];
					break;
				case 1:
					this.minYHell = limiter[i++];
					this.maxYHell = limiter[i++];
					break;
				case 2:
					this.minYEnd = limiter[i++];
					this.maxYEnd = limiter[i++];
					break;
			}
				
		}
		
	}
	
	
	public int getMaxYEnd() {
		return maxYEnd;
	}
	
	public int getMaxYHell() {
		return maxYHell;
	}
	
	public int getMaxYOverworld() {
		return maxYOverworld;
	}
	
	public int getMinYEnd() {
		return minYEnd;
	}
	
	public int getMinYHell() {
		return minYHell;
	}
	
	public int getMinYOverworld() {
		return minYOverworld;
	}
	
	
	public int getLoop() {
		return loop;
	}
	
	public boolean isGeneratEnd() {
		return isGeneratEnd;
	}
	
	public boolean isGeneratHell() {
		return isGeneratHell;
	}
	
	public boolean isGeneratOverworld() {
		return isGeneratOverworld;
	}
	
	public WorldGenerator getGenMinable() {
		return genMinable;
	}
}
