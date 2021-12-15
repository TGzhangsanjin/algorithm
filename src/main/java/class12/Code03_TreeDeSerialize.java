package class12;

import class11.BinaryNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的反序列化，
 *  说明：参数 Code02_TreeSerialize 的说明即可
 * @Author 张三金
 * @Date 2021/12/13 0013 10:45
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_TreeDeSerialize {

    /**
     *  先序遍历 -- 反序列化
     */
    public static BinaryNode<Integer> buildByPre (String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        String[] array = str.split(",");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            queue.add(array[i]);
        }
        return preb(queue);
    }

    public static BinaryNode<Integer> preb (Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String value = queue.poll();
        if ("null".equals(value)) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>(Integer.valueOf(value));
        node.setLeft(preb(queue));
        node.setRight(preb(queue));
        return node;
    }

    /**
     * 后序遍历 -- 反序列化
     */
    public static BinaryNode<Integer> buildByPos (String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        String[] array = str.split(",");
        Queue<String> queue = new LinkedList<>();
        // 后序序列化是 左右中   转换成 中右左  ， 这样做的原因是反序列化时要先构造头结点
        // 当然这里也可以用一个 stack，只不过遍历复制的时候从 i = 0 开始而已
        for (int i = array.length - 1; i >= 0; i--) {
            queue.add(array[i]);
        }
        return posb(queue);
    }

    public static BinaryNode<Integer> posb (Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String value = queue.poll();
        if ("null".equals(value)) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>(Integer.valueOf(value));
        // 这里先赋值右孩子，因为queue 中的顺序是 中右左
        node.setRight(posb(queue));
        node.setLeft(posb(queue));
        return node;
    }

    /**
     * 按层遍历 -- 反序列化
     */
    public static BinaryNode<Integer> buildByLevel (String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        String[] array = str.split(",");
        int index = 0;
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        BinaryNode<Integer> head = new BinaryNode<>(Integer.valueOf(array[index++]));
        queue.add(head);
        while (index < array.length) {
            BinaryNode<Integer> current = queue.poll();
            current.setLeft(generateNode(array[index++]));
            // 这里因为如果没有右孩子也会填充 null， 所以不会导致数组越界
            current.setRight(generateNode(array[index++]));
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        return head;
    }

    public static BinaryNode<Integer> generateNode(String value) {
        if (value == null) {
            return null;
        }
        if ("null".equals(value)) {
            return null;
        }
        return new BinaryNode<>(Integer.valueOf(value));
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxLevel = 100;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> node = generateRandomBST(maxLevel, maxValue);
            String preString = Code02_TreeSerialize.preSerialize(node);
            String posString = Code02_TreeSerialize.posSerialize(node);
            String levelString = Code02_TreeSerialize.level(node);

            BinaryNode<Integer> preNode = buildByPre(preString);
            BinaryNode<Integer> posNode = buildByPos(posString);
            BinaryNode<Integer> levelNode = buildByLevel(levelString);

            if (!isTheSameTree(preNode, posNode)) {
                System.out.println("Opps!!!!");
            }
            if (!isTheSameTree(posNode, levelNode)) {
                System.out.println("Opps!!!！");
            }

        }


    }

    /**
     * 随机生成层数不超过 maxLevel 的一棵二叉树
     */
    public static BinaryNode<Integer> generateRandomBST (int maxLevel, int maxValue) {
        return generateRandomBSTDo(1, maxLevel, maxValue);
    }

    public static BinaryNode<Integer> generateRandomBSTDo(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>((int) (Math.random() * maxValue));
        node.setLeft(generateRandomBSTDo(level + 1, maxLevel, maxValue));
        node.setRight(generateRandomBSTDo(level + 1, maxLevel, maxValue));
        return node;
    };

    public static boolean isTheSameTree (BinaryNode<Integer> head1, BinaryNode<Integer> head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (!head1.getValue().equals(head2.getValue())) {
            return false;
        }
        return isTheSameTree(head1.getLeft(), head2.getLeft()) && isTheSameTree(head1.getRight(), head2.getRight());
    }


}
