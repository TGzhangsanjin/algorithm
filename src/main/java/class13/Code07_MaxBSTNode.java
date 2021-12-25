package class13;

import class11.BinaryNode;

/**
 * 给定一棵二叉树的头结点 head，返回这课二叉树中最大搜索子树的头结点
 *
 * 和 Code06_MaxBSTCount 思路是一样的，只不过一个是返回最大搜索子树的节点个数，一个返回头结点
 *
 * 节点 x 的 Info 信息
 *      (1) isBCT 是否最大搜索子树
 *      (2) max 节点的最大值
 *      (3) min 节点的最小值
 *      (4) maxNode 最大搜索子树的头结点
 *      (4) maxNodeCount 最大搜索子树的节点个数
 * @Author 张三金
 * @Date 2021/12/25 0025 17:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code07_MaxBSTNode {

    public static BinaryNode<Integer> maxBSTNode (BinaryNode<Integer> head) {
        if (head == null) {
            return null;
        }
    }

    public static class Info {
        boolean isBCT;
        int max;
        int min;
        BinaryNode<Integer> maxNode;
        int maxNodeCount;

        public Info (boolean isBCT, int max, int min, BinaryNode<Integer> maxNode, int maxNodeCount) {
            this.isBCT = isBCT;
            this.max = max;
            this.min = min;
            this.maxNode = maxNode;
            this.maxNodeCount = maxNodeCount;
        }
    }

    public static Info process (BinaryNode<Integer> node) {
        // base case
        if (node == null) {
            return new Info(true, 0, 0, null, 0);
        }
        Info leftInfo = process(node.getLeft());
        Info rightInfo = process(node.getRight());
        boolean isBCT = false;
        int max = Math.max(node.getValue(), Math.max(leftInfo.max, rightInfo.max));
        int min = Math.min(node.getValue(), Math.max(leftInfo.min, rightInfo.min));
        BinaryNode<Integer> maxNode;
        int maxNodeCount;
        if (leftInfo.isBCT && rightInfo.isBCT) {
            if (node.getValue() > leftInfo.max && node.getValue() < leftInfo.min) {
                // 只有这一种情况下当前节点才是搜索二叉树
                isBCT = true;
                maxNode = node;
                maxNodeCount = leftInfo.maxNodeCount + rightInfo.maxNodeCount + 1;
            } else {
                if (leftInfo.maxNodeCount > rightInfo.maxNodeCount) {
                    maxNode = leftInfo.maxNode;
                    maxNodeCount = leftInfo.maxNodeCount;
                }
            }
        } else if ()

    }
}
