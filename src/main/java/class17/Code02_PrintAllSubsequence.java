package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印字符串的所有子序列，其实就是一棵满二叉树所有头节点到叶子节点的路径情况
 * @Author 张三金
 * @Date 2022/1/8 0008 10:52
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_PrintAllSubsequence {

    // 会出现重复的数据，不会去重
    public static List<String> allSubsequence01 (String str) {
        char[] chars = str.toCharArray();
        List<String> ans  = new ArrayList<>();

        process01(chars, 0, ans, "");
        return ans;
    }


    /**
     * chars: 字符串数组
     * index： 当前到达的位置
     * ans: 所有的子序列情况
     * path： 之前的策略，就是index之前选择了的字符串
     *
     */
    public static void process01 (char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length) {
            // 将当前这条路的结果放入结果集中
            ans.add(path);
            return;
        }
        // char[index] 字符不选择
        process01(chars, index + 1, ans, path);
        // char[index] 字符选择
        process01(chars, index + 1, ans, path + chars[index]);
    }

    public static Set<String> allSubsequence02 (String str) {
        char[] chars = str.toCharArray();
        Set<String> ans  = new HashSet<>();

        process02(chars, 0, ans, "");
        return ans;
    }


    /**
     * chars: 字符串数组
     * index： 当前到达的位置
     * ans: 所有的子序列情况
     * path： 之前的策略，就是index之前选择了的字符串
     *
     */
    public static void process02 (char[] chars, int index, Set<String> ans, String path) {
        if (index == chars.length) {
            // 将当前这条路的结果放入结果集中
            ans.add(path);
            return;
        }
        // char[index] 字符不选择
        process02(chars, index + 1, ans, path);
        // char[index] 字符选择
        process02(chars, index + 1, ans, path + chars[index]);
    }

    public static void main(String[] args) {
        String str = "acc";
        List<String> ans01 = allSubsequence01(str);
        for (String an : ans01) {
            System.out.println(an);
        }
        System.out.println("=================");

        Set<String> ans02 = allSubsequence02(str);
        for (String an : ans02) {
            System.out.println(an);
        }
    }
}
