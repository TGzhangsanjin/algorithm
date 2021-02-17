package class05;

/**
 * 求区间和个数
 * 问题描述： 给定一个数组arr，两个整数lower和upper，返回arr中有多少个子数组的累加和[lower,upper]范围上
 * 思路：
 * 1. 对于原数组array, 位置i到位置j的累加和Sum(i...j)在范围[Lower, Upper] 上，等价于Sum(0...j) - Sum(0...i-1)在[Lower, Upper]上；
 * 2. 假设0...j的累加和Sum(0...j) = x,  求必须以j结尾的子数组的累加和有多少个在[Lower, Upper], 等同于求j之前的所有前缀和中游多少个前缀和在[x-Upper, x-Lower]上;
 * 3. 先求每个位置上的累加和，生成一个前缀累加和数组， 去处理累加和数组即可
 * @Author 张三金
 * @Date 2021/2/7 0007 19:27
 * @Company jzb
 * @Version 1.0.0
 */
public class CountOfRangeSum {

    public static int getCountOfRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        // 先生成累加和数组，后续的操作都在累加和数组上进行
        long[] sumArray = new long[nums.length];
        sumArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArray[i] += sumArray[i - 1] + nums[i];
        }
        return process(sumArray, 0, nums.length - 1, lower, upper);
    }

    public static int process(long[] array, int left, int right, int lower, int upper) {
        if (left == right) {
            // 这里需要特别注意，当数组中只有一个数字的时候，一定需要处理
            return array[left] >= lower && array[left] <= upper ? 1 : 0;
        }
        int middle = left + ((right - left) >> 1);
        return process(array, left, middle, lower, upper)
                + process(array, middle + 1, right, lower, upper)
                + merge(array, left, middle, right, lower, upper);
    }

    public static int merge(long[] array, int left, int middle,  int right, int lower, int upper) {
        int ans = 0;
        int windowL = left;
        int windowR = left;
        // windowLeft 和 windowRight 两个下标在for循环中时不回退的
        for (int i = middle + 1; i <= right; i++) {
            long min = array[i] - upper;
            long max = array[i] - lower;
            while(array[windowL] < min && windowL <= middle) {
                windowL++;
            }
            while(array[windowR] <= max && windowR <= middle) {
                windowR++;
            }
            ans += windowR - windowL;
        }

        // 处理排序
        long[] help = new long[right - left + 1];
        int p1 = left;
        int p2 = middle + 1;
        int i = 0;
        while (p1 <= middle && p2 <= right) {
            help[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }
        while (p1 <= middle) {
            help[i++] = array[p1++];
        }
        while (p2 <= right) {
            help[i++] = array[p2++];
        }
        // 拷贝数组
        for (int j = left; j <= right ; j++) {
            array[j] = help[j - left];
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int arraySize = 1000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            // 生成两个随机数，lower 和  upper
            int lower = (int) (Math.random() * range) - 1;
            int upper = (int) (Math.random() * range) - 1;
            while(lower >= upper) {
                upper = (int) (Math.random() * range) - 1;
            }

            int[] array = new int[arraySize];
            for (int j = 0; j < arraySize; j++) {
                array[j] = (int) (Math.random() * range) - 1;
            }
            int[] array1 = copyArray(array);
            if (getCountOfRangeSum(array, lower, upper) != test(array1, lower, upper)) {
                System.out.println("出错了！！！");
            }
        }
    }


    public static int test (int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        // 这里还需要用long的方式来处理
        long[] sumArray = new long[nums.length];
        sumArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArray[i] += sumArray[i - 1] + nums[i];
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sumArray[i] >= lower && sumArray[i] <= upper) {
                ans ++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                long temp = sumArray[j] - sumArray[i];
                if (temp >= lower && temp <= upper) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int[] copyArray(int[] array1) {
        int[] array2 = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[i];
        }
        return array2;
    }
}
