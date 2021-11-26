package class06;

import utils.ArrayUtil;

/**
 * 快排2.0
 *  思路：
 *      相比较与快排1.0 partition 返回的是等于区的前后位置，每次递归确定的是所有与最后一个数相等的数的位置
 *      同样的，每次的比较值都是 right 位置上的数
 *  时间复杂度：依然是 O(n^2)
 * @Author 张三金
 * @Date 2021/11/26 0026 11:06
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_QuickSort02 {

    public static void quickSort02 (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }


    public static void process (int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        int[] partition = partition(array, left, right);
        if (partition[0] == -1) {
            return;
        }
        process(array, left, partition[0] - 1);
        process(array, partition[1] + 1, right);
    }

    /**
     * 将数组array 的 [left, right] 划分为大于区，等于区， 大于区，三个区域
     * 最后将 array[right] 和大于区的第一个数进行交换
     * @return 返回的是等于区的开始下标和结束下标
     */
    public static int[] partition (int[] array, int left, int right) {
        if (left > right) {
            return new int[] {-1, -1};
        }
        if (left == right) {
            return new int[] {left, right};
        }
        int lessEqual = left - 1;
        int moreEqual = right;
        int index = left;
        while (index < moreEqual) {
            if (array[index] == array[right]) {
                index++;
            } else if(array[index] < array[right]){
                ArrayUtil.swapTwoNum(array, ++lessEqual, index++);
            } else {
                // 注意这里的index 不能往右移动了
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
            quickSort02(array01);
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
