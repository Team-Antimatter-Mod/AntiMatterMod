package antimattermod.core.World.Structure.Tiamat;

import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 隕石のメインパーツ
 * <br>Created by Raiti-chan on 2016/10/14.
 *
 * @author Raiti-chan
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComponentTiamatCenter extends StructureComponent {
	
	@SuppressWarnings("unused")
	public int radius = 5;
	
	List<StructureComponent> structureComponents = new ArrayList<>();
	
	@SuppressWarnings("unused")
	public ComponentTiamatCenter() {
	}
	
	@SuppressWarnings("unused")
	ComponentTiamatCenter(Random random, int chunkX, int chunkZ) {
		
		this.boundingBox = new StructureBoundingBox(chunkX << 4, 1, chunkZ << 4, 15, 150, 15);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void buildComponent(StructureComponent component, List list, Random random) {
		ComponentTiamatCenterLine componentE = new ComponentTiamatCenterLine(random, (this.boundingBox.minX >> 4) + 1, (this.boundingBox.minZ >> 4), ForgeDirection.EAST, 1);
		((ComponentTiamatCenter) component).structureComponents.add(componentE);
		list.add(componentE);
		ComponentTiamatCenterLine componentW = new ComponentTiamatCenterLine(random, (this.boundingBox.minX >> 4) - 1, (this.boundingBox.minZ >> 4), ForgeDirection.WEST, 1);
		((ComponentTiamatCenter) component).structureComponents.add(componentW);
		list.add(componentW);
		ComponentTiamatCenterLine componentS = new ComponentTiamatCenterLine(random, (this.boundingBox.minX >> 4), (this.boundingBox.minZ >> 4) + 1, ForgeDirection.SOUTH, 1);
		((ComponentTiamatCenter) component).structureComponents.add(componentS);
		list.add(componentS);
		ComponentTiamatCenterLine componentN = new ComponentTiamatCenterLine(random, (this.boundingBox.minX >> 4), (this.boundingBox.minZ >> 4) - 1, ForgeDirection.NORTH, 1);
		((ComponentTiamatCenter) component).structureComponents.add(componentN);
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
		this.fillWithAir(world, boundingBox, 0, 0, 0, 15, 150, 15);
		world.setBlock(0, 0, 0, AntiMatterModRegistry.explosionTestBlock);
		
		int X = 0, Y = 2 << 4, Z = 0, R = 5 << 4;
		
		Y:for (int y = Y - R; y <= Y; y++) {
			for (int x = X - R; x <= X + R; x++) {
				for (int z = Z - R; z <= Z + R; z++) {
					if (y <= 0) break Y;
					double dis = ((X - x) * (X - x) + ((Z - z) * (Z - z)) + ((Y - y) * (Y - y)));
					
					if (dis < R * R && y > 0) {
						world.setBlock(x, y, z, Blocks.air, 0, 2);
					}
				}
			}
		}
		return true;
	}
	
}
