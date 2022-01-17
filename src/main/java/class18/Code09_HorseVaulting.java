package class18;

/**
 * 跳马问题
 *  问题描述：
 *      想象一个象棋的棋盘，把整个棋盘放入坐标系中的第一象限，棋盘的最左下角是 (0, 0) 位置
 *      那么整个棋盘就是横坐标上9条线，纵坐标上10条线的区域，给出三个参数 x, y, k
 *      返回 "马" 从(0, 0) 位置出发走 k 步，最终落在(x, y) 位置上的走法有多少种？
 * @Author 张三金
 * @Date 2022/1/17 0017 11:46
 * @Company jzb
 * @Version 1.0.0
 */
public class Code09_HorseVaulting {

    public static int horseVaulting (int x, int y, int k) {
        if (x < 0 || y < 0 || k <= 0) {
            return 0;
        }
        return process(x, y, 0, 0, k);
    }


    /**
     * (x, y) 目标位置
     * (i, j) 当前所在位置
     * rest: 剩余的步数
     * 返回(i, j) 位置走到(x, y)位置，走 rest 步的所有可能性
     */
    public static int process (int x, int y, int i, int j, int rest) {
        if (i < 0 || j < 0 || i > 8 || j > 9) {
            // 规避一些跳出棋盘的越界的情况
            return 0;
        }
        if (rest == 0) {
            // 剩余 0 步，且刚好停在了目标位置，说明是一种有效解
            return (x == i && y == j) ? 1 : 0;
        }
        // 马跳日一共有8中跳法
        int ways = process(x, y , i + 2, j + 1, rest - 1);
        ways += process(x, y, i + 1, j + 2, rest - 1);
        ways += process(x, y, i - 1, j + 2, rest - 1);
        ways += process(x, y, i - 2, j + 1, rest - 1);
        ways += process(x, y, i + 2, j - 1, rest - 1);
        ways += process(x, y, i + 1, j - 2, rest - 1);
        ways += process(x, y, i - 1, j - 2, rest - 1);
        ways += process(x, y, i - 2, j - 1, rest - 1);
        return ways;
    }

    /**
     * 改写 dp, 三个可变参数 i、j、rest，那就就相当于一个三维的空间
     * 这里看 rest 进行讨论， 通过 递归的 base case 发现可以将所有 rest == 0 的数据初始化
     * 递归的含义中可以得知： 指定rest上的值，可以根据 rest-1 的值推导出来，最终返回 dp[a][b][rest] 即可
     *
     * ps: 这里变量特别多，注意千万别搞混了
     */
    public static int dp (int a, int b, int k) {
        if (a < 0 || b < 0 || k <= 0) {
            return 0;
        }
        int[][][] dp = new int[9][10][k + 1];
        // 初始化 rest == 1 的数据，除了dp[9][10][0] = 1， 其它都是 0
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 10; y++) {
                    dp[x][y][rest] = pick(dp,x + 2, y + 1, rest - 1);
                    dp[x][y][rest] += pick(dp,x + 1, y + 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x- 1, y + 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x - 2, y + 1, rest - 1);
                    dp[x][y][rest] += pick(dp,x+ 2, y - 1, rest - 1);
                    dp[x][y][rest] += pick(dp,x+ 1, y - 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x- 1, y - 2, rest - 1);
                    dp[x][y][rest] += pick(dp,x- 2, y - 1, rest - 1);
                }
            }
        }

        return dp[0][0][k];
    }

    public static int pick (int[][][] dp, int x, int y, int z) {
        if (x < 0 || y < 0 || x > 8 || y > 9) {
            // 规避一些跳出棋盘的越界的情况
            return 0;
        }
        return dp[x][y][z];
    }

    public static void main(String[] args) {

        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(horseVaulting(x,y,step));
        System.out.println(dp(x,y,step));
    }
}
