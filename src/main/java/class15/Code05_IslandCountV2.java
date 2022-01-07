package class15;

/**
 *  题目链接 https://leetcode-cn.com/problems/number-of-islands-ii/
 * 岛数量问题2
 *  问题描述： 初始化一个 matrix[m][n], 每个位置上的值都是 0, 然后按照顺序一个个往矩阵中放入 1, 求每次放入1之后, 举证中的岛数量
 *
 *
 * @Author 张三金
 * @Date 2022/1/7 0007 15:18
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_IslandCountV2 {

    /**
     * m*n大小的矩阵
     * positions 是一个 2*k的矩阵 （k <= m*n）
     * 如果输入的是 2*k 大小的矩阵，那么返回的一位数组大小也肯定是 k
     */
    public static int[] numIsland2 (int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m, n);
        int[] ans = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            ans[i] = unionFind.connect(positions[i][0], positions[i][1]);
        }
        return ans;
    }

    public static class UnionFind {

        // key: 节点 value: 祖先
        int[] parents;
        // key: 节点 value：节点代表的集合大小
        int[] sizes;

        int row;
        int col;
        int count;

        int[] help;

        public UnionFind (int m, int n) {
            row = m;
            col = n;
            parents = new int[m * n];
            sizes = new int[m * n];
            help = new int[m * n];
            count = 0;
        }

        public int findAncestor (int i) {
            int helpIndex = 0;
            while (parents[i] != i) {
                help[helpIndex++] = parents[i];
                i = parents[i];
            }
            while (helpIndex > 0) {
                parents[--helpIndex] = i;
            }
            return i;
        }

        public void union (int i1, int j1, int i2, int j2) {
            if (i1 == row || i1 < 0 || j1 == col || j1 < 0) {
                // 越界了，不处理
                return;
            }
            int index1 = index(i1, j1);
            int index2 = index(i2, j2);
            if (sizes[index1] == 0 || sizes[index2] == 0) {
                // 如果两个节点有一个没有空降过，则啥都不做
                return;
            }
            int ancestor1 = findAncestor(index1);
            int ancestor2 = findAncestor(index2);

            if (ancestor1 != ancestor2) {
                int big = sizes[ancestor1] >= sizes[ancestor2] ? ancestor1:ancestor2;
                int small = big == ancestor1 ? ancestor2: ancestor1;
                parents[small] = big;
                sizes[big] = sizes[small] + sizes[big];
                // 千万注意，单纯的求岛数量时，这里的sizes[small] 可以设置为0，但是这里不能，因为要用这个来判断这个节点是否处理过了
                count--;
            }
        }

        public int connect (int r, int c) {
            int index = index(r,c);
            if (sizes[index] == 0) {
                // 这里出现不为0的情况，传过来的 positions 中可能会有位置相同的点传进行（即可能会有重复）
                parents[index] = index;
                sizes[index] = 1;
                count++;
                // 去和当前位置的上下左右去做 union
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return count;
        }

        public int index (int i, int j) {
            return i * col + j;
        }
    }


    public static void main(String[] args) {
        int[][] positions = {{0, 0}, {0,1},{1,2}, {2,1}};
        int[] ans = numIsland2(3,3,positions);

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + ",");
        }
    }

    // 如果 m和n都很大，比如上亿的话，而 positions 特别小的话，那么上面方法方法就需要开辟很大的数组，额外空间复杂度就太高了
    public static int[] numIsland22 (int m, int n, int[][] positions) {

    }
}
