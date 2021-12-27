package class13;

import class11.BinaryNode;

import java.util.*;

/**
 * 问题描述：
 *  给定一个二叉树的头结点 head，和二叉树中的两个节点 a 和 b， 返回a和b的最低公共祖先
 * @Author 张三金
 * @Date 2021/12/27 0027 13:58
 * @Company jzb
 * @Version 1.0.0
 */
public class Code08_LowestCommonAncestor {

    // For Test 使用map容器的方式
    public static BinaryNode<Integer> lowestCommonAncestorTest(BinaryNode<Integer> head, BinaryNode<Integer> a
            , BinaryNode<Integer> b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        HashMap<BinaryNode<Integer>, BinaryNode<Integer>> parents = new HashMap<>();
        // 第一步，先按层遍历二叉树，构造每个节点的父节点的 map集合
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();;
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
        BinaryNode<Integer> current = parents.get(a);
        while (current != null) {
            set.add(current);
            current = parents.get(current);
        }
        // 第三步，去 parents中找 b的父亲，一个个找，找到一个如果存在 set 中，一直在set中找到最后一个即是最低公共祖先
        current = parents.get(b);
        while (set.contains(current) && parents.get(current) != null) {
//            set.remove(current);  删不删都没关系
            current = parents.get(current);
        }
        return current;
    }
}
