package class18;

/**
 * arr 是面值数组，其中的值都是正数且没有重复，在给定一个正数 aim
 * 每个值都认为是一种面值，且认为张数是无限的
 * 返回组成 aim 的方法数
 * 例如： arr = [1, 2], aim = 4
 * 方法如下： 1 + 1 + 1 + 1、 1 + 1 + 2、 2 + 2
 * 一共有 3 种方法，所以返回 3
 * @Author 张三金
 * @Date 2022/1/19 0019 23:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code13_CoinQuestion02 {

    public static int coin01 (int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        return process01(arr, 0, aim);
    }

    /**
     * 假设数组大小时 N
     * arr[i...i-1] 这些位置的货币已经用过了，做好了决定，
     * 返回 arr[i...N-1] 位置凑齐 rest 值的方法数
     */
    public static int process01 (int[] arr, int i, int rest) {
        int N = arr.length;
        // 这个判断可以不要，因为 arr[i] * zhang <= rest 里面确定了，rest - arr[i] * zhang >= 0, 所以传入递归中的rest参数一定是 >= 0的
//        if (rest < 0) {
//            return 0;
//        }
        if (i == N) {
            return rest == 0 ? 1 : 0;
        }
        // 看看当前位置的货币选择多少张
        int ans = 0;
        for (int zhang = 0; arr[i] * zhang <= rest ; zhang++) {
            ans += process01(arr, i + 1, rest - arr[i] * zhang);
        }
        return ans;
    }

    public static int coin02 (int[] arr, int rest) {
        if (arr == null || arr.length == 0 || rest < 1) {
            return 0;
        }
        int N = arr.length + 1;
        int M = rest + 1;
        int[][] dp = new int[N][M];
        // 初始化最后一行的数据
        dp[N-1][0] = 1;
        for (int i = N - 2; i >=0; i--) {
            for (int j = 0; j < M; j++) {

                // 这个得画出矩阵来看才比较好理解，
                // dp[i][j] 的值等于其 i + 1 行的 x1, x2, x3 这三个位置的和
                // 而dp[i][j-arr[i]] 的值等于 i + 1 行的 x1,x2 这两个位置的和
                // 而 dp[i][j-arr[i]] 这个值时比dp[i][j]的值先确定的，那么 dp[i][j] = dp[i][j-arr[i]] + x3
                dp[i][j] = dp[i+1][j]; // dp[i+1][j] 就是 x3
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
//                for (int zhang = 0; arr[i] * zhang <= j ; zhang++) {
//                    dp[i][j] += dp[i + 1][j - arr[i] * zhang];
//                }
            }
        }

        return dp[0][M-1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        System.out.println(coin01(arr, 4));
        System.out.println(coin02(arr, 4));


        int testTimes = 1000;
        int oneTimeNums = 10;
        int range = 100;
        boolean success = true;
        System.out.println("测试开始！");
        for (int i = 0; i < testTimes; i++) {
            int rest = (int) (Math.random() * 250) + 1;
            int[] array = generateRandomArray(oneTimeNums, range);
            int count01 = coin01(array, rest);
            int count02 = coin02(array, rest);
            if (count01 != count02) {
                System.out.println("Opps!");
                success = false;
                break;
            }
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
