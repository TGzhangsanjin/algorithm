package class05;

import utils.ArrayUtil;

/**
 * 逆序对问题
 * 问题描述：
 *  在一个数组中，对于任何一个前面的数a和后面的数b，如果(a, b) 是降序的，就称为逆序对，返回数组中所有的逆序对
 *  要求：时间复杂度O(nlogn)
 *  思路：
 *      与小和问题的思路一直，使用归并排序, 但是这里比较左、右两边的数组时，下标都要从右边开始
 *      对于一个数 a，找出右边有n个数小于它，那么它对逆序对的贡献就是 n 个，
 *      同样的需要注意，相等的情况下，要先拷贝右边，要留着左边继续和右边剩下的数进行比较
 * @Author 张三金
 * @Date 2021/11/24 0024 11:37
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_ReverseOrderPair {

    public static int reverseOrderPair (int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        return process(array, 0, array.length - 1);
    }

    public static int process (int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        return process(array, left, middle)
                + process(array, middle + 1, right)
                    + merge(array, left, middle, right);
    }

    public static int merge (int[] array, int left, int middle, int right) {
        int[] sortedArray = new int[right - left + 1];
        int leftIndex = middle;
        int rightIndex = right;
        int current = sortedArray.length - 1;
        int nums = 0;
        while (leftIndex >= left && rightIndex > middle) {
            if (array[leftIndex] > array[rightIndex]) {
                nums += rightIndex - middle;
            }
            sortedArray[current--] = array[leftIndex] > array[rightIndex] ? array[leftIndex--]:array[rightIndex--];
        }
        while (leftIndex >= left) {
            sortedArray[current--] =array[leftIndex--];
        }
        while (rightIndex > middle) {
            sortedArray[current--] =array[rightIndex--];
        }
        for (int i = left; i <= right; i++) {
            array[i] = sortedArray[i - left];
        }
        return nums;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            if (reverseOrderPair((array01)) != testReversePair(array02)) {
                System.out.println("Opps!!!!");
            }
        }
    }

    public static int testReversePair (int[] array) {
        int nums = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    nums++;
                }
            }
        }
        return nums;
    }


}
