package class01;

/**
 * 冒泡排序， 时间复杂度  O(n^2)
 * 思路：
 * 1. 遍历 0 - N-1，相邻两个数比较交换，将最大的数挤到 N-1 的位置上
 * 2. 遍历 0 - N-2, 相邻两个数比较交换，将最大的数挤到 N-2 的位置上
 * 。。。。。。
 * 3. 遍历 0 - i，相邻两个数比较交换，将最大的数挤到 i 位置上
 */
public class BubbleSort {

    public static void main(String[] args) {
        // 写一个选择排序的对数器，来测试冒泡排序的正确性
        int testTimes = 10000; // 测试的次数
        int oneTimeNums = 1000; // 每次测试的数字个数
        int range = 1000;  // 数字的大小范围

        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = randomArray(oneTimeNums, range);
            int[] arr2 = copyArray(arr1);
            SelectionSort.selectionSort(arr1);
            bubble(arr2);
            for (int j = 0; j < arr1.length; j ++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println("出错了！！！！！");
                }
            }

        }
        System.out.println("执行成功！！！！");
    }

    public static void bubble (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1]) {
                    // 不停的去交换两个相邻的数
                    swap(array, j, j + 1);
                }
            }
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

    /**
     * 生成一个大小指定、数字范围指定的随机数组
     * @param oneTimeNums 数组指定大小
     * @param range 数字返回指定
     * @return 数组
     */
    public static int[] randomArray (int oneTimeNums, int range) {
        int[] array = new int[oneTimeNums];
        for (int i = 0; i < oneTimeNums; i++) {
            array[i] = (int) Math.random() * range + 1;
        }
        return array;
    }

    /**
     * 拷贝一个数组
     * @param array 被拷贝的数组
     * @return 数组
     */
    public static int[] copyArray (int[] array) {
        int[] copy = new int[array.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }
}
