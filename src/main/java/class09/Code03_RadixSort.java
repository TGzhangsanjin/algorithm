package class09;

import utils.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 基数排序  时间复杂度 O(N), 额外空间复杂度 O(N)
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
 * 不使用容器的方式：
 *      (1) 先遍历所有的数找出最大值，判断最大的数是几位数 n
 *      (2) 遍历 n
 *      (3) 每次遍历时，准备一个长度为10的辅助数组 count, 数组 count 每个下标存的是数组中下标对应的数出现测次数
 *      (4) 求 count 数组的前缀和数组 countPrefixCount
 *      (5) 从右往左遍历原数组，判断遍历的数的位数index ,countPrefixCount[index] 的值就是该数在原数组中的位置，然后 countPrefixCount[index]--
 * @Author 张三金
 * @Date 2021/12/10 0010 9:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_RadixSort {

    // 不使用容器的方式
    public static void radixSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        radixSort(array, 0, array.length - 1, maxBit(array));
    }

    public static void radixSort (int[] array, int left, int right, int digit) {
        int[] help = new int[right - left + 1];
        // 数组中的最大值时多少位，原数组就进出多少次
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[10];
            // count[0] 当前位(d位)是0的有多少个
            // count[1] 当前位(d位)是1的有多少个
            // count[2] 当前位(d位)是1的有多少个
            for (int i = left; i <= right; i++) {
                count[getDigit(array[i], d)]++;
            }
            // 求 count 变成前缀和
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 从右往左遍历数组
            for (int i = right; i >= left ; i--) {
                // 当前遍历的数在 d 位上的数字
                int currentDigit = getDigit(array[i], d);
                help[count[currentDigit] - 1] = array[i];
                // 这行注释掉的代码时错误的，一开始想着不使用help数组，直接交换，发现不行，因为交换后当前循环的遍历就会错乱
//                ArrayUtil.swapTwoNum(array, i, left + count[currentDigit] - 1);
                count[currentDigit]--;
            }
            // 将 help 数组拷贝回去到原数组中
            for (int i = left; i <= right; i++) {
                array[i] = help[i - left];
            }
        }
    }

    /**
     * 取出number 这个数在 d 位上的数字，
     * 比如：d = 1, 表示取出number 个位数字上的数来
     *      d = 2, 则去除number 十位数字上的数来
     */
    public static int getDigit (int number, int d) {
        return (int) (number / Math.pow(10, d - 1)) % 10;
    }

    public static int maxBit(int[] array) {
        int max = 0;
        for (int i: array) {
            max = Math.max(max, i);
        }
        int digit = 0;
        while (max > 0) {
            digit++;
            max /= 10;
        }
        return digit;
    }

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
//            radixSort01(array01);
            radixSort(array01);
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
