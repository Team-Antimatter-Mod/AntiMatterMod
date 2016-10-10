package antimattaermod.core.Energy.Generator.Block;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import antimattaermod.core.Energy.APVoltage;
import antimattaermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator;
import antimattaermod.core.Energy.IAPGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author C6H2Cl2
 */
public class BlockFurnaceGenerator extends BlockContainer implements IAPGenerator{
    //定数
    private APVoltage voltage = APVoltage.HV;
    private int energyStorage = voltage.getMaxEnergy() * 20 * 600;
    
    //ブロックテクスチャ―
    @SideOnly(Side.CLIENT)
    private IIcon Front_OFF;
    @SideOnly(Side.CLIENT)
    private IIcon Front_ON;
    @SideOnly(Side.CLIENT)
    private IIcon AnotherIcon;
    
    public BlockFurnaceGenerator() {
        super(Material.rock);
        //他modとの競合回避でAPつけた
        setBlockName("furnaceGeneratorAP");
        setHardness(50f);
        setResistance(50f);
        setHarvestLevel("pickaxe",3);
        setCreativeTab(AntiMatterModRegistry.tabMachines);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(player.getHeldItem() == null){
            return false;
        }
        ItemStack heldItem = player.getHeldItem();
        int fuelVal = GameRegistry.getFuelValue(heldItem);
        if(fuelVal == 0){
            fuelVal = TileEntityFurnace.getItemBurnTime(heldItem);
        }
        if(fuelVal < 1600){
            return false;
        }
        TileEntityFurnaceGenerator tileEntity = (TileEntityFurnaceGenerator)world.getTileEntity(x,y,z);
        if(tileEntity.isFuelMax()){
            return false;
        }
        int stackSize = heldItem.stackSize;
        for (int i=0;i<stackSize;++i){
            float remainder = tileEntity.addFuel(fuelVal);
            heldItem.stackSize --;
            if(remainder > 0){
                break;
            }
        }
        
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.Front_OFF = register.registerIcon(AntiMatterModCore.MOD_ID+":machine/tier1_furnacegenerator_off");
        this.Front_ON = register.registerIcon(AntiMatterModCore.MOD_ID+":machine/tier1_furnacegenerator_on");
        this.AnotherIcon = register.registerIcon(AntiMatterModCore.MOD_ID+":machine/tier1_casing");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == ForgeDirection.SOUTH.ordinal() ? this.Front_OFF : this.AnotherIcon;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityFurnaceGenerator fg = (TileEntityFurnaceGenerator) world.getTileEntity(x,y,z);
        return side == world.getBlockMetadata(x,y,z) ? fg.getFuelValue() > 0 ? this.Front_ON : this.Front_OFF : this.AnotherIcon;
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        
        int playerDir = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        ForgeDirection[] blockDir = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
        world.setBlockMetadataWithNotify(x, y, z, blockDir[playerDir].ordinal(), 2);
    }
    
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityFurnaceGenerator();
    }

    @Override
    public int getMaxStoreEnergy() {
        return energyStorage;
    }

    @Override
    public boolean canSendEnergy() {
        return true;
    }

    @Override
    public boolean canReceiveEnergy() {
        return false;
    }

    @Override
    public APVoltage getSendVoltage() {
        return voltage;
    }

    @Override
    public APVoltage getReceiveVoltage() {
        return APVoltage.ZeroVoltage;
    }

    @Override
    public int getStoredEnergy() {
        return 0;
    }

    //BlockからIAPGeneratorのメソッドは呼ばないこと！
    @Override
    public float getMaxFuelValue() {
        return 0;
    }

    @Override
    public float getFuelValue() {
        return 0;
    }

    @Override
    public int getCurrentGenerate() {
        return 0;
    }

    @Override
    public boolean isFuelMax() {
        return false;
    }
}
