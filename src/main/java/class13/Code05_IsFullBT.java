package class13;

import class11.BinaryNode;
import class12.Code03_PrintBinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 判断一棵二叉树是不是满二叉树 Full Binary Tree
 * 满二叉树：如果一个二叉树的高度是 h， 那么节点的个数是 2^h - 1
 * 思路：
 *  递归套路
 *  1. 当前节点x的可能性
 *      (1) 对于当前节点 x， 如果 x 的左子树是FBT，右子树是FBT，且左右子树的高度相同，则当前节点x是FBT
 *  2. Info 信息
 *      (1) isFBT, 当前节点是否是满二叉树
 *      (2) height, 当前节点的高度
 */
public class Code05_IsFullBT {

    public static boolean isFullBT (BinaryNode<Integer> head) {
        return process(head).isFBT;
    }

    public static class Info {
        public boolean isFBT;
        public int height;
        public Info (boolean isFBT, int height) {
            this.isFBT = isFBT;
            this.height = height;
        }
    }

    public static Info process(BinaryNode<Integer> node) {
        // base case
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.getLeft());
        Info rightInfo = process(node.getRight());
        boolean isBFT = false;
        if (leftInfo.isFBT && rightInfo.isFBT && leftInfo.height == rightInfo.height) {
            isBFT = true;
        }
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(isBFT, height);
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLevel = 4;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            boolean isFullBT = isFullBT(head);
            boolean isFullBTTest = isFullBTTest(head);
            if (isFullBT != isFullBTTest) {
                System.out.println("Opps!!!!!");
                Code03_PrintBinaryTree.printTree(head);
                System.out.println();
            }
        }
//        BinaryNode<Integer> head = new BinaryNode<>(1);
//        head.setRight(new BinaryNode<>(2));
//        head.getRight().setLeft(new BinaryNode<>(3));
//        head.getRight().getLeft().setLeft(new BinaryNode<>(4));
//        head.getRight().getLeft().setRight(new BinaryNode<>(5));
//        head.getRight().setRight(new BinaryNode<>(6));
//        head.getRight().getRight().setRight(new BinaryNode<>(7));
//
//        System.out.println(binaryTreeHeight(head));
//        Code03_PrintBinaryTree.printTree(head);
    }

    // For Test
    public static boolean isFullBTTest (BinaryNode<Integer> head) {
        if (head == null) {
            return true;
        }
        int height = binaryTreeHeight(head);
        int count = binaryTreeNodeCount(head);
        return (Math.pow(2, height) - 1) == count;
    }

    // 求一棵二叉树的高度，思路是按层遍历
    public static int binaryTreeHeight (BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        BinaryNode<Integer> currentEnd = head;
        BinaryNode<Integer> nextEnd = head;
        int height = 0;
        while (!queue.isEmpty()) {
            BinaryNode<Integer> current = queue.poll();
            if (current.getLeft() != null) {
                nextEnd =  current.getLeft();
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                nextEnd = current.getRight();
                queue.add(current.getRight());
            }
            if (currentEnd == current) {
                height ++;
                currentEnd = nextEnd;
            }
        }
        return height;
    }

    // 求一棵二叉树的节点个数，中遍历
    public static int binaryTreeNodeCount (BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        int count = 0;
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.getLeft();
            } else {
                // head 为空就从栈里面弹出一个
                head = stack.pop();
//                System.out.println();
                count++;
                head = head.getRight();
            }
        }
        return count;
    }

    public static BinaryNode<Integer> generateBT (int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }
    public static BinaryNode<Integer> generate (int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>((int)(Math.random() * maxValue));
        node.setLeft(generate(level + 1, maxLevel, maxValue));
        node.setRight(generate(level + 1, maxLevel, maxValue));
        return node;
    }
}
