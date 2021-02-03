package class04;

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
        int windowLeft = 0;
        int windowRight = 0;
        int i = 0;
        int count = 0;
        while (windowLeft <= middle && windowLeft <= right) {
            // 处理逆序对的个数
            count += array[windowLeft] > array[windowRight] ? (right - middle + 1) : 0;
            // 注意，当相等时应该先把左组的数拷贝到help中
            help[i++] = array[windowLeft] <= array[windowRight] ? array[windowLeft++] : array[windowRight++];
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
}
