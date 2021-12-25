package class13;

import class11.BinaryNode;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵树是否为完全二叉树CBT（Complete Binary Tree）
 *
 * 思路：
 *  按层遍历所有的节点
 *  （1） 如果节点 x 有右孩子，没有左孩子，则直接返回 false
 *  （2） 如果节点 x 没有两个孩子，那么，x之后的节点都必须是叶子节点
 */
public class Code01_IsCBT {

    public static boolean isCBT (BinaryNode<Integer> head) {
        if (head == null) {
            return true;
        }

        // 叶子节点开始的标志
        boolean leafBegin = false;
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            BinaryNode<Integer> left = head.getLeft();
            BinaryNode<Integer> right = head.getRight();
            // 左孩子为空，右孩子为空，直接返回false
            if (left == null && right != null) {
                return false;
            }
            // 已经遇到了左右孩子不双全的节点，并且当前节点不是叶子节点，则直接返回false
            if (leafBegin && (left != null || right != null)) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                leafBegin = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxLevel = 4;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            boolean isCBT01 = isCBT(head);
            boolean isCBT02 = isCBT2(head);
            if (isCBT01 != isCBT02) {
                System.out.println("Opps!!!!!!");
            }
        }
    }

    /**
     *  使用递归套路的方式去解决这个问题
     *
     *  对于 x 节点分析可能性：
     *      (1) 左子树是满二叉树， 右子树是满二叉树，   （左子树高度 == 右子树高度）或者 （左子树高度 == 右子树高度 + 1） ，则是完全二叉树
     *      (2) 左子树是满二叉树， 右子树是完全二叉树， 左子树高度 == 右子树高度， 则是完全二叉树
     *      (3) 左子树是完全二叉树， 右子树是满二叉树， 左子树高度 == 右子树高度 + 1， 则是完全二叉树
     *      (4) 左子树是完全二叉树， 右子树是完全二叉树， 不处理，即判断不出来是否是完全二叉树，认为不是
     *
     *  info信息：
     *      (1) isCBT 是否是完全二叉树
     *      (2) height 二叉树的高度
     */
    public static boolean isCBT2 (BinaryNode<Integer> head) {
        return process(head).isCBT;
    }

    public static class Info {
        public boolean isCBT;
        public boolean isFBT;
        public int height;
        public Info (boolean isCBT, boolean isFBT, int height) {
            this.isCBT = isCBT;
            this.isFBT = isFBT;
            this.height = height;
        }
    }

    public static Info process (BinaryNode<Integer> node) {
        // base case
        if (node == null) {
            return new Info(true, true,0);
        }
        Info leftInfo = process(node.getLeft());
        Info rightInfo = process(node.getRight());
        boolean isCBT = false;
        boolean isFBT = false;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        if (leftInfo.isFBT && rightInfo.isFBT) {
            if (leftInfo.height == rightInfo.height) {
                isCBT = true;
                isFBT = true;
            } else if (leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            }
        } else if (leftInfo.isFBT && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        }else if (leftInfo.isCBT && rightInfo.isFBT && (leftInfo.height - rightInfo.height == 1)) {
            isCBT = true;
        }
        return new Info(isCBT, isFBT, height);
    }

    public static BinaryNode<Integer> generateBT (int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static BinaryNode<Integer> generate (int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>(1);
        node.setLeft(generate(level + 1, maxLevel, maxValue));
        node.setRight(generate(level + 1, maxLevel, maxValue));
        return node;
    }

}
