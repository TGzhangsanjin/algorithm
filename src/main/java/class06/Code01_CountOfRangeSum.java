package class06;

import utils.ArrayUtil;

/**
 * 区间和
 * 这道题直接在leetcode测评：
 * https://leetcode.com/problems/count-of-range-sum/
 * 问题描述：
 *  给定一个数组，两个整数 lower 和 upper，
 *  返回arr中有多少个数组的前缀和在 [lower, upper]
 *  即有多少个 arr[i,j] 的和在 [lower, upper]之间，，其中 i <= j, 也就是说，i = j也算一种情况
 *
 *  思路：
 *      (1) 首先明确一点，数组中有多少个累加和，可以看成以每个 i 为结尾的累加和有多少个，i 属于 [0, arr.length - 1]
 *      (2) 生成一个前缀和的辅助数组
 *      (3) 假设 [0, i] 之前的前缀和是x，求必须以i结尾的累加和有多少个在 [lower, upper] 范围上，
 *           可以转换成 i 之前的所有前缀和有多少个在[x-upper, x-lower] 上
 *      (4) 使用归并排序
 * @Author 张三金
 * @Date 2021/11/27 0027 11:52
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {

        // 求前缀和数组
        long[] sumArray = new long[nums.length];
        sumArray[0] = nums[0];
        for (int i = 1; i < sumArray.length; i++) {
            sumArray[i] = sumArray[i-1] + nums[i];
        }

        return count(sumArray, 0, sumArray.length - 1, lower, upper);
    }

    public static int count (long[] sumArray, int left, int right, int lower, int upper) {

        if (left == right) {
            if (sumArray[left] >= lower && sumArray[left] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int middle = left + ((right - left) >> 1);
        int leftCount = count(sumArray, left, middle, lower, upper);
        int rightCount = count(sumArray, middle + 1, right, lower, upper);
        int mergeCount = merge(sumArray, left, middle, right, lower, upper);
        return leftCount + rightCount + mergeCount;
    }

    public static int merge (long[] sumArray, int left, int middle, int right, int lower, int upper) {

        int ans = 0;
        // 这两个变量是不会回退的
        int windowL = left;
        int windowR = left;
        // [windowL, windowR) 表明这个区间是不包含windowR 的
        for (int i = middle + 1; i <= right; i++) {
            long min = sumArray[i] - upper;
            long max = sumArray[i] - lower;
            // 因为这个区间不包含 windowR 所以这里可以直接用 <=
            while (windowR <= middle && sumArray[windowR] <= max) {
                windowR++;
            }
            while (windowL <= middle && sumArray[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        // 要先计算个数，在排序即要先保证排序前的数[left, right] 都是已经算完了累加和的
        int leftIndex = left;
        int rightIndex = middle + 1;
        int index = 0;
        long[] sortedArray = new long[right - left + 1];
        while (leftIndex <= middle && rightIndex <= right) {
            sortedArray[index++] = sumArray[leftIndex] <= sumArray[rightIndex]
                    ? sumArray[leftIndex++]:sumArray[rightIndex++];
        }
        while (leftIndex <= middle) {
            sortedArray[index++] = sumArray[leftIndex++];
        }
        while (rightIndex <= right) {
            sortedArray[index++] = sumArray[rightIndex++];
        }
        for (int i = left; i <= right; i++) {
            sumArray[i] = sortedArray[i - left];
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 3;
        int range = 2;
        for (int i = 0; i < testTimes; i++) {
            int lower = ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
            int upper = ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
            if (lower > upper) {
                lower = lower ^ upper;
                upper = lower ^ upper;
                lower = lower ^ upper;
            }
            if (lower == upper) {
                upper++;
            }
            int[] array01 = ArrayUtil.generateRandomArray(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            if (countRangeSum(array02, lower, upper) != test02(array01, lower, upper)) {
                System.out.println("Opps!!!!!");
            }
        }
//        checkTest02AndTest02();
    }

    public static void checkTest02AndTest02 () {
        int testTimes = 1000;
        int oneTimeNums = 10;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int lower = ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
            int upper = ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
            int[] array = ArrayUtil.generateRandomArray(oneTimeNums, range);
            if (test01(array, lower, upper) != test02(array, lower, upper)) {
                System.out.println("Opps!!!!!");
            }
        }
    }

    /**
     * ForTest02
     *
     * 使用一个前缀和的辅助数组，
     * 时间复杂度 O(n^2)
     * 注意这里数组溢出的问题解决不了
     */
    public static long test02 (int[] nums, int lower, int upper) {
        long[] sumArray = new long[nums.length];
        // 先求前缀和数组
        sumArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArray[i] = sumArray[i-1] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < sumArray.length; i++) {
            for (int j = i; j < sumArray.length; j++) {
                if (i == j) {
                    if (nums[i] >= lower && nums[i] <= upper) {
                        count++;
                    }
                } else {
                    // i 到 j 之间的前缀和是 sumArray[j] - sumArray[i] + nums[i]
                    long sum = sumArray[j] - sumArray[i] + nums[i];
                    if (sum >= lower && sum <= upper) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * ForTest01
     *  O(n^3) 的时间复杂度的实现方式
     */
    public static int test01 (int[] nums, int lower, int upper) {
        int count = 0;
        // 三个嵌套的for循环，时间复杂度 O(n^3)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    // 防止溢出
                    if ((sum > 0 && nums[k] > 0) && ((Integer.MAX_VALUE - sum) < nums[k])) {
                        break;
                    }
                    // 防止溢出
                    if ((sum < 0 && nums[k] < 0) && ((Integer.MIN_VALUE - sum) > nums[k])) {
                        break;
                    }
                    sum += nums[k];
                }
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }
}
