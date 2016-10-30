package antimattermod.core.Block.TileEntity;

import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Item.ClayCruciblePattern;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Raiti-chan on 2016/10/15.
 * 粘土るつぼのたいるえんちちー
 *
 * @author Raiti-chan
 */
public class TileEntityClayCrucible extends TileEntity {
	
	private ItemStack stack;
	
	private ClayCruciblePattern.ClayCruciblePatternList modeMeta = null;
	
	private int MaxOres = 9;
	
	private int time;
	
	private ClayCrucibleState state = ClayCrucibleState.NONE;
	
	
	public TileEntityClayCrucible() {
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);
		NBTTagCompound compound = new NBTTagCompound();
		if (stack != null) stack.writeToNBT(compound);
		nbtTag.setTag("Item", compound);
		nbtTag.setInteger("time", time);
		nbtTag.setByte("mode", (byte) (modeMeta == null ? -1 : modeMeta.ordinal()));
		nbtTag.setByte("state", (byte) this.state.ordinal());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		NBTTagCompound compound = nbtTag.getCompoundTag("Item");
		stack = ItemStack.loadItemStackFromNBT(compound);
		time = nbtTag.getInteger("time");
		byte modeB = nbtTag.getByte("mode");
		modeMeta = modeB == -1 ? null : ClayCruciblePattern.ClayCruciblePatternList.values()[modeB];
		this.MaxOres = modeMeta == null ? 9 : modeMeta.stackSize;
		this.state = ClayCrucibleState.values()[nbtTag.getByte("state")];
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			switch (state) {
				case NONE://何もしていない状態
					heatingCheck();
					time = time <= 0 ? 0 : time - 1;
					break;
				case HEATING://加熱中処理
					if (time > 200 * this.stack.stackSize) {
						this.state = ClayCrucibleState.MELTED;
						worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
					} else {
						heatingCheck();
						time++;
					}
					break;
				case MELTED://溶けた後の処理
					if (time <= 0) {
						this.state = ClayCrucibleState.SOLIDIFIED;
						worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
					} else {
						coolingCheck();
						time--;
					}
					break;
				case SOLIDIFIED://固めた後の処理
					break;
			}
		}
	}
	
	private void heatingCheck() {
		if (this.stack == null || this.stack.stackSize != this.MaxOres) {
			this.state = ClayCrucibleState.NONE;
			return;
		}
		TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
		if (tileEntity instanceof TileEntityClayCrucibleHeater) {
			if (((TileEntityClayCrucibleHeater) tileEntity).isBurning()) {
				this.state = ClayCrucibleState.HEATING;
			} else {
				this.state = ClayCrucibleState.NONE;
			}
		}
		
	}
	
	private void coolingCheck() {
		TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
		if (tileEntity instanceof TileEntityClayCrucibleHeater) {
			if (((TileEntityClayCrucibleHeater) tileEntity).isBurning()) {
				time ++;
			}
		}
		
	}
	
	public int addOres(ItemStack stack) {
		if (stack.getItem() == Items.iron_ingot) {
			if (this.stack == null) {
				ItemStack addStack = stack.copy();
				addStack.stackSize = addStack.stackSize <= MaxOres ? addStack.stackSize : this.MaxOres;
				this.stack = addStack;
				return addStack.stackSize;
			} else if (this.stack.stackSize < MaxOres && stack.getItem() == this.getStack().getItem()) {
				int a = MaxOres - this.stack.stackSize;
				ItemStack addStack = stack.copy();
				addStack.stackSize = addStack.stackSize <= a ? addStack.stackSize : a;
				this.getStack().stackSize += addStack.stackSize;
				return addStack.stackSize;
			}
		}
		return 0;
	}
	
	public ItemStack setMode(ItemStack stack) {
		time = 0;//タイマーを初期化
		state = ClayCrucibleState.NONE;
		if (stack != null && stack.getItem() instanceof ClayCruciblePattern) {
			ItemStack retItemStack = modeMeta == null ? null : new ItemStack(AntiMatterModRegistry.clayCruciblePattern, 1, modeMeta.ordinal());
			this.modeMeta = ClayCruciblePattern.ClayCruciblePatternList.values()[stack.getItemDamage()];
			this.MaxOres = modeMeta.stackSize;
			if (this.stack != null && this.MaxOres < this.stack.stackSize) {//アイテムがあふれたときの処理
				ItemStack dropStack = this.stack.copy();
				dropStack.stackSize = this.stack.stackSize - MaxOres;
				this.stack.stackSize = MaxOres;
				dropItems(dropStack);
			}
			return retItemStack;
		} else {
			ItemStack retItemStack = modeMeta == null ? null : new ItemStack(AntiMatterModRegistry.clayCruciblePattern, 1, modeMeta.ordinal());
			this.modeMeta = null;
			this.MaxOres = 9;
			if (this.stack != null && this.MaxOres < this.stack.stackSize) {//アイテムがあふれたときの処理
				ItemStack dropStack = this.stack.copy();
				dropStack.stackSize = this.stack.stackSize - MaxOres;
				this.stack.stackSize = MaxOres;
				dropItems(dropStack);
			}
			return retItemStack;
		}
	}
	
	private void dropItems(ItemStack dropStack) {
		if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops") && !this.worldObj.restoringBlockSnapshots) {
			float f = 0.7F;
			double d0 = (double) (worldObj.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d1 = (double) (worldObj.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d2 = (double) (worldObj.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			EntityItem entityItem = new EntityItem(this.worldObj, (double) this.xCoord + d0, (double) this.yCoord + d1, (double) this.zCoord + d2, dropStack);
			entityItem.delayBeforeCanPickup = 10;
			worldObj.spawnEntityInWorld(entityItem);
		}
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public ClayCrucibleState getState() {
		return state;
	}
	
	public int getMaxOres() {
		return MaxOres;
	}
	
	@SuppressWarnings("WeakerAccess")
	public enum ClayCrucibleState {
		NONE,
		HEATING,
		MELTED,
		SOLIDIFIED,
		
	}
	
}
