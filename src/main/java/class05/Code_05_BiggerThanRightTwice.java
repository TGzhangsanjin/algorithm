package class05;

import utils.ArrayUtil;

/**
 * 两倍问题
 * 问题描述：
 *  在一个数组中，对于每个数 num ，求有多少个后面的数 * 2仍 < num, 求总个数
 *  比如：[3,1,7,0,2]
 *      3的后面有：1，0
 *      1的后面有：0
 *      7的后面有：0，2
 *      0的后面没有
 *      2的后面没有
 *          所以总共有5个
 * @Author 张三金
 * @Date 2021/11/24 0024 14:22
 * @Company jzb
 * @Version 1.0.0
 */
public class Code_05_BiggerThanRightTwice {

    public static int biggerThanRightTwice (int[] array) {
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
        return process(array, left, middle) + process(array, middle + 1, right) + merge(array, left, middle, right);
    }

    public static int merge (int[] array, int left, int middle, int right) {

        int answer = 0;
        int windowR = middle + 1;
        for (int i = left; i <= middle ; i++) {
            // 因为左右两边都是排好序的，所以当前的i也会大于右边 windowR之前的数，这里比较绕
            // 运用了 windowR 下标不回退的技巧，这里的 for + while 实际上复杂度也是 O(n)
            while (windowR <= right && array[i] > (array[windowR] * 2)) {
                windowR++;
            }
            answer += windowR - middle - 1;
        }


        int[] sortedArray = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = middle + 1;
        int current = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            sortedArray[current++] = array[leftIndex] < array[rightIndex] ? array[leftIndex++]: array[rightIndex++];
        }
        while (leftIndex <= middle) {
            sortedArray[current++] = array[leftIndex++];
        }
        while (rightIndex <= right) {
            sortedArray[current++] = array[rightIndex++];
        }
        for (int i = left; i <= right ; i++) {
            array[i] = sortedArray[i - left];
        }

        return answer;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            if (biggerThanRightTwice(array01) != test(array02)) {
                System.out.println("Opps!!!!!");
            }
        }
    }

    public static int test (int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > (array[j] * 2)) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
