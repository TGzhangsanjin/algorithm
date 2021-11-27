package class06;

import utils.ArrayUtil;

/**
 * 区间和
 * 这道题直接在leetcode测评：
 * https://leetcode.com/problems/count-of-range-sum/
 * 问题描述：
 *  给定一个数组，两个整数 lower 和 upper，
 *  返回arr中有多少个数组的累加和在 [lower, upper]
 *  即有多少个 arr[i,j] 的和在 [lower, upper]之间，，其中 i <= j, 也就是说，i = j也算一种情况
 *
 *  思路：
 *      （1） 先生成一个累加和的辅助数组
 * @Author 张三金
 * @Date 2021/11/27 0027 11:52
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_CountOfRangeSum {

//    [-2,5,-1]
//            -2
//            2

    public static void main(String[] args) {
//
//        [-2147483647,0,-2147483647,2147483647]
//        -564
//        3864
//        int[] array = {-2147483647,0,-2147483647, 2147483647};
//        System.out.println(test01(array, -564, 3864));
//        int[] array = {-2,5, -1};


//        System.out.println(test02(array, -2, 2));
//        int[] array = {1, -1};
//        int[] array = {1, 0};
//        System.out.println(test02(array, 0, 0));

        checkTest02AndTest02();
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
     * 使用一个累加和的辅助数组，
     * 时间复杂度 O(n^2)
     * 注意这里数组溢出的问题解决不了
     */
    public static int test02 (int[] nums, int lower, int upper) {
        int[] sumArray = new int[nums.length];
        // 先求累加和数组
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
                    // i 到 j 之间的累加和是 sumArray[j] - sumArray[i] + nums[i]
                    int sum = sumArray[j] - sumArray[i] + nums[i];
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
