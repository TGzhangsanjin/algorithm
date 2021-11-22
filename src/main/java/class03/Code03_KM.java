package class03;

/**
 * 一个数组中，有一种数出现了K次，其他数都出现了 M次， M>1, K<M,找到出现了 K次的数，
 * 要求：额外空间复杂度 O(1), 时间复杂度 O(N)
 *
 * 思路：
 *  1. 准备一个额外的32位数组，array01, 用来存放原条件数组各个位置上的二进制和，
 *     比如，将原条件数组所有数的二进制第一位相加的和，放入 array01[31]中，原条件数组所有数的二进制和放入 array01[30]中
 *  2. 假设这个出现了 K 次的数是a，如果 array01[31]的值模M为0，则说明 a的第一位是0，反之则为1,
 *     即 array01[31]%M == 0, 则 a的第一位是0，反之则为1， array01[31]%M != 0, 则 a 的第一位是1，反之则为0
 * @Author 张三金
 * @Date 2021/11/19 0019 14:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_KM {

    public static int findK (int[] array, int k, int m) {
        int[] temp = new int[32];
        // 得到每个位上有多少个1
        for (int i = temp.length - 1; i >= 0 ; i--) {
            for (int j = 0; j < array.length; j++) {
                int num = array[j] << i;
                num = num & (-num);
                if ((num & 1) != 0) {
                    temp[i]++;
                }
            }
        }
        for (int i = temp.length - 1; i >= temp.length - 4 ; i--) {
//            System.out.println(temp[i] + ",");
        }
        int ans = 0;
//        for (int i = 0; i < temp.length; i++) {
//            if ((temp[i] % m != 0) && (temp[i] % m == k)) {
//                // 表名出现了k次的数，在第i位上是1
//                ans |= (1 << i);
//            } else {
//                return -1;
//            }
//        }
        return ans;
    }

    public static void main(String[] args) {
//        0001, 0010, 0010
        int[] array = {3,2,2};
        System.out.println(findK(array, 1,2));

    }
}
