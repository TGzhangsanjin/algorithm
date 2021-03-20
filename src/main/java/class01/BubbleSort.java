package class01;

import utils.Util01;

/**
 * 冒泡排序 时间复杂度O(n * n), 空间复杂度 O(1)
 * @Author 张三金
 * @Date 2021/3/19 0019 23:12
 * @Company jzb
 * @Version 1.0.0
 */
public class BubbleSort {

    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    Util01.swapArray(array, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array1 = Util01.randomArray(oneTimeNums, range);
            int[] array2 = Util01.copyArray(array1);
            bubbleSort(array1);
            InsertSort.insertSort(array2);
            for (int j = 0; j < array1.length; j++) {
                if (array1[j] != array2[j]) {
                    System.out.println("OOPS!!!!!!!!");
                }
            }
        }
    }
}
