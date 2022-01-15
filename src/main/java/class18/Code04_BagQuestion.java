package class18;

/**
 * 背包问题
 * 给出两个一维数组  w[] 表示重量，， v[]表示值，  w[i]、v[i] 表示分别表示第i号货物的重量和价值，
 * 还有个输入参数  int bag, 表示背包的能够放入的最大重量
 * 求背包中能够装下的最大价值
 *
 * 思路：
 *   先暴力枚举，并把这种暴力枚举变成递归实现
 *   每个货物要么选择要么不选择，可能的情况其实就是一棵二叉树
 *
 * @Author 张三金
 * @Date 2022/1/15 0015 9:33
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_BagQuestion {

    /**
     * 所有的货物，重量和价值，都在w和v数组中（认为其中都没有负数）
     * bag: 背包容量，不能超过这个重量
     * 返回：不超重的情况下，能够得到的最大价值
     */
    public static int maxValue (int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    /**
     * index: 处理index索引的货物，选择或者不选择, 并且认为index之前的货物已经做好了抉择
     * rest：背包剩余的容量
     * 返回价值
     */
    public static int process (int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            // 1. 这里必须小于0，因为考虑有些货物重量是0，但是有价值
            // 2. 必须返回-1，给上游，告诉它，你的决定是错的
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        // 不选择当前货物
        int p1 = process(w, v, index + 1, rest);
        // 选择当前货物
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            // base case 中的返回 -1 就是用在这里
            p2 = next + v[index];
        }
        return Math.max(p1, p2);
    }

    /**
     * 分析发现，这个递归过程中时存在两变量， 一个index， 一个 rest, 并且是会存在重复的子过程的
     * 比如 w = {3, 2, 5, ......}  v = {7, 4, 6}  bag = 15
     * 当w[0]选择，w[1]选择，w[2] 不选择，这个时候 index = 3, rest = 10
     * 当w[0]不选，w[1]不选，w[2]选择，这个时候，  index = 3, rest = 10
     * 很明显存在重复的子过程，所以使用记忆搜索或者 dp是很有必要的，
     */
    public static int maxValue2 (int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int[][] cache = new int[w.length + 1][bag + 1];
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                cache[i][j] = -2;
            }
        }
        return process2(w, v, 0, bag, cache);
    }

    public static int process2 (int[] w, int[] v, int index, int rest, int[][] cache) {
        if (cache[index][rest] != -2) {
            return cache[index][rest];
        }
        int ans = 0;
        if (rest < 0) {
            ans = -1;
        } else if (index == w.length) {
            ans = 0;
        } else {
            // 不选择当前货物
            int p1 = process(w, v, index + 1, rest);
            // 选择当前货物
            int p2 = 0;
            int next = process(w, v, index + 1, rest - w[index]);
            if (next != -1) {
                // base case 中的返回 -1 就是用在这里
                p2 = next + v[index];
            }
            ans = Math.max(p1, p2);
        }
        cache[index][rest] = ans;
        return ans;
    }

    /**
     * dp解决
     *
     * 注意： dp[i][j] 代表的是抽象当前 index 和 rest 的情况，这个情况是啥呢？
     * 就是在知道bag的 rest剩余容量的情况下 index 之前都选了，index选择或者不选择的背包最大价值
     */
    public static int maxValue3 (int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        // index 作为矩阵中的行，bag作为矩阵中的列
        int[][] dp = new int[w.length + 1][bag + 1];
        int row = dp.length;
        int col = dp[0].length;
        // index 行的值依赖 index + 1 行的值， 且最后一行的值都是 0
        // 注意最后一行都是0， 那么行数一定要比货物数量 大1，即 dp.length = w.length + 1; 因为只有这样，才能代表base case 中的 if(index == w.length) return 0;
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                // 这一步就相当于，递归base case 中的 if (rest < 0) return -1;
                if (j - w[i] >= 0) {
                    p2 = v[i] + dp[i + 1][j - w[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
//        int[] w = { 3, 2, 4, 7, 3, 1, 7, 2, 4, 8, 10};
        int[] w = { 3, 2, 4, 7, 3, 1, 7, 1};
//        int[] v = { 5, 6, 3, 19, 12, 4, 2, 6, 4, 3, 15};
        int[] v = { 5, 6, 3, 19, 12, 4, 2, 20};
        int bag = 15;
        System.out.println(maxValue(w, v, bag));
        System.out.println(maxValue2(w, v, bag));
        System.out.println(maxValue3(w, v, bag));
    }

}
