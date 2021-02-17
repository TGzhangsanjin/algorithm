package class05;

/**
 * 快速排序
 * @Author 张三金
 * @Date 2021/2/17 0017 7:37
 * @Company jzb
 * @Version 1.0.0
 */
public class QuickSort {


    /**
     * 快排1.0 版本
     * 思路：递归方式处理，每次递归，以数组的最后一个值作为partition值，
     * 具体：每次遍历arr[L...R-1]，将 <= partition 的值放在左边，>partition 的值放在右边，小区右界下标为min, 最后将arr[min + 1] 和 arr[R]进行交换
     *      然后再去递归处理 arr[L...min] 和 arr[min+2...R]
     * 总结： 即每次递归都只确定一个数
     */
    public static void quickSort01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process01(arr, 0, arr.length - 1);
    }

    public static void process01(int [] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = partition01(arr, left, right);
        if (middle == -1) {
            return;
        }
        process01(arr, left, middle - 1);
        process01(arr, middle + 1, right);
    }

    /**
     * 返回分割小区和大区的下标
     */
    public static int partition01(int [] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return left;
        }
        int min = left - 1;
        int i = left;
        while (i < right) {
            if (arr[i] <= arr[right]) {
                swap(arr, i, ++min);
            }
            i++;
        }
        // 最后将arr[right]值放到正确的位置
        swap(arr, ++min, right);
        return min;
    }

    public static void main(String[] args) {
        int size = 1000;
        int range = 100;
        int[] arr1 = randomArray(size, range);
        int[] arr2 = copyArray(arr1);
        bubbleSort(arr1);
        quickSort01(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.println("出错了！！！！");
            }
        }
    }


    /**
     * For Test
     * 手写一个冒泡排序用作对数器
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    /**
     * For Test
     */
    public static void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (i < 0 || i > arr.length - 1) {
            return;
        }
        if (j < 0 || j > arr.length - 1) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * For Test
     */
    public static int[] copyArray(int [] arr) {
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
        return arr2;
    }

    /**
     * 生成一个随机数组
     */
    public static int[] randomArray(int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * range) + 1;
        }
        return array;
    }
}
