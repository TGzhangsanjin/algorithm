package binaryTree;

import utils.Util01;

/**
 * 二叉树的递归遍历
 * @Author 张三金
 * @Date 2021/4/25 0025 23:53
 * @Company jzb
 * @Version 1.0.0
 */
public class RecursionErgodic {

    /**
     * 二叉树的先序遍历， 头左右
     */
    public static void pre (BinaryTreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.getValue() + ",");
        pre(head.getLeft());
        pre(head.getRight());
    }

    /**
     * 二叉树的中序遍历， 左右头
     */
    public static void middle(BinaryTreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        middle(head.getLeft());
        System.out.print(head.getValue() + ",");
        middle(head.getRight());
    }

    /**
     * 二叉树的后序遍历， 左右头
     */
    public static void after(BinaryTreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        after(head.getLeft());
        after(head.getRight());
        System.out.print(head.getValue() + ",");
    }


    public static void main(String[] args) {
        // 生成一个随机数组
        int[] array1 = Util01.randomArray(100, 100);
        BinaryTreeNode[] array2 = new BinaryTreeNode[array1.length];
        BinaryTreeNode<Integer> head = new BinaryTreeNode<>((int) (Math.random() * 100));
        for (int i = 0; i < array2.length; i++) {
            array2[i] = new BinaryTreeNode<>(i);
        }
        for (int index = 0; index < array2.length; index++) {
            if ((index * 2 + 1) < array2.length) {
                array2[index].setLeft(array2[index * 2 + 1]);
            }
            if ((index * 2 + 2) < array2.length) {
                array2[index].setRight(array2[index * 2 + 2]);
            }
        }
//        pre(array2[0]);
        middle(array2[0]);
//        after(array2[0]);

    }

}
