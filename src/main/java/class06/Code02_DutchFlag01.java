package class06;

import utils.ArrayUtil;

/**
 * 荷兰国旗 1.0
 * 问题描述：给定一个数组array，和一个数m, 需要将数组中的小于等于m的数放到数组array的左边，大于m的数放到数组右边
 * ps: 结果数组中，小于等于区内部的顺序是无所谓的，大于区内部的顺序也是无所谓的
 * 要求：额外空间复杂度为O(1), 时间复杂度为 O(n)
 * 思路：
 *  遍历整个数组，将左边从0开始看成小于等于区
 *  (1) 当前数 <= m 当前数和小于等于区的下一个数进行交换，当前数后移
 *  (2) 当前数 > m 当前数直接后移
 *  原理： 相当于小于等于区一直在推着大于区往后走
 * @Author 张三金
 * @Date 2021/11/24 0024 9:08
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_DutchFlag01 {

    public static void dutchFlag (int[] array, int m) {
        if (array == null || array.length < 2) {
            return;
        }
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= m) {
                swap(array, i, ++index);
            }
        }
    }
    public static void swap (int[] array, int a, int b) {
        if (a == b || array[a] == array[b]) {
            return;
        }
        array[a] = array[a] ^ array[b];
        array[b] = array[a] ^ array[b];
        array[a] = array[a] ^ array[b];
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int m = (int) (Math.random() * range) + 1;
            int[] array = ArrayUtil.generateRandomArray(oneTimeNums, range);
            // 找出数组中，小于等于m的数有多少个
            int lessThanM = 0;
            for (int i1 : array) {
                if (i1 <= m) {
                    lessThanM++;
                }
            }
            dutchFlag(array, m);
            for (int j = 0; j < lessThanM; j++) {
                if (array[j] > m) {
                    System.out.println("Opps0001!!!!!!");
                }
            }
            for (int j = lessThanM; j < array.length; j++) {
                if (array[j] <= m) {
                    System.out.println("Opps0002!!!!!!");
                }
            }
        }

    }

}
