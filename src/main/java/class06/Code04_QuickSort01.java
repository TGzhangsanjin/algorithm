package class06;

import utils.ArrayUtil;

/**
 * 快排1.0版本
 * 思路：
 *  使用归并排序，每次递归时：以最后一个数作为基准，将数组[0, arr.length - 2]分为小于区等于区、大于区两个区，每次递归就将最后一个数给确定下来了，
 *  最后将每次递归的最后一个数和 大于区的第一个数进行交换
 *  相当于，没递归一次确定一个数的位置
 * 时间复杂度：O(n^2)
 * @Author 张三金
 * @Date 2021/11/26 0026 10:25
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_QuickSort01 {


    public static void quickSort01 (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    public static void process (int[] array, int left, int right) {
        // 这个判断一定要加上，要不然递归没办法结束
        if (left >= right) {
            return;
        }
        int partition = partition(array, left, right);
        process(array, left, partition - 1);
        process(array, partition + 1, right);
    }

    /**
     * 将 数组 array的 [left, right] 之间， 以 arrayp[right] 为基准，划分为小于等于区，大于区，
     * 最后 array[right] 和 大于区的第一个数进行位置交换
     * @return  返回的是，最开始的array[right]最终所处的位置
     */
    public static int partition (int[] array, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return left;
        }

        int lessEqual = left - 1;
        int index = left;
        while (index < right) {
            if (array[index] <= array[right]) {
                ArrayUtil.swapTwoNum(array, ++lessEqual, index);
            }
            index++;
        }
        ArrayUtil.swapTwoNum(array, ++lessEqual, right);
        return lessEqual;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            quickSort01(array01);
            insertSort(array02);
            for (int j = 0; j < array01.length; j++) {
                if (array01[j] != array02[j]) {
                    System.out.println("Opps!!!!!!");
                }
            }
        }

    }

    public static void insertSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] <= array[j - 1]) {
                ArrayUtil.swapTwoNum(array, j, j - 1);
                j--;
            }
        }
    }

}
