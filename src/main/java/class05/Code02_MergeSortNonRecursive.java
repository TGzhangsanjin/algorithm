package class05;

import utils.ArrayUtil;

/**
 * 归并排序的非递归实现
 *  思路：
 *      通过步长的方式去实现，
 *      mergeSize = 1，相当于左组1个数，右组1个数，进行归并merge
 *      mergeSize = 2，相当于左组2两个数，右组2个数，进行归并merge
 *      mergeSize = 4, 相当于左组4四个数，右组4个数，进行归并merge
 *      。。。。。。
 *  时间复杂度： O(N * logN)
 *
 *  ps: 思路很清晰，主要是边界问题需要注意
 */
public class Code02_MergeSortNonRecursive {

    public static void mergeSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int mergeSize = 1;
        while (mergeSize < array.length) {
            int left = 0;
            while (left < array.length) {
                // 如果步长已经比当前右边的数还多的时候，就没必要在往下走了
                if (mergeSize >= array.length - left) {
                    break;
                }
                int middle = left + mergeSize - 1;
                // 这个right的下标在没有超出数组长度的情况下，就是 middle + mergeSize,
                // 如果超出了那么 right 就是 array.length - 1
                int right = middle + Math.min(mergeSize, array.length - middle - 1);
                merge(array, left, middle, right);
                left = right + 1;
            }
            // 步长乘2
            mergeSize <<= 1;
        }

    }

    public static void merge (int[] array, int left, int middle, int right) {
        if (left >= right) {
            return;
        }
        int[] sortedArray = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = middle + 1;
        int index = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            sortedArray[index++] = array[leftIndex] <= array[rightIndex] ? array[leftIndex++]:array[rightIndex++];
        }
        while (leftIndex <= middle) {
            sortedArray[index++] = array[leftIndex++];
        }
        while (rightIndex <= right) {
            sortedArray[index++] = array[rightIndex++];
        }
        // 将排好序的数据拷贝回原数组
        for (int i = left; i<= right; i++) {
            array[i] = sortedArray[i - left];
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTestNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray(oneTestNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            mergeSort(array01);
            insertSort(array02);
            for (int j = 0; j < array01.length; j++) {
                if (array01[j] != array02[j]) {
                    System.out.println("Opps!!!!!");
                }
            }
        }
    }


    public static void insertSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                ArrayUtil.swapTwoNum(array, j, j - 1);
                j--;
            }
        }

    }
}
