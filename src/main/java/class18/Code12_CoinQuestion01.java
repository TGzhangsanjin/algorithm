package class18;

import utils.ArrayUtil;

/**
 * arr是货币数组，其中的值都是正数，再给定一个正数min.
 * 每个值都认为是一张货币，即便是相同的货币也认为每一张都是不同的，
 * 返回组成 aim 的方法数
 * 例如：arr = [1,1,1], aim = 2
 * arr[0] 和 arr[1] 能组成 2，arr[1] 和 arr[2] 能组成 2，arr[0] 和 arr[2] 能组成2
 * 一共就 3 种方法，所以返回 3
 *
 * 很明显，这是一个从左往右的尝试模型
 * @Author 张三金
 * @Date 2022/1/19 0019 22:47
 * @Company jzb
 * @Version 1.0.0
 */
public class Code12_CoinQuestion01 {

    public static int coin01 (int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        return process01(arr, 0, aim);
    }

    /**
     * 数组长度是 N
     * arr[0...i-1]这些位置上的数字已经决定好了，且剩下需要凑齐的值是 aim
     * 返回 arr[i...N-1] 这些位置上凑齐 rest 值的方法数
     */
    public static int process01 (int[] arr, int i, int rest) {
        int N = arr.length;
        if (i == N) {
            // 越界了，这个时候如果刚好剩余的rest 是0， 那么之前的尝试就是对的，就是一种方法
            return rest == 0 ? 1 : 0;
        }
        if (rest < 0) {
            // 剩余小于0了，说明你做的这种尝试肯定不对
            return 0;
        }

        // 当前位置的货币不选择
        int p1 = process01(arr, i + 1, rest);
        // 当前位置的货币选择
        int p2 = process01(arr, i + 1, rest - arr[i]);
        return p1 + p2;
    }

    public static int coin02 (int[] arr, int rest) {
        if (arr == null || arr.length == 0 || rest < 1) {
            return 0;
        }
        int N = arr.length;
        int M = rest;
        int[][] dp = new int[N+1][M + 1];
        // 最后一行的数据就初始化好了
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] =  dp[i + 1][j];
                if (j - arr[i] >= 0 && j - arr[i] <= M) {
                    dp[i][j] += dp[i + 1][j - arr[i]];
                }
            }
        }
        return dp[0][M];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        System.out.println(coin01(arr, 2));
        System.out.println(coin02(arr, 2));

        int testTimes = 1000;
        int oneTimeNums = 40;
        int range = 100;
        boolean success = true;
        System.out.println("测试开始！");
        for (int i = 0; i < testTimes; i++) {
            int aim = (int) (Math.random() * 750) + 1;
            int[] array = generateRandomArray(oneTimeNums, range);
//            int count01 = coin01(array, aim);
            int count02 = coin02(array, aim);
//            if (count01 != count02) {
//                System.out.println("Opps!");
//                success = false;
//                break;
//            }
        }
        if (success) {
            System.out.println("测试结束！");
        }
    }

    /**
     * 生成一个随机数组，数组大小是[1, sizeMax]，，数组中每个数的范围区间是 [1, range]
     * @param sizeMax 数组的最大长度
     * @param range 区间
     * @return 随机数组
     */
    public static int[] generateRandomArray (int sizeMax, int range) {
        int size = (int) (Math.random() * sizeMax) + 1;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * range) + 1);
        }
        return array;
    }

}
