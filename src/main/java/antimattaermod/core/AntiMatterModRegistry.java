package antimattaermod.core;

import antimattaermod.core.Block.BlockSoil;
import antimattaermod.core.Block.ClayCrucible;
import antimattaermod.core.Block.TileEntity.TileEntityClayCrucible;
import antimattaermod.core.Energy.Generator.Block.BlockSatStove;
import antimattaermod.core.Block.Ores.BedrockOreBlock;
import antimattaermod.core.Energy.Transfer.BlockCable;
import antimattaermod.core.Energy.Transfer.TileEntityCable;
import antimattaermod.core.Item.ItemBlock.CableItemBlock;
import antimattaermod.core.Item.ItemBlock.MetaItemBlock;
import antimattaermod.core.Block.Ores.DifferentOreBlock;
import antimattaermod.core.Block.Ores.OreBlock;
import antimattaermod.core.Energy.Generator.Block.BlockFurnaceGenerator;
import antimattaermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator;
import antimattaermod.core.Item.StatesChecker;
import antimattaermod.core.Item.tool.Hammer;
import antimattaermod.core.Item.tool.Wrench;
import antimattaermod.core.Util.AddInformationfunction;
import antimattaermod.core.Util.BlockUtil;
import antimattaermod.core.Util.ItemUtil;
import antimattaermod.core.World.Ore.OreGenerator;
import antimattaermod.core.World.Structure.AMMStructureEventHandler;
import antimattaermod.core.World.Structure.Test.StructureTestStart;
import antimattaermod.core.World.Structure.Tiamat.ComponentTiamatCenter;
import antimattaermod.core.World.Structure.Tiamat.ComponentTiamatCenterLine;
import antimattaermod.core.World.Structure.Tiamat.StructureTiamatStart;
import antimattaermod.core.crafting.RecipeRemover;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import static antimattaermod.core.AntiMatterModCore.proxy;

/**
 * @author C6H2Cl2
 */
public class AntiMatterModRegistry {
	
    //CreativeTab ======================================================================================================
    //鉱石
    public static final CreativeTabs tabOreBlock = new CreativeTabs("amOreBlock") {
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
            return ingot_01;
        }
    };
    //中間素材
    public final static CreativeTabs tabImaterial = new CreativeTabs("amIntermediateMaterial") {
        @Override
        public Item getTabIconItem() {
            return motor_01;
        }
    };
    //機械・発電機
    public final static CreativeTabs tabMachines = new CreativeTabs("ammachines") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(furnaceGenerator);
        }
    };
    //ツール
    public final static CreativeTabs tabTools = new CreativeTabs("amtools") {
        @Override
        public Item getTabIconItem() {
            return hammer_01;
        }
    };
    //圧縮ブロック
    public final static CreativeTabs tabCompressedBlocks = new CreativeTabs("amcompressedblocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.iron_block);}
    };


    //==================================================================================================================
	
    //Item  ============================================================================================================
    //素材
	public static final Item ingot_01 = ItemUtil.CreateItem("ingot_01","ingot/ingot_01",17,AntiMatterModRegistry.tabMaterials, AddInformationfunction::IngotInformation);
    public static final Item crystal_01 = ItemUtil.CreateItem("crystal_01","crystal/crystal_01",7,AntiMatterModRegistry.tabMaterials);
    public static final Item powder_01 = ItemUtil.CreateItem("powder_01","powder/powder_01",1,AntiMatterModRegistry.tabMaterials);
    
    public static final Item wire = ItemUtil.CreateItem("wire_01","wire/wire_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item plate_01 = ItemUtil.CreateItem("plate_01","plate/plate_01",4,AntiMatterModRegistry.tabImaterial);
    public static final Item crystalplate_01 = ItemUtil.CreateItem("crystalplate_01","plate/crystalplate_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item industrialplate_01 = ItemUtil.CreateItem("industrialplate_01","plate/industrialplate_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item conductivematerial_01 = ItemUtil.CreateItem("conductivematerial_01","conductivematerial/conductivematerial_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item rod_01 = ItemUtil.CreateItem("rod_01","rod/rod_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item gear_01 = ItemUtil.CreateItem("gear_01","gear/gear_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item turbine_01 = ItemUtil.CreateItem("turbine_01","turbine/turbine_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item turbineblade_01 = ItemUtil.CreateItem("turbineblade_01","turbine/turbineblade_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item shaft_01 = ItemUtil.CreateItem("shaft_01","turbine/shaft_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item motor_01 = ItemUtil.CreateItem("motor_01","motor/motor_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item motorparts_01 = ItemUtil.CreateItem("motorparts_01","motor/motorparts_01",2,AntiMatterModRegistry.tabImaterial);
    public static final Item motormagnet_01 = ItemUtil.CreateItem("motormagnet_01","motormagnet/motormagnet_01",1,AntiMatterModRegistry.tabImaterial);
    public static final Item magnetizedingot_01 = ItemUtil.CreateItem("magnetizedingot_01","motormagnet/magnetizedingot_01",1,AntiMatterModRegistry.tabImaterial);




    //ツール類
    public static final Item statesChecker = new StatesChecker();
    public static final Item hammer_01 = new Hammer("IronHammer","iron_hammer",10);//耐久値10のハンマーを追加(使えるのは11回)
    public static final Item wrench_01 = new Wrench("IronWrench","iron_wrench",10);//耐久値10のレンチを追加(使えるのは11回)
    //==================================================================================================================

    //Block  ===========================================================================================================
    //鉱石
    public static final Block crystalOreBlock_1 = new DifferentOreBlock(Material.rock, "crystalOreBlock_01", "crystalore/crystaloreblock_01", AntiMatterModRegistry.tabOreBlock, 6, new float[]{5.0F,5.0F}, new byte[]{3,3}, crystal_01, new int[]{0,1});
    public static final Block bedrockCrystalOreBlock_1 = new BedrockOreBlock("bedrockCrystalOreBlock_01",crystalOreBlock_1);

    public static final Block powderOreBlock_1 = new DifferentOreBlock(Material.rock, "powderOreBlock_01", "powderore/powderoreblock_01", AntiMatterModRegistry.tabOreBlock, 1, new float[]{5.0F}, new byte[]{3}, powder_01, new int[]{0}, new int[]{3,3});
    public static final Block bedrockPowderOreBlock_1 = new BedrockOreBlock("bedrockPowderOreBlock_01",powderOreBlock_1);
    
    public static final Block oreBlock_1 = new OreBlock(Material.rock, "oreBlock_01", "ore/oreblock_01", AntiMatterModRegistry.tabOreBlock, 16, new float[]{5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F}, new byte[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3});
    public static final Block bedrockOreBlock_1 = new BedrockOreBlock("bedrockOreBlock_01",oreBlock_1);

    //発電機
    public static final Block furnaceGenerator = new BlockFurnaceGenerator();
    public static final Block cable = new BlockCable(Material.rock);

    //かまど
    public static final Block satStove = new BlockSatStove();

    //その他
    public static final Block soilBlock = new BlockSoil();

    //筐体
    public static final Block tier1_machinecasing = BlockUtil.CreateBlock("tier1_machinecasing","casing/tier1_machinecasing_01",Material.rock,AntiMatterModRegistry.tabMachines,1,5f,5f);
	
    //インゴット系圧縮ブロック
    public static final Block ingotblock_01 = BlockUtil.CreateBlock("ingotblock_01","compressedblock/ingotblock_01",Material.rock,AntiMatterModRegistry.tabCompressedBlocks,15,5f,5f);
	
	//テスト
	public static final Block clayCrucible = new ClayCrucible();


    //==================================================================================================================


    //preinitで行う登録処理
    static void registerPreInit(FMLPreInitializationEvent event){
        //Itemの登録 ===================================================================================================
            //素材
        GameRegistry.registerItem(crystal_01, "material");
        GameRegistry.registerItem(ingot_01,"ingot_01");
        GameRegistry.registerItem(powder_01,"powder_01");
            //中間素材
        GameRegistry.registerItem(wire,"wire");
        GameRegistry.registerItem(plate_01,"plate_01");
        GameRegistry.registerItem(crystalplate_01,"crystalplate_01");
        GameRegistry.registerItem(industrialplate_01,"industrialplate_01");
        GameRegistry.registerItem(conductivematerial_01,"conductivematerial_01");
        GameRegistry.registerItem(rod_01,"rod_01");
        GameRegistry.registerItem(gear_01,"gear_01");
        GameRegistry.registerItem(turbine_01,"turbine_01");
        GameRegistry.registerItem(turbineblade_01,"turbineblade_01");
        GameRegistry.registerItem(shaft_01,"shaft_01");
        GameRegistry.registerItem(motor_01,"motor_01");
        GameRegistry.registerItem(motorparts_01,"motorparts_01");
        GameRegistry.registerItem(motormagnet_01,"motormagnet_01");
        GameRegistry.registerItem(magnetizedingot_01,"magnetizedingot_01");
            //ツール
        GameRegistry.registerItem(statesChecker,"statesCheckerAP");
        GameRegistry.registerItem(hammer_01,"hammer_01");//追加
        GameRegistry.registerItem(wrench_01,"wrench_01");
        
        //Blockの登録 ==================================================================================================
            //鉱石
        GameRegistry.registerBlock(crystalOreBlock_1, MetaItemBlock.class, "crystalOreBlock_01");
        GameRegistry.registerBlock(powderOreBlock_1, MetaItemBlock.class, "powderOreBlock_01");
        GameRegistry.registerBlock(oreBlock_1, MetaItemBlock.class, "oreBlock_01");
            //岩盤鉱石
        GameRegistry.registerBlock(bedrockCrystalOreBlock_1, MetaItemBlock.class, "bedrockCrystalOreBlock_01");
        GameRegistry.registerBlock(bedrockPowderOreBlock_1, MetaItemBlock.class, "bedrockPowderOreBlock_01");
        GameRegistry.registerBlock(bedrockOreBlock_1, MetaItemBlock.class, "bedrockOreBlock_01");
            //機械
        GameRegistry.registerBlock(furnaceGenerator,"furnaceGeneratorAP");
        GameRegistry.registerBlock(cable, CableItemBlock.class,"Cable");
        GameRegistry.registerBlock(satStove,"satStove");
        GameRegistry.registerBlock(tier1_machinecasing,"tier1_machinecasing");
            //その他
        GameRegistry.registerBlock(soilBlock,"soilBlock");
        GameRegistry.registerBlock(ingotblock_01,"ingotblock_01");
            //テスト
        GameRegistry.registerBlock(clayCrucible,"clayCrucible");

	        //鉱石辞書登録
        OreDictionary.registerOre("plateIron",new ItemStack(AntiMatterModRegistry.industrialplate_01,1,0));
        OreDictionary.registerOre("craftingToolHardHammer",new ItemStack(AntiMatterModRegistry.hammer_01,1,OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("craftingToolWrench",new ItemStack(AntiMatterModRegistry.wrench_01,1,OreDictionary.WILDCARD_VALUE));
        
        
        
        //Renderの登録 =================================================================================================
        proxy.registerRenderThings();
        
        //Recipe削除 ===================================================================================================
        RecipeRemover.removeRecipe(Items.stick);
        RecipeRemover.removeShapedRecipe(new ItemStack(Items.bucket), "I I", " I ", 'I', Items.iron_ingot);
    }
    //initで行う登録処理
    static void registerInit(FMLInitializationEvent event){

        //TileEntityの登録 =============================================================================================
        GameRegistry.registerTileEntity(TileEntityFurnaceGenerator.class,"tileFurnaceGeneratorAP");
        GameRegistry.registerTileEntity(TileEntityCable.class,"tileCableAP");
        
        GameRegistry.registerTileEntity(TileEntityClayCrucible.class, "tileClayCrucible");
        
        //WorldGeneratorの登録 =========================================================================================
        GameRegistry.registerWorldGenerator(new OreGenerator(),2);
        
        //チャンク生成イベントのフック
        MinecraftForge.EVENT_BUS.register(new AMMStructureEventHandler());
    
        MapGenStructureIO.registerStructure(StructureTestStart.class, "Test");
        MapGenStructureIO.func_143031_a(StructureTestStart.ComponentTest1.class, "Test1");
        MapGenStructureIO.func_143031_a(StructureTestStart.ComponentTest2.class, "Test2");
        MapGenStructureIO.func_143031_a(StructureTestStart.ComponentTest3.class, "Test3");
        MapGenStructureIO.func_143031_a(StructureTestStart.ComponentTest4.class, "Test4");
        
        MapGenStructureIO.registerStructure(StructureTiamatStart.class, "TiamatComet");
        MapGenStructureIO.func_143031_a(ComponentTiamatCenter.class, "TiamaitCometCenter");
        MapGenStructureIO.func_143031_a(ComponentTiamatCenterLine.class, "TiamatCometCenterLine");
        
    }
    //postinitで行う処理
    static void registerPostInit(FMLPostInitializationEvent event){
        //他mod関連の操作
    }
}
