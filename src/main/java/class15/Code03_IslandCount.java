package class15;

/**
 * 本题为leetcode原题
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 * 给定一个二维数组 matrix, 里面的值不是1就是0
 * 上、下、左、右相邻的1认为是一片岛， 返回 matrix中岛的数量
 *
 * @Author 张三金
 * @Date 2021/12/30 0030 11:26
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_IslandCount {

    /**
     * 感染的方式实现
     * 从左往右，从上往下去遍历每一个位置的数据，只要遍历到1，然后递归的将其上、下、左、右的值全部感染成 2
     * 时间复杂度： O(m * n)
     */
    public int numIsland (char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    infect(grid, i, j);
                }
            }
        }
        return count;
    }

    // 去递归感染位置 martix[i][j] 周围所以的 '1'
    public void infect (char[][] matrix, int i, int j) {
        // baseCase1 下标越界了，返回
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return;
        }
        // baseCase2 为0或者被感染了为2，返回
        if (matrix[i][j] != '1') {
            return;
        }
        // 被感染了设置为 '2'
        matrix[i][j] = 2;
        // 感染左边
        infect(matrix,i - 1, j);
        // 感染右边
        infect(matrix,i + 1, j);
        // 感染上边
        infect(matrix, i, j + 1);
        // 感染下边
        infect(matrix, i, j - 1);
    }

    /**
     *  并查集思路解决此问题
     */
    public void numIsland2 (char[][] grid){

    }

    class UnionFind2<T> {



    }
}
