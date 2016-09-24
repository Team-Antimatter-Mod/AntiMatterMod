package antimattaermod.core;

import antimattaermod.core.Energy.Transfer.BlockCable;
import antimattaermod.core.Item.IngotBase;
import antimattaermod.core.Item.ItemBlock.CableItemBlock;
import antimattaermod.core.Item.ItemBlock.MetaItemBlock;
import antimattaermod.core.Block.Ores.CrystalOreBlock;
import antimattaermod.core.Block.Ores.OreBlock;
import antimattaermod.core.Energy.Generator.Block.BlockFurnaceGenerator;
import antimattaermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator;
import antimattaermod.core.Item.StatesChecker;
import antimattaermod.core.Item.Wire;
import antimattaermod.core.Util.ItemUtil;
import antimattaermod.core.Util.MetaItemBase;
import antimattaermod.core.World.OreGenerator;
import antimattaermod.core.crafting.RecipeRemover;
import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

import static antimattaermod.core.AntiMatterModCore.proxy;

/**
 * @author C6H2Cl2
 */
public class AntiMatterModRegistry {
	
    //CreativeTab ======================================================================================================
    //鉱石
    public static final CreativeTabs tabOreBlock = new CreativeTabs("tabOreBlock") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.diamond_ore);
        }
    };
    //素材
    public final static CreativeTabs tabMaterials = new CreativeTabs("ammaterials") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Items.diamond;
        }
    };
    //機械・発電機
    public final static CreativeTabs tabMachines = new CreativeTabs("ammachines") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.furnace);
        }
    };


    //==================================================================================================================
	
    //Item  ============================================================================================================
    //素材
    public static Item materials = ItemUtil.CreateItem(new MetaItemBase(13) {
        @SuppressWarnings("unchecked")
        @Override
        public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
            if(item.getItemDamage() == 11) {
                list.add(StatCollector.translateToLocal("tile.crystal_01_11.information_1"));
                list.add(StatCollector.translateToLocal("tile.crystal_01_11.information_2"));
                list.add(ChatFormatting.RED+StatCollector.translateToLocal("tile.crystal_01_11.information.name"));
            }
        }
    },"crystal_01", "crystal/crystal_01", AntiMatterModRegistry.tabMaterials);
    public static IngotBase ingot_01 = new IngotBase("ingot_01","ingot_01",32);//public static Item ingot_01 = ItemUtil.CreateItem("ingot_01","ingot_01",32); 特に別クラスにする意味がなければで可能
    public static Wire wire = new Wire("wire","wire",3);
    
    //ツール類
    public static final Item statesChecker = new StatesChecker();
    //==================================================================================================================

    //Block  ===========================================================================================================
    //鉱石
    public static Block crystalOreBlock_1 = new CrystalOreBlock(Material.rock, "crystalOreBlock_01", "stone", "crystalore/crystaloreblock_01", AntiMatterModRegistry.tabOreBlock, 2, new float[]{5.0F,5.0F}, new byte[]{3,3}, materials, new int[]{10,12});
    public  static Block oreBlock_1 = new OreBlock(Material.rock, "oreBlock_01", "stone", "ore/oreblock_01", AntiMatterModRegistry.tabOreBlock, 3, new float[]{5.0F,5.0F,5.0F}, new byte[]{3,3,3});

    //発電機
    public static final Block furnaceGenerator = new BlockFurnaceGenerator();
    public static final Block cable = new BlockCable(Material.rock);
    //==================================================================================================================


    //preinitで行う登録処理
    static void registerPreInit(FMLPreInitializationEvent event){
        //Itemの登録
        GameRegistry.registerItem(materials, "material");
        GameRegistry.registerItem(ingot_01,"ingot_01");
        GameRegistry.registerItem(wire,"wire");
        GameRegistry.registerItem(statesChecker,"statesCheckerAP");
        //Blockの登録
        GameRegistry.registerBlock(crystalOreBlock_1, MetaItemBlock.class, "crystalOreBlock_01");
        GameRegistry.registerBlock(oreBlock_1, MetaItemBlock.class, "oreBlock_01");
        GameRegistry.registerBlock(furnaceGenerator,"furnaceGeneratorAP");
        GameRegistry.registerBlock(cable, CableItemBlock.class,"Cable");
        //Renderの登録
        proxy.registerRenderThings();
        //Recipe削除
        RecipeRemover.removeRecipe(Items.stick);
    }
    //initで行う登録処理
    static void registerInit(FMLInitializationEvent event){
        //レシピの登録
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.wire,1,0),"S","S",'S',new ItemStack(AntiMatterModRegistry.ingot_01,1,3));
        //TileEntityの登録
        GameRegistry.registerTileEntity(TileEntityFurnaceGenerator.class,"tileFurnaceGeneratorAP");
        //WorldGeneratorの登録
        GameRegistry.registerWorldGenerator(new OreGenerator(),2);
        
        
    }
    //postinitで行う処理
    static void registerPostInit(FMLPostInitializationEvent event){
        //他mod関連の操作
    }
}
