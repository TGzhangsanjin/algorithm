package class12;

import class11.BinaryNode;
import com.sun.deploy.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化
 *  只有先序和后序的序列化，，二叉树的中序序列化是不存在的，因为有歧义
 * 序列化的过程实际就是将一棵二叉树转换成 字符串或者数组的过程，如果一个节点的孩子为空，也需要进行存储
 *
 *  二叉树是无法通过中序遍历的方式实现序列化和反序列化的，因为不同的两棵树，可能得到同样的中序序列，即使补了控制也一样
 *  比如如下两棵树：补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 *         __2
 *        /
 *       1
 *       和
 *       1__
 *          \
 *           2
 * @Author 张三金
 * @Date 2021/12/13 0013 10:44
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_TreeSerialize {

    /**
     *  先序遍历 - 序列化
     *  使用逗号 "," 进行分隔
     */
    public static String preSerialize (BinaryNode<Integer> head) {
        if (head == null) {
            return null;
        }
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        stack.push(head);
        String serializeString = "";
        while (!stack.isEmpty()) {
            head = stack.pop();

            if (head == null) {
                // 从栈中如果弹出来的数据为空的时候，同样需要设置一个 "null", 到字符串中去
                serializeString += "null,";
                continue;
            } else {
                serializeString +=  head.getValue() + ",";
            }
            // 这里如果左右孩子为空的话，同样的需要将空值压入栈中
//            if (head.getRight() != null) {
                stack.push(head.getRight());
//            }
//            if (head.getLeft() != null) {
               stack.push(head.getLeft());
//            }
        }
        return serializeString.substring(0, serializeString.length() - 1);
    }

    /**
     * 后序遍历 - 序列化
     */
    public static String posSerialize (BinaryNode<Integer> head) {
        if (head == null) {
            return null;
        }
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        Stack<BinaryNode<Integer>> outStack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            outStack.push(head);
            if (head == null) {
                continue;
            }
            stack.push(head.getLeft());
            stack.push(head.getRight());
        }
        String str = "";
        while (!outStack.isEmpty()) {
            //
            if (outStack.peek() == null) {
                str += "null,";
                outStack.pop();
            } else {
                str += outStack.pop().getValue() + ",";
            }
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * 按层（宽度优先）遍历 - 序列化
     */
    public static String level (BinaryNode<Integer> head) {
        if (head == null) {
            return null;
        }
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        String str = "";
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head == null) {
                str += "null,";
            } else {
                str += head.getValue() + ",";
                queue.add(head.getLeft());
                queue.add(head.getRight());
            }
        }
        return str.substring(0, str.length() - 1);
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

        // 先序序列化  1,2,4,null,null,5,null,null,3,6,7,null,null,bull,8,null,null
        System.out.println();
        System.out.println(preSerialize(node1));
        // 后序序列化 null,null,4,null,null,5,2,null,null,7,null,6,null,null,8,3,1
        System.out.println();
        System.out.println(posSerialize(node1));
        // 按层序列化 1,2,3,4,5,6,8,null,null,null,null,7,null,null,null,null,null
        System.out.println();
        System.out.println(level(node1));
    }
}
