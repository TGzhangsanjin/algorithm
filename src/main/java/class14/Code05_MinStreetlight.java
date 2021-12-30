package class14;

/**
 * 最少路灯问题
 * 给定一个字符串 str， 字符串只由 'X' 和 '.' 两种字符构成
 * 'X' 表示墙，不能放灯，也不需要点亮
 * '.' 表示居民点，可以放灯，需要点亮
 * 如果灯放在 i 位置， 可以让i、 i-1 和 i+1 三个位置同时被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 *
 * 贪心策略： 依次遍历每个字符
 *  1. 如果i位置时 X，则不管继续遍历 i+1
 *  2. 如果i位置是 . , i+1位置是X，则 i位置必须放灯，继续遍历 i+2
 *  3. 如果i位置是 . , i+1位置是 . , i+2 位置是X，则i+1或者i位置放灯皆可，继续遍历 i+3
 *  4. 如果i位置是 . , i+1位置是 . , i+2 位置是 .，则在i+1位置放灯皆可，继续遍历 i+3
 * @Author 张三金
 * @Date 2021/12/30 0030 10:47
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_MinStreetlight {

    public static int minStreetlight (String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        int i = 0;
        int count = 0;
        while (i < str.length()) {
            char current = str.charAt(i);
            if (current == 'X') {
                i++;
            } else {
                // 只要当前不是墙，就要考虑在 i、i+1、i+2 三者之间放一个灯
                count ++;
                if ((i + 1) >= str.length()) {
                    break;
                } else {
                    if (str.charAt(i + 1) == 'X') {
                        // i+1是墙，则肯定放灯到i位置，
                        count += 2;
                    } else {
                        // 都把灯放到 i+1位置，然后这次直接过掉三个点
                        count += 3;
                    }
                }
            }
        }
        return count
    }
}
