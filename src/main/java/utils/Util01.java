package utils;

/**
 * @Author 张三金
 * @Date 2021/3/19 0019 22:34
 * @Company jzb
 * @Version 1.0.0
 */
public class Util01 {

    /**
     * 生成一个正整数构成的随机数组
     * @param size 数组的大小
     * @param range 数组的范围  [1, range]
     * @return 返回一个随机数组
     */
    public static int[] randomArray(int size, int range) {
        if (size < 1 || range < 1) {
            return null;
        }
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * range) + 1;
        }
        return array;
    }

    /**
     *  拷贝数组
     */
    public static int[] copyArray(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int[] targetArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            targetArray[i] = array[i];
        }
        return targetArray;
    }

    /**
     * 交换数组中两个下标的值
     */
    public static void swapArray(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
