package class11;

/**
 * 二叉树的递归遍历
 *  (1) 先序遍历，先打印头节点，再打印左孩子，最后打印右孩子（头左右）
 *  (2) 中序遍历，先打印左孩子，再打印头节点，最后打印右孩子（左头右）
 *  (2) 后序遍历，先打印右孩子，再打印左孩子，最后打印头结点（右左头）
 *  ps；其实二叉树的遍历一共有6种， 另外 3 种方式是 头右左、右头左、左右头
 * @Author 张三金
 * @Date 2021/12/11 0011 9:06
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_RecursiveTraversal {

    /**
     *   *****递归序******
     *
     * 遍历二叉树的时候，每个节点 x 都会经过自己三次
     *  1. 来到 x 自己一次
     *  2. 从左子树回到自己这里 又一次
     *  3. 从右子树回到自己这里，又一次
     *
     *  所以决定先序、中序还是后续的关键是，打印语句在这三次中的哪一次打印
     */
    public static void f (BinaryNode<Integer> head) {

        // 1 打印放到这里 -> 先序
        f(head.getLeft());
        // 2 打印放到这里 -> 中序
        f(head.getRight());
        // 3 打印放到这里 -> 后序
    }

    /**
     * 二叉树的先序遍历 - 递归实现
     */
    public static void pre (BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.getValue() + " -> ");
        pre(head.getLeft());
        pre(head.getRight());
    }

    /**
     * 二叉树的中序遍历 - 递归实现
     */
    public static void in(BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        in(head.getLeft());
        System.out.print(head.getValue() + " -> ");
        in(head.getRight());
    }

    /**
     * 二叉树的后序遍历 - 递归实现
     */
    public static void pos(BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        pos(head.getLeft());
        pos(head.getRight());
        System.out.print(head.getValue() + " -> ");
    }

    public static void main(String[] args) {
        BinaryNode<Integer> node = new BinaryNode<>(1);
        node.setLeft(new BinaryNode<>(2));
        node.getLeft().setLeft(new BinaryNode<>(4));
        node.getLeft().setRight(new BinaryNode<>(5));

        node.setRight(new BinaryNode<>(3));
        node.getRight().setLeft(new BinaryNode<>(6));
        node.getRight().getLeft().setLeft(new BinaryNode<>(7));
        node.getRight().setRight(new BinaryNode<>(8));

        // 先序遍历  1 -> 2 -> 4 -> 5 -> 3 -> 6 -> 7 -> 8
        pre(node);
        System.out.println("===============");
        // 中序遍历 4 -> 2 -> 5 -> 1 -> 7 -> 6 -> 3 -> 8
        in(node);
        System.out.println("===============");
        // 后序遍历 4 -> 5 -> 2 -> 7 -> 6 -> 8 -> 3 -> 1
        pos(node);

    }
}
