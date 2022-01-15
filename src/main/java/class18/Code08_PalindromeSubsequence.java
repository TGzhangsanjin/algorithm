package class18;

/**
 * 最长回文序列
 * leetcode: https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * ps: 子串一般指需要连续的字符串，子序列一般指不需要连续的字符串  但具体的含义还是要看题目的指定
 * @Author 张三金
 * @Date 2022/1/15 0015 23:34
 * @Company jzb
 * @Version 1.0.0
 */
public class Code08_PalindromeSubsequence {

    /**
     * 动态规划改写
     * 其实就是就是把递归抽象成一个矩阵，现有递归的思路，才会有dp
     */
    public static int lastDp (String text1, String text2) {
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

    /**
     * 借用最长公共子序列的方法 求出字符串 s 的逆序字符串 s1, 那么 s 和 s1 的最长公共子序列就是 s 的最长回文序列
     */
    public static int longestPalindromeSubseq (String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String reverse = reverse(s);
        return lastDp(s, reverse);
    }

    /**
     * 返回字符串 s 的逆序字符串
     */
    public static String reverse (String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = chars.length - 1; i >= 0 ; i--) {
            ans.append(chars[i]);
        }
        return ans.toString();
    }


    /**
     * 直接暴力递归开干
     */
    public static int longestPalindromeSubseq02 (String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process2(s.toCharArray(), 0, s.length() - 1);
    }

    /**
     * 返回 chars[left...right] 范围内的最长公共子序列，其实就是四种情况
     * 1. 最长公共子序列chars[left] 和 chars[right]都不包含
     * 2. 最长公共子序列包含 chars[left] 字符，不包含chars[right] 字符
     * 3. 最长公共子序列不包含 chars[left] 字符，包含 chars[right] 字符
     * 4. 最长公共子序列包含 chars[left] 和 chars[right] 字符, 那么 chars[left] == chars[right]
     */
    public static int process2 (char[] chars, int left, int right) {
        if (left == right) {
            // 只有一个字符，那么最大的长度就是1咯
            return 1;
        }
        if (left == right - 1) {
            // 包含两个字符
            return chars[left] == chars[right] ? 2 : 1;
        }

        // 第一种情况，都不包含
        int p1 = process2(chars, left + 1, right - 1);
        // 第二种情况，包含chars[left]
        int p2 = process2(chars, left, right - 1);
        // 第三种情况，包含chars[right]
        int p3 = process2(chars, left + 1, right);
        // 第四种情况，都包含, 如果 chars[left] != chars[right] 说明这种情况不满足，就不用考虑了，直接设置为 0
        int p4 = chars[left] == chars[right] ? 2 + process2(chars, left + 1, right - 1) : 0;

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindromeSubseq(s));
        System.out.println(longestPalindromeSubseq02(s));
    }
}
