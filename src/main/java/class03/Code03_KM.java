package class03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 一个数组中，有一种数出现了K次，其他数都出现了 M次， M>1, K<M,找到出现了 K次的数，
 * 要求：额外空间复杂度 O(1), 时间复杂度 O(N)
 *
 * 思路：
 *  1. 准备一个额外的32位数组，array01, 用来存放原条件数组各个位置上的二进制和，
 *     比如，将原条件数组所有数的二进制第一位相加的和，放入 array01[0]中，原条件数组所有数的二进制第二位的和放入 array01[1]中
 *  2. 假设这个出现了 K 次的数是a，如果 array01[31]的值模M为0，则说明 a的第一位是0，反之则为1,
 *     即 array01[0]%M == 0, 则 a的第一位是0，反之则为1， array01[1]%M != 0, 则 a 的第一位是1，反之则为0
 * @Author 张三金
 * @Date 2021/11/19 0019 14:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_KM {

    public static int onlyKTimes (int[] array, int k, int m) {
        int[] t = new int[32];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 32; j++) {
                // (array[i] >> i) & 1 用来判断 array[i] 这个数在第j上是否是1，，
                // 注意： 二进制右边的第一个数表示对应的是数组下标时 0，从右边数第32个数对应的数组下标时31
                t[j] += (array[i] >> j) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                if (t[i] % m == k) {
                    // 表示目标数在第i位上是1
                    ans |= (1 << i);
                } else {
                    // 如果找不到这种数，则直接返回 -1 报错
                    return -1;
                }


            }

        }
        // 如果出现k次的是0，则单独处理判断一下
        if (ans == 0) {
            int count = 0;
            for (int num : array) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxKinds = 5; // 最多允许有多少种数
        int range = 1000; // 每个数的大小范围 [-range, range]
        int testTimes = 10000;
        int max = 100; // m的最大值
        for (int i = 0; i < testTimes; i++) {
            int a = (int)(Math.random() * max) + 1;
            int b = (int)(Math.random() * max) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] array = randomArray(maxKinds, range, k, m);
            int ans01 = onlyKTimes(array, k, m);
            int ans02 = test(array, k , m);
            if (ans01 != ans02) {
                System.out.println("Opps!!!!!!!");
            }
        }
    }

    public static int test (int[] array, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == k) {
                return integer;
            }
        }
        return -1;
    }

    public static int[] randomArray (int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNum(range);
        // 真命天子出现的次数
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNum(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // [-range, range] 这个数出现的范围
    public static int randomNum (int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
}
