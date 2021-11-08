package old.class04;

/**
 * 逆序对问题
 * 描述：在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有逆序对
 * 思路： 使用归并排序，在merge方法中，对于左组中的每一个数x，看x找出右组中有多少个数比x大
 * @Author 张三金
 * @Date 2021/2/3 0003 8:27
 * @Company jzb
 * @Version 1.0.0
 */
public class ReversePair {


    public static int getReversePair(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }

        return process(array, 0, array.length - 1);
    }

    public static int process(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }

        int middle = left + ((right - left) >> 1);
        return process(array, left, middle) + process(array, middle + 1, right) + merge(array, left, middle, right);
    }

    public static int merge (int[] array, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        int windowLeft = middle;
        int windowRight = right;
        int i = help.length - 1;
        int count = 0;
        while (windowLeft >= left && windowRight > middle) {

            count += array[windowLeft] > array[windowRight] ? (windowRight - middle) : 0;

            // 注意，把更小的数拷贝到help中，当相等时应该先把右组的数拷贝到help中
            help[i--] = array[windowLeft] > array[windowRight] ? array[windowLeft--] : array[windowRight--];
        }
        while (windowLeft >= left) {
            help[i--] = array[windowLeft--];
        }
        while (windowRight > middle) {
            help[i--] = array[windowRight--];
        }

        // 拷贝数组
        for (int j = left; j <= right; j++) {
            array[j] = help[j - left];
        }
        return count;
    }

    /**
     * 错误的方法
     */
    public static int mergeError (int[] array, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        int windowLeft = left;
        int windowRight = middle + 1;
        int i = 0;
        int count = 0;
        while (windowLeft <= middle && windowRight <= right) {
            // 处理逆序对的个数
            if (array[windowLeft] == array[windowRight]) {
                // 这里不行的原因是，相等的时候这样算是不行的，你不知道array[windowRight] 之前有多少个数比array[windowLeft]小，
                // 并不是windowRight - middle - 1,, 而采用降序的方式就不用考虑这个问题了，因为降序的时候只要相等，就直接左移windowRight,
                // 直到找到小于array[windowLeft]的那个数
                count += windowRight - middle - 1;
            } else if (array[windowLeft] > array[windowRight]) {
                count += windowRight - middle;
            }
//            count += array[windowLeft] > array[windowRight] ? (windowRight - middle) : 0;

            // 注意，当相等时应该先把左组的数拷贝到help中
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
        return count;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNum = 1100;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array1 = new int[oneTimeNum];
            for (int j = 0; j < array1.length; j++) {
                array1[j] = (int)(Math.random() * range) + 1;
            }
            int[] array2 = copyArray(array1);
            if (getReversePair(array1) != test(array2)) {
                System.out.println("出错了！");
            }
        }
    }

    public static int[] copyArray(int[] array1) {
        int[] array2 = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[i];
        }
        return array2;
    }

    /**
     * for test
     */
    public static int test(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int sums = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[i]) {
                    sums++;
                }
            }
        }
        return sums;
    }
}
