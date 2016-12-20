package antimattermod.core.Item.tool;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.ClayCrucibleHeater;
import antimattermod.core.Block.IWrenchAction;
import antimattermod.core.Energy.Generator.Block.BlockFurnaceGenerator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by kojin15.
 */
public class ItemWrench extends Item implements AMMItemBase {

    public ItemWrench(String name, String texture) {
        setCreativeTab(AntiMatterModRegistry.tabTools);
        setUnlocalizedName(name);
        setTextureName(AntiMatterModCore.MOD_ID + ":" + "tool/" + texture);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        if (block instanceof IWrenchAction) {
            if (!player.isSneaking()) {
                ((IWrenchAction) block).onWrenchClick(world, player, x, y, z, meta, side, hitDirection(hitX, hitY, hitZ, side));
            }
            else {
                ((IWrenchAction) block).onWrenchShiftClick(world, player, x, y, z, meta, side, hitDirection(hitX, hitY, hitZ, side));
            }
            return true;
        }
        return false;
    }



    public static boolean onTop(double hitX, double hitY, double hitZ, int side) {
        switch (side) {
            case 0:
                if (hitX >= 0.25D && hitX <= 0.75D && hitZ >= 0.25D && hitZ <= 0.75D) {
                    return true;
                }
                break;
            case 1:
                if (((hitX >= 0.0D && hitX <= 0.25D) || (hitX >= 0.75D && hitX <= 1.0D)) && ((hitZ >= 0.0D && hitZ <= 0.25D) || (hitZ >= 0.75D && hitZ <= 1.0D))) {
                    return true;
                }
                break;
            case 2:
                if (hitX > 0.25D && hitX < 0.75D && hitY > 0.0D && hitY < 0.25D) {
                    return true;
                }
                break;
            case 3:
                if (hitX > 0.25D && hitX < 0.75D && hitY > 0.0D && hitY < 0.25D) {
                    return true;
                }
                break;
            case 4:
                if (hitY > 0.0D && hitY < 0.25D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            case 5:
                if (hitY > 0.0D && hitY < 0.25D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean onBottom(double hitX, double hitY, double hitZ, int side) {
        switch (side) {
            case 0:
                if (((hitX >= 0.0D && hitX <= 0.25D) || (hitX >= 0.75D && hitX <= 1.0D)) && ((hitZ >= 0.0D && hitZ <= 0.25D) || (hitZ >= 0.75D && hitZ <= 1.0D))) {
                    return true;
                }
                break;
            case 1:
                if (hitX >= 0.25D && hitX <= 0.75D && hitZ >= 0.25D && hitZ <= 0.75D) {
                    return true;
                }
                break;
            case 2:
                if (hitX > 0.25D && hitX < 0.75D && hitY > 0.75D && hitY < 1.0D) {
                    return true;
                }
                break;
            case 3:
                if (hitX > 0.25D && hitX < 0.75D && hitY > 0.75D && hitY < 1.0D) {
                    return true;
                }
                break;
            case 4:
                if (hitY > 0.75D && hitY < 1.0D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            case 5:
                if (hitY > 0.75D && hitY < 1.0D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean onNorth(double hitX, double hitY, double hitZ, int side) {
        switch (side) {
            case 0:
                if (hitX > 0.25D && hitX < 0.75D && hitZ > 0.0D && hitZ < 0.25D) {
                    return true;
                }
                break;
            case 1:
                if (hitX > 0.25D && hitX < 0.75D && hitZ > 0.0D && hitZ < 0.25D) {
                    return true;
                }
                break;
            case 2:
                if (hitX >= 0.25D && hitX <= 0.75D && hitY >= 0.25D && hitY <= 0.75D) {
                    return true;
                }
                break;
            case 3:
                if (((hitX >= 0.0D && hitX <= 0.25D) || (hitX >= 0.75D && hitX <= 1.0D)) && ((hitY >= 0.0D && hitY <= 0.25D) || (hitY >= 0.75D && hitY <= 1.0D))) {
                    return true;
                }
                break;
            case 4:
                if (hitY > 0.25D && hitY < 0.75D && hitZ > 0.0D && hitZ < 0.25D) {
                    return true;
                }
                break;
            case 5:
                if (hitY > 0.25D && hitY < 0.75D && hitZ > 0.0D && hitZ < 0.25D) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean onSouth(double hitX, double hitY, double hitZ, int side) {
        switch (side) {
            case 0:
                if (hitX > 0.25D && hitX < 0.75D && hitZ > 0.75D && hitZ < 1.0D) {
                    return true;
                }
                break;
            case 1:
                if (hitX > 0.25D && hitX < 0.75D && hitZ > 0.75D && hitZ < 1.0D) {
                    return true;
                }
                break;
            case 2:
                if (((hitX >= 0.0D && hitX <= 0.25D) || (hitX >= 0.75D && hitX <= 1.0D)) && ((hitY >= 0.0D && hitY <= 0.25D) || (hitY >= 0.75D && hitY <= 1.0D))) {
                    return true;
                }
                break;
            case 3:
                if (hitX >= 0.25D && hitX <= 0.75D && hitY >= 0.25D && hitY <= 0.75D) {
                    return true;
                }
                break;
            case 4:
                if (hitY > 0.25D && hitY < 0.75D && hitZ > 0.75D && hitZ < 1.0D) {
                    return true;
                }
                break;
            case 5:
                if (hitY > 0.25D && hitY < 0.75D && hitZ > 0.75D && hitZ < 1.0D) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean onEast(double hitX, double hitY, double hitZ, int side) {
        switch (side) {
            case 0:
                if (hitX > 0.0D && hitX < 0.25D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            case 1:
                if (hitX > 0.0D && hitX < 0.25D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            case 2:
                if (hitX > 0.0D && hitX < 0.25D && hitY > 0.25D && hitY < 0.75D) {
                    return true;
                }
                break;
            case 3:
                if (hitX > 0.0D && hitX < 0.25D && hitY > 0.25D && hitY < 0.75D) {
                    return true;
                }
                break;
            case 4:
                if (hitY >= 0.25D && hitY <= 0.75D && hitZ >= 0.25D && hitZ <= 0.75D) {
                    return true;
                }
                break;
            case 5:
                if (((hitY >= 0.0D && hitY <= 0.25D) || (hitY >= 0.75D && hitY <= 1.0D)) && ((hitZ >= 0.0D && hitZ <= 0.25D) || (hitZ >= 0.75D && hitZ <= 1.0D))) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean onWest(double hitX, double hitY, double hitZ, int side) {
        switch (side) {
            case 0:
                if (hitX > 0.75D && hitX < 1.0D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            case 1:
                if (hitX > 0.75D && hitX < 1.0D && hitZ > 0.25D && hitZ < 0.75D) {
                    return true;
                }
                break;
            case 2:
                if (hitX > 0.75D && hitX < 1.0D && hitY > 0.25D && hitY < 0.75D) {
                    return true;
                }
                break;
            case 3:
                if (hitX > 0.75D && hitX < 1.0D && hitY > 0.25D && hitY < 0.75D) {
                    return true;
                }
                break;
            case 4:
                if (((hitY >= 0.0D && hitY <= 0.25D) || (hitY >= 0.75D && hitY <= 1.0D)) && ((hitZ >= 0.0D && hitZ <= 0.25D) || (hitZ >= 0.75D && hitZ <= 1.0D))) {
                    return true;
                }
                break;
            case 5:
                if (hitY >= 0.25D && hitY <= 0.75D && hitZ >= 0.25D && hitZ <= 0.75D) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private int hitDirection(double hitX, double hitY, double hitZ, int side) {
        if (onTop(hitX, hitY, hitZ, side)) {
            return 0;
        }
        if (onBottom(hitX, hitY, hitZ, side)) {
            return 1;
        }
        if (onNorth(hitX, hitY, hitZ, side)) {
            return 2;
        }
        if (onSouth(hitX, hitY, hitZ, side)) {
            return 3;
        }
        if (onEast(hitX, hitY, hitZ, side)) {
            return 4;
        }
        if (onWest(hitX, hitY, hitZ, side)) {
            return 5;
        }
        return -1;
    }

}

