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
     * 荷兰国旗1.0版本： 给定一个数组arr，和数组中的一个下标 x，小于等于arr[x]的在数组中放左边，大于arr[x]的放右边
     * 思路： arr[0...m] 为小于等于区,让小于等于区推着大于区向右边走
     * 即：从0到arr.length - 1 遍历，  arr[0..m]为小于等于区，当arr[i]大于arr[x]时，直接跳到下一个，当arr[i] <= arr[x]时，
     *    交换arr[i+1] 和 arr[m+1] 的值进行交换
     */
    public static void dutchFlag01(int[] arr, int x) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (x < 0 || x > arr.length - 1) {
            return;
        }
        int partitionNum = arr[x];
        int min = -1;
        int i = 0;
        while (i <= arr.length - 1) {
            if (arr[i] <= partitionNum) {
                swap(arr, i, ++min);
            }
            i++;
        }
    }

    public static void main(String[] args) {
        int size = 10000;
        int range = 10000;
        int[] arr = randomArray(size, range);
        // 随机生成一个下标
        int x = (int) (Math.random() * size);
        int partitionNum = arr[x];
        dutchFlag01(arr, x);
        validateDutchFlag01(arr, partitionNum);
//        for (int i = 0; i < size; i++) {
//            System.out.print(arr[i] + ",");
//        }
    }

    public static void validateDutchFlag01(int[] arr, int partitionNum) {
        // 找出最小区的右边界
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= partitionNum) {
                min++;
            }
        }
        if (min + 1 == arr.length - 1) {
            return;
        }
        // 判断min+1 到arr.length - 1 之间是否还存在小于等于partitionNum 的数，如果存在则抛出错误
        for (int i = min + 1; i < arr.length; i++) {
            if (arr[i] <= partitionNum) {
                System.out.println("出错了！！！！！");
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
