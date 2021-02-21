package class06;


import java.util.PriorityQueue;

/**
 * 堆排序
 * @author 张三金
 */
public class HeapSort {

    /**
     *  堆排序， 时间复杂度为 O(N*logN)
     */
    public static void myHeapSort(int[] array) {
        if (array == null && array.length < 2) {
            return ;
        }

        // 1、建堆
        // N * logN
        for (int i = 0; i < array.length; i++) {
            // logN
            heapInsert(array, i);
        }
        // 如果仅仅是用来做堆排序，也可以采用自底向下的建堆方式，建堆过程的时间复杂度可以从 N*logN 降低到 N，
//        for (int i = array.length - 1; i >= 0; i--) {
//            heapify(array, i, array.length);
//        }

        // 2、从堆中一个一个的弹出元素到数组末尾
        int heapSize = array.length;
        // N * logN
        while(heapSize > 0) {
            swap(array, 0 , --heapSize);
            // logN
            heapify(array, 0, heapSize);
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int testSize = 100;
        int range = 100;
        // 优先级队列，默认是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = randomSize(testSize, range);
            int[] arr2 = copyArray(arr1);
            for (int j = 0; j < arr1.length; j++) {
                heap.add(arr1[j]);
            }
            myHeapSort(arr1);
            bubbleSort(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println("出错了！！！");
                }
            }
            for (int x = 0; x < arr1.length; x++) {
                if (arr1[x] != heap.poll()) {
                    System.out.println("出错了！！！");
                }
            }
        }
    }

    /**
     * 大根堆的插入
     */
    public static void heapInsert(int[] array, int index) {
        while (array[index] > array[(index - 1) / 2]) {
            swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 从堆中将下标为index的元素下沉放到大根堆中正确的位置
     */
    public static void heapify(int[] array, int index, int heapSize) {
        // 左孩子的下标
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = (left + 1) < heapSize  && array[left + 1]  > array[left] ? left + 1: left;
            largest = array[largest] > array[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(array, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] randomSize(int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * range) + 1;
        }
        return array;
    }

    public static int[] copyArray (int[] arr1) {
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }

    /**
     * For Test
     */
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j , j + 1);
                }
            }
        }
    }

}
