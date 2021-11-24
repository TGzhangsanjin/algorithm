package class05;

import utils.ArrayUtil;

/**
 * 小和问题
 * 问题描述：
 *  一个数组中，一个数左边比它小的数的总和，叫做该数的小和，所有数的小和累加起来，叫做数组的小和。求数组的小和
 *  要求：时间复杂度为 O(nlogn)
 *  思路：
 *      使用归并排序，转换思路，对于一个数 a，找出右边有n个数大于它，那么它对数组小和的贡献就是 n * a
 *
 * @Author 张三金
 * @Date 2021/11/24 0024 10:58
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_LessSum {

    public static int lessSum (int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        return process(array, 0, array.length - 1);
    }

    public static int process(int[] array, int left, int right) {
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
        int leftIndex = left;
        int rightIndex = middle + 1;
        int current = 0;
        int answer = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            answer += array[leftIndex] < array[rightIndex] ? array[leftIndex] * (right - rightIndex + 1):0;
            // 注意：这里必须是小于，就是相等的时候一定要先拷贝右组
            sortedArray[current++] = array[leftIndex] < array[rightIndex] ? array[leftIndex++]:array[rightIndex++];
        }
        while (leftIndex <= middle) {
            sortedArray[current++] = array[leftIndex++];
        }
        while (rightIndex <= right) {
            sortedArray[current++] = array[rightIndex++];
        }
        // 拷贝数组
        System.arraycopy(sortedArray, 0, array, left, right - left  + 1);
//        for (int i = left; i <= right; i++) {
//            array[i] = sortedArray[i - left];
//        }
        return answer;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            int ans01 = lessSum(array01);
            int ans02 = testLessSum(array02);
            if (ans01 != ans02) {
                System.out.println("Opps!!!!!! ans01==" + ans01 + ", ans02==" + ans02);
            }
        }
    }

    // ForTest
    public static int testLessSum (int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    sum += array[j];
                }
            }
        }
        return sum;
    }
}
