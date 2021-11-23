package class04;

/**
 * 递归获取最大值
 * @Author 张三金
 * @Date 2021/11/23 0023 11:03
 * @Company jzb
 * @Version 1.0.0
 */
public class Code11_RecursionGetMax {

    public static int getMax (int[] array) {
        if (array.length < 2) {
            return array[0];
        }
        return process(array, 0, array.length - 1);
    }

    public static int process (int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        }
        int middle = left + ((right - left) >> 1);
        int leftMax = process(array, left, middle);
        int rightMax = process(array, middle + 1, right);
        return Math.max(leftMax, rightMax);
    }
}
