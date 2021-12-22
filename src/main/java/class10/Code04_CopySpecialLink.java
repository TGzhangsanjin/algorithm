package class10;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 * 拷贝一个特殊的链表
 * 问题说明：
 *  一个特殊的链表类型如下（节点就是本类的内部类）
 *  rand 指针是单链表节点结构中新增的指针，rand可能访问链表中的任意一个节点，也可能指向null，
 *  给定一个 Node 节点类型组成的无环链表，请实现一个函数完成这个链表的复制，并返回复制的新链表的头结点
 *  【要求】时间复杂度 O(N), 额外空间复杂度 O(1)
 *
 *  思路：
 *      1. 对于每一个节点 X, 在节点 x 和 x.next 节点之间插入一个新的节点 x1
 *      2. 分离 x 和 x1， x1的 rand节点可以通过 x.rand 找到
 * @Author 张三金
 * @Date 2021/12/22 0022 17:36
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_CopySpecialLink {

    private static class Node {
        /**
         * 节点值
         */
        int val;

        /**
         * 下一个节点
         */
        Node next;

        /**
         * 随机指向的一个节点
         */
        Node random;

        public Node (int data) {
            val = data;
        }
    }

    /**
     * 使用Map 容器的方式
     */
    public static Node copyUserMap (Node head) {
        if (head == null) {
            return null;
        }

        // key: 老的链表节点  value: 新的链表节点
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copySpecialLink (Node head) {
        if (head == null) {
            return null;
        }

        // 第一步，原来的链表是 1 -> 2 -> 3 -> 4 -> 5 ..., 变成 1->1'->2->2'->3->3'->4->4'->5->5'....
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = new Node(current.val);
            current.next.next = next;
            current = next;
        }

        // 第二步 设置好 1‘ 2’ 3‘ 4’ 5‘ 的 random指针
        current = head;
        while (current != null) {
            next = current.next.next;
            // 这一步很关键，新节点的 random指针，也就是 老节点的random指针的下一个节点
            current.next.random = current.random != null ? current.random.next:null;
            current = next;
        }

        // 第三步 分离新老节点
        Node rsp = head.next;
        Node copy = rsp;
        current = head;
        while (current != null) {
            next = current.next.next;
            current.next = next;
            copy.next = next != null ? next.next : null;
            current = next;
            copy = current != null ? current.next: null;
        }
        return rsp;
    }
}
