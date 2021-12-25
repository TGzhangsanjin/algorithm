package class13;

import class11.BinaryNode;
import class12.Code03_PrintBinaryTree;

import java.util.ArrayList;

/**
 * 给定一棵二叉树的头结点 head，返回这课二叉树中最大搜索子树的头结点
 *
 * 和 Code06_MaxBSTCount 思路是一样的，只不过一个是返回最大搜索子树的节点个数，一个返回头结点
 *
 * 节点 x 的 Info 信息
 *      (1) maxBCTSize 最大搜索子树节点数
 *      (2) size 最大节点数
 *      (3) max 节点的最大值
 *      (4) min 节点的最小值
 *      (5) maxNode 最大搜索子树的头结点
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
        return process(head).maxNode;
    }

    public static class Info {
        int maxBSTSize;
        int size;
        int max;
        int min;
        BinaryNode<Integer> maxNode;

        public Info (int maxBSTSize, int size, int max, int min, BinaryNode<Integer> maxNode) {
            this.maxBSTSize = maxBSTSize;
            this.size = size;
            this.max = max;
            this.min = min;
            this.maxNode = maxNode;
        }
    }

    public static Info process (BinaryNode<Integer> node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.getLeft());
        Info rightInfo = process(node.getRight());
        int max = node.getValue();
        int min = node.getValue();
        int size = 1;
        int maxBSTSize = Math.max(leftInfo == null ? 0:leftInfo.maxBSTSize, rightInfo == null ? 0:rightInfo.maxBSTSize);
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            size += leftInfo.size;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            size += rightInfo.size;
        }

        BinaryNode<Integer> maxNode;
        if (leftInfo == null && rightInfo == null) {
            maxNode = node;
        } else if (leftInfo != null && rightInfo != null) {
            maxNode = leftInfo.maxBSTSize >= rightInfo.maxBSTSize ? leftInfo.maxNode : rightInfo.maxNode;
        } else if (rightInfo != null) {
            maxNode = rightInfo.maxNode;
        } else {
            maxNode = leftInfo.maxNode;
        }


        boolean leftBST = leftInfo == null ? true : leftInfo.maxBSTSize == leftInfo.size;
        boolean rightBST = rightInfo == null ? true : rightInfo.maxBSTSize == rightInfo.size;

        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < node.getValue();
            boolean rightMinMoreX = rightInfo == null ? true : rightInfo.min > node.getValue();
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0:leftInfo.maxBSTSize;
                int rightSize = rightInfo == null ? 0:rightInfo.maxBSTSize;
                maxBSTSize = 1 + leftSize + rightSize;
                maxNode = node;
            }
        }
        return new Info(maxBSTSize, size, max, min, maxNode);
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLevel = 10;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            BinaryNode<Integer> node01 = maxBSTNode(head);
            BinaryNode<Integer> node02 = maxSubBSTHead1(head);
            if (node01 != node02) {
                System.out.println("Opps!!!!!");
                Code03_PrintBinaryTree.printTree(head);
                System.out.println();
                break;
            }
        }
    }


    // For Test
    public static BinaryNode<Integer> maxSubBSTHead1(BinaryNode<Integer> head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        BinaryNode<Integer> leftAns = maxSubBSTHead1(head.getLeft());
        BinaryNode<Integer> rightAns = maxSubBSTHead1(head.getRight());
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        ArrayList<BinaryNode<Integer>> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).getValue() <= arr.get(i - 1).getValue()){
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(BinaryNode<Integer> head, ArrayList<BinaryNode<Integer>> arr) {
        if (head == null) {
            return;
        }
        in(head.getLeft(), arr);
        arr.add(head);
        in(head.getRight(), arr);
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
