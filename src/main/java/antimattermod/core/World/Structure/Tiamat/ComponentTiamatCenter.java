package antimattermod.core.World.Structure.Tiamat;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Raiti on 2016/10/14.
 */
public class ComponentTiamatCenter extends StructureComponent {
	
	public int radius = 5;
	
	public List<StructureComponent> structureComponents = new ArrayList<>();
	
	@SuppressWarnings("unused")
	public ComponentTiamatCenter(){}
	
	public ComponentTiamatCenter(Random random, int chunkX, int chunkZ){
		
		this.boundingBox = new StructureBoundingBox(chunkX << 4, 1, chunkZ << 4, 15,150,15);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void buildComponent(StructureComponent component, List list, Random random) {
		ComponentTiamatCenterLine componentE = new ComponentTiamatCenterLine(random,(this.boundingBox.minX>>4)+1,(this.boundingBox.minZ>>4), ForgeDirection.EAST, 1);
		((ComponentTiamatCenter)component).structureComponents.add(componentE);
		list.add(componentE);
		ComponentTiamatCenterLine componentW = new ComponentTiamatCenterLine(random,(this.boundingBox.minX>>4)-1,(this.boundingBox.minZ>>4), ForgeDirection.WEST, 1);
		((ComponentTiamatCenter)component).structureComponents.add(componentW);
		list.add(componentW);
		ComponentTiamatCenterLine componentS = new ComponentTiamatCenterLine(random,(this.boundingBox.minX>>4),(this.boundingBox.minZ>>4)+1, ForgeDirection.SOUTH, 1);
		((ComponentTiamatCenter)component).structureComponents.add(componentS);
		list.add(componentS);
		ComponentTiamatCenterLine componentN = new ComponentTiamatCenterLine(random,(this.boundingBox.minX>>4),(this.boundingBox.minZ>>4)-1, ForgeDirection.NORTH, 1);
		((ComponentTiamatCenter)component).structureComponents.add(componentN);
		list.add(componentN);
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
