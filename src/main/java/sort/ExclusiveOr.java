package sort;

public class ExclusiveOr {

    public static void main(String[] args) {
        int[] arr = {1,3,1,3,3,3,5,1,5,5,5};
        System.out.println(findOdd(arr));

        System.out.println(getRightOne(30));
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

}
