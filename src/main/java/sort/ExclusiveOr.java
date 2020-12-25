package sort;

public class ExclusiveOr {

    public static void main(String[] args) {
        int[] arr = {1,3,1,3,3,3,5,1,5,5,5,6,6,7};
        printOddTimes2(arr);
        // @TODO  写一个对数器，来验证，算法方法 printOddTimes2()

    }

    /**
     * 一个数组中有一个数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这个数
     * @param arr 数组
     * @return 返回个数为奇数的数
     */
    public static int findOdd (int [] arr) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        return xor;
    }

    /**
     *  提取一个int类型数组的最右侧的一个1， 例如， 10100，则提取出100，即是舍去最右侧一个1的左边之后留下数
     * @return 返回最右侧的一个1
     */
    public static int getRightOne(int a) {
        return a & (~a + 1);
//        return a & (-a);
    }

    /**
     * 一个树祖宗，有两种数存在奇数次，其它数都存在偶数次，打印这两种树
     * @param arr
     */
    public static void printOddTimes2(int[] arr) {

        // 先获取到 a^b 的值, 假设 a和b是这两种数
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            // xor == a ^ b
            xor ^= arr[i];
        }

        // 提取xor最右侧的一个1来，
        // 例如： 10000111001111100
        //提取后: 00000000000000100
        int rightOne = xor & (~xor + 1);
        int xor_ = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((rightOne & arr[i]) != 0) {
                xor_ ^= arr[i];
            }
        }
        //xor_ 就是 a、b中的1种数，，那么另一个数就是  xor_ ^ xor

        System.out.println("打印出这两个数：" +  xor_ + "、" + (xor_ ^ xor));


    }

}
