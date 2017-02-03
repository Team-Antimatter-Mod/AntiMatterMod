package antimattermod.core

import antimattermod.core.Energy.Block.EnergyControllerBlock
import antimattermod.core.Energy.Block.Generator.BlockFurnaceGenerator
import antimattermod.core.Energy.TileEntity.Generator.TileEntityFurnaceGenerator
import antimattermod.core.Energy.TileEntity.TileEnergyController
import antimattermod.core.Energy.Item.StatesChecker
import antimattermod.core.Energy.Item.Wrench.ItemWrench
import antimattermod.core.Energy.MultiBlock.BlockMultiController
import antimattermod.core.Energy.MultiBlock.TileMultiController
import antimattermod.core.Fluid.tank.BlockBasicTank
import antimattermod.core.Fluid.tank.ItemBlockBasicTank
import antimattermod.core.Fluid.tank.TileBasicTank
import antimattermod.core.Mob.EntityDeveloperBoss
import antimattermod.core.Mob.ItemEgg.ItemDeveloperBossEgg
import antimattermod.core.Util.AddInformationfunction
import antimattermod.core.World.Chunk.AMMChunkManager
import antimattermod.core.World.Chunk.BlockChunkLoader
import cpw.mods.fml.common.registry.EntityRegistry
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import cpw.mods.fml.common.network.NetworkRegistry
import net.minecraftforge.common.ForgeChunkManager


/**
 * @author C6H2Cl2
 */
object AMMRegistry {
    //Item  ============================================================================================================
    //ツール類
    val statesChecker: Item = StatesChecker()
    val toolWrench: Item = ItemWrench("toolWrench", "toolwrench", AddInformationfunction { item, player, list, isdebug -> antimattermod.core.Util.AddInformationfunction.WrenchInformation(item, player, list, isdebug) })

    //テスト用
    val developerBossEgg: Item = ItemDeveloperBossEgg()

    //Block  ===========================================================================================================
    //発電機
    val furnaceGenerator: Block = BlockFurnaceGenerator()
    //エネルギー制御
    val energyController = Array<Block>(15, ::EnergyControllerBlock)

    val multiController = BlockMultiController()
    val chunkLoader = BlockChunkLoader()
    val basicTank = BlockBasicTank()

    fun handlePreinit() {
        //Itemの登録 ===================================================================================================
        //ツール
        GameRegistry.registerItem(statesChecker, "statesCheckerAP")
        GameRegistry.registerItem(developerBossEgg, "developerBossEgg")
        GameRegistry.registerItem(toolWrench, "toolWrench")

        //機械
        GameRegistry.registerBlock(furnaceGenerator, "furnaceGeneratorAP")
        //GameRegistry.registerBlock(energyController, "energyControllerAP")
        energyController.forEach {
            GameRegistry.registerBlock(it, it.unlocalizedName)
        }
        GameRegistry.registerBlock(multiController, "multiController")
        GameRegistry.registerBlock(chunkLoader, "AMMChunkLoader")
        GameRegistry.registerBlock(basicTank, ItemBlockBasicTank::class.java, "AMMBasicTank")

        //ChunkManagerの登録 ===========================================================================================
        ForgeChunkManager.setForcedChunkLoadingCallback(AntiMatterModCore.INSTANCE, AMMChunkManager())
    }

    fun handleInit() {
        //TileEntityの登録 =============================================================================================
        GameRegistry.registerTileEntity(TileEntityFurnaceGenerator::class.java, "tileFurnaceGeneratorAP")
        GameRegistry.registerTileEntity(TileEnergyController::class.java, "tileEnergyControllerAP")
        GameRegistry.registerTileEntity(TileMultiController::class.java, "tileMultiController")
        GameRegistry.registerTileEntity(TileBasicTank::class.java, "tileBasicTank")

        //Entityの登録 =============================================================================================
        EntityRegistry.registerModEntity(EntityDeveloperBoss::class.java, "DeveloperBoss", 1, AntiMatterModCore.MOD_ID, 250, 1, false)

        NetworkRegistry.INSTANCE.registerGuiHandler(AntiMatterModCore.INSTANCE, AMMGuiHandler())

    }
}