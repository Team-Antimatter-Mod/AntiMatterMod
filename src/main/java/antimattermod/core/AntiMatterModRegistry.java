package antimattermod.core;

import antimattermod.core.Block.BlockSoil;
import antimattermod.core.Block.ClayCrucible;
import antimattermod.core.Block.TileEntity.TileEntityClayCrucible;
import antimattermod.core.Block.BlockSatStove;
import antimattermod.core.Block.Ores.BedrockOreBlock;
import antimattermod.core.Energy.Transfer.BlockCable;
import antimattermod.core.Energy.Transfer.TileEntityCable;
import antimattermod.core.Item.ClayCruciblePattern;
import antimattermod.core.Item.ItemBlock.CableItemBlock;
import antimattermod.core.Item.ItemBlock.MetaItemBlock;
import antimattermod.core.Block.Ores.DifferentOreBlock;
import antimattermod.core.Block.Ores.OreBlock;
import antimattermod.core.Energy.Generator.Block.BlockFurnaceGenerator;
import antimattermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator;
import antimattermod.core.Item.Marmite;
import antimattermod.core.Item.StatesChecker;
import antimattermod.core.Item.tool.*;
import antimattermod.core.Util.AMMToolMaterial;
import antimattermod.core.Util.AddInformationfunction;
import antimattermod.core.Util.BlockUtil;
import antimattermod.core.Util.ItemUtil;
import antimattermod.core.World.Ore.OreGenerator;
import antimattermod.core.World.Structure.AMMStructureEventHandler;
import antimattermod.core.World.Structure.Test.StructureTestStart;
import antimattermod.core.World.Structure.Tiamat.ComponentTiamatCenter;
import antimattermod.core.World.Structure.Tiamat.ComponentTiamatCenterLine;
import antimattermod.core.World.Structure.Tiamat.StructureTiamatStart;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;

import static antimattermod.core.AntiMatterModCore.proxy;

/**
 * AMMのメインレジストリークラス
 *
 * @author C6H2Cl2
 */
@SuppressWarnings("WeakerAccess")
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
			return Item.getItemFromBlock(Blocks.iron_block);
		}
	};
	
	
	//==================================================================================================================
	
	//Item  ============================================================================================================
	//素材
	public static final Item ingot_01 = ItemUtil.CreateItem("ingot_01", "ingot/ingot_01", 17, AntiMatterModRegistry.tabMaterials, AddInformationfunction::IngotInformation);
	public static final Item crystal_01 = ItemUtil.CreateItem("crystal_01", "crystal/crystal_01", 6, AntiMatterModRegistry.tabMaterials);
	public static final Item crystal_02 = ItemUtil.CreateItem("crystal_02", "crystal/crystal_02", 1, AntiMatterModRegistry.tabMaterials);
	public static final Item powder_01 = ItemUtil.CreateItem("powder_01", "powder/powder_01", 1, AntiMatterModRegistry.tabMaterials);
	public static final Item nugget_01 = ItemUtil.CreateItem("nugget_01", "nugget/nugget_01", 16, AntiMatterModRegistry.tabMaterials);
	
	public static final Item alloyingot_01 = ItemUtil.CreateItem("alloyingot_01", "ingot/alloyingot_01", 17, AntiMatterModRegistry.tabMaterials);
	
	
	public static final Item wire = ItemUtil.CreateItem("wire_01", "wire/wire_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item plate_01 = ItemUtil.CreateItem("plate_01", "plate/plate_01", 8, AntiMatterModRegistry.tabImaterial);
	public static final Item crystalplate_02 = ItemUtil.CreateItem("crystalplate_02", "plate/crystalplate_02", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item industrialplate_01 = ItemUtil.CreateItem("industrialplate_01", "plate/industrialplate_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item conductivematerial_01 = ItemUtil.CreateItem("conductivematerial_01", "conductivematerial/conductivematerial_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item stick_01 = ItemUtil.CreateItem("stick_01", "stick/stick_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item industrialstick_01 = ItemUtil.CreateItem("industrialstick_01", "stick/industrialstick_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item gear_01 = ItemUtil.CreateItem("gear_01", "gear/gear_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item turbine_01 = ItemUtil.CreateItem("turbine_01", "turbine/turbine_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item turbineblade_01 = ItemUtil.CreateItem("turbineblade_01", "turbine/turbineblade_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item shaft_01 = ItemUtil.CreateItem("shaft_01", "turbine/shaft_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item motor_01 = ItemUtil.CreateItem("motor_01", "motor/motor_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item motorparts_01 = ItemUtil.CreateItem("motorparts_01", "motor/motorparts_01", 2, AntiMatterModRegistry.tabImaterial);
	public static final Item motormagnet_01 = ItemUtil.CreateItem("motormagnet_01", "motormagnet/motormagnet_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item magnetizedingot_01 = ItemUtil.CreateItem("magnetizedingot_01", "motormagnet/magnetizedingot_01", 1, AntiMatterModRegistry.tabImaterial);
	public static final Item screw_01 = ItemUtil.CreateItem("screw_01", "screw/screw_01", 1, AntiMatterModRegistry.tabImaterial);
	
	//粘土るつぼのパターンアイテム
	public static final Item clayCruciblePattern = new ClayCruciblePattern();
	
	//食料
	public static final ItemFood marmite = new Marmite("marmite");
	
	
	//ツール類
	public static final Item statesChecker = new StatesChecker();
	public static final Item hammer_01 = new Hammer("IronHammer", "iron_hammer", AMMToolMaterial.IRON);//耐久値10のハンマーを追加(使えるのは11回)
	public static final Item wrench_01 = new Wrench("IronWrench", "iron_wrench", AMMToolMaterial.IRON);//耐久値10のレンチを追加(使えるのは11回)
	public static final Item wirecutter_01 = new WireCutter("IronWirecutter", "iron_wirecutter", AMMToolMaterial.IRON);//耐久値10のワイヤーカッターを追加(使えるのは11回)
	public static final Item file_01 = new File("IronFile", "iron_file", AMMToolMaterial.IRON);//耐久値10の鑢（ヤスリ）を追加(使えるのは11回)
	public static final Item screwdriver_01 = new ScrewDriver("IronScrewDriver", "iron_screwdriver", AMMToolMaterial.IRON);//耐久値10のドライバーを追加(使えるのは11回)
	
	//マイニングハンマー
	public static final Item miningHammer_01 = new MiningHammer("IronMiningHammer", "iron_mininghammer", AMMToolMaterial.IRON, 1);//鉄素材のハンマー3×3を追加
	public static final Item miningHammer_02 = new MiningHammer("DiamondMiningHammer","diamond_mininghammer", AMMToolMaterial.DIAMOND,1);//ダイヤ素材のハンマー3×3
	public static final Item miningHammer_03 = new MiningHammer("RaitiumMiningHammer","raitium_mininghammer", AMMToolMaterial.Raitium,1);//ライチウム素材のハンマー3×3
	public static final Item miningHammer_04 = new MiningHammer("DrantiumMiningHammer","drantium_mininghammer", AMMToolMaterial.Drantium,1);
	public static final Item miningHammer_05 = new MiningHammer("PalaziriteMiningHammer","palazirite_mininghammer", AMMToolMaterial.Palazirite,1);
	//==================================================================================================================
	
	//Block  ===========================================================================================================
	//鉱石
	public static final Block crystalOreBlock_1 = new DifferentOreBlock(Material.rock, "crystalOreBlock_01", "crystalore/crystaloreblock_01", AntiMatterModRegistry.tabOreBlock, 6, new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F}, new byte[]{3, 3, 3, 3, 3, 3}, crystal_01, new int[]{0, 1, 2, 3, 4, 5});
	public static final Block bedrockCrystalOreBlock_1 = new BedrockOreBlock("bedrockCrystalOreBlock_01", crystalOreBlock_1);
	
	public static final Block crystalOreBlock_2 = new DifferentOreBlock(Material.rock, "crystalOreBlock_02", "crystalore/crystaloreblock_02", AntiMatterModRegistry.tabOreBlock, 1, new float[]{5.0F}, new byte[]{3}, crystal_02, new int[]{0});
	public static final Block bedrockCrystalOreBlock_2 = new BedrockOreBlock("bedrockCrystalOreBlock_02", crystalOreBlock_2);
	
	public static final Block powderOreBlock_1 = new DifferentOreBlock(Material.rock, "powderOreBlock_01", "powderore/powderoreblock_01", AntiMatterModRegistry.tabOreBlock, 1, new float[]{5.0F}, new byte[]{3}, powder_01, new int[]{0}, new int[]{3, 3});
	public static final Block bedrockPowderOreBlock_1 = new BedrockOreBlock("bedrockPowderOreBlock_01", powderOreBlock_1);
	
	public static final Block oreBlock_1 = new OreBlock(Material.rock, "oreBlock_01", "ore/oreblock_01", AntiMatterModRegistry.tabOreBlock, 16, new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F}, new byte[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3});
	public static final Block bedrockOreBlock_1 = new BedrockOreBlock("bedrockOreBlock_01", oreBlock_1);
	
	//発電機
	public static final Block furnaceGenerator = new BlockFurnaceGenerator();
	public static final Block cable = new BlockCable(Material.rock);
	
	//かまど
	public static final Block satStove = new BlockSatStove();
	
	//その他
	public static final Block soilBlock = new BlockSoil();
	
	//筐体
	public static final Block tier1_machinecasing = BlockUtil.CreateBlock("tier1_machinecasing", "casing/tier1_machinecasing_01", Material.rock, AntiMatterModRegistry.tabMachines, 1, 5f, 5f);
	public static final Block tier1_machinehull = BlockUtil.CreateBlock("tier1_machinehull", "casing/tier1_machinehull_01", Material.rock, AntiMatterModRegistry.tabMachines, 1, 5f, 5f);
	
	//インゴット系圧縮ブロック
	public static final Block tiamiteblock = BlockUtil.CreateBlock("tiamiteblock", "compressedblock/tiamiteblock", Material.rock, AntiMatterModRegistry.tabCompressedBlocks, 1, 5f, 5f);
	public static final Block ingotblock_01 = BlockUtil.CreateBlock("ingotblock_01", "compressedblock/ingotblock_01", Material.rock, AntiMatterModRegistry.tabCompressedBlocks, 16, 5f, 5f);
	public static final Block crystalblock_01 = BlockUtil.CreateBlock("crystalblock_01", "compressedblock/crystalblock_01", Material.rock, AntiMatterModRegistry.tabCompressedBlocks, 6, 5f, 5f);
	public static final Block crystalblock_02 = BlockUtil.CreateBlock("crystalblock_02", "compressedblock/crystalblock_02", Material.rock, AntiMatterModRegistry.tabCompressedBlocks, 1, 5f, 5f);
	public static final Block powderblock_01 = BlockUtil.CreateBlock("powderblock_01", "compressedblock/powderblock_01", Material.rock, AntiMatterModRegistry.tabCompressedBlocks, 1, 5f, 5f);
	
	//テスト
	public static final Block clayCrucible = new ClayCrucible();
	
	
	//==================================================================================================================
	
	
	//preinitで行う登録処理
	static void registerPreInit(@SuppressWarnings("UnusedParameters") FMLPreInitializationEvent event) {
		//Itemの登録 ===================================================================================================
		//素材
		GameRegistry.registerItem(crystal_01, "crystal_01");
		GameRegistry.registerItem(crystal_02, "crystal_02");
		GameRegistry.registerItem(ingot_01, "ingot_01");
		GameRegistry.registerItem(powder_01, "powder_01");
		GameRegistry.registerItem(nugget_01, "nugget_01");
		
		GameRegistry.registerItem(alloyingot_01, "alloyingot_01");
		//中間素材
		GameRegistry.registerItem(wire, "wire");
		GameRegistry.registerItem(plate_01, "plate_01");
		GameRegistry.registerItem(crystalplate_02, "crystalplate_02");
		GameRegistry.registerItem(industrialplate_01, "industrialplate_01");
		GameRegistry.registerItem(conductivematerial_01, "conductivematerial_01");
		GameRegistry.registerItem(stick_01, "stick_01");
		GameRegistry.registerItem(industrialstick_01, "industrialstick_01");
		GameRegistry.registerItem(gear_01, "gear_01");
		GameRegistry.registerItem(turbine_01, "turbine_01");
		GameRegistry.registerItem(turbineblade_01, "turbineblade_01");
		GameRegistry.registerItem(shaft_01, "shaft_01");
		GameRegistry.registerItem(motor_01, "motor_01");
		GameRegistry.registerItem(motorparts_01, "motorparts_01");
		GameRegistry.registerItem(motormagnet_01, "motormagnet_01");
		GameRegistry.registerItem(magnetizedingot_01, "magnetizedingot_01");
		GameRegistry.registerItem(screw_01, "screw_01");
		//粘土るつぼのパターンアイテム
		GameRegistry.registerItem(clayCruciblePattern,"clayCruciblePattern");
		//食料
		GameRegistry.registerItem(marmite, "marmite");
		//ツール
		GameRegistry.registerItem(statesChecker, "statesCheckerAP");
		GameRegistry.registerItem(hammer_01, "hammer_01");//追加
		GameRegistry.registerItem(wrench_01, "wrench_01");
		GameRegistry.registerItem(wirecutter_01, "wirecutter_01");
		GameRegistry.registerItem(file_01, "file_01");
		GameRegistry.registerItem(screwdriver_01, "screwdriver_01");
		//マイニングハンマー
		GameRegistry.registerItem(miningHammer_01,"miningHammer_01");
		GameRegistry.registerItem(miningHammer_02,"miningHammer_02");
		GameRegistry.registerItem(miningHammer_03,"miningHammer_03");
		GameRegistry.registerItem(miningHammer_04,"miningHammer_04");
		GameRegistry.registerItem(miningHammer_05,"miningHammer_05");

		//Blockの登録 ==================================================================================================
		//鉱石
		GameRegistry.registerBlock(crystalOreBlock_1, MetaItemBlock.class, "crystalOreBlock_01");
		GameRegistry.registerBlock(crystalOreBlock_2, MetaItemBlock.class, "crystalOreBlock_02");
		GameRegistry.registerBlock(powderOreBlock_1, MetaItemBlock.class, "powderOreBlock_01");
		GameRegistry.registerBlock(oreBlock_1, MetaItemBlock.class, "oreBlock_01");
		//岩盤鉱石
		GameRegistry.registerBlock(bedrockCrystalOreBlock_1, MetaItemBlock.class, "bedrockCrystalOreBlock_01");
		GameRegistry.registerBlock(bedrockCrystalOreBlock_2, MetaItemBlock.class, "bedrockCrystalOreBlock_02");
		GameRegistry.registerBlock(bedrockPowderOreBlock_1, MetaItemBlock.class, "bedrockPowderOreBlock_01");
		GameRegistry.registerBlock(bedrockOreBlock_1, MetaItemBlock.class, "bedrockOreBlock_01");
		//機械
		GameRegistry.registerBlock(furnaceGenerator, "furnaceGeneratorAP");
		GameRegistry.registerBlock(cable, CableItemBlock.class, "Cable");
		GameRegistry.registerBlock(satStove, "satStove");
		GameRegistry.registerBlock(tier1_machinecasing, "tier1_machinecasing");
		GameRegistry.registerBlock(tier1_machinehull, "tier1_machinehull");
		//その他
		GameRegistry.registerBlock(soilBlock, "soilBlock");
		//鉱石ブロック
		GameRegistry.registerBlock(tiamiteblock, MetaItemBlock.class, "tiamiteblock");
		GameRegistry.registerBlock(ingotblock_01, MetaItemBlock.class, "ingotblock_01");
		GameRegistry.registerBlock(crystalblock_01, MetaItemBlock.class, "crystalblock_01");
		GameRegistry.registerBlock(crystalblock_02, MetaItemBlock.class, "crystalblock_02");
		GameRegistry.registerBlock(powderblock_01, MetaItemBlock.class, "powderblock_01");
		//テスト
		GameRegistry.registerBlock(clayCrucible, "clayCrucible");
		
		
		//Renderの登録 =================================================================================================
		proxy.registerRenderThings();
		
		
	}
	
	//initで行う登録処理
	static void registerInit(@SuppressWarnings("UnusedParameters") FMLInitializationEvent event) {
		
		//TileEntityの登録 =============================================================================================
		GameRegistry.registerTileEntity(TileEntityFurnaceGenerator.class, "tileFurnaceGeneratorAP");
		GameRegistry.registerTileEntity(TileEntityCable.class, "tileCableAP");
		
		GameRegistry.registerTileEntity(TileEntityClayCrucible.class, "tileClayCrucible");
		
		//WorldGeneratorの登録 =========================================================================================
		GameRegistry.registerWorldGenerator(new OreGenerator(), 2);
		
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
	static void registerPostInit(@SuppressWarnings("UnusedParameters") FMLPostInitializationEvent event) {
		//他mod関連の操作
	}
}
