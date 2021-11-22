package class03;

/**
 * 异或的基本概念
 *  1. 异或运算，相同为 0，不同为 1
 *  2. 同或运算，相同为 1， 不同为 0
 *  异或原运算实际就是无进位相加
 *  3. 0^N = N ， N^N = 0
 *  4. 异或运算满足交换率和结合率
 * @Author 张三金
 * @Date 2021/11/19 0019 11:13
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_XORBase {

    /**
     * 适用异或运算交换数组中的两个数
     * 注意: 不能用來交換两个内存区域相同的数，否则连个数都会变成 0
     * @param array 数组
     * @param a
     * @param b
     */
    public static void exchangeTwoNum (int[] array, int a, int b) {
        if (array[a] == array[b]) {
           return;
        }
        array[a] = array[a] ^ array[b];
        array[b] = array[a] ^ array[b];
        array[a] = array[a] ^ array[b];
    }

    /**
     * 一个数组中，有一种数出现了奇数次，其它的数都出现了偶数次，怎么找到这种树，并打印出来
     */
    public static int oneOddOtherEven (int[] array) {
        int xor = 0;
        for (int i = 0; i < array.length; i++) {
            xor ^= array[i];
        }
        return xor;
    }

    /**
     * 提取一个数最右侧的一个 1 出来
     * a 与上 a取反加1，，也就是 a 与上 a的相反数
     * @param a
     * @return
     */
    public static int collectRightOne (int a) {
//        return a & (~a + 1);
        return a & (-a);
    }

}
