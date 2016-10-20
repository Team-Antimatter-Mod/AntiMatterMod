package antimattermod.core.World.Structure.Tiamat;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

/**
 * Created by Raiti on 2016/10/14.
 */
public class ComponentTiamatCenterLine extends StructureComponent {
	
	private int count;
	
	private ForgeDirection direction;
	
	@SuppressWarnings("unused")
	public ComponentTiamatCenterLine(){}
	
	public ComponentTiamatCenterLine(Random random, int chunkX, int chunkZ, ForgeDirection direction, int count){
		this.count = count;
		this.boundingBox = new StructureBoundingBox(chunkX << 4, 1, chunkZ << 4, (chunkX << 4)+15,150,(chunkZ << 4)+15);
		this.direction = direction;
	}
	
	@Override
	public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
		
	}
	
	@Override
	protected void func_143012_a(NBTTagCompound p_143012_1_) {
		
	}
	
	@Override
	protected void func_143011_b(NBTTagCompound p_143011_1_) {
		
	}
	
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox boundingBox) {
		this.fillWithAir(world,boundingBox, 0, 0, 0, 15, 150, 15);
		return true;
	}
}
