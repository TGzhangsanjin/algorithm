package class07;

import utils.ArrayUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 问题描述：
 *  已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的
 *
 * 思路：
 *  遍历每个数，维持一个大小时k的堆
 * 时间复杂度：O(N * logK)
 * @Author 张三金
 * @Date 2021/12/1 0001 17:21
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_SotedArrayDistanceLessK {

    public static void lessKSort (int[] array, int k) {
        if (array == null || array.length < 2) {
            return;
        }
        if (k == 0) {
            return;
        }
//        MyHeap myHeap = new MyHeap(k);
        PriorityQueue<Integer> myHeap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(array.length - 1, k - 1); index++) {
            myHeap.add(array[index]);
        }
        int i = 0;
        for (; index < array.length; i++, index++) {
            myHeap.add(array[index]);
            array[i] = myHeap.poll();
        }
        while (!myHeap.isEmpty()) {
            array[i++] = myHeap.poll();
        }
    }

    public static void main(String[] args) {
        int testTimes = 5000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] array = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] array01 = ArrayUtil.copyArray(array);
            int[] array02 = ArrayUtil.copyArray(array01);

            lessKSort(array01, k);
            mergeSort(array02);
            for (int j = 0; j < array01.length; j++) {
                if (array01[j] != array02[j]) {
                    System.out.println("Opps!!!!! array01[j] == " + array01[j] + ", array02[j]==" + array02[j]);
                }
            }

        }
    }

    // 搞一个小根堆
    public static class MyHeap {
        public int[] array;
        public int heapSize;
        public int heapLimit;

        public MyHeap (int limit) {
            array = new int[limit];
            heapLimit = limit;
            heapSize = 0;
        }

        public void add (int data) {
            if (heapSize == heapLimit) {
                return;
            }
            array[heapSize++] = data;
            heapInsert(array, heapSize - 1);
        }

        public int poll () {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            int data = array[0];
            ArrayUtil.swapTwoNum(array, 0, --heapSize);
            heapify(array, 0, heapSize);
            return data;
        }

        public boolean isEmpty () {
            return heapSize == 0;
        }

        public static void heapInsert (int[] arr, int index) {
            while (index > 0) {
                if (arr[index] < arr[(index - 1) >> 1]) {
                    ArrayUtil.swapTwoNum(arr, index, (index - 1) >> 1);
                    index = (index - 1) >> 1;
                } else {
                    break;
                }
            }
        }

        public static void heapify (int[] arr, int index, int heapSize) {
            int left = (index << 1) + 1;
            while (left < heapSize) {
                int minimum = (left + 1) < heapSize && arr[left + 1] < arr[left] ? left + 1:left;
                minimum = arr[minimum] < arr[index] ? minimum:index;
                if (minimum == index) {
                    break;
                }
                ArrayUtil.swapTwoNum(arr, index, minimum);
                index = minimum;
                left = (index << 1) + 1;
            }
        }
    }

    public static void mergeSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    public static void process (int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + ((right - left) >> 1);
        process(array, left, middle);
        process(array, middle + 1, right);
        merge(array, left, middle, right);
    }

    public static void merge (int[] array, int left, int middle, int right) {
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
        for (int i = left; i <= right; i++) {
            array[i] = sortedArray[i - left];
        }
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    public static void insertSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j-1]) {
                ArrayUtil.swapTwoNum(array, j, j - 1);
                j--;
            }
        }
    }
}
