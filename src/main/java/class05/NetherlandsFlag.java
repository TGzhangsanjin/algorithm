package class05;

/**
 * 荷兰国旗问题
 * @Author 张三金
 * @Date 2021/2/17 0017 7:37
 * @Company jzb
 * @Version 1.0.0
 */
public class NetherlandsFlag {


    /**
     * 荷兰国旗1.0版本： 给定一个数组arr，和数组中的一个下标 x，小于等于arr[x]的在数组中放左边，大于arr[x]的放右边
     * 思路： arr[0...m] 为小于等于区,让小于等于区推着大于区向右边走
     * 即：从0到arr.length - 1 遍历，  arr[0..m]为小于等于区，当arr[i]大于arr[x]时，直接跳到下一个，当arr[i] <= arr[x]时，
     *    交换arr[i+1] 和 arr[m+1] 的值进行交换
     */
    public static void netherlandsFlag01(int[] arr, int x) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (x < 0 || x > arr.length - 1) {
            return;
        }
        int partitionNum = arr[x];
        int min = -1;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] <= partitionNum) {
                swap(arr, i, ++min);
            }
            i++;
        }
    }

    /**
     * 荷兰国旗2.0版本：给定一个数组arr，和数组中的一个下标 x，小于arr[x]的在数组中放左边，等于arr[x]的在数组中放中间, 大于arr[x]的放右边
     * 思路：左边为最小区arr[0...min]，右边为最大区arr[max...arr.length - 1]，
     * 具体：从0到arr.length - 1 遍历数组，
     *      当arr[i] < arr[x] 时，将arr[i] 与 arr[min + 1] 进行交换,i继续往后面走，
     *      当arr[i] > arr[x]时，将arr[i] 与 arr[max - 1] 进行交换，i不往下走，继续比较
     *      当arr[i] == arr[x] 时，不做任何操作，继续往下遍历，一直到i == max - 1 停止
     */
    public static void netherlandsFlag02(int[] arr, int x) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (x < 0 || x > arr.length - 1) {
            return;
        }

        int partitionNum = arr[x];

        // 最小区的有边界
        int min = -1;
        // 最大区的左边界
        int max = arr.length;
        int i = 0;
        while (i < max) {
            if (arr[i] < partitionNum) {
                swap(arr, i, ++min);
                i++;
            } else if (arr[i] > partitionNum) {
                swap(arr, i, --max);
            } else {
                i++;
            }
        }
    }


    public static void main(String[] args) {
        int size = 10000;
        int range = 10000;
        int[] arr = randomArray(size, range);
        int[] arr2 = copyArray(arr);
        // 随机生成一个下标
        int x = (int) (Math.random() * size);
        int partitionNum = arr[x];
        netherlandsFlag01(arr, x);
        validateNetherlands01(arr, partitionNum);
        netherlandsFlag02(arr2, x);
        validateNetherlands02(arr2, partitionNum);
    }

    public static void validateNetherlands01(int[] arr, int partitionNum) {
        // 找出最小区的右边界
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= partitionNum) {
                min++;
            }
        }
        if (min == arr.length - 1) {
            return;
        }
        // 判断min+1 到arr.length - 1 之间是否还存在小于等于partitionNum 的数，如果存在则抛出错误
        for (int i = min + 1; i < arr.length; i++) {
            if (arr[i] <= partitionNum) {
                System.out.println("出错了！！！！！");
            }
        }
    }

    public static void validateNetherlands02(int[] arr, int partitionNum) {
        // 找出最小区的右边界
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < partitionNum) {
                min++;
            }
        }
        if (min + 1 == arr.length - 1) {
            return;
        }
        // 判断min+1 到arr.length - 1 之间是否还存在小于等于partitionNum 的数，如果存在则抛出错误
        for (int i = min + 1; i < arr.length; i++) {
            if (arr[i] < partitionNum) {
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
