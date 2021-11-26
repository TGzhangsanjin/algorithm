package class06;

import utils.ArrayUtil;

/**
 * 快排 3.0
 *  思路：
 *      （1）处理数组array[left, right] 之间的数据时，现在left和right之间随机取一个数m，将m和数组的最后一个数进行交换，
 *          将数组分为小于区，等于区，大于区三个区域
 *       (2) 经过步骤（1），m这个数已经在数组的正确位置，然后递归再去处理（1）中剩下的小于区和大于区
 *  时间复杂度：O(N * logN), 额外空间复杂度 O(logN)
 * @Author 张三金
 * @Date 2021/11/26 0026 11:56
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_QuickSort03 {

    public static void quickSort03 (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    public static void process (int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] partition = partition(array, left, right);
        process(array, left, partition[0] - 1);
        process(array, partition[1] + 1, right);
    }

    public static int[] partition (int[] array, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        // 将 left - right 之间的随机一个数和 right 交换，作为排序的基准值进行处理
        ArrayUtil.swapTwoNum(array, right, left + (int) (Math.random() * (right - left + 1)));
        int lessEqual = left - 1;
        int moreEqual = right;
        int index = left;
        while (index < moreEqual) {
            if (array[index] == array[right]) {
                index++;
            } else if (array[index] < array[right]) {
                ArrayUtil.swapTwoNum(array, ++lessEqual, index++);
            } else {
                // 注意这里的index 不需要往右移动
                ArrayUtil.swapTwoNum(array, --moreEqual, index);
            }
        }
        ArrayUtil.swapTwoNum(array, moreEqual, right);
        return new int[]{lessEqual + 1, moreEqual};
    }
    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            quickSort03(array01);
            insertSort(array02);
            for (int j = 0; j < array01.length; j++) {
                if (array01[j] != array02[j]) {
                    System.out.println("Opps!");
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
