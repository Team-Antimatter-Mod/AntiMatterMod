package antimattermod.core.Block.TileEntity

import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Block.ClayCrucibleHeater
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * Created by Raiti-chan on 2016/10/27.
 * 粘土るつぼヒーターのタイルえんちちー
 * @author Raiti-chan
 */
class TileEntityClayCrucibleHeater : TileEntity() {
	
	//変数==============================================================================================================
	//燃料のItemStack
	private var stack: ItemStack = ItemStack(Items.coal, 0)
	//燃料の木炭の数
	private var charcoalSize = 0
	//燃焼可能時間
	private var time = 0
	//燃焼中
	var isBurning: Boolean = false
		private set
	
	init {
		
	}
	
	
	//tick処理
	override fun updateEntity() {
		when (time) {
			0 -> {
				if (stack.stackSize < 1 || this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) != AntiMatterModRegistry.clayCrucible) return
				stack.stackSize--
				charcoalSize = if (charcoalSize > 0) --charcoalSize else 0
				time += 1600
				isBurning = true
				if(!this.worldObj.isRemote){
					this.worldObj.markBlockForUpdate(this.xCoord,this.yCoord,this.zCoord)
				}
				val meta = worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)
				worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, if (meta < 6) meta + 6 else meta, 2)
			}
			1 -> {
				if (stack.stackSize > 0 && this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == AntiMatterModRegistry.clayCrucible) {
					stack.stackSize--
					charcoalSize = if (charcoalSize > 0) --charcoalSize else 0
					time += 160
					--time
				} else {
					--time
					isBurning = false
					if(!this.worldObj.isRemote){
						this.worldObj.markBlockForUpdate(this.xCoord,this.yCoord,this.zCoord)
					}
					val meta: Int = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)
					this.getWorldObj().setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, if (meta > 5) meta - 6 else meta, 2)
				}
			}
			else -> --time
		}
		if(isBurning){
			if (this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == Blocks.air) {
				this.worldObj.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, Blocks.fire)
			}
		}else {
			if (this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == Blocks.fire){
				this.worldObj.setBlock(this.xCoord, this.yCoord + 1, this.zCoord, Blocks.air)
			}
		}
	}
	
	//NBTへの書き込み
	override fun writeToNBT(compound: NBTTagCompound?) {
		super.writeToNBT(compound)
		compound!!.setInteger("time", this.time)                    //燃焼時間の書き込み
		compound.setInteger("fuel", this.stack.stackSize)        //燃料の数の書き込み
		compound.setInteger("charcoalSize", this.charcoalSize)    //燃料の木炭の数の書き込み
	}
	
	//NBTの読み込み
	override fun readFromNBT(compound: NBTTagCompound?) {
		super.readFromNBT(compound)
		this.time = compound!!.getInteger("time")
		this.stack.stackSize = compound.getInteger("fuel")
		this.charcoalSize = compound.getInteger("charcoalSize")
		this.isBurning = this.time > 0
	}
	
	//読み込みパケット
	override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
		this.readFromNBT(pkt!!.func_148857_g())
	}
	
	//書き込みパケット
	override fun getDescriptionPacket(): Packet {
		val tagCompound = NBTTagCompound()
		writeToNBT(tagCompound)
		return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound)
	}
	
	/**
	 * 燃料の追加
	 * @param addStack 追加する燃料のItemStack
	 */
	fun addFuel(addStack: ItemStack) {
		val maxAddFuel = Companion.FUEL_MAX - this.stack.stackSize
		val addFuelStack: Int
		//燃料の追加
		if (addStack.stackSize > maxAddFuel) {
			//入れたスタックのサイズが挿入可能サイズを超えていた場合
			addFuelStack = maxAddFuel
			addStack.stackSize -= addFuelStack
			this.stack.stackSize += addFuelStack
		} else {
			//入れたスタックのサイズが挿入可能サイズ以下だった場合
			addFuelStack = addStack.stackSize
			addStack.stackSize -= addFuelStack
			this.stack.stackSize += addFuelStack
		}
		//木炭だった場合の処理
		if (addStack.itemDamage == 1) {
			this.charcoalSize += addFuelStack
		}
		
	}
	
	//ブロックが破壊されたときに内部保持のアイテムをドロップさせます
	fun dropItems(block: ClayCrucibleHeater, world: World, x: Int, y: Int, z: Int) {
		if (this.charcoalSize > 0) {
			block.dropBlockAsItem(world, x, y, z, ItemStack(Items.coal, charcoalSize, 1))
			this.stack.stackSize -= charcoalSize
			charcoalSize = 0
		}
		if (this.stack.stackSize > 0) {
			block.dropBlockAsItem(world, x, y, z, this.stack.copy())
			this.stack.stackSize = 0
		}
	}
	
	//内部保持アイテムを搬出します
	fun removeFuel(): ItemStack? {
		if (this.stack.stackSize < 1) return null
		if (this.charcoalSize > 0) {
			val retStack = ItemStack(Items.coal, charcoalSize, 1)
			this.stack.stackSize -= charcoalSize
			this.charcoalSize = 0
			return retStack
		} else {
			val retStack = this.stack.copy()
			this.stack.stackSize -= retStack.stackSize
			return retStack
		}
	}
	
	companion object {
		//定数==============================================================================================================
		//最大燃料
		@JvmStatic val FUEL_MAX = 64
	}
	
	
}
