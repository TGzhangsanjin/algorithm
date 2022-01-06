package class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 本题为leetcode原题
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 * 给定一个二维数组 matrix, 里面的值不是1就是0
 * 上、下、左、右相邻的1认为是一片岛， 返回 matrix中岛的数量
 *
 * 并查集思路解决此问题
 *      遍历每一个节点，都去找左边和上边的'1'进行合并（不需要去找右边和下边的），
 *
 * @Author 张三金
 * @Date 2021/12/30 0030 11:26
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_IslandCountUnionFind {

    public static void main(String[] args) {

        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};

        System.out.println(numIslands(grid));
    }


    public static int numIslands (char[][] grid){
        int row = grid.length;
        int col = grid[0].length;
        // 这里额外用一个对象的原因是因为，二维数组里面的字符无法区分，在map中key值都是相同的，所以用了一个对象
        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFind<Dot> unionFind = new UnionFind<>(dotList);
        // 处理第一行的数据
        for (int i = 1; i < col; i++) {
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                unionFind.union(dots[0][i - 1], dots[0][i]);
            }
        }
        // 处理第一列的数据
        for (int i = 1; i < row; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                unionFind.union(dots[i - 1][0], dots[i][0]);
            }
        }
        // 处理其它行列的数据
        for (int i = 1; i < row; i++) {
           for (int j = 1; j <col; j++) {
               if (grid[i][j] == '1') {
                   if (grid[i - 1][j] == '1') {
                       unionFind.union(dots[i][j], dots[i - 1][j]);
                   }
                   if (grid[i][j - 1] == '1') {
                       unionFind.union(dots[i][j], dots[i][j - 1]);
                   }
               }
           }
        }
        return unionFind.sets();
    }

    public static class Dot {

    }

    public static class MyNode<T> {
        T value;

        public MyNode (T value) {
            this.value = value;
        }
    }

    public static class UnionFind<T> {
        public HashMap<T, MyNode<T>> nodes;
        // 元素的祖先
        public HashMap<MyNode<T>, MyNode<T>> parents;
        // 每个代表节点代表的集合的元素个数
        public HashMap<MyNode<T>, Integer> sizeMap;

        public UnionFind (List<T> values) {
            nodes = new HashMap<>(values.size());
            parents = new HashMap<>(values.size());
            sizeMap = new HashMap<>(values.size());
            for (T t : values) {
                MyNode<T> node = new MyNode<>(t);
                nodes.put(t, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public MyNode<T> findAncestor (MyNode<T> node) {
            Stack<MyNode<T>> stack = new Stack<>();
            while (parents.get(node) != node) {
                stack.push(node);
                node = parents.get(node);
            }
            // 压缩路径
            while (!stack.isEmpty()) {
                MyNode<T> current = stack.pop();
                parents.put(current, node);
                sizeMap.remove(current);
            }
            return node;
        }

        public void union (T t1, T t2) {
            MyNode<T> ancestor01 = findAncestor(nodes.get(t1));
            MyNode<T> ancestor02 = findAncestor(nodes.get(t2));
            if (ancestor01 != ancestor02) {
                int size01 = sizeMap.get(ancestor01);
//                System.out.println(ancestor02);
                int size02 = sizeMap.get(ancestor02);
                MyNode<T> big = size01 > size02 ?ancestor01:ancestor02;
                MyNode<T> small = big == ancestor01 ? ancestor02:ancestor01;
                parents.put(small, big);
                sizeMap.put(big, size01 + size02);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }
}
