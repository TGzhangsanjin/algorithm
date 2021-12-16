package class12;

import class11.BinaryNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点
 *  思路：
 *      按层遍历，实现某个机制，知道某一层结束了.
 *      准备两个变量，currentEnd：当前层的最后一个节点，nextEnd: 当前层下一层的最后一个节点
 * @Author 张三金
 * @Date 2021/12/16 0016 9:16
 * @Company jzb
 * @Version 1.0.0
 */
public class Code06_TreeMaxWidth {


    public static int maxWidthLevel (BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        BinaryNode<Integer> currentEnd = head;
        BinaryNode<Integer> nextEnd = null;
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        int max = 0;
        int currentLevelCount = 0;
        while (!queue.isEmpty()) {
            currentLevelCount ++;
            BinaryNode<Integer> node = queue.poll();
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
                nextEnd = node.getLeft();
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
                nextEnd = node.getRight();
            }
            if (node == currentEnd) {
                max = Math.max(max, currentLevelCount);
                currentEnd = nextEnd;
                nextEnd = null;
                currentLevelCount = 0;
            }
        }
        return max;
    }

    public static int maxWidthLevelUseMap (BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        Map<BinaryNode<Integer>, Integer> map = new HashMap<>();
        map.put(head, 1);
        // 当前所在的层
        int currentLevel = 1;
        // 当前所在层的节点个数
        int currentLevelNodeCounts = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            BinaryNode<Integer> node = queue.poll();
            // 当前从队列中出来的节点所在的层
            int currentNodeLevel = map.get(node);
            if (node.getLeft() != null) {
                // 孩子节点所在的层自然而然就是当前层 +1
                map.put(node.getLeft(), currentNodeLevel + 1);
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                // 孩子节点所在的层自然而然就是当前层 +1
                map.put(node.getRight(), currentNodeLevel + 1);
                queue.add(node.getRight());
            }
            if (currentLevel == currentNodeLevel) {
                currentLevelNodeCounts++;
            } else {
                max = Math.max(max, currentLevelNodeCounts);
                currentLevel++;
                currentLevelNodeCounts = 1;
            }
        }
        max = Math.max(max, currentLevelNodeCounts);
        return max;
    }


    public static void main(String[] args) {
        int testTimes = 1000;
        int maxLevel = 3;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> node =  generateBTree(maxLevel, maxValue);
            int count1 = maxWidthLevel(node);
            int count2 = maxWidthLevelUseMap(node);
            if (count1 != count2) {
                System.out.println("Opps!!!!!!!!!!!!");
            }
        }
    }

    public static BinaryNode<Integer> generateBTree (int maxLevel, int maxValue) {
        return generateBtreeDo(1, maxLevel, maxValue);
    }

    public static BinaryNode<Integer> generateBtreeDo(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>((int)(Math.random() * maxValue));
        node.setLeft(generateBtreeDo(level + 1, maxLevel, maxValue));
        node.setRight(generateBtreeDo(level + 1, maxLevel, maxValue));
        return node;
    }
}
