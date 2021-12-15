package class12;

import class11.BinaryNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 431
 * 实现将一棵多叉树转换成二叉树，，同时实现将一棵二叉树转换成多叉树
 *
 * 思路：
 *  1. 多叉树转换成二叉树：对于一个节点 x，将节点 x 的所有孩子都转换成左树右边界的形式
 *
 * @Author 张三金
 * @Date 2021/12/15 0015 15:45
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_EncodeNTreeToBTree {


    /**
     * 多叉树转换成二叉树：  对于一个节点 x，将节点 x 的所有孩子都转换成左树右边界的形式
     */
    public static BinaryNode<Integer> encode (TreeNode<Integer> head) {
        if(head == null) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>(head.getValue());
        node.setLeft(en(head.getChildren()));
        return node;
    }

    public static BinaryNode<Integer> en (List<TreeNode<Integer>> children) {
        BinaryNode<Integer> head = null;
        BinaryNode<Integer> current = null;
        for (TreeNode<Integer> child : children) {
            BinaryNode<Integer> bNode = new BinaryNode<>(child.getValue());
            if (head == null) {
                // 处理第一个孩子
                head = bNode;
            } else {
                // 从第二个孩子开始往右边挂
                current.setRight(bNode);
            }
            current = bNode;
            // 堆每一个child都要递归的进行 en 处理
            current.setLeft(en(child.getChildren()));
        }
        return head;
    }

    /**
     * 将二叉树反序列化为多叉树
     * @param head
     * @return
     */
    public static TreeNode<Integer> decode (BinaryNode<Integer> head) {
        if (head == null) {
            return null;
        }
        TreeNode<Integer> treeNode = new TreeNode<>(head.getValue());
        treeNode.setChildren(de(head.getLeft()));
        return treeNode;
    }

    public static List<TreeNode<Integer>> de (BinaryNode<Integer> root) {
        List<TreeNode<Integer>> children = new ArrayList<>();
        // 这个root 相当于所有左树右边界的长兄，，要把我自己和我所有的兄弟们搞成一个链表返回给父节点
        while (root != null) {
            // 先把自己的孩子节点搞定，再和兄弟们汇合
            TreeNode<Integer> tNode = new TreeNode<>(root.getValue(), de(root.getLeft()));
            children.add(tNode);
            root = root.getRight();
        }
        return children;
    }
}
