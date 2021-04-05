package class01;

import utils.Util01;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 局部最小值
 * 描述： 有一个无序数组，且数组中任意两个数都不相等，返回数组中的一个局部最小值，局部最小值的概念就是某个数小于其相邻的数
 * 思路：使用二分法，
 * @Author 张三金
 * @Date 2021/4/5 0005 20:36
 * @Company jzb
 * @Version 1.0.0
 */
public class LocalMin {

    public static int solve (int[] array) {
        if (array == null && array.length < 2) {
            return -1;
        }
        // 判断index = 0 时是否满足
        if (array[0] < array[1]) {
            return 0;
        }
        // 判断 index = 1 时是否满足
        if (array[array.length - 1] < array[array.length - 2]) {
            return array.length - 1;
        }
        // 说明整个数组是一个向下凹的趋势，那么数组中一定存在局部最小值（非两个边界）
        int left = 1;
        int right = array.length - 2;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (array[middle] > array[middle + 1]) {
                left = middle + 1;
            } else if (array[middle] >array[middle - 1]){
                right = middle - 1;
            } else {
                return middle;
            }
        }
        // 这就是返回left 和 right 相等的时候
        return left;
    }

    /**
     * 生成一个互不相等的随机数组
     */
    public static int[] randomUnequalArray(int size, int range) {
        if (size < 1 || range < 1) {
            return null;
        }
        if (range < size * 10) {
            return null;
        }
        int[] array = new int[size];
        int lastNumber = -1;
        for (int i = 0; i < size; i++) {
            int number = (int) (Math.random() * range) + 1;
            while (lastNumber == number) {
                number = (int) (Math.random() * range) + 1;
            }
            lastNumber = number;
            array[i] = number;
        }
        return array;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNums = 100;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = randomUnequalArray(oneTimeNums, range);
            int index = solve(array01);
            if (index != -1) {
                if (index == 0 && array01[0] < array01[1]) {
                } else if ((index == array01.length - 1) && (array01[array01.length - 1] < array01[array01.length - 2])) {
                } else if (array01[index] < array01[index + 1] && array01[index] < array01[index - 1]){
                } else {
                    System.out.println("OOPS111111111111111");
                }
            } else {
                System.out.println("OOPS2222222222222222222222");
            }
        }
    }
}
