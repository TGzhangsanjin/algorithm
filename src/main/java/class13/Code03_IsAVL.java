package class13;

import class11.BinaryNode;

/**
 * 判断一棵树是不是平衡二叉树
 * 平衡二叉树： AVL树种任何节点的两个子树的高度差都不能超过 1
 * AVl （Adelson-Velskii、Landis两个人名）
 *
 * 思路：
 *  递归套路
 *  1. 对于节点x,列出可能性
 *      x 的左子树是平衡二叉树、x的右子树是平衡二叉树，且 x 的左右子树高度差不超过1，则x作为头结点的子树是平衡二叉树
 *  2. 确定Info信息
 *      (1) isAVL 是否是平衡二叉树
 *      (2) height 树的高度
 */
public class Code03_IsAVL {

    public static class Info {
        public boolean isAVL;
        public int height;
        public Info (boolean i, int h) {
            isAVL = i;
            height = h;
        }
    }

    public static boolean isAVL (BinaryNode<Integer> head) {
        return process(head).isAVL;
    }
    public static Info process (BinaryNode<Integer> node) {
        // 递归的 base case
        // 同时也很容易确定空值得 info信息
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.getLeft());
        Info rightInfo = process(node.getRight());

        boolean isAVL = true;
        // 高度那就是左右子树的最大高度+1
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        if (!leftInfo.isAVL || !rightInfo.isAVL) {
            // 左右子树只要有一个不是AVL，那当前节点也不是AVL
            isAVL = false;
        } else if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            // 左右子树的高度差超过1，那当前节点不是AVL
            isAVL = false;
        }
        return new Info(isAVL, height);
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLevel = 10;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            boolean isAVL = isAVL(head);
            boolean isAVLTest = isAVLTest(head);
            if (isAVL != isAVLTest) {
                System.out.println("Opps!!!!!!!");
            }
        }
    }

    // For Test
    public static boolean isAVLTest (BinaryNode<Integer> head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process(head, ans);
        return ans[0];
    }

    public static int process (BinaryNode<Integer> node, boolean[] ans) {
        if (!ans[0] || node == null) {
            // 如果已经知道了当前不是平衡二叉树，或者已经遍历到底了，则直接返回不用管了
            return 0;
        }
        int leftHeight = process(node.getLeft(), ans);
        int rightHeight = process(node.getRight(), ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static BinaryNode<Integer> generateBT (int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }
    public static BinaryNode<Integer> generate (int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>((int) (Math.random() * maxValue));
        node.setLeft(generate(level + 1, maxLevel, maxValue));
        node.setRight(generate(level + 1, maxLevel, maxValue));
        return node;
    }
}
