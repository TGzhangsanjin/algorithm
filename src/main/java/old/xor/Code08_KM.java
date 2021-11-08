package old.xor;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * 一个数组中有一种数出现了K次，其它数都出现了M次，M > 1, K < M,找到出现了K次的数，要求，额外空间复杂度为 0（1），时间复杂度为 O(N)
 *
 * @Author 张三金
 * @Date 2021/1/17 0017 14:41
 * @Company jzb
 * @Version 1.0.0
 */
public class Code08_KM {

    // 对数器
    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == k) {
                return integer;
            }
        }
        return -1;
    }

    /**
     * 生成一个随机数组，数组大小为maxLen, 数组范围为range
     * @param maKinds 有几种数
     * @param range 数组中数的范围
     * @param k 只有一种数出现了k次
     * @param m 其它所有的数都出现了m次
     * @return 一个有效的随机数组
     */
    public static int[] randomArray(int maKinds, int range, int k, int m) {
        // 出现k次的数字
        int kTimeNum = randomNumber(range);

        // 真命天子出现的次数 （以50%的概率出现k次）
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) +1);

        int numKinds =(int) (Math.random() * maKinds) + 2;
        // 数组的长度即为： k + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = kTimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(kTimeNum);

        while(numKinds != 0) {
            int currentNum = 0;
            do {
                currentNum = randomNumber(range);
            }while (set.contains(currentNum));
            set.add(currentNum); // 放一个新的数字
            numKinds--;
            // 在arr将这个新的数字填入，并且填入 m 个
            for (int i = 0; i < m; i++) {
                arr[index++] = currentNum;
            }
        }

        // 打乱数组 arr
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机的和j位置的数做交换
            int j = (int)(Math.random() * arr.length); // 0 ~ arrlength -1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    /**
     *  生成一个随机数
     * @param range 随机种子
     * @return 返回[-range, range] 中的一个随机数
     */
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    public static int onlyKTimes(int[] arr, int k, int m) {

        // 用于收集每个数在各个位置上1出现的次数
        // t[0] 0位置上的1出现了几个
        // t[0] 1位置上的1出现了几个
        int [] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                if ( ((num >> i) & 1) != 0) {
                    // 表示 num 在第 i 位上的二进制的值为 1
                    t[i]++;
                }
                // 上面这几行可以换成 t[i] += ((num >> i) & 1)
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
//            if (t[i] % m != 0) {
//                // 表示出现K次的这种数，在第 i 位置上是有 1 的 （其实这个时候 t[i]%m == k）
//                ans |= (1 << i); // 把ans二进制中每个位置上为1的赋值进去
//            }
            if (t[i] % m == 0) {
                continue;
            }
            if (t[i] % m == k) {
                ans |= (1 << i);
            } else {
                // 表名这种数没有出现m次，也没有出现 k 次
                return -1;
            }
        }

        if (ans == 0) {
            // 处理真命天子为 0， 且出现的次数不是k次的情况
           int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count ++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int kinds = 200;
        int range = 200;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始!");
        for (int i = 0; i < testTime; i++) {
            int a = ((int) Math.random() * max) + 1;  // a： 1 ~ max之间的数
            int b = ((int) Math.random() * max) + 1;  // b： 1 ~ max之间的数
            int k = Math.min(a, b);
            int m = Math.min(a, b);
            // 为了保证 k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimes(arr, k, m);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束!");
//        int[] arr = {3,1,3,3,1,1,4,4,4,5,5,5,-1,-1};
//        int k = 2;
//        int m = 3;
//        System.out.println(test(arr, 2, 3));
//        System.out.println(onlyKTimes(arr, 2, 3));
    }
}
