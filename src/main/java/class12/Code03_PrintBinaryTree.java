package class12;

import class11.BinaryNode;

/**
 * 打印函数
 *
 * @TODO 还没有看
 * @Author 张三金
 * @Date 2021/12/16 0016 11:45
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_PrintBinaryTree {

    public static void printTree(BinaryNode<Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(BinaryNode<Integer> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.getRight(), height + 1, "v", len);
        String val = to + head.getValue() + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.getLeft(), height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        BinaryNode<Integer> head = new BinaryNode<>(1);
        head.setLeft(new BinaryNode<>(-222222222));
        head.setRight(new BinaryNode<>(3));
        head.getLeft().setLeft(new BinaryNode<>(Integer.MIN_VALUE));
        head.getRight().setLeft(new BinaryNode<>(55555555));
        head.getRight().setRight(new BinaryNode<>(66));
        head.getLeft().getLeft().setRight(new BinaryNode<>(777));
        printTree(head);

        head = new BinaryNode<>(1);
        head.setLeft(new BinaryNode<>(2));
        head.setRight(new BinaryNode<>(3));
        head.getLeft().setLeft(new BinaryNode<>(4));
        head.getRight().setLeft(new BinaryNode<>(5));
        head.getRight().setRight(new BinaryNode<>(6));
        head.getLeft().getLeft().setRight(new BinaryNode<>(7));
        printTree(head);

        head = new BinaryNode<>(1);
        head.setLeft(new BinaryNode<>(1));
        head.setRight(new BinaryNode<>(1));
        head.getLeft().setLeft(new BinaryNode<>(1));
        head.getRight().setLeft(new BinaryNode<>(1));
        head.getRight().setRight(new BinaryNode<>(1));
        head.getLeft().getLeft().setRight(new BinaryNode<>(1));
        printTree(head);

    }
}
