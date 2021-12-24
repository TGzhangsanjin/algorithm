package class13;

import class11.BinaryNode;
import class12.Code03_PrintBinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一棵二叉树的头结点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
 * 思路：
 *  递归套路，
 *  1. 对于节点x的可能性
 *      (1) 最大距离不经过 x，那么当前的最大距离就是 x左右子树的最大距离
 *      (2) 最大距离经过 x，那么当前的最大距离就是x左子树的高度 + 右子树的高度 + 1
 *  2. 确定Info 信息
 *      (1) maxDistance 当前节点的最大距离
 *      (2) height 高度
 */
public class Code04_MaxDistance {

    public static class Info {
        public int maxDistance;
        public int height;
        public Info (int m, int h) {
            maxDistance = m;
            height = h;
        }
    }

    public static int maxDistance (BinaryNode<Integer> head) {
        return process(head).maxDistance;
    }

    public static Info process(BinaryNode<Integer> node){
        // 很好确定的 base case
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.getLeft());
        Info rightInfo = process(node.getRight());

        // 当前节点的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 确定最大距离

        // 最大距离不经过x
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        // 最大距离经过x，这一点很重要，只要最大距离经过x，那么一定是左右子树的高度和+1
        int p3 = leftInfo.height + rightInfo.height + 1;

        return new Info(Math.max(p1, Math.max(p2, p3)), height);
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            int dis01 = maxDistance(head);
            int dis02 = maxDistance1(head);
            if (dis01 != dis02) {
                System.out.println("Oops!");
                Code03_PrintBinaryTree.printTree(head);
                System.out.println();
            }
        }
        System.out.println("finish!");
    }

    // For Test
    public static int maxDistance1(BinaryNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        ArrayList<BinaryNode<Integer>> arr = getPrelist(head);
        HashMap<BinaryNode<Integer>, BinaryNode<Integer>> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static ArrayList<BinaryNode<Integer>> getPrelist(BinaryNode<Integer> head) {
        ArrayList<BinaryNode<Integer>> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static void fillPrelist(BinaryNode<Integer> head, ArrayList<BinaryNode<Integer>> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.getLeft(), arr);
        fillPrelist(head.getRight(), arr);
    }

    public static HashMap<BinaryNode<Integer>, BinaryNode<Integer>> getParentMap(BinaryNode<Integer> head) {
        HashMap<BinaryNode<Integer>, BinaryNode<Integer>> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
    }

    public static void fillParentMap(BinaryNode<Integer> head, HashMap<BinaryNode<Integer>, BinaryNode<Integer>> parentMap) {
        if (head.getLeft() != null) {
            parentMap.put(head.getLeft(), head);
            fillParentMap(head.getLeft(), parentMap);
        }
        if (head.getRight() != null) {
            parentMap.put(head.getRight(), head);
            fillParentMap(head.getRight(), parentMap);
        }
    }

    public static int distance(HashMap<BinaryNode<Integer>, BinaryNode<Integer>> parentMap
            , BinaryNode<Integer> o1, BinaryNode<Integer> o2) {
        HashSet<BinaryNode<Integer>> o1Set = new HashSet<>();
        BinaryNode<Integer> cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        BinaryNode<Integer> lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
    }

    public static BinaryNode<Integer> generateBT (int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }
    public static BinaryNode<Integer> generate (int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>((int) (Math.random() * maxValue));
        node.setLeft(generate(level + 1, maxLevel, maxValue));
        node.setRight(generate(level + 1, maxLevel, maxValue));
        return node;
    }
}
