package antimattaermod.core.World.Structure.Tiamat;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

/**
 * Created by Raiti on 2016/10/14.
 * ティアマト彗星生成判定
 */
@SuppressWarnings("unused")
public class TiamatMapGen extends MapGenStructure {
	
	@Override
	public String func_143025_a() {
		return "TiamatComet";
	}
	
	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		return chunkX == 0 & chunkZ == 0;
	}
	
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new StructureTiamatStart(this.worldObj, this.rand, chunkX, chunkZ);
	}
	
}
