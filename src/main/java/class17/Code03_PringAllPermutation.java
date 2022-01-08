package class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全排列
 * @Author 张三金
 * @Date 2022/1/8 0008 11:35
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_PringAllPermutation {


    // 这是一种比较挫的方式，需要额外申请一个 rest数组，
    public static List<String> permutation01 (String str) {
        char[] chars = str.toCharArray();
        List<Character> rest = new ArrayList<>();
        for (char c : chars) {
            rest.add(c);
        }
        List<String> ans = new ArrayList<>();
        process01(rest, "", ans);
        return ans;
    }

    /**
     * rest： 剩下没处理过的字符
     * path: 已经确定定的字符
     * ans：答案
     */
    public static void process01 (List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            // 没有可选的字符了，直接填充答案
            ans.add(path);
            return;
        }
        for (int i = 0; i < rest.size(); i++) {
            Character c = rest.get(i);
            // 移除当前字符，然后递归处理剩下的可能性
            rest.remove(i);
            process01(rest, path + c, ans);
            // 恢复现场，这一步很重要
            rest.add(i, c);
        }
    }

    // 这是一种比较好的递归方式了
    public static List<String> permutation02 (String str) {
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process02(chars, 0, ans);
        return ans;
    }

    /**
     *
     * chars 当前字符数组，里面就是之前做过的决定
     * index 当前递归到的下标
     * ans 答案
     */
    public static void process02 (char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            // 触底了
            ans.add(String.valueOf(chars));
            return;
        }
        // index 和后面的进行交换递归即可，，和前面的其实已经处理过了
        for (int i = index; i < chars.length; i++) {
            boolean[] visited = new boolean[256];
            if (!visited[chars[i]]) {
                visited[chars[i]] = true;
                swap(chars, index, i);
                process02(chars, index + 1, ans);
                swap(chars, index, i);
            }
        }
    }


    public static void main(String[] args) {
        String str = "acc";
        List<String> ans01 = permutation01(str);
        for (String s : ans01) {
            System.out.println(s);
        }
        System.out.println("===========");
        List<String> ans02 = permutation02(str);
        for (String s : ans02) {
            System.out.println(s);
        }
    }


    public static void swap(char[] chars, int i, int j) {
        if (i == j) {
            return;
        }
        char temp = chars[j];
        chars[j] = chars[i];
        chars[i] = temp;
    }
}
