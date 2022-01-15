package class18;

/**
 * 最长公共子序列问题
 *
 * leetcode 链接：https://leetcode.com/problems/longest-common-subsequence/
 *
 * @Author 张三金
 * @Date 2022/1/15 0015 19:06
 * @Company jzb
 * @Version 1.0.0
 */
public class Code07_LongestCommonSubsequence {

    public static int longestCommonSubsequence (String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        return process(c1, c2, c1.length - 1, c2.length - 1);
    }


    /**
     * 返回 text1[0...i] 和 text[0...j] 的最长公共子序列的长度
     *
     */
    public static int process (char[] text1, char[] text2, int i, int j) {
        if (i == 0 && j == 0) {
            // 都只剩一个字符了
            return text1[i] == text2[j] ? 1 : 0;
        } else if (i == 0) {
            // i 到底了，看看 text1[i] 和 text2[j] 是否相同，相同就返回1，不相同就接着往下遍历 j
            if (text1[i] == text2[j]) {
                return 1;
            } else {
                return process(text1, text2, i, j - 1);
            }
        } else if (j == 0) {
            if (text1[i] == text2[j]) {
                return 1;
            } else {
                return process(text1, text2, i - 1, j);
            }
        } else {
            /**
             * 这里采用的是样本分析法，根据i，去分析各种可能性，这里只有四种可能
             * 1. 最长公共子序列一定不以 text1[i] 结尾，但一定不以 text1[j] 结尾
             * 2. 最长公共子序列可能以 text1[i] 结尾，但一定不以 text[j] 结尾
             * 3. 最长公共子序列一定不以 text[i] 结尾，但可能以 text[j] 结尾
             * 4. 最长公共子序列一定以 text[i] 和 text[j] 结尾
             *
             * 其实第1中可能就包含在了 2 和 3 中
             *
             * 这样分的话就比较清楚，代码虽抽象，但简单
             */
            // 这是第2中情况
            int p1 = process(text1, text2, i, j - 1);
            // 这是第3中情况
            int p2 = process(text1, text2, i - 1, j);
            // 这是第4中情况
            int p3 = text1[i] == text2[j] ? (1 + process(text1, text2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    /**
     * 动态规划改写
     * 其实就是就是把递归抽象成一个矩阵，现有递归的思路，才会有dp
     */
    public static int dp (String text1, String text2) {
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        int row = c1.length;
        int col = c2.length;
        int[][] dp = new int[row][col];
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        // 处理第一行的数据 (相当于递归中的 else if (i == 0))
        for (int i = 1; i < col; i++) {
            dp[0][i] = c1[0] == c2[i] ? 1 : dp[0][i - 1];
        }
        // 处理第一列的数据 (相当于递归中的 else if (j == 0))
        for (int i = 1; i < row; i++) {
            dp[i][0] = c1[i] == c2[0] ? 1 : dp[i -1][0];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = c1[i] == c2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestCommonSubsequence(s1, s2));
        System.out.println(dp(s1, s2));
    }
}
