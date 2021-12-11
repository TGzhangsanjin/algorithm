package class11;

import java.util.Stack;

/**
 * 二叉树的非递归遍历实现
 *
 * ps: 上线的代码是不能出现递归的，因为递归使用了系统栈，而系统栈时有限的
 * @Author 张三金
 * @Date 2021/12/11 0011 10:30
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_NonRecursiveTraversal {

    /**
     * 先序遍历 头左右 -- 非递归
     */
    public static void pre (BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            // 弹出的时候打印
            System.out.print(head.getValue() + " -> ");

            // 压入的手先压入右孩子，再压入左孩子，这样弹出的时候就是先弹出左孩子
            if (head.getRight() != null) {
                stack.push(head.getRight());
            }
            if (head.getLeft() != null) {
                stack.push(head.getLeft());
            }
        }
    }

    /**
     * 将先序遍历改写成  "头右左" 的实现 -- 非递归
     */
    public static void pre01 (BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            // 弹出的手打印
            System.out.print(head.getValue() + " -> ");

            // 压入的时候先压入左孩子，再压入右孩子， 这样弹出的时候就先弹出右孩子，这样就可以保证 "头右左" 的打印顺序
            if (head.getLeft() != null) {
                stack.push(head.getLeft());
            }
            if (head.getRight() != null) {
                stack.push(head.getRight());
            }
        }
    }

    /**
     * 后续遍历 使用了两个栈 -- 非递归实现
     * 上面的  pre01 方法实现的是 "头右左" 的遍历实现，即再准备一个栈 stack02，弹出stack01（或者说打印）的时候将节点压入 stack02，
     * 最后依次弹出 stack02 中的节点，进行打印，就是左右头后续遍历方式了
     */

    public static void pos (BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryNode<Integer>> stack01 = new Stack<>();
        Stack<BinaryNode<Integer>> stack02 = new Stack<>();
        stack01.push(head);
        while (!stack01.isEmpty()) {
            head = stack01.pop();
            // stack01 弹出的时候压入 stack02
            stack02.push(head);
            if (head.getLeft() != null) {
                stack01.push(head.getLeft());
            }
            if (head.getRight() != null) {
                stack01.push(head.getRight());
            }
        }

        while (!stack02.isEmpty()) {
            System.out.print(stack02.pop().getValue() + " -> ");
        }
    }

    /**
     * 后序遍历（如何只使用一个栈实现后序遍历） -- 非递归实现
     * @TODO 很难
     */
    public static void pos02 (BinaryNode<String> head) {

    }

    /**
     * 中序遍历 -- 非递归实现
     * 思路：
     *  （1）对于每一个节点 x ，x 压入栈中，先将 x 节点的所有左边界的节点全部压入栈中，一直到左孩子为空
     *  （2）第一个步骤中当为空的时候，则开始从栈中弹出节点并打印，打印好的同时去处理这个弹出节点的右孩子，这个时候这个右孩子就变成了 x， 然后回到步骤(1)
     *   (3) 一直到
     *
     *  ps: 中序遍历的思想就是，整个二叉树是可以被所有左边界给分解掉的，
     */
    public static void in (BinaryNode<Integer> head) {
        if (head == null) {
            return;
        }
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        // 这个 !stack.isEmpty() 用来处理第一个节点或者头结点进入循环用的，
        // 如果栈为空，head 也为空，那么循环就停了
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                // 一直将左边界压入栈中
                stack.push(head);
                head = head.getLeft();
            } else {
                // 左边界压完了，开始先弹出左边界，并打印
                head = stack.pop();
                System.out.print(head.getValue() + " -> ");
                // 弹出左边界后再去处理右子树
                head = head.getRight();
            }
        }
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

        // 先序遍历   1 -> 2 -> 4 -> 5 -> 3 -> 6 -> 7 -> 8
        pre(node);
        System.out.println("===============");
        // 头右左遍历 1 -> 3 -> 8 -> 6 -> 7 -> 2 -> 5 -> 4
        pre01(node);
        System.out.println("===============");
        // 中序遍历 4 -> 2 -> 5 -> 1 -> 7 -> 6 -> 3 -> 8
        in(node);
        System.out.println("===============");
        // 后序遍历 4 -> 5 -> 2 -> 7 -> 6 -> 8 -> 3 -> 1
        pos(node);

    }
}
