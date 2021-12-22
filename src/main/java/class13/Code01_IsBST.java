package class13;

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
 */
public class Code01_IsBST {

    private static class Node {
        int value;
        Node left;
        Node right;
        public Node (int v) {
            value = v;
        }
    }

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

    public static Info process (Node node) {
        // 当为空时，不知道返回什么信息Info 的时候，就直接返回空，在后面的代码都要考虑为空的情况
        if (node == null) {
            return null;
        }
        Info lefInfo = process(node.left);
        Info rightInfo = process(node.right);
        // 当前节点需要返回的三个信息数据
        boolean isBST = true;
        int max = node.value;
        int min = node.value;
        // 计算最大值和最小值
        if (lefInfo != null) {
            max = Math.max(max, lefInfo.max);
            min = Math.max(min, lefInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.max(min, rightInfo.min);
        }
        // 下面就是去分析各种情况, 这里就是排除 node 作为头节点部位BST的所以情况
        if (lefInfo != null) {
            // 左子树不是BST或者左子树的最大值大于等于当前值，那么node作为头节点的子树自然也不是BST
            if (!lefInfo.isBST || lefInfo.max >= node.value) {
                isBST = false;
            }
        } else if (rightInfo != null) {
            // 右子树不是BST或者右子树的最小值小于等于当前值，那么node作为头节点的子树自然也不是BST
            if (!rightInfo.isBST || rightInfo.min <= node.value) {
                isBST = false;
            }
        }
        return new Info(isBST, max, min);
    }
}
