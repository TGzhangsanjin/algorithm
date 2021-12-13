package class12;

import class11.BinaryNode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 二叉树的按层遍历， 也就是宽度优先遍历
 * 思路： 使用队列
 *  （1）对于 current 节点， current节点从队列中出来时，打印
 *  （2）current 打印后，有左入左，有右入右，即有直接左孩子，就将直接左孩子放入队列，有直接右孩子就将直接右孩子放入队列
 *
 * @Author 张三金
 * @Date 2021/12/11 0011 16:46
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_LevelTraversal {

    public static void levelTraversal (BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            BinaryNode<Integer> current = queue.poll();
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
            System.out.print(current.getValue() + " -> ");
        }
    }

    public static void main(String[] args) {
        BinaryNode<Integer> node1 = new BinaryNode<>(1);
        node1.setLeft(new BinaryNode<>(2));
        node1.getLeft().setLeft(new BinaryNode<>(4));
        node1.getLeft().setRight(new BinaryNode<>(5));

        node1.setRight(new BinaryNode<>(3));
        node1.getRight().setLeft(new BinaryNode<>(6));
        node1.getRight().getLeft().setLeft(new BinaryNode<>(7));
        node1.getRight().setRight(new BinaryNode<>(8));

        BinaryNode<Integer> node2 = new BinaryNode<>(1);
        node2.setLeft(new BinaryNode<>(2));
        node2.getLeft().setLeft(new BinaryNode<>(4));
        node2.getLeft().setRight(new BinaryNode<>(5));

        node2.setRight(new BinaryNode<>(3));
        node2.getRight().setLeft(new BinaryNode<>(6));
        node2.getRight().setRight(new BinaryNode<>(7));

        // 宽度优先遍历  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 8 > 7
        levelTraversal(node1);
        System.out.println("============");
        // 宽度优先遍历  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
        levelTraversal(node2);
    }
}
