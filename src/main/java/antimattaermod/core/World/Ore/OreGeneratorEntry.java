package antimattaermod.core.World.Ore;

import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Raiti-chan on 2016/09/23.
 * 鉱石生成のエントリー
 * @author Raiti-chan
 */
public class OreGeneratorEntry {
	
	public static final int PROPERTY_OVERWORLD = 0;
	
	public static final int PROPERTY_HELL = 1;
	
	public static final int PROPERTY_END = 2;
	
	public static final ArrayList<OreGeneratorEntry> ORE_GENERATOR_ENTRIES = new ArrayList<>();
	
	static {
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 0, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 1, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 2, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 3, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 4, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 5, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 6, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 7, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 8, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 9, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 10, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 11, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 12, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 13, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 14, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.oreBlock_1, 15, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new BedrockGenerator(),1,true,false,false,0,4,5));

		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.crystalOreBlock_1, 0, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.crystalOreBlock_1, 1, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.crystalOreBlock_1, 2, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.crystalOreBlock_1, 3, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.crystalOreBlock_1, 4, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));
		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.crystalOreBlock_1, 5, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));

		ORE_GENERATOR_ENTRIES.add(new OreGeneratorEntry(new WorldGenOres(AntiMatterModRegistry.powderOreBlock_1, 0, 10),5,true,true,true,PROPERTY_OVERWORLD,10,100,PROPERTY_HELL,10,200,PROPERTY_END,10,200));


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
	
	/**
	 * 鉱石生成システムのエントリー<br>
	 * 生成高度配列の指定方法は
	 * PROPERTY_OVERWORLD,10,25 -- 通常ワールドに10～25の間に生成
	 * PROPERTY_HELL,150,200    -- ネザーに150～200の間に生成
	 * これを1列にしたもの
	 * @param genMinable ジェネレーターアルゴリズムクラス
	 * @param loop 1チャンク内にジェネレートを実行する回数
	 * @param isGeneratOverworld 通常世界に生成するか
	 * @param isGeneratHell ネザーに生成するか
	 * @param isGeneratEnd エンドに生成するか
	 * @param limiter 生成高度配列
	 */
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
	
	private GenerateFunction[] generateFunctions;
	
	/**
	 * じぇねれーとするときの処理を書き換えます
	 * @param function 書き換えた関数
	 * @param target 書き換えるディメンションプロぱていー
	 * @return
	 */
	public OreGeneratorEntry addGeneratFunction(GenerateFunction function, int ... target){
		if (this.generateFunctions == null) this.generateFunctions = new GenerateFunction[3];
		if(target[0] == -1){
			this.generateFunctions[0] = function;
			this.generateFunctions[1] = function;
			this.generateFunctions[2] = function;
		}
		for (int i : target){
			if(this.generateFunctions.length < i) continue;
			this.generateFunctions[i] = function;
		}
		
		
		return this;
	}
	
	/**
	 * 生成処理を持っているか
	 * @return 持っていたらtrue
	 */
	public boolean hasGenerateFunction(){
		return generateFunctions == null ? false : true;
	}
	
	/**
	 * 生成処理関数の取得
	 * @param index ディメンション
	 * @return 生成関数
	 */
	public GenerateFunction getGenerateFunction(int index){
		if(generateFunctions.length < index) return null;
		return this.generateFunctions[index];
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
	
	/**
	 * 生成関数
	 * @author Raiti-chan
	 */
	@FunctionalInterface
	public interface GenerateFunction{
		void oreGenerate(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider);
	}
}
