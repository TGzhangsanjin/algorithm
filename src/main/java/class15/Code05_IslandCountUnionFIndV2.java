package class15;

/**
 * 并查集解决岛问题
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 * 并查集中不使用map的方式，二维数组转换成一维数组， 二维数组 matrix[i][j] 中对应一维数组的下标就是 array[i*列数] + j
 * @Author 张三金
 * @Date 2022/1/6 0006 11:01
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_IslandCountUnionFIndV2 {

    public static int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 1; i < col; i++) {
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                unionFind.union(0, i -1, 0, i);
            }
        }
        for (int i = 1; i < row; i++ ) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                unionFind.union(i - 1, 0, i, 0);
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionFind.union(i - 1, j, i, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionFind.union(i, j - 1, i, j);
                    }
                }
            }
        }
        return unionFind.count;
    }


    public static class UnionFind {

        // 每个节点的祖先，下标表示节点，值表示其祖先
        public int[] parents;
        // 节点代表的集合的大小，下标表示节点，值表示集合大小
        public int[] sizes;
        public int[] help;
        public int row;
        public int col;
        // 集合数量
        public int count;

        // 初始化数组
        public UnionFind (char[][] grid) {
            row = grid.length;
            col = grid[0].length;
            parents = new int[row * col];
            sizes = new int[row * col];
            help = new int[row * col];
            count = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int index = index(i, j);
                    parents[index] = index;
                    sizes[index] = 1;
                    if (grid[i][j] == '1') {
                        count++;
                    }
                }
            }
        }

        public int findAncestor (int i) {
            int helpIndex = 0;
            while (parents[i] != i) {
                help[helpIndex++] = parents[i];
                i = parents[i];
            }
            // 压缩路径
            while (helpIndex > 0) {
                parents[help[--helpIndex]] = i;
            }
            return i;
        }

        // (i1, j1)和 (i2, j2) 两个位置进行union
        public void union (int i1, int j1, int i2, int j2) {
            int index01 = index(i1, j1);
            int index02 = index(i2, j2);
            int ancestor01 = findAncestor(index01);
            int ancestor02 = findAncestor(index02);
            if (ancestor01 != ancestor02) {
                int big = sizes[ancestor01] >= sizes[ancestor02] ? ancestor01:ancestor02;
                int small = big == ancestor01 ? ancestor02: ancestor01;

                parents[small] = big;
                sizes[big] = sizes[ancestor01] + sizes[ancestor02];
                // 这一行可有可无
                sizes[small] = 0;
                count--;
            }
        }

        // 二维数组的位置转换成一维数组的下标
        // (i, j) -> k
        public int index (int i, int j) {
            return i * col + j;
        }

    }

    public static void main(String[] args) {
        int maxRow = 10000;
        int maxCol = 10000;

        char[][] grid01 = generateMatrix(maxRow, maxCol);
        char[][] grid02 = copyMatrix(grid01);
        char[][] grid03 = copyMatrix(grid01);
        long start01 = System.currentTimeMillis();
        int ans = Code03_IslandCountInfect.numIsland(grid01);
        long end01 = System.currentTimeMillis();
        System.out.println(ans + " 感染方式耗时： " + (end01 - start01) + "ms");

        start01 = System.currentTimeMillis();
        ans = Code05_IslandCountUnionFIndV2.numIslands(grid02);
        end01 = System.currentTimeMillis();
        System.out.println(ans + " 并查集数组方式： " + (end01 - start01) + "ms");

        start01 = System.currentTimeMillis();
        ans = Code04_IslandCountUnionFind.numIslands(grid03);
        end01 = System.currentTimeMillis();
        System.out.println(ans + " 并查集map方式： " + (end01 - start01) + "ms");
    }


    public static char[][] copyMatrix (char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        char[][] copy = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }

    public static char[][] generateMatrix (int maxRow, int maxCol) {
        int row = (int) (Math.random() * maxRow) + 1;
        int col = (int) (Math.random() * maxCol) + 1;

        char[][] grid= new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (Math.random() < 0.4) {
                    grid[i][j] = '1';
                } else {
                    grid[i][j] = '0';
                }
            }
        }
        return grid;
    }

}
