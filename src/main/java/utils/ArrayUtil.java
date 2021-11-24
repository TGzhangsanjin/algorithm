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
     * 生成一个随机数组，数组大小时 size，，数组中每个数的范围区间是 [1, range]
     * @param size 数组大小
     * @param range 区间
     * @return 随机数组
     */
    public static int[] generateRandomArray (int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * range) + 1;
        }
        return array;
    }
}
