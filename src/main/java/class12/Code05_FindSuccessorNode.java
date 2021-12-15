package class12;

/**
 * 查找二叉树某个节点的后继节点
 * 后继节点的定义： 二叉树中序遍历中的下一个节点
 *
 * 思路：
 *   对于节点x，分两种情况
 *  1. 如果节点 x 有右孩子，那么 x 的后继节点就是 x 的右孩子
 *  2. 如果节点 x 没有右孩子，那么 x 一直去找他的一个作为左孩子的父亲节点，第二种情况如下
 *
 *      如下集中请款：x的后继节点是 y
 *        __ y                            __y              x__
 *       /                               /                    \
 *      a__                             x                      y
 *          \
 *           b__
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
            return node.getRight();
        }
        SuccessorNode<Integer> current = node.getParent();
        while (current != null && ) {
            node = node.getParent();
            if (node.getParent()!= null && node.getParent().get) {

            }
        }
    }
}
