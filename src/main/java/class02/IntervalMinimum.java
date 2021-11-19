package class02;

/**
 * 区间最小值的问题
 * 给定一个数组，数组中任意相邻两个数不相等，数组汇总找到一个数，这个数的比他的左边和右边都大
 * 思路：
 *  1. 第一个数如果比第二个数小，则直接返回第一个数
 *  2. 最后一个数如果比倒数第二个数小，则直接返回最后一个数
 *  3. 如果情况1和情况2都不满足，则在数组中必能找到一个这样的一个满足条件的数
 *
 *  ps: 局部最小值问题，也表明了适用二分法，也不一定完全需要数组保证有序
 * @Author 张三金
 * @Date 2021/11/19 0019 10:26
 * @Company jzb
 * @Version 1.0.0
 */
public class IntervalMinimum {

    public static int min (int[] array) {
        if (array.length == 1 || array[0] < array[1]) {
            return 0;
        }
        if (array[array.length - 1] < array[array.length - 2]) {
            return array.length - 1;
        }
        // 这里可以直接从 1 和 array.length - 2 开始，这样就不会while循环里面的判断时，就不会出现数组越界的问题了，
        int left = 1;
        int right = array.length - 2;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (array[middle] > array[middle - 1]) {
                right = middle - 1;
            } else if (array[middle] > array[middle + 1]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array = array(oneTimeNums, range);
            int index = min(array);
            if (index == 0) {
                if (array[index] > array[1]) {
                    System.out.println("Opps111111!!!!");
                }
            } else if (index == (array.length - 1)) {
                if (array[array.length - 1] > array[array.length - 2]) {
                    System.out.println("Opps222222!!!!");
                }
            } else {
                if (array[index] > array[index - 1] || array[index] > array[index + 1]) {
                    System.out.println("Opps3333333!!!! index ==== " + index);
                }
            }
        }

    }

    /**
     * 生成一个满足条件的数组
     * @param size 数组大小
     * @param range 数组范围
     * @return 数组
     */
    public static int[] array (int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * range) + 1;
            if (i > 0) {
                while (array[i] == array[i - 1]) {
                    array[i] = (int) (Math.random() * range) + 1;
                }
            }
        }
        return array;
    }
}
