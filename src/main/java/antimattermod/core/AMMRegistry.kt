@file:JvmName("AMMRegistry")
@file:Suppress("INTERFACE_STATIC_METHOD_CALL_FROM_JAVA6_TARGET")

package antimattermod.core

import antimattermod.core.Energy.Block.EnergyControllerBlock
import antimattermod.core.Energy.Block.Generator.BlockFurnaceGenerator
import antimattermod.core.Energy.Filler.BlockFiller
import antimattermod.core.Energy.Filler.BlockMarker
import antimattermod.core.Energy.Filler.ModePattern.*
import antimattermod.core.Energy.Item.StatesChecker
import antimattermod.core.Energy.Item.Wrench.ItemWrench
import antimattermod.core.Energy.MachineTier.Tier1
import antimattermod.core.Energy.MultiBlock.BlockMultiController
import antimattermod.core.Fluid.tank.BlockBasicTank
import antimattermod.core.Mob.EntityDeveloperBoss
import antimattermod.core.Mob.ItemEgg.ItemDeveloperBossEgg
import antimattermod.core.Util.AddInformationfunction
import antimattermod.core.Util.AddInformationfunction.WrenchInformation
import antimattermod.core.World.Chunk.AMMChunkManager
import antimattermod.core.World.Chunk.BlockChunkLoader
import antimattermod.core.Energy.Filler.ModePattern.FillerModePattern.Companion.FillerRegistry
import c6h2cl2.YukariLib.Util.RegisterHandler
import cpw.mods.fml.common.registry.EntityRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import cpw.mods.fml.common.network.NetworkRegistry
import net.minecraftforge.common.ForgeChunkManager
import java.util.*


/**
 * @author C6H2Cl2
 */
object AMMRegistry {
    //Item  ============================================================================================================
    //ツール類
    val statesChecker: Item = StatesChecker()
    val toolWrench: Item = ItemWrench("toolWrench", "toolwrench", AddInformationfunction { item, player, list, isdebug -> WrenchInformation(item, player, list, isdebug) })
    val fillerPattern: Item = ItemModePattern()

    //テスト用
    val developerBossEgg: Item = ItemDeveloperBossEgg()

    //Block  ===========================================================================================================
    //発電機
    val furnaceGenerator: Block = BlockFurnaceGenerator(Tier1)
    //エネルギー制御
    val energyController = Array<Block>(15, ::EnergyControllerBlock)

    val marker = BlockMarker()
    val filler = BlockFiller()
    val multiController = BlockMultiController()
    val chunkLoader = BlockChunkLoader()
    val basicTank = BlockBasicTank()

    val fillerModeList: ArrayList<FillerModePattern> = arrayListOf()

    fun handlePreInit() {
        RegisterHandler().build(this).handle()

        //ChunkManagerの登録 ===========================================================================================
        ForgeChunkManager.setForcedChunkLoadingCallback(AntiMatterModCore.INSTANCE, AMMChunkManager())
    }

    fun handleInit() {
        //Entityの登録 =============================================================================================
        EntityRegistry.registerModEntity(EntityDeveloperBoss::class.java, "DeveloperBoss", 1, AntiMatterModCore.MOD_ID, 250, 1, false)

        NetworkRegistry.INSTANCE.registerGuiHandler(AntiMatterModCore.INSTANCE, AMMGuiHandler())

        FillerRegistry.registerMode(PatternPlace())
        FillerRegistry.registerMode(PatternDestruction())
        FillerRegistry.registerMode(PatternErase())

    }
}