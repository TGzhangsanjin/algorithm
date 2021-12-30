package class15;

/**
 * LeetCode 547. Friend Circles
 * 测试链接：https://leetcode.com/problems/friend-circles/
 * 求朋友圈的个数问题
 *  问题说明：
 *      1. 一个班级汇总有多个学生，学生a的朋友是学生b，那么就认为a和b属于一个朋友圈，如果 b和朋友是c，那么就认为 a,b,c是一个朋友圈
 *      2. 输入是一个大小时 N*N 的二维数组（矩阵）， a[i][j] = 1, 表示 i和j是朋友， a[i][j] = 0, 表示 i和j不是朋友，
 *         且如果a[i][j] = 1, 那么a[j][i] 也一定等于1（含义就是a是b的朋友，那么b肯定也是a的朋友），如果a[i][j]=0，那么a[j][i]=0
 *         a[i][i] 肯定也是1，认为自己肯定是自己的朋友
 *
 * @Author 张三金
 * @Date 2021/12/27 0027 11:05
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_FriendCircle {

    // m 是一个矩阵，且行列数相同
    public static int findCircleNum (int[][] m) {

        UnionFind unionFind = new UnionFind(m);
        // 这两个循环表示只遍历矩阵的右上半区，
        for (int i = 0; i < m.length; i++) {
            for (int j = i+1; j < m[i].length; j++) {
                // i 和 j代表的是两个人，一开始他们都指向自己
                // 当[i][j] == 1 时，表示两个人认识，这个这个时候说明需要将i和j合并
                if (m[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    public static class UnionFind {
        // parents[i] = k， 表示i网上指向的节点时 k（注意不一定是祖先）
        public int[] parents;
        // size[i] 表示i代表的集合大小，即朋友圈个数
        // 只有i是代表节点时，size[i] 才有意义
        public int[] size;
        // 辅助数组，在找祖先的过程中，用来代替栈的， 因为数组的效率是比栈要更高的
        public int[] help;
        // 代表节点的个数，即朋友圈的个数
        public int sets;

        public UnionFind (int[][] m) {
            parents = new int[m.length];
            size = new int[m.length];
            help = new int[m.length];
            sets = m.length;
            for (int i = 0; i < m.length; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int findAncestor (int i) {
            int index = -1;
            while (i != parents[i]) {
                help[++index] = i;
                i = parents[i];
            }
            // 路径压缩，把当前节点以及中间的几点全部指向同一个祖先
            while (index >= 0) {
                parents[help[index--]] = i;
            }
            return i;
        }

        public void union (int i, int j) {
            int iHead = findAncestor(i);
            int jHead = findAncestor(j);
            if (iHead == jHead) {
                return;
            }
            int iHeadSize = size[iHead];
            int jHeadSize = size[jHead];
            int big = iHeadSize > jHeadSize ?iHead : jHead;
            int small = big == iHead ?jHead:iHead;
            parents[small] = big;
            size[big] = iHeadSize + jHeadSize;
//            size[small] = 0;
            // 朋友圈的个数需要减少一个
            sets--;
        }
    }
}
