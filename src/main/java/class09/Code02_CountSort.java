package class09;

import utils.ArrayUtil;

/**
 * 计数排序（属于桶排序的一种）
 *  使用场景有限制，数据范围比较小，比如年龄
 *  时间复杂度： O(N)
 * @Author 张三金
 * @Date 2021/12/9 0009 14:56
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_CountSort {

    // only for (0 ~ 200] value
    public static void countSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int max = 0;
        for (int i : array) {
            max = Math.max(i, max);
        }
        // array 的值对应 bucket上的下标
        int[] bucket = new int[max + 1];
        for (int i: array) {
            bucket[i]++;
        }
        // 复制回去
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                array[index++] = i;
                bucket[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTimeNums = 1000;
        int range = 200;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray03(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            countSort(array01);
            quickSort(array02, 0, array02.length - 1);

            for (int j = 0; j < array01.length; j++) {
                if (array01[j] != array02[j]) {
                    System.out.println("Opps!!!!!!");
                }
            }
        }
    }


    // For Test 写一个快速排序用作对数器
    public static void quickSort (int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] partition = partition(array, left, right);
        quickSort(array, left, partition[0] - 1);
        quickSort(array, partition[1] + 1, right);
    }

    public static int[] partition (int[] array, int left, int right) {

        // 小于区的下标
        int smallIndex = left - 1;
        // 大于区的下标
        int bigIndex = right;
        int index = left;
        // 在数组上取一个随机位置的数，然后和最右侧位置上的数进行交换
        ArrayUtil.swapTwoNum(array, left + (int)(Math.random() * (right - left + 1)), right);
        while (index < bigIndex) {
            if (array[index] < array[right]) {
                // 和等于区的第一个数交换，小于区长度+1， index往下走+1
                ArrayUtil.swapTwoNum(array, ++smallIndex, index++);
            } else if (array[index] == array[right]) {
                // index 直接往下走 +1
                index++;
            } else {
                // 和大于区的第一个数交换即可
                ArrayUtil.swapTwoNum(array, --bigIndex, index);
            }
        }
        // 将最右侧的一个数和大于区最左侧的一个数进行交换
        ArrayUtil.swapTwoNum(array, right, bigIndex);
        return new int[]{smallIndex + 1, bigIndex};
    }

}
