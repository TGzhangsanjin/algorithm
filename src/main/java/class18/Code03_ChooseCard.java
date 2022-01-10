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
        int first = f(arr, 0, arr.length - 1);
        int last = g(arr, 0, arr.length - 1);
        return Math.max(first, last);
    }

    // 先手函数
    public static int f (int[] arr, int L, int R) {
        if (L == R) {
            // 先手么，那这张牌就是自己拿咯
            return arr[L];
        }
        // 拿左边的牌，获得的积分
        int left = arr[L] + g(arr, L + 1, R);
        // 拿右边的牌，获得的积分
        int right  = arr[R] + g(arr, L, R - 1);
        // 先手函数，所以返回最大值
        return Math.max(left, right);
    }

    // 后手函数
    public static int g (int[] arr, int L, int R) {
        if (L == R) {
            // 自己作为后手，只剩一张牌了，自然被对方拿走了，那么自己啥都没有
            return 0;
        }
        // 我是后手，当前这张牌是对手选的，arr[L]或者arr[R] 肯定就不算我的分数了
        int left = f(arr, L + 1, R);
        int right  = f(arr, L, R - 1);
        // 我作为后手，对手不是傻子，对手是绝顶聪明的，他肯定会把最少的留给我
        return Math.min(left, right);
    }

    public static void main(String[] args) {

        int[] arr = {1, 100 ,1};
        System.out.println(win01(arr));
    }
}
