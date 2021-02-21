package class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 问题描述：
 *  已知一个几乎有序的数组。几乎有序是指：如果把数组排好序的话，每个元素移动的距离一定不超过K，并且K相对于数组长度来说时非常小的，
 *  请选择一个合适的排序策略，堆这个数组进行排序
 * @Author 张三金
 * @Date 2021/2/21 0021 15:38
 * @Company jzb
 * @Version 1.0.0
 */
public class SortArrayDistanceLessK {

    /**
     * 直接使用优先队列 时间复杂度  N * log(k + 1)
     */
    public static void distanceLessKSort01(int[] array, int k) {
        if (array == null && array.length < 2) {
            return;
        }
        if (k < 0) {
            return;
        }
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(k, array.length); index++) {
            heap.add(array[index]);
        }
        int i = 0;
        for (; index < array.length; i++, index++) {
            heap.add(array[index]);
            array[i] = heap.poll();
        }

        while (!heap.isEmpty()) {
            array[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTimeNums = 100;
        int range = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = randomArrayNoMoveMoreK(oneTimeNums, range, 6);
            int[] arr2 = copyArray(arr1);
            Arrays.sort(arr1);
            distanceLessKSort01(arr2,6);
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println("出错了！");
                }
            }
        }
    }

    /**
     * 返回一个随机数组，且这个随机数组的位置随机交换（两个交换的数的下标距离不能超过k），
     */
    public static int[] randomArrayNoMoveMoreK (int size, int range, int k) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        // 对数组先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但保证交换的数之间的距离不超过k
        // isSwap[i] == true, 表示数组i位置的数已经交换过了
        // isSwap[i] == false, 表示数组i位置的数还没有交换过
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i +(int) (Math.random() * k) + 1, arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                swap(arr, i, j);
            }
        }
        return arr;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] copyArray (int[] arr1) {
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }
}
