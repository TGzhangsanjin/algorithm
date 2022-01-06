package class15;

/**
 * 并查集解决岛问题
 * 并查集中不使用map的方式，二维数组转换成一维数组， 二维数组 matrix[i][j] 中对应一维数组的下标就是 array[i*列数] + j
 * @Author 张三金
 * @Date 2022/1/6 0006 11:01
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_IslandCountUnionFIndV2 {


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
                    count++;
                }
            }
        }

        public int findAncestor (int i) {
            int helpIndex = 0;
            while (parents[i] != i) {
                help[helpIndex] = parents[i];
                helpIndex++;
                i = parents[i];
            }
            // 压缩路径
            while (helpIndex >= 0) {
                parents[help[helpIndex]] = i;
                helpIndex--;
            }
            return i;
        }

        // (i1, j1)和 (i2, j2) 两个位置进行union
        public void union (int i1, int j1, int i2, int j2) {
            int index01 = index(i1, j1);
            int index02 = index(i2, j2);
            int ancestor01 = findAncestor(index01);
            int ancestor02 = findAncestor(index02);
            int big = sizes[ancestor01] > sizes[ancestor02] ? ancestor01:ancestor02;
            int small = big == ancestor01 ? ancestor02: ancestor01;

            parents[small] = big;
            sizes[big] = sizes[ancestor01] + sizes[ancestor02];
            // 这一行可有可无
            sizes[small] = 0;
            count--;
        }


        // 二维数组的位置转换成一维数组的下标
        // (i, j) -> k
        public int index (int i, int j) {
            return i * col + j;
        }

    }

}
