package class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 汉诺塔问题
 *
 * 思路： 比较基础的思路就是，  把N个盘子从左边移到右边分三步走
 *      (1) 将 [1, n-1] 个盘子从左边移到中间
 *      (2) 将第n个盘子从左边移动到右边
 *      (3) 将[1,n-1] 个盘子从中间移动到右边
 *   一个通用的思路就是，目的将 n 个盘子借助other从 from 移动到 to
 *   (1) 将 [1, n-1] 个盘子从 from移动到other（借助to）
 *   (2) 将 n 从 from 直接移动到 to
 *   (3) 将 [1, n-1] 个盘子从other 移动到 to (借助from)
 *
 * @Author 张三金
 * @Date 2022/1/8 0008 9:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_Hanoi {

    /**
     * 目标： 将n个盘子从左边移动到右边
     *
     * list: 为了测试做对数器用
     */
    public static void hanoi01 (int n, List<String> list) {
        if (n == 0) {
            return;
        }
        leftToMiddle(n - 1, list);
        String action = "Move " + n + " from left to right";
//        System.out.println(action);
        list.add(action);
        middleToRight(n - 1, list);
    }

    public static void leftToMiddle(int n, List<String> list) {
        if (n == 0) {
            return;
        }
        leftToRight(n-1, list);
        String action = "Move " + n + " from left to middle";
//        System.out.println(action);
        list.add(action);
        rightToMiddle(n-1, list);
    }

    public static void middleToRight(int n, List<String> list) {
        if (n == 0) {
            return;
        }
        middleToLeft(n-1, list);
        String action = "Move " + n + " from middle to right";
//        System.out.println(action);
        list.add(action);
        leftToRight(n-1, list);
    }

    public static void leftToRight (int n, List<String> list) {
        if (n == 0) {
            return;
        }
        leftToMiddle(n-1, list);
        String action = "Move " + n + " from left to right";
//        System.out.println(action);
        list.add(action);
        middleToRight(n-1, list);
    }

    public static void middleToLeft (int n, List<String> list) {
        if (n == 0) {
            return;
        }
        middleToRight(n-1, list);
        String action = "Move " + n + " from middle to left";
//        System.out.println(action);
        list.add(action);
        rightToLeft(n-1, list);
    }

    public static void rightToLeft(int n, List<String> list) {
        if (n == 0) {
            return;
        }
        rightToMiddle(n-1, list);
        String action = "Move " + n + " from right to left";
//        System.out.println(action);
        list.add(action);
        leftToRight(n-1, list);
    }

    public static void rightToMiddle(int n, List<String> list) {
        if (n == 0) {
            return;
        }
        rightToLeft(n-1, list);
        String action = "Move " + n + " from right to middle";
//        System.out.println(action);
        list.add(action);
        leftToMiddle(n-1, list);
    }

    public static void main(String[] args) {

        // @TODO 这个测试用例是通不过的，不知道啥原因，不知道是不是因为递归嵌套多了导致顺序每次可能不一样的原因导致的
        int testTimes = 100;
        int num = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < testTimes; i++) {
            List<String> list01 = new ArrayList<>();
            hanoi01(num, list01);

            List<String> list02 = new ArrayList<>();
            hanoi02(num, list02);
            for (int j = 0; j < list01.size(); j++) {
                if (!list01.get(i).equals(list02.get(i))) {
                    System.out.println(num);
                    System.out.println(i);
                    System.out.println(list01.get(i));
                    System.out.println(list02.get(i));
                    System.out.println("Opps!!!!");
                }
            }
        }

    }

    public static void hanoi02 (int n, List<String> list) {
        process(n, "left", "right", "middle", list);
    }

    // from: 来源地
    // to: 目的地
    // other：借助点
    public static void process (int n, String from, String to, String other, List<String> list) {
        if (n == 0) {
            return;
        }
        process(n-1, from, other, to, list);
        String action = "Move " + n + " from " + from + " to " + to;
//        System.out.println(action);
        list.add(action);
        process(n-1, other, to, from, list);
    }
}
