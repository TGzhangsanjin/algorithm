package class14;

import utils.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 字符串最大字典序
 * 题目描述：
 *  给定一个由字符串组成的数组 strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中，字典序最小的结果
 *
 *  字典序： 通俗理解就是字符串在字典里面的顺序，比如： abc 就应该就应该排在 bac 前面， b 应该排在 ba前面， bdc 应该排在 bfa前面
 *
 *  贪心策略的选择：
 *      1. 策略1：如果字符串x1 小于字符串 x2， 那么 x1就应该排在 x2的前面，
 *          反例：["b", "ba"], "b" < "ba", 但是实际的最小字典序中 "ba" < "b" 即 "ba" 是排在 “b” 前面的
 *          所以策略1不行
 *      2. 策略2：对于数组中的两个字符串 x1、x2， 如果 x1.x2 < x2.x1, 那么最小字典序中 x1要排在x2前面, 其中这个 . 表示两个字符串拼接的意思
 *          证明：
 *              (1) 字符串在程序中都对应着一个数字，假设本题中的字符串都是小写字母，那么可以将每一个字符串可以看成一个26进制的数
 *              (2) 要证明策略2，其实就是要证明其具有传递性，假设 x1.x2 < x2.x1 且 x2.x3 < x3.x2, 则有 x1.x2 < x2.x3
 *              (3) 证明：
 *  贪心策略的证明一般都很复杂，不用刻意去证明，直接写一个暴力的对数器，进行比较就行了，
 * @Author 张三金
 * @Date 2021/12/25 0025 9:47
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_MinDictSort {

    public static String minDictSort (String[] array) {
        if (array == null) {
            return "";
        }
        Arrays.sort(array, (o1, o2) -> {
            if ((o1 + o2).equals(o2)) {
                return 0;
            }
            // jdk 的 String类自己实现了 compareTo 方法，，该方法就是基于字典序比较的
            return (o1 + o2).compareTo(o2 + o1);
        });
        String ans = "";
        for (int i = 0; i < array.length; i++) {
            ans += array[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxSize = 10;
        int maxLength = 10;
        for (int i = 0; i < testTimes; i++) {
            String[] array01 = generateStringArray(maxSize, maxLength);
            String[] array02 = ArrayUtil.copyArray(array01);
            String str01 = minDictSort(array01);
            String str02 = minDictSortForTest(array02);
            if (!str01.equals(str02)) {
                System.out.println("Opps!!!!!");
            }
        }
    }

    /**
     *
     * @param array
     * @return
     */
    public static String minDictSortForTest (String[] array) {
        List<String> allList = process(array);
        if(allList == null || allList.size() == 0) {
            return "";
        }
        // TreeSet 是自带排序的
        TreeSet<String> set = new TreeSet<>(allList);
        return set.first();
    }

    // 返回 array 所有字符串的全排列
    public static List<String> process (String[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            String first = array[i];
            List<String> allNextList = process(removeIndexArray(array, i));
            if (allNextList == null) {
                // 这个条件很重要
                ans.add(first);
                continue;
            }
            for (String s : allNextList) {
                ans.add(first + s);
            }
        }
        return ans;
    }

    // 去除 array 数组中 index 位置上的数据, 然后返回该数组
    public static String[] removeIndexArray (String[] array, int index) {
        if (array == null || array.length < 1 || index > array.length - 1) {
            return null;
        }
        String[] ans = new String[array.length - 1];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (index != i) {
                ans[j++] = array[i];
            }
        }
        return ans;
    }

    // 生成一个随机的字符串数组
    public static String[] generateStringArray (int maxSize, int maxLength) {
        int length = (int) (Math.random() * maxSize);
        if (length == 0) {
            return null;
        }
        String[] array = new String[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateString(maxLength);
        }
        return array;
    }
    
    // 生成一个随机的字符串
    public static String generateString (int maxLength) {
        String str = "";
        int length = (int) (Math.random() * maxLength) + 1;
        for (int i = 0; i < length; i++) {
            str += (char) (Math.random() * 26 + 'a');
        }
        return str;
    }


























}
