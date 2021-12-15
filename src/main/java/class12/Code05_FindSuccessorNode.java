package class12;

import class11.BinaryNode;

/**
 * 查找二叉树某个节点的后继节点
 * 后继节点的定义： 二叉树中序遍历中的下一个节点
 *
 * 思路：
 *   对于节点x，分两种情况
 *  1. 如果节点 x 有右子树，那么 x 的后继节点就是 右子树的左边界
 *  2. 如果节点 x 没有右孩子，那么 x 一直去找他的一个作为左子树的父亲节点，
 *
 *      如下集中请款：x的后继节点是 y
 *        __ y                            __y              x__
 *       /                               /                    \
 *      a__                             x                    __a
 *          \                                               /
 *           b__                                           y
 *              \
 *               c__
 *                  \
 *                   x
 * @Author 张三金
 * @Date 2021/12/15 0015 17:32
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_FindSuccessorNode {

    public static SuccessorNode<Integer> findSuccessorNode (SuccessorNode<Integer> node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() != null) {
            return getLeftMost(node.getRight());
        }
        SuccessorNode<Integer> parent = node.getParent();
        while (parent != null && parent.getRight() == node) {
            // 当前节点是其父节点的右孩子
            node = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     *  获取 node 的左边界（即最左边的一个孩子）
     */
    public static SuccessorNode<Integer> getLeftMost (SuccessorNode<Integer> node) {
        if (node == null) {
            return null;
        }
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public static void main(String[] args) {
        SuccessorNode<Integer> node1 = new SuccessorNode<>(1);
        node1.setLeft(new SuccessorNode<>(2));
        node1.getLeft().setParent(node1);
        node1.getLeft().setLeft(new SuccessorNode<>(4));
        node1.getLeft().getLeft().setParent(node1.getLeft());
        node1.getLeft().setRight(new SuccessorNode<>(5));
        node1.getLeft().getRight().setParent(node1.getLeft());

        node1.setRight(new SuccessorNode<>(3));
        node1.getRight().setParent(node1);
        node1.getRight().setLeft(new SuccessorNode<>(6));
        node1.getRight().getLeft().setParent(node1.getRight());
        node1.getRight().getLeft().setLeft(new SuccessorNode<>(7));
        node1.getRight().getLeft().getLeft().setParent(node1.getRight().getLeft());
        node1.getRight().setRight(new SuccessorNode<>(8));
        node1.getRight().getRight().setParent(node1.getRight());
        // 4 -> 2 -> 5 -> 1 -> 7 -> 6 -> 3 -> 8
        System.out.println(findSuccessorNode(node1.getRight().getLeft().getLeft()).getValue());

    }
}
