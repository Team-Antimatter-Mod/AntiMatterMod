package antimattermod.core.World.Structure.Tiamat;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.List;
import java.util.Random;

/**
 *
 * <br>Created by Raiti-chan on 2016/10/14.
 *
 * @author Raiti-chan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class StructureTiamatStart extends StructureStart{
	
	private int centerChunkX, centerChunkZ;
	
	@SuppressWarnings("unused")
	public StructureTiamatStart(){}
	
	@SuppressWarnings("unchecked")
	StructureTiamatStart(World world, Random random, int chunkX, int chunkZ){
		this.centerChunkX = chunkX;
		this.centerChunkZ = chunkZ;
		ComponentTiamatCenter componentCenter = new ComponentTiamatCenter(random,chunkX,chunkZ);
		this.components.add(componentCenter);
		
		componentCenter.buildComponent(componentCenter,this.components,random);
		
		List<StructureComponent> list = componentCenter.structureComponents;
		while (!list.isEmpty()){
			int k = random.nextInt(list.size());
			StructureComponent structureComponent = list.remove(k);
			structureComponent.buildComponent(componentCenter,this.components,random);
		}
		
		this.updateBoundingBox();
	}
	
}
