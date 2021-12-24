package class13;

import class11.BinaryNode;
import class12.Code03_PrintBinaryTree;

import java.util.ArrayList;

/**
 * 判断一棵二叉树是否是搜索二叉树 BST (Binary Search Tree)
 * 搜索二叉树的概念： 对于一棵二叉树的每一个节点 x ，如果x它的左子树上的每一个节点的值都小于x的值, 右子树上的每一个节点值都大于x的值
 *
 * 思路：
 *  使用递归
 *  （1） 对于每一个节点x节点需要掌握四个信息，
 *      a. x 的左子树是否是BST
 *      b. x 的右子树是否是BST
 *      c. x 左子树的最大值
 *      c. x 右子树的最小值
 *   (2) 递归时每个节点 x 都应该返回三个信息
 *      a. x作为头结点的子树是否是 BST
 *      b. x作为头结点的子树的最大值 max
 *      c. x作为头结点的子树的最小值 min
 *
 *  ps: 标准的搜索二叉树不考虑两个节点的值相同的情况，如果需要考虑，也应该将相同的值放在同一个节点上（即一个节点可以存储多个值）
 *
 */
public class Code02_IsBST {

    /**
     * 孩子节点传递给父节点时的信息
     */
    private static class Info {
        boolean isBST;
        int max;
        int min;
        public Info (boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST (BinaryNode<Integer> head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process (BinaryNode<Integer> node) {
        // 当为空时，不知道返回什么信息Info 的时候，就直接返回空，在后面的代码都要考虑为空的情况
        // 同时这个也是递归的 base case
        if (node == null) {
            return null;
        }
        Info lefInfo = process(node.getLeft());
        Info rightInfo = process(node.getRight());
        // 当前节点需要返回的三个信息数据
        boolean isBST = true;
        int max = node.getValue();
        int min = node.getValue();
        // 计算最大值和最小值
        if (lefInfo != null) {
            max = Math.max(max, lefInfo.max);
            min = Math.min(min, lefInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        // 下面就是去分析各种情况, 这里就是排除 node 作为头节点部位BST的所以情况
        // 注意这下面两种情况要同时满足才行
        if (lefInfo != null) {
            // 左子树不是BST或者左子树的最大值大于等于当前值，那么node作为头节点的子树自然也不是BST
            if (!lefInfo.isBST || lefInfo.max >= node.getValue()) {
                isBST = false;
            }
        }
        if (rightInfo != null) {
            // 右子树不是BST或者右子树的最小值小于等于当前值，那么node作为头节点的子树自然也不是BST
            if (!rightInfo.isBST || rightInfo.min <= node.getValue()) {
                isBST = false;
            }
        }
        return new Info(isBST, max, min);
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxLevel = 10;
        int maxValue = 10000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            boolean isBST = isBST(head);
            boolean testIsBST = isBST01(head);

            if (isBST != testIsBST) {
                System.out.println("OPPS");
                System.out.println("isBST == " + isBST + ",  testIsBST == " + testIsBST);
                Code03_PrintBinaryTree.printTree(head);
                System.out.println();
            }
        }
    }

    /**
     * For Test 对数器
     * 判断一棵二叉树是否搜索二叉树，其实相当于中序遍历二叉树，遍历的结果前面节点的值都必须小于后面节点的值
     */
    public static boolean isBST01 (BinaryNode<Integer> head) {
        if (head == null) {
            return true;
        }
        ArrayList<Integer> array = new ArrayList<>();
        in(head, array);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) <= array.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void in (BinaryNode<Integer> node, ArrayList<Integer> array) {
        if (node == null) {
            return;
        }
        in(node.getLeft(), array);
        array.add(node.getValue());
        in(node.getRight(), array);
    }

    // 生成一个随机的二叉树
    public static BinaryNode<Integer> generateBT (int maxLevel, int maxValue) {
        if (maxLevel < 1) {
            return null;
        }
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
