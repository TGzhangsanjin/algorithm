package class01;

import utils.Util01;

/**
 *
 * 选择排序
 * @Author 张三金
 * @Date 2021/3/18 0018 21:03
 * @Company jzb
 * @Version 1.0.0
 */
public class SelectedSort {

    public static void sorted(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }
        int minIndex;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            // 每次循环将右侧的最小值与左侧排好序的末尾+1下标的值做交换
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            Util01.swapArray(array, i , minIndex);
        }
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTimeNums = 100;
        int range = 100;
        int[] array = Util01.randomArray(oneTimeNums, range);
        sorted(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }
}
