package class13;

import class11.BinaryNode;
import class12.Code03_PrintBinaryTree;

import java.util.*;

/**
 * 问题描述：
 *  给定一个二叉树的头结点 head，和二叉树中的两个节点 a 和 b， 返回a和b的最低公共祖先
 *
 *  递归套路：
 *
 *  info信息
 *  1. findA 树上有没有找到 a
 *  2. findB 树上有没有找到 b
 *  3. ans 最低公共祖先
 * @Author 张三金
 * @Date 2021/12/27 0027 13:58
 * @Company jzb
 * @Version 1.0.0
 */
public class Code08_LowestCommonAncestor {

    public static BinaryNode<Integer> lowestCommonAncestor (BinaryNode<Integer> node, BinaryNode<Integer> a,
                                                            BinaryNode<Integer> b) {
        if (a == b) {
            return null;
        }
        return process(node, a, b).ans;
    }

    public static class Info {
        boolean findA;
        boolean findB;
        BinaryNode<Integer> ans;
        public Info (boolean findA, boolean findB, BinaryNode<Integer> ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }
    public static Info process (BinaryNode<Integer> node, BinaryNode<Integer> a, BinaryNode<Integer> b) {
        // base case
        if (node == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(node.getLeft(), a, b);
        Info rightInfo = process(node.getRight(), a, b);
        BinaryNode<Integer> ans = null;
        boolean findA = (a == node) || leftInfo.findA || rightInfo.findA;
        boolean findB = (b == node) || leftInfo.findB || rightInfo.findB;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = node;
            }
        }
        return new Info(findA, findB, ans);
    }


    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLevel = 8;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            BinaryNode<Integer> head = generateBT(maxLevel, maxValue);
            BinaryNode<Integer> a = findOne(head);
            BinaryNode<Integer> b = findOne(head);
            BinaryNode<Integer> ans01 = lowestCommonAncestor(head, a, b);
            BinaryNode<Integer> ans02 = lowestCommonAncestorTest(head, a, b);
            if (ans01 != ans02) {
                System.out.println("Opps!!!!");
                Code03_PrintBinaryTree.printTree(head);
                System.out.println(a.getValue());
                System.out.println(b.getValue());
                System.out.println(ans02.getValue());
                break;
            }
        }
    }


    // For Test 使用map容器的方式
    public static BinaryNode<Integer> lowestCommonAncestorTest(BinaryNode<Integer> head, BinaryNode<Integer> a
            , BinaryNode<Integer> b) {
        if (head == null || a == null || b == null || (a == b)) {
            return null;
        }
        if (a == head || b == head) {
            return head;
        }
        HashMap<BinaryNode<Integer>, BinaryNode<Integer>> parents = new HashMap<>();
        // 第一步，先按层遍历二叉树，构造每个节点的父节点的 map集合
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.getLeft() != null) {
                queue.add(head.getLeft());
                parents.put(head.getLeft(), head);
            }
            if (head.getRight() != null) {
                queue.add(head.getRight());
                parents.put(head.getRight(), head);
            }
        }
        // 第二步，将其中一个节点 a 的所有父亲放到 Set 集合中
        Set<BinaryNode<Integer>> set = new HashSet<>();
        // 把a也放到set里面去
        BinaryNode<Integer> current = a;
        while (current != null) {
            set.add(current);
            current = parents.get(current);
        }
        // 第三步，去 parents中找 b的父亲, 一直在set中找到就停下，然后返回
        current = b;
        while (!set.contains(current)) {
            current = parents.get(current);
        }
        return current;
    }

    // 在树种，随机找一个节点
    public static BinaryNode<Integer> findOne (BinaryNode<Integer> head) {
        if (head == null) {
            return null;
        }
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (Math.random() < 0.1) {
                return head;
            }
            if (head.getLeft() != null) {
                queue.add(head.getLeft());
            }
            if (head.getRight() != null) {
                queue.add(head.getRight());
            }
        }
        return null;
    }

    public static BinaryNode<Integer> generateBT (int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }
    public static BinaryNode<Integer> generate (int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>((int)(Math.random() * maxValue));
        node.setLeft(generate(level + 1, maxLevel, maxValue));
        node.setRight(generate(level + 1, maxLevel, maxValue));
        return node;
    }
}
