package class10;

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
        int value;

        /**
         * 下一个节点
         */
        Node next;

        /**
         * 随机指向的一个节点
         */
        Node rand;

        public Node (int data) {
            value = data;
        }
    }
}
