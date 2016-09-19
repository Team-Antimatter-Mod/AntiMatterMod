package antimattaermod.core;

import antimattaermod.core.Block.MetaItemBlock;
import antimattaermod.core.Block.Ores.CrystalOreBlock;
import antimattaermod.core.Util.ItemUtil;
import antimattaermod.core.Util.MetaItemBase;
import com.mojang.realmsclient.gui.ChatFormatting;
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

/**
 * @author C6H2Cl2
 */
public class AntiMatterModRegistry {
    //Item  ============================================================================================================
    //素材
    public static Item materials = ItemUtil.CreateItem(new MetaItemBase(13) {
        @Override
        public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
            if(item.getItemDamage() == 11) {
                list.add(StatCollector.translateToLocal("tile.material_11.information_1"));
                list.add(StatCollector.translateToLocal("tile.material_11.information_2"));
                list.add(ChatFormatting.RED+StatCollector.translateToLocal("tile.material_11.information.name"));
            }
        }
    },"material", "antimattermodcore:material", AntiMatterModRegistry.tabMaterials);
    //==================================================================================================================

    //Block  ===========================================================================================================
    //鉱石
    public static Block crystalOreBlock_1 = new CrystalOreBlock(Material.rock, "crystalOreBlock_1", "antimattermodcore:crystaloreblock_1", AntiMatterModRegistry.tabOreBlock, 2,
            new float[]{5.0F,5.0F}, new byte[]{3,3}, materials, new int[]{10,12});
    //==================================================================================================================

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

    //preinitで行う登録処理
    public static void registerPreInit(FMLPreInitializationEvent event){
        //Itemの登録
        GameRegistry.registerItem(materials, "material");
        //Blockの登録
        GameRegistry.registerBlock(crystalOreBlock_1, MetaItemBlock.class, "crystalOreBlock_1");
    }
}
