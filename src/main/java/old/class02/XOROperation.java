package old.class02;

import java.io.File;

/**
 * 异或运算的常见问题
 * @Author 张三金
 * @Date 2021/4/5 0005 23:03
 * @Company jzb
 * @Version 1.0.0
 */
public class XOROperation {

    /**
     * 使用异或的方式交换两个数的值
     */
    public static void swapNumber (int a, int b) {
        System.out.println("a=" + a + ", b=" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ", b=" + b);
    }

    /**
     * 一个数组中有一个数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这个数
     */
    public static int findOneOddNumber (int[] array) {
        if (array == null) {
            // 这里其实应该抛出异常
            return 0;
        }
        int xor = 0;
        // 因为偶数次个自己异或的结果就是0
        for (int i = 0; i < array.length; i++) {
            xor ^= array[i];
        }
        return xor;
    }

    /**
     * 一个数组中有两种数(这两个数不相等)出现了奇数次，其它数都出现了偶数次，怎么找到并打印这两种数
     */
    public static int[] findTwoOddNumber(int[] array) {
        if (array == null) {
            // 这里其实应该抛出异常
            return new int[]{0, 0};
        }
        int xor = 0;
        // 假设 a 和 b 就是出现了奇数次的两个数
        // 这个循环得出来的 old.xor = a ^ b
        for (int i = 0; i < array.length; i++) {
            xor ^= array[i];
        }
        // 提取 old.xor 最右侧的1来
        int rightOne = xor & (-xor);

        int a = 0;
        // 因为a和b不相等且出现了奇数次，那么 a和b在人rightOne的1的这个位置上肯定是a和b中其中一个为1其中一个为0
        // 下面这个循环就是得到在rightOne 1的这个位置上也为1的那个数
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & rightOne) != 0) {
                a ^= array[i];
            }
        }
        int b = xor ^ a;
        return new int[]{a, b};
    }

    public static void main(String[] args) {
//        swapNumber(7, -9);
//        int[] array = {2,2,2,3,2,3,7,-1,7};
//        System.out.println(findOneOddNumber(array));
//
//        int[] array1 = {2,2,2,3,2,3,6,7,-1,7, 6, 7};
//        int[] result = findTwoOddNumber(array1);
//        System.out.println(result[0] + "======"+ result[1]);

        File file = new File("file:///C:/Users/Administrator/Downloads/%E4%B8%9A%E4%B8%BB%E5%8D%95%E4%BD%8D%E5%88%97%E8%A1%A8%20-%20%E5%89%AF%E6%9C%AC.html");
        System.out.println(file);
    }
}
