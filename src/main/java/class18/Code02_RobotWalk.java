package class18;

/**
 * 问题描述：
 *  假设有排成一行的N个位置，记为 1到N，N一定大于或等于2，开始的时候机器人在start位置上（ 1<=cur<=N），机器人一步只能往左或者往右移动一个位置
 *  假如机器人来到 1 位置，那么下一步只能往右来到 2位置，假如机器人来到 N 位置，那么下一步只能往左来到 N-1位置
 *  假如机器人来到中间位置，那么下一步可以往左或者往右移动
 *  假设机器人必须走 K步， 最终来到 aim 位置（1<=aim<=N）的方法有多少种
 *  给定四个参数： N，start， aim， K, 返回方法数
 * @Author 张三金
 * @Date 2022/1/10 0010 11:22
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_RobotWalk {

    /**
     * start : 开始位置
     * aim: 目标位置
     * N: 一共有多少个位置
     * K: 走多少步
     * 返回：所以走法的数量
     */
    public static int robotWalk01 (int start, int aim, int N, int K) {
        return process01(start, K, aim, N);
    }

    /**
     * cur: 机器人当前来到的位置
     * rest: 机器人还有多少步需要走
     * aim: 机器人的目标位置
     * N: 所以逇位置 1~N
     * 返回：及其人从cur位置走rest步来到 aim，一共有多少种走法
     */
    public static int process01 (int cur, int rest, int aim, int N) {
        if (rest == 0) {
            // 如果不能再走了，这个是机器人来到了 aim 位置，则说明当前这是一种走法，如果没有来到 aim位置，说明这不是一种走法
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            // 往右走一步
            return process01(cur + 1, rest - 1, aim, N);
        }
        if (cur == N) {
            // 往左走一步
            return process01(cur - 1, rest - 1, aim, N);
        }
        // 往左或者往右的所有情况的总和
        return process01(cur + 1, rest - 1, aim, N) + process01(cur - 1, rest - 1, aim, N);
    }

    // 加缓存的方式
    public static int robotWalk02 (int start, int aim, int N, int K) {
        // 注意这里dp数组的大小应该是由 N 和 K决定的
        int[][] dp = new int[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return process02(start, K, aim, N, dp);
    }

    public static int process02 (int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            // 命中缓存了
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process02(cur + 1, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process02(cur - 1, rest - 1, aim, N, dp);
        } else {
            ans = process02(cur + 1, rest - 1, aim, N, dp) + process02(cur - 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int start = 2;
        int aim = 10;
        int N = 12;
        int K = 100;
        System.out.println(robotWalk02(start, aim, N, K));
        System.out.println(robotWalk01(start, aim, N, K));

    }
}
