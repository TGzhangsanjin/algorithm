package class18;

/**
 * 路径最小累加和问题
 * 题目描述：
 *      给定一个二维数组 matrix, 一个人必须从左上角出发，最后到达右下角
 *      沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 *      返回最小距离累加和
 *
 *    ps: 数组中的值都大于0
 *
 * @Author 张三金
 * @Date 2022/1/18 0018 22:28
 * @Company jzb
 * @Version 1.0.0
 */
public class Code11_MinPathNum {

    public static int minPathNum01(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        return process01(matrix, 0, 0);
    }

    /**
     * 认为数组是一个 N * M 的矩阵
     *
     * 递归含义： (i, j) 位置 到 martix[N-1][m-1] 的最小累加和
     */
    public static int process01 (int[][] matrix, int i, int j) {
        int N = matrix.length;
        int M = matrix[0].length;

        if (i == N || j == M) {
            // 越界了
            return 0;
        }
        if (i == N - 1 && j == M - 1) {
            return matrix[i][j];
        }
        int min = 0;
        // 向右走
        int p1 = process01(matrix, i, j + 1);
        // 向下走
        int p2 = process01(matrix, i + 1, j);

        // 如果其中一个位置越界了，就返回另一个位置的值
        if (p1 == 0) {
            return p2 + matrix[i][j];
        } else if (p2 == 0){
            return p1 + matrix[i][j];
        }
        return Math.min(p1, p2) + matrix[i][j];
    }

    /**
     * 这里如果按照递归的含义的话，初始位置是 dp[N-1][M-1], 最后返回 dp[0][0] 的结果
     * 这里单纯为了抽象成dp的时候，分析时自然一点，认为初始位置是 dp[0][0], 然后返回 dp[N-1][M-1] 的值
     * （从左上角走到右下角和从右下角走到左上角肯定是一样的结果）
     * @param matrix
     * @return
     */
    public static int minPathNum02 (int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        // 这里如果从递归
        int[][] dp = new int[N][M];
        dp[0][0] = matrix[0][0];
        // 第一行的值
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i-1] + matrix[0][i];
        }
        // 第一列的值
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + matrix[i][j];
            }
        }
        return dp[N-1][M-1];
    }
    /**
     * 压缩数组
     * 省空间的做法，只需要一个额外的一位数组就可以（当然就会改变原有数组的值）
     */
    public static int minPathNum04(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        // 第一行的值
        for (int i = 1; i < M; i++) {
            matrix[0][i] = matrix[0][i-1] + matrix[0][i];
        }
        // 第一列的值
        for (int i = 1; i < N; i++) {
            matrix[i][0] = matrix[i-1][0] + matrix[i][0];
        }
        if (M < N) {
            int[] last = new int[M];
            // 初始化第一行
            last[0] = matrix[0][0];
            for (int i = 1; i < N; i++) {
                last[i] = last[i-1] + matrix[i][0];
            }
            int[] current = new int[M];
            for (int i = 1; i < N; i++) { // 行
                current[0] = matrix[i][0];
                for (int j = 1; j < M; j++) { // 列
                    matrix[i][j] = Math.min(current[j-1], matrix[i-1][j]) + matrix[i][j];
                    // 动态维护 current 的值
                    current[j] = matrix[i][j];
                }
            }
        } else {
            int[] array = new int[N];
            for (int i = 1; i < M; i++) { // 列
                array[0] = matrix[0][i];
                for (int j = 1; j < N; j++) { // 行
                    matrix[j][i] = Math.min(array[j-1], matrix[j][i-1]) + matrix[j][i];
                    // 动态维护 array 的值
                    array[j] = matrix[j][i];
                }
            }
        }
        return matrix[N-1][M-1];
    }

    /**
     * 针对 minPathNum04， 如果原数组有 100万列， 只有4行，，那就jiji了，所以要判断一下
     *
     */
    public static int minPathNum05(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        // 第一行的值
        for (int i = 1; i < M; i++) {
            matrix[0][i] = matrix[0][i-1] + matrix[0][i];
        }
        // 第一列的值
        for (int i = 1; i < N; i++) {
            matrix[i][0] = matrix[i-1][0] + matrix[i][0];
        }
        if (M < N) {
            int[] array = new int[M];
            for (int i = 1; i < N; i++) { // 行
                array[0] = matrix[i][0];
                for (int j = 1; j < M; j++) { // 列
                    matrix[i][j] = Math.min(array[j-1], matrix[i-1][j]) + matrix[i][j];
                    // 动态维护 array 的值
                    array[j] = matrix[i][j];
                }
            }
        } else {
            int[] array = new int[N];
            for (int i = 1; i < M; i++) { // 列
                array[0] = matrix[0][i];
                for (int j = 1; j < N; j++) { // 行
                    matrix[j][i] = Math.min(array[j-1], matrix[j][i-1]) + matrix[j][i];
                    // 动态维护 array 的值
                    array[j] = matrix[j][i];
                }
            }
        }

        return matrix[N-1][M-1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 1, 8, 6, 2, 7},
                {2, 1, 5, 7, 2, 6},
                {4, 2, 1, 2, 5, 8},
                {8, 3, 1, 1, 1, 3}
        };

        System.out.println(minPathNum01(matrix));
        System.out.println(minPathNum02(matrix));
//        System.out.println(minPathNum04(matrix));
        System.out.println(minPathNum05(matrix));

        int testTimes = 1000;
        int maxRow = 12;
        int maxCol = 12;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[][] array = generateMatrix(maxRow, maxCol);
            int sum01 = minPathNum01(array);
//            int sum02 = minPathNum02(array);
//            int sum02 = minPathNum04(array);
            int sum02 = minPathNum05(array);

            if (sum01 != sum02) {
                System.out.println("Opps!!!");
            }
        }
        System.out.println("测试结束");
    }

    // 生成一个二维数组
    public static int[][] generateMatrix (int maxRow, int maxCol) {
        int row = (int) (Math.random() * maxRow) + 1;
        int col = (int) (Math.random() * maxCol) + 1;
        int[][] matrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = (int) (Math.random() * 100) + 1;
            }
        }
        return matrix;
    }
}
