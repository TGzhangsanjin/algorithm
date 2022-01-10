package class18;

/**
 * 选纸牌问题
 *
 * 问题描述：
 *  给定一个整型数组 arr， 代表数组不同的纸牌连成一条线，玩家A和玩家B依次拿走一张纸牌，
 *  规定玩家A先拿，玩家B后拿，但是每个玩家只能拿走最左边或者最右边的纸牌，玩家A和玩家B都绝顶聪明，
 *  请返回最后的获胜者的分数
 *
 *
 * @Author 张三金
 * @Date 2022/1/10 0010 14:38
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_ChooseCard {

    public static int win01 (int[] arr) {
        int first = f1(arr, 0, arr.length - 1);
        int last = g1(arr, 0, arr.length - 1);
        return Math.max(first, last);
    }
    // 先手函数
    public static int f1(int[] arr, int L, int R) {
        if (L == R) {
            // 先手么，那这张牌就是自己拿咯
            return arr[L];
        }
        // 拿左边的牌，获得的积分
        int left = arr[L] + g1(arr, L + 1, R);
        // 拿右边的牌，获得的积分
        int right  = arr[R] + g1(arr, L, R - 1);
        // 先手函数，所以返回最大值
        return Math.max(left, right);
    }

    // 后手函数
    public static int g1(int[] arr, int L, int R) {
        if (L == R) {
            // 自己作为后手，只剩一张牌了，自然被对方拿走了，那么自己啥都没有
            return 0;
        }
        // 我是后手，当前这张牌是对手选的，arr[L]或者arr[R] 肯定就不算我的分数了
        int left = f1(arr, L + 1, R);
        int right  = f1(arr, L, R - 1);
        // 我作为后手，对手不是傻子，对手是绝顶聪明的，他肯定会把最少的留给我
        return Math.min(left, right);
    }

    public static int win02 (int[] arr) {
        int[][] dpF = new int[arr.length][arr.length];
        int[][] dpG = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                dpF[i][j] = -1;
                dpG[i][j] = -1;
            }
        }
        int first = f2(arr, 0, arr.length - 1, dpF, dpG);
        int last = g2(arr, 0, arr.length - 1, dpF, dpG);
        return Math.max(first, last);
    }

    public static int f2 (int[] arr, int L, int R, int[][] dpF, int[][] dpG) {
        if (dpF[L][R] != -1) {
            return dpF[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int left = arr[L] + g2(arr, L + 1, R, dpF, dpG);
            int right  = arr[R] + g2(arr, L, R - 1, dpF, dpG);
            ans = Math.max(left, right);
        }
        dpF[L][R] = ans;
        return ans;
    }

    public static int g2(int[] arr, int L, int R, int[][] dpF, int[][] dpG) {
        if (dpG[L][R] != -1) {
            return dpG[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = 0;
        } else {
            int left = f2(arr, L + 1, R, dpF, dpG);
            int right  = f2(arr, L, R - 1, dpF, dpG);
            ans =  Math.min(left, right);
        }
        dpG[L][R] = ans;
        return ans;
    }


    /**
     * 两个矩阵，一个先手矩阵，一个后手矩阵，因为 left <= right 所以两个矩阵都只需要考虑右上角那一半
     * 根据base case 可以得出
     *  先手矩阵从左上往右下对角线的值（left==right） 就是对应的arr[left]的值
     *  后手举证从左上往右下对角线的值都是0
     * 后手矩阵中非对角线的值 g[left][right] = Math.min(f[left][right - 1], f[left + 1][right])  (左边的值+下边的值)
     * 先手矩阵中非对角线的值 f[left][right] = Math.max(arr[left] + g[left + 1][right], arr[right] + g[left][right - 1])
     *
     *  最后返回结果 Math.max(f[0][arr.length], g[0][arr.length])
     */
    public static int win03 (int[] arr) {
        // 先手矩阵
        int[][] f = new int[arr.length][arr.length];
        // 后手矩阵
        int[][] g = new int[arr.length][arr.length];
        // 初始化先手矩阵的对角线
        for (int i = 0; i < arr.length; i++) {
            f[i][i] = arr[i];
        }
        // 遍历每一列，为什么选择最外面遍历每一列，因为对角线往下爬，，列会比行先越界，
        for (int startCol = 1; startCol < arr.length; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < arr.length) {
                f[L][R] = Math.max(arr[L] + g[L + 1][R], arr[R] + g[L][R - 1]);
                g[L][R] = Math.min(f[L][R - 1], f[L + 1][R]);
                L++;
                R++;
            }
        }
        return Math.max(f[0][arr.length - 1], g[0][arr.length - 1]);
    }

    public static void main(String[] args) {

        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win01(arr));
        System.out.println(win02(arr));
        System.out.println(win03(arr));
    }
}
