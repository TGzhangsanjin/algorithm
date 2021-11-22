package class03;

/**
 * 问题： 一个数组中，有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 * 思路：
 *  1. 假设这两个要找的数是 a 和 b ，先将所有的数异或，得到的值 xor = a ^ b;
 *  2. 提取xor最右侧的一个1来，得到 xor1, 假设最右侧的这个1是第x位，那么数组中所有的数可以分成两拨，一拨是第x位是1的，另一拨是第x位是0的
 *  3. 用 xor 去异或 第x位上是1的所有的数， 得到的结果就是其中一个数 a,,另一个数 b = a ^ xor
 * @Author 张三金
 * @Date 2021/11/19 0019 13:46
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_TwoOddOtherEven {

    /**
     * 输入一个数组，返回一个大小是2的数组，返回值中a和b中更小的那个数是a[0], 更大的那个数是 a[1]
     * @param array
     * @return
     */
    public static int[] findOdd (int[] array) {
        int xor = 0;
        for (int i = 0; i < array.length; i++) {
            xor ^= array[i];
        }
        // 取出 xor 最后侧的一个 1 来
        int xor1 = xor & (-xor);
        int a = 0;
        for (int i = 0; i < array.length; i++) {
            if ((xor1 & array[i]) > 0) {
                a ^= array[i];
            }
        }
        int b = xor ^ a;
        System.out.println("a == " + a + "b == " + b);
        return new int[]{a, b};
    }

    public static void checkFindOdd () {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {

        }
    }

    // ForTest 生成一个数组,这个数组中，有两个数出现了奇数次，其它数出现了偶数次
    public static int[] getArray (int size, int range) {

        int[] array = new int[size];

        // 第一个数出现的次数
        int firstOddTimes = (int) (Math.random() * size);
        while (firstOddTimes % 2 == 0) {
            firstOddTimes = (int) (Math.random() * size);
        }
        // 第一个数
        int firstNum = (int) (Math.random() * range) + 1;
        for (int i = 0; i < firstOddTimes; i++) {
            array[i] = firstNum;
        }
        // 第二个数出现的次数
        int secondOddTimes = (int) (Math.random() * (size - firstOddTimes));
        while (secondOddTimes % 2 == 0) {
            secondOddTimes = (int) (Math.random() * (size - firstOddTimes));
        }
        // 第二个数
        int secondNum = (int) (Math.random() * range) + 1;
        for (int i = firstOddTimes; i < firstOddTimes + secondOddTimes; i++) {
            array[i] = secondNum;
        }
        // 去填充其它出现偶数次的数
        return null;
    }

    public static void main(String[] args) {
        int[] array = {1,1,1,2,2,3,3,4,4,5,5,4,5,4};
        findOdd(array);
    }
}
