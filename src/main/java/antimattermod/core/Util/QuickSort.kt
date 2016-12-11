package antimattermod.core.Util

/**
 * @author C6H2Cl2
 */
class QuickSort {
    fun quickSort(array: Array<Double>, from: Int = 0, to: Int = array.size - 1) :Array<Double>{
        if (from < to) {
            val q = partition(array, from, to)
            quickSort(array, from, q - 1)
            quickSort(array, q + 1, to)
        }
        return array
}

    fun partition(array: Array<Double>, left: Int, right: Int): Int {

        val pivot = array[right]
        var i = left - 1

        for (j in left..right - 1) {
            if (array[j] <= pivot) {
                i++
                array.swap(i, j)
            }
        }

        array.swap(i + 1, right)

        return i + 1
    }

    fun <T> Array<T>.swap(i: Int, j: Int) {
        val t = this[i]
        this[i] = this[j]
        this[j] = t
    }
}