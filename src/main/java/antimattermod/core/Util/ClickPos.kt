package antimattermod.core.Util

import net.minecraftforge.common.util.ForgeDirection

/**
 * Created by kojin15.
 */
data class ClickPos(var side: Int, var posX: Float, var posY: Float, var posZ: Float) {

    /**
     * 変更を加えようとした面
     * @return 数値はバニラのsideと同じ
     */
    fun getClickedDirection(): Int {
        if (onUp(side, posX, posY, posZ)) {
            return 0
        }
        if (onDown(side, posX, posY, posZ)) {
            return 1
        }
        if (onNorth(side, posX, posY, posZ)) {
            return 2
        }
        if (onSouth(side, posX, posY, posZ)) {
            return 3
        }
        if (onEast(side, posX, posY, posZ)) {
            return 4
        }
        if (onWest(side, posX, posY, posZ)) {
            return 5
        }
        return -1
    }

    private fun onUp(side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        when (side) {
            0 -> if (hitX >= 0.25 && hitX <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                return true
            }
            1 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                return true
            }
            2 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.0 && hitY < 0.25) {
                return true
            }
            3 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.0 && hitY < 0.25) {
                return true
            }
            4 -> if (hitY > 0.0 && hitY < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            5 -> if (hitY > 0.0 && hitY < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            else -> return false
        }
        return false
    }

    private fun onDown(side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        when (side) {
            0 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                return true
            }
            1 -> if (hitX >= 0.25 && hitX <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                return true
            }
            2 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.75 && hitY < 1.0) {
                return true
            }
            3 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.75 && hitY < 1.0) {
                return true
            }
            4 -> if (hitY > 0.75 && hitY < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            5 -> if (hitY > 0.75 && hitY < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            else -> return false
        }
        return false
    }

    private fun onNorth(side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        when (side) {
            0 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                return true
            }
            1 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                return true
            }
            2 -> if (hitX >= 0.25 && hitX <= 0.75 && hitY >= 0.25 && hitY <= 0.75) {
                return true
            }
            3 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0)) {
                return true
            }
            4 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                return true
            }
            5 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                return true
            }
            else -> return false
        }
        return false
    }

    private fun onSouth(side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        when (side) {
            0 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                return true
            }
            1 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                return true
            }
            2 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0)) {
                return true
            }
            3 -> if (hitX >= 0.25 && hitX <= 0.75 && hitY >= 0.25 && hitY <= 0.75) {
                return true
            }
            4 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                return true
            }
            5 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                return true
            }
            else -> return false
        }
        return false
    }

    private fun onEast(side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        when (side) {
            0 -> if (hitX > 0.0 && hitX < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            1 -> if (hitX > 0.0 && hitX < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            2 -> if (hitX > 0.0 && hitX < 0.25 && hitY > 0.25 && hitY < 0.75) {
                return true
            }
            3 -> if (hitX > 0.0 && hitX < 0.25 && hitY > 0.25 && hitY < 0.75) {
                return true
            }
            4 -> if (hitY >= 0.25 && hitY <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                return true
            }
            5 -> if ((hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                return true
            }
            else -> return false
        }
        return false
    }

    private fun onWest(side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        when (side) {
            0 -> if (hitX > 0.75 && hitX < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            1 -> if (hitX > 0.75 && hitX < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                return true
            }
            2 -> if (hitX > 0.75 && hitX < 1.0 && hitY > 0.25 && hitY < 0.75) {
                return true
            }
            3 -> if (hitX > 0.75 && hitX < 1.0 && hitY > 0.25 && hitY < 0.75) {
                return true
            }
            4 -> if ((hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                return true
            }
            5 -> if (hitY >= 0.25 && hitY <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                return true
            }
            else -> return false
        }
        return false
    }

}