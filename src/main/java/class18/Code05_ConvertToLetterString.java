package class18;

/**
 * 字符串数字转换成字母
 * 问题描述：
 *  规定1和A对应、2和B对应、3和C对应 。。。 26和Z对应
 *  那么一个数字字符串比如 "111", 可以转化为：“AAA”、“KA” 和 "AK"， 返回结果就是 3
 *  "305" 就无法转化，因为 "0" 是无效的，“30” 也是无效的，所以返回结果就是 0
 *  给定一个只有数字字符组组成的字符串 str, 返回有多少种转化结果
 *
 *
 * @Author 张三金
 * @Date 2022/1/15 0015 11:30
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_ConvertToLetterString {

    public static int number (String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        return process(str.toCharArray(), 0);

    }

    /**
     * chars[0...i-1] 的转换可能性已经确定了，无需过问了
     * chars[i....] 去转换，返回有多少种转换方法
     */
    public static int process (char[] chars, int index) {
        if (index == chars.length) {
            // 这里要返回1，说明啥，说明你顶到底了，都没有问题，那么这就是一条有效的路径，也就是一种可能性
            return 1;
        }
        if (chars[index] <= '0' || chars[index] > '9') {
            // 无效的字符串
            return 0;
        }
        // 当前字符串独立处理
        int p1 = process(chars, index + 1);

        // 当前字符串和后面的字符串连在一起处理
        int p2 = 0;
        if (index + 1 != chars.length && ((chars[index] - '0') * 10 + (chars[index + 1] - '0')) < 27) {
            // 1. index + 2 不能越界  2. index和index+1 两个连在一起组成的数字不能超过26
            p2 = process(chars, index + 2);
        }
        return p1 + p2;
    }

    /**
     * dp 改写
     */
    public static int number02 (String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        // 注意要多加一个1
        int[] dp = new int[chars.length + 1];
        dp[chars.length] = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            if (chars[i] < '0' || chars[i] > '9') {
                return 0;
            }
            // 这个 chars[i] != '0' 这个操作很骚，表名如果当前字符是0的话，那么当前字符就不处理了，它必须和它前面一个字符连在一起组成一个数字才行
            // 很抽象
            if (chars[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < chars.length  && ((chars[i] - '0') * 10 + (chars[i + 1] - '0')) < 27) {
                    // 1. index + 2 不能越界  2. index和index+1 两个连在一起组成的数字不能超过26
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];

    }


    public static void main(String[] args) {
//        String str = "11123643532432";
        String str = "111";
        System.out.println(number(str));
        System.out.println(number02(str));
    }


}
