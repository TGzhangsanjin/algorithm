package class18;

/**
 * 斐波那契
 * @Author 张三金
 * @Date 2022/1/10 0010 11:09
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_Fibonacci {

    public static int fibonacci01 (int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci01(n - 1) + fibonacci01(n - 2);
    }

    public static int fibonacci02 (int n) {
        int[] cache = new int[1000];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }
        return process02(n, cache);
    }

    public static int process02 (int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }
        int ans;
        if (n == 0) {
            ans = 0;
        } else if (n == 1) {
            ans = 1;
        } else {
            ans = process02(n - 1, cache) + process02(n - 2, cache);
        }
        cache[n] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int n = 45;
        System.out.println(fibonacci02(n));
        System.out.println(fibonacci01(n));
    }
}
