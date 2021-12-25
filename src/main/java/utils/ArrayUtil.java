package utils;

/**
 * 一些数组相关的会使用很多次的方法
 * @Author 张三金
 * @Date 2021/11/24 0024 9:28
 * @Company jzb
 * @Version 1.0.0
 */
public class ArrayUtil {

    /**
     * 生成一个随机数组，数组大小时 size，，数组中每个数的范围区间是 [-range, range]
     * @param size 数组大小
     * @param range 区间
     * @return 随机数组
     */
    public static int[] generateRandomArray (int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
        }
        return array;
    }

    /**
     * 生成一个随机数组，数组大小时 size，，数组中每个数的范围区间是 [-range, range]
     * @param size 数组大小
     * @param range 区间
     * @return 随机数组
     */
    public static long[] generateRandomArray02 (int size, int range) {
        long[] array = new long[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
        }
        return array;
    }

    /**
     * 生成一个随机数组，数组大小时 size，，数组中每个数的范围区间是 [1, range]
     * @param size 数组大小
     * @param range 区间
     * @return 随机数组
     */
    public static int[] generateRandomArray03 (int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * range) + 1);
        }
        return array;
    }

    /**
     * 生成一个随机数组，数组大小是[sizeMin, sizeMax]，，数组中每个数的范围区间是 [1, range]
     * @param sizeMin 数组的最小长度
     * @param sizeMax 数组的最大长度
     * @param range 区间
     * @return 随机数组
     */
    public static int[] generateRandomArray (int sizeMin, int sizeMax, int range) {
        int size = sizeMin + (int) (Math.random() * (sizeMax - sizeMin + 1));
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * range) + 1);
        }
        return array;
    }

    /**
     * 交换数组 array 中 a和b位置上的两个数进行交换
     * @param array 数组
     */
    public static void swapTwoNum (int[] array, int a, int b) {
        if (a == b || array[a] == array[b]) {
            return;
        }
        array[a] = array[a] ^ array[b];
        array[b] = array[a] ^ array[b];
        array[a] = array[a] ^ array[b];
    }

    /**
     * 交换数组 array 中 a和b位置上的两个数进行交换
     * @param array 数组
     */
    public static <T extends Object> void swapTwoNum (T[] array, int a, int b) {
        if (a == b || array[a] == array[b]) {
            return;
        }
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * 复制一个数组
     * @param array 数组
     */
    public static int[] copyArray (int[] array) {
        int[] copyArray = new int[array.length];
        for (int i = 0; i < copyArray.length; i++) {
            copyArray[i] = array[i];
        }
        return copyArray;
    }

    /**
     * 复制一个数组
     * @param array 数组
     */
    public static String[] copyArray (String[] array) {
        if (array == null || array.length < 1) {
            return null;
        }
        String[] copyArray = new String[array.length];
        for (int i = 0; i < copyArray.length; i++) {
            copyArray[i] = array[i];
        }
        return copyArray;
    }


    /**
     * 复制一个数组
     * @param array 数组
     */
    public static long[] copyArray (long[] array) {
        long[] copyArray = new long[array.length];
        for (int i = 0; i < copyArray.length; i++) {
            copyArray[i] = array[i];
        }
        return copyArray;
    }
}
