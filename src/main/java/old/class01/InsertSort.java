package old.class01;

import old.sort.SelectSort;
import old.utils.Util01;

/**
 * 插入排序 时间复杂度 O(n * n), 空间复杂度O(1)
 * @Author 张三金
 * @Date 2021/3/19 0019 22:41
 * @Company jzb
 * @Version 1.0.0
 */
public class InsertSort {

    public static void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int number = array[i];
            // number这个数最终要放到哪个位置下
            int index = i;
            // 每次将下一个数，插入到已排好序的数组中的正确位置
            for (int j = i - 1; j > -1; j --) {
                index = j;
                if (number < array[j]) {
                    array[j + 1] = array[j];
                } else {
                    index++;
                    break;
                }
            }
            array[index] = number;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTimeNums = 10000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] array1 = Util01.randomArray(oneTimeNums, range);
            int[] array2 = Util01.copyArray(array1);
            insertSort(array1);
            SelectSort.selectSort(array2);
            for (int j = 0; j < array1.length; j++) {
                if (array1[j] != array2[j]) {
                    System.out.println("oops!!!!");
                }
            }
        }
    }
}
