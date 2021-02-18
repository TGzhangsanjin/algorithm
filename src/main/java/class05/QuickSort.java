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
     * 思路：递归方式处理，每次递归，以数组的最后一个值作为partition值，将这个数放到正确的位置
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

    /**
     * 快排2.0 版本
     * 思路：相对于1.0版本，。每次选出一组数来，即将最后一个值partition以及与该值相等的数都放到正确的位置
     * 具体： partition 方法使用荷兰国旗的2.0版本
     * @param arr
     */
    public static void quickSort02(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process02(arr, 0, arr.length - 1);
    }
    public static void process02(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] partitionArr = partition02(arr, left, right);
        if (partitionArr != null) {
            process02(arr, left, partitionArr[0] - 1);
            process02(arr, partitionArr[1] + 1, right);
        }
    }

    /**
     * 返回相等区的左界和右界
     */
    public static int[] partition02(int[] arr, int left, int right) {
        if (left >= right) {
            return null;
        }
        int min = left - 1;
        int max = right;
        int i = left;
        while (i < max) {
            if (arr[i] < arr[right]) {
                swap(arr, i, ++min);
                i++;
            } else if (arr[i] > arr[right]) {
                swap(arr, i, --max);
            } else {
                i++;
            }
        }
        swap(arr, right, max);
        return new int[]{min + 1, max};
    }

    public static void main(String[] args) {
        int size = 10000;
        int range = 1000;
        int[] arr1 = randomArray(size, range);
        int[] arr2 = copyArray(arr1);
        int[] arr3 = copyArray(arr1);
        bubbleSort(arr1);
        quickSort01(arr2);
        quickSort02(arr3);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr3[i]) {
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
