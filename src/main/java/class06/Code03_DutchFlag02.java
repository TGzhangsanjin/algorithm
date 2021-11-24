package class06;

import utils.ArrayUtil;

/**
 * 荷兰国旗 2.0
 * 问题描述：给定一个数组array，和一个数m, 需要将数组中的小于m的数放到数组array的左边，等于m的数放到数组中间，大于m的数放到数组右边
 * 要求：额外空间复杂度为O(1), 时间复杂度为 O(n)
 * 思路：
 *  遍历数组，遍历过程中维护三个区域，数组左边是小于区，中间是等于区，右边是大于区，当遍历下边i抵达大于区时，就结束
 *  遍历过程中：
 *      (1) 当前数 < m, 当前数和小于区的下一个数进行交换，当前数后移
 *      (2) 当前数 = m， 当前数直接后移
 *      (3) 当前数 > m, 当前数和大于区的前一个数进行交换，当前数不动，接着比较。
 *          这里不后移的原因是因为换过来的数是从后面过来的，而右边过来的数时还没有进行比较过
 *   原理就是：小于区往右移，大于区往左移，把中间留给等于去
 * @Author 张三金
 * @Date 2021/11/24 0024 9:08
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_DutchFlag02 {

    public static void dutchFlag (int[] array, int m) {
        if (array == null || array.length < 1) {
            return;
        }
        int lessIndex = -1;
        int greaterIndex = array.length;
        int current = 0;
        while (current < greaterIndex) {
            // 注意这里一定要先处理大于的情况，处理完大于的情况后，当前数还需要后续判断是否小于m
            // 如果大于m，当前数就停在这里
            while (current < greaterIndex && array[current] > m) {
                ArrayUtil.swapTwoNum(array, current, --greaterIndex);
            }
            if (array[current] < m) {
                // 小于
                ArrayUtil.swapTwoNum(array, current, ++lessIndex);
            }
            current++;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int m = (int) (Math.random() * range) + 1;
            int[] array = ArrayUtil.generateRandomArray(oneTimeNums, range);
            // 数组中等于 m 的个数
            int equalNum = 0;
            // 数组中小于 m 的个数
            int lessThanNum = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == m) {
                    equalNum++;
                } else if (array[j] < m) {
                    lessThanNum++;
                }
            }
            dutchFlag(array, m);
            int index = 0;
            for (; index < lessThanNum; index++) {
                if (array[index] >= m) {
                    System.out.println("小于区出错了！！！！");
                }
            }
            for (; index < (lessThanNum + equalNum); index++) {
                if (array[index] != m) {
                    System.out.println("等于区出错了！！！！");
                }
            }
            for (; index < array.length; index++) {
                if (array[index] <= m) {
                    System.out.println("大于区出错了！！！！");
                }
            }
        }
    }

}
