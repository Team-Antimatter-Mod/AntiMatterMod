package antimattaermod.core.Block.TileEntity;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Raiti on 2016/10/15.
 */
public class TileEntityClayCrucible extends TileEntity {
	
	private ItemStack oreBlock;
	
	private static final int MaxOres = 6;
	
	private int time;
	
	private boolean isHeated = false;
	
	public TileEntityClayCrucible(){
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);
		NBTTagCompound compound = new NBTTagCompound();
		if(oreBlock != null)oreBlock.writeToNBT(compound);
		nbtTag.setTag("Item",compound);
		nbtTag.setInteger("time",time);
		nbtTag.setBoolean("isHeated",isHeated);
		nbtTag.setString("TileEntity","ClayCrucible");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		NBTTagCompound compound = nbtTag.getCompoundTag("Item");
		oreBlock = ItemStack.loadItemStackFromNBT(compound);
		isHeated = nbtTag.getBoolean("isHeated");
		time = nbtTag.getInteger("time");
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,1,tagCompound);
	}
	
	@Override
	public void updateEntity() {
		if(isHeated){
			time ++;
			if (time > 200){
				getWorldObj().setBlock(xCoord,yCoord,zCoord, Blocks.iron_block);
			}
		}else {
			time --;
		}
	}
	
	public int addOres(ItemStack stack){
		if(stack.getItem() == Items.iron_ingot){
			if(oreBlock == null){
				ItemStack addStack = stack.copy();
				addStack.stackSize = addStack.stackSize <= MaxOres ? addStack.stackSize:6;
				this.oreBlock = addStack;
				return addStack.stackSize;
			}else if (oreBlock.stackSize < MaxOres){
				int a = MaxOres - oreBlock.stackSize;
				ItemStack addStack = stack.copy();
			}
		}
		return 0;
	}
	
	
	public ItemStack getOreBlock(){
		return oreBlock;
	}
}
