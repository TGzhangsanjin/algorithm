package class09;

import class04.SingleNode;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head01和head02，请实现两个函数：
 * 如果两个链表相交，请返回第一个相交的节点，如果不相交，返回null
 * 【要求】如果两个链表的长度之和为 N， 时间复杂度达到 O(N), 额外空间复杂度需要达到 O(1)
 *
 * 思路：
 *      (1) 需要先实现一个判断链表是否有环，如果有环则返回入环节点的方法  （这里用到了不知道是一个啥定理，先记住）
 *          a. 使用快慢指针 slow, fast, 从头结点开始遍历，慢指针每次走一个节点，快指针每次走两个节点
 *          b. 如果快指针最终能指向空，则说明链表无环，如果链表有环，那么慢指针和快指正一定会在环上的某一个节点相遇，这个时候停下来
 *          c. 第2个步骤中快指针和慢指针相遇后，将 fast 指向头结点，fast 和 slow 接着往下走，每次走一步，最终fast 和 slow 第一次相遇的节点就是入环节点
 * @Author 张三金
 * @Date 2021/12/21 0021 11:37
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_LoopLinkIntersect {


    /**
     *  如果 head 链表有环则返回入环节点，没有环则返回 null
     */
    public SingleNode<Integer> getLoopNode(SingleNode<Integer> head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            return null;
        }
        SingleNode<Integer> slow = head.getNext();
        SingleNode<Integer> fast = head.getNext().getNext();
        // 这个循环用于让slow 和 fast 相遇， 当然如果链表没有环，则不会相遇，直接返回 null
        while (slow != fast) {
            if (fast.getNext() == null || fast.getNext().getNext() == null) {
                return null;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        // 让 fast 指向 head， 然后slow和fast 同时往下走，每次只走一个节点，最终会在入环节点相遇（这是一个不知道啥定理，先记住）
        fast = head;
        while (slow != fast) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;
    }
}
