package class13;

import class11.BinaryNode;

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
        // 叶子节点开始的标志
        boolean leafBegin = false;
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            BinaryNode<Integer> left = head.getLeft();
            BinaryNode<Integer> right = head.getLeft();
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
}
