package class18;

import java.util.HashMap;
import java.util.Map;

/**
 * 贴纸问题
 * 问题描述：
 *  给定一个字符串str，给定一个字符串数组 array, 出现的字符都是小写英文字母，数组array中每一个字符串代表一张贴纸
 *  现在可以把单个字符剪开使用，目的是拼出 str 来
 *  返回最少需要多少张贴纸可以完成这个任务
 *  例子： str = "babac", array = ["ba", "c", "abcd"]
 *  可以用 两张“ba” 和 一张 “c”, 也可以用两张 "abcd", 也可以用一张"ba"和一张"abcd"
 *  所以最少是2张， 返回结果 2
 *
 * leetcode 原题：https://leetcode.com/problems/stickers-to-spell-word
 * @Author 张三金
 * @Date 2022/1/15 0015 16:32
 * @Company jzb
 * @Version 1.0.0
 */
public class Code06_StickersToSpellWords {

    /**
     * 返回 -1 表示给出的贴纸没办法拼凑成目标字符串
     */
    public static int minStickers(String[] stickers, String target) {
        int ans = process(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * stickers： 所有的贴纸
     * target: 需要拼接成的字符串
     * 返回：需要的最少张数
     *
     * 思路： 拼凑成 target需要n张贴纸，遍历每张贴纸作为第一张贴纸的所有情况
     */
    public static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = removeTarget(target, first);
            if (rest.length() != target.length()) {
                min = Math.min(min, process(stickers, rest));
            }
        }
        // 这里为什么要 + 1 呢
        // +1 其实就是这个递归的含义，for循环表示，去掉某一张贴纸，剩下的target需要多少张贴纸，求出min
        // 那么自然而让，当前就需要用 min + 1 来返回
        return min + ((min == Integer.MAX_VALUE) ? 0 : 1);
    }

    /**
     * target 目标字符串
     * sticker 贴纸
     * 返回target 使用了一张sticker 之后剩下的字符串
     */
    public static String removeTarget (String target, String sticker) {
        char[] chars01 = target.toCharArray();
        char[] chars02 = sticker.toCharArray();
        int[] count = new int[26];
        // 经过下面两个循环，就能知道，每个字符剩余多少
        for (char c : chars01) {
            count[c - 'a'] ++;
        }
        for (char c : chars02) {
            count[c - 'a'] --;
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    ans.append((char)(i + 'a'));
                }
            }
        }
        return ans.toString();
    }

    /**
     * 剪枝优化(这个方法依旧超时)
     */
    public static int minStickers2 (String[] stickers, String target) {
        // 每张贴纸的词频统计
        int[][] array = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            // 某一张贴纸
            char[] sticker = stickers[i].toCharArray();
            for (int j = 0; j < sticker.length; j++) {
                array[i][sticker[j] - 'a'] ++;
            }
        }
        int ans = process2(array, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * stickers[i] 表示，当初 i 号贴纸中的字符统计
     * rest[] 数组是剩余字符串的词频统计, 注意这里千万不要用数组，为什么，因为数组传值传的是引用，一变就都一起变了
     * rest 剩余的字符串
     * 每一种贴纸都有无穷张
     * 返回搞定 rest 的最少张数
     */
    public static int process2 (int[][] stickers, String rest) {
        if (rest.length() == 0) {
            return 0;
        }

        // rest 的词频统计
        int[] restCount = new int[26];
        char[] chars = rest.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            restCount[chars[i] - 'a'] ++;
        }
        int min = Integer.MAX_VALUE;
        for (int index = 0; index < stickers.length; index++) {
            int[] current = stickers[index];
            // 最关键的优化，重要的剪枝！在rest中随便找一个字符，看看current中是否包含，如果不包含，这张贴纸就彻底废弃, 没必要走后面的尝试了
            // 随便在 rest中找一个存在的字符就行，不一定是第一个 chars[0]
            if (current[chars[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (restCount[j] > 0) {
                        // 某个小写字母剩下多少个
                        int nums = restCount[j] - current[j];
                        for (int i = 0; i < nums; i++) {
                            builder.append((char)(j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process2(stickers, builder.toString()));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    /**
     * 缓存优化
     * 这里面递归的唯一变量就是一个字符串，不是数字，没办法左那种动态规划的表格方法
     */
    public static int minStickers3 (String[] stickers, String target) {
        // 每张贴纸的词频统计
        int[][] array = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            // 某一张贴纸
            char[] sticker = stickers[i].toCharArray();
            for (int j = 0; j < sticker.length; j++) {
                array[i][sticker[j] - 'a'] ++;
            }
        }
        Map<String, Integer> cache = new HashMap<>();
        int ans = process3(array, target, cache);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3 (int[][] stickers, String rest, Map<String, Integer> cache) {
        if (rest.length() == 0) {
            return 0;
        }
        if (cache.get(rest) != null) {
            return cache.get(rest);
        }
        // rest 的词频统计
        int[] restCount = new int[26];
        char[] chars = rest.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            restCount[chars[i] - 'a'] ++;
        }
        int min = Integer.MAX_VALUE;
        for (int index = 0; index < stickers.length; index++) {
            int[] current = stickers[index];
            // 最关键的优化，重要的剪枝！在rest中随便找一个字符，看看current中是否包含，如果不包含，这张贴纸就彻底废弃, 没必要走后面的尝试了
            // 随便在 rest中找一个存在的字符就行，不一定是第一个 chars[0]
            if (current[chars[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (restCount[j] > 0) {
                        // 某个小写字母剩下多少个
                        int nums = restCount[j] - current[j];
                        for (int i = 0; i < nums; i++) {
                            builder.append((char)(j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process3(stickers, builder.toString(), cache));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        cache.put(rest, ans);
        return ans;
    }

    public static void main(String[] args) {
        String[] stickers = {"ba", "c", "abcd"};
        String str = "babac";
        System.out.println(minStickers(stickers, str));
        System.out.println(minStickers2(stickers, str));
        System.out.println(minStickers3(stickers, str));
    }
}
