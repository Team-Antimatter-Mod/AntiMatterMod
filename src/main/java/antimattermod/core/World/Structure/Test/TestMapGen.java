package antimattermod.core.World.Structure.Test;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

/**
 * Created by Raiti on 2016/10/12.
 */
public class TestMapGen extends MapGenStructure {
	
	
	@Override
	public String func_143025_a() {
		return "TestStructure";
	}
	
	@Override
	protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_) {
		return p_75047_1_ == 0 & p_75047_2_ == 0 ? true : false;
	}
	
	@Override
	protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) {
		return new StructureTestStart(this.worldObj, this.rand, p_75049_1_,p_75049_2_);
	}
}
