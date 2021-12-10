package class09;

import utils.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 基数排序
 * 经典的数据范围：非负的，可以用10进制来进行理解的数
 * ps: 其它的数据的话都要尽量转换成经典的数据范围，比如处理负数时，先将所有的数字都加上最小值的相反数，后面再减掉（但是可能会有溢出问题）
 * 思路：
 *      (1) 先遍历所有的数找出最大值，判断最大的数是几位数 n
 *      (2) 准备10个桶
 *      (3) 第一步将所有数字根据个位数字进入对应的桶中，然后再将所有的数字倒出来，先进先出
 *      (4) 第二部所有数字根据十位数字进入对应的桶中，然后将所有数字导出来，先进先出
 *      (5) 第三部所有数字根据十位数字进入对应的桶中，然后将所有数字导出来，先进先出
 *      .........................................
 *      .........................................
 * @Author 张三金
 * @Date 2021/12/10 0010 9:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_RadixSort {

    // 使用容器的方式 1.0
    public static void radixSort01 (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int max = 0;
        // 找出数组中的最大值
        for (int i: array) {
            max = Math.max(i, max);
        }
        // 判断最大值是多少位的数
        int digitCount = 0;
        while (max > 0) {
            digitCount++;
            max /= 10;
        }
        // 准备10个桶
        PriorityQueue<Integer>[] buckets = new PriorityQueue[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new PriorityQueue<Integer>();
        }
        int digitFlag = 1;
        for (int i = 0; i < digitCount; i++) {
            // 将数字放入桶中
            for (int j = 0; j < array.length; j++) {
                buckets[(array[j] / digitFlag) % 10].add(array[j]);
            }
            // 然后按照桶的顺序再倒出来
            int index = 0;
            for (PriorityQueue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    array[index++] = bucket.poll();
                }
            }
            digitFlag *= 10;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray03(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            radixSort01(array01);
            quickSort(array02, 0, array02.length - 1);
            for (int j = 0; j < array01.length; j++) {
                if (array01[j] != array02[j]) {
                    System.out.println("Opps!!!!");
                }
            }

        }
    }

    public static void quickSort (int[] array, int left, int right) {
        if (array == null || array.length < 2) {
            return;
        }
        if (left >= right) {
            return;
        }
        int[] partition = partition(array, left, right);
        quickSort(array, left, partition[0] - 1);
        quickSort(array, partition[1] + 1, right);
    }

    public static int[] partition (int[] array, int left, int right) {
        if (left == right) {
            return new int[]{left, left};
        }
        if (left > right) {
            return new int[]{-1, -1};
        }
        // 先在[left, right] 上面随机取一个数，和 right 的值进行交换
        ArrayUtil.swapTwoNum(array, right, left + (int) (Math.random() * (right - left + 1)));
        int smallArea = left - 1;
        int largeArea = right;
        int index = left;
        while (index < largeArea) {
            if (array[index] < array[right]) {
                ArrayUtil.swapTwoNum(array, ++smallArea, index++);
            } else if (array[index] == array[right]){
                index++;
            } else {
                ArrayUtil.swapTwoNum(array, --largeArea, index);
            }
        }
        ArrayUtil.swapTwoNum(array, right, largeArea);
        return new int[]{smallArea + 1, largeArea};
    }
}
