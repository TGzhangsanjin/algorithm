package class07;

import utils.ArrayUtil;

/**
 * 堆排序
 * @Author 张三金
 * @Date 2021/11/29 0029 17:28
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_HeapSort {

    public static void heapSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // O(N * logN)
//        for (int i = 0; i < array.length; i++) { // O(N)
//            // 依次把每个位置的数都调到对的位置上，使得数组维持一个大根堆的结构
//            heapInsert(array, i); // O(logN)
//        }

        // O(N) 这种方式比上面那种循环的方式是要更好的，但整个方法的时间复杂度仍是 O(N * logN),因为方法最后的while循环时 O(N * logN)
        for (int i = array.length - 1; i >= 0; i--) {
            // 依次把每个位置的数都调到对的位置上，使得数组维持一个大根堆的结构
            heapify(array, i, array.length);
        }

        int heapSize = array.length;
        ArrayUtil.swapTwoNum(array, 0, --heapSize);
        // O(N * logN)
        while (heapSize > 0) {  // O(N)
            heapify(array, 0, heapSize); // O(logN)
            ArrayUtil.swapTwoNum(array, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 10;
        int range = 10;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = ArrayUtil.generateRandomArray(oneTimeNums, range);
            int[] array02 = ArrayUtil.copyArray(array01);
            heapSort(array01);
            insertSort(array02);
            for (int j = 0; j < array01.length; j++) {
                int a = array01[j];
                int b = array02[j];
                if (!isEqual(a, b)) {
                    System.out.println("Opps!!!!!!!!" + j);
                }
            }
        }
    }

    /**
     * 数组 arr中现在新加进来的数停在了 index 位置, 依次往上移动
     * 移动到干不掉自己的父节点或者移动到 0， 停止！
     */
    private static void heapInsert (int[] arr, int index) {
        while (index > 0) {
            if (arr[index] > arr[(index - 1) >> 1]) {
                ArrayUtil.swapTwoNum(arr, index, (index - 1) >> 1);
                index = (index - 1) >> 1;
            } else {
                break;
            }
        }
    }

    /**
     * 数组中，index位置所在的数往下沉，当它的孩子不比它大时，停！
     */
    private static void heapify (int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找出左孩子和右孩子中的大者
            int largest = (left + 1) < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 找出孩子和父亲的大者
            largest = arr[largest] > arr[index] ? largest: index;
            if (largest == index) {
                break;
            }
            ArrayUtil.swapTwoNum(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void insertSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                ArrayUtil.swapTwoNum(array, j, j-1);
                j--;
            }
        }
    }

    public static boolean isEqual (Integer o1, Integer o2) {
        if (o1 == null && o2 == null) {
            return true;
        }
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }
}
