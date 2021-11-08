package class01;

/**
 * 选择排序
 * @Author 张三金
 * @Date 2021/11/8 0008 8:37
 * @Company jzb
 * @Version 1.0.0
 */
public class SelectionSort {


    public static void main(String[] args) {
        int[] array = {-2, 4,12,24,56,78,23,1,23,45};
        selectionSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    public static void selectionSort (int [] array ) {
        if (array == null || array.length < 2) {
            return;
        }
        // 0 ~ N-1 之间的最大值选出来，放到 N-1 位置
        // 0 ~ N-2 之间的最大值选出来，放到 N-2 位置
        // ......
        // 0 ~ i 之间的z最大值选出来，放到 i 位置
        for (int i = array.length - 1; i > 0; i--) {
            int maxIndex = i;
            for (int j = 0; j <= i; j++) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(array, maxIndex, i);
        }
    }

    public static void swap (int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
