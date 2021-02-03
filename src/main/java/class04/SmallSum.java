package class04;

/**
 *
 * 小和问题
 * 问题描述： 对于每个数字，将左边比该数字小的数累加起来放到到该数字所在的位置上，最终求整个数组的和，即为小和
 * 思路：递归归并的方法求解，对于左数组中的每个数，求右数组中有多少个数比它大，
 * 例如：左数组left_arr[i]在右数组中有x个数比left_arr[i]大，则需要累加 x * left_arr[i]
 * 注意：merge方法中，当两个数相等的数一定要先拷贝右组的数，因为求的是左边比右边小的数
 * @Author 张三金
 * @Date 2021/2/3 0003 7:24
 * @Company jzb
 * @Version 1.0.0
 */
public class SmallSum {


    public static int getSmallSum(int[] array) {
        if (array == null || array.length < 2) {
            return Integer.MIN_VALUE;
        }
        return process(array, 0, array.length - 1);
    }

    public static int process(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        int leftSum = process(array, left, middle);
        int rightSum = process(array, middle +1, right);
        return leftSum + rightSum + merge(array, left, middle, right);
    }

    public static int merge(int[] array, int left, int middle,  int right) {

        int[] help = new int[right - left + 1];
        int windowLeft = left;
        int windowRight = middle + 1;
        // help数组的下标
        int i = 0;
        // merge的累加和
        int sum = 0;
        while(windowLeft <= middle && windowRight <= right) {
            // 处理累加
            sum += array[windowLeft] < array[windowRight] ? array[windowLeft] * (right - windowRight + 1) : 0;

            help[i++] = array[windowLeft] < array[windowRight] ? array[windowLeft++] : array[windowRight++];
        }
        while (windowLeft <= middle) {
            help[i++] = array[windowLeft++];
        }
        while (windowRight <= right) {
            help[i++] = array[windowRight++];
        }
        // 拷贝数组
        for (int j = left; j <= right; j++) {
            array[j] = help[j - left];
        }
        return sum;
    }

    /**
     * 暴力求解的办法
     */
    public static int m(int[] array) {
        if (array == null || array.length < 2) {
            return Integer.MIN_VALUE;
        }
        // 添加一个smallSum数组，放每个位置的小和
        int[] help = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            int smallSum = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    smallSum += array[j];
                }
            }
            help[i] = smallSum;
        }
        // 各个位置上小和的累加和
        int totalSmallSum = 0;
        for (int i = 0; i < help.length; i++) {
            totalSmallSum += help[i];
        }
        return totalSmallSum;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int range = 100;
        int oneTimeNums = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = new int[oneTimeNums];
            for (int j = 0; j < oneTimeNums; j++) {
                arr1[j] = (int) (Math.random() * range) + 1;
            }
            int[] arr2 = arr1.clone();
            if (m(arr1) != getSmallSum(arr2)) {
                System.out.println("出错了！！！");
            }
        }
    }



}
