package class13;

import class11.BinaryNode;
import class12.Code03_PrintBinaryTree;

import java.beans.BeanInfo;
import java.util.ArrayList;

/**
 * 给定一个二叉树的头结点 head， 该二叉树可能是搜索二叉树，也可能不是，返回head 的最大二叉搜索子树的节点个数
 * 思路：
 *  递归套路
 *  1. 对节点 x 分析可能性
 *      (1) x 不做头（意思就是不在，以x节点为头结点的二叉树不是BST），
 *          a. 那么就应该返回左子树的最大二叉搜索子树、右子树的最大二叉搜索子树二者的最大值
 *      (2) x 做头
 *          a. 左子树是二叉搜索子树
 *          b. 右子树是二叉搜索子树
 *          c. x > 左子树的最大值
 *          d. x < 右子树的最小值
 *  2. Info 信息
 *      (1) maxBSTSize 最大二叉搜索子树的值
 *      (2) size 节点数
 *      (3) max 最大值
 *      (4) min 最小值
 */
public class Code06_MaxBSTCount {

    public static int maxBST (BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSize;
    }

    public static class Info {
        int maxBSTSize;
        int size;
        int max;
        int min;
        public Info (int maxBSTSize, int size, int max, int min){
            this.maxBSTSize = maxBSTSize;
            this.size = size;
            this.max = max;
            this.min = min;
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
        boolean leftBST = leftInfo == null ? true : leftInfo.maxBSTSize == leftInfo.size;
        boolean rightBST = rightInfo == null ? true : rightInfo.maxBSTSize == rightInfo.size;
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < node.getValue();
            boolean rightMinMoreX = rightInfo == null ? true : rightInfo.min > node.getValue();
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0:leftInfo.maxBSTSize;
                int rightSize = rightInfo == null ? 0:rightInfo.maxBSTSize;
                maxBSTSize = 1 + leftSize + rightSize;
            }
        }
        return new Info(maxBSTSize, size, max, min);
    }


    public static void main(String[] args) {

        int testTimes = 100000;
        int maxLevel = 10;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            int size01 = maxBST(head);
            int size02 = maxSubBSTSize1(head);
            if (size01 != size02) {
                System.out.println("Opps!!!!!");
                Code03_PrintBinaryTree.printTree(head);
                System.out.println();
            }
        }
    }



    // For Test
    public static int getBSTSize(BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        ArrayList<BinaryNode<Integer>> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).getValue() <= arr.get(i - 1).getValue()) {
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


    // For Test
    public static int maxSubBSTSize1(BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.getLeft()), maxSubBSTSize1(head.getRight()));
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
