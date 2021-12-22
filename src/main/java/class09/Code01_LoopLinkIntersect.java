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
 *      (2) 如果两个链表都是无环链表
 *          a. 分别求出 head01 和 head02 的长度 length01 和 length02, 同时得出两个链表的结束节点 end01 和 end 02
 *          b. 如果 end01 不等于 end02 那么说明两个链表肯定不相交，反之肯定相交
 *          c. 根据第2步，如果判断出两个链表存在交点，则让长度更长的链表先走 |length01 - length02 |步， 知道相遇的第一个节点就是相交节点
 *      (3) 如果一个建表有环，一个链表无环，那么二者绝不可能相交
 *      (4) 如果两个链表同时有环
 *          a. 找到 head01 和 head02 的入环节点，循环遍历其中一个head01，如果遍历完一圈后没有遇到 head02 的入环节点，则说明没有交点
 *          b. 两个链表同时入环，而且可以得到这个入环节点 loopNode, 就可以把这个 loopNode 当成结束节点，使用（2）中的方法找到相交节点即可
 *          c. 两个链表再不同的地方入环，相当于就是有两个相交节点，两个入环节点即可以看成第一次相交的节点，返回其中一个即可
 * @Author 张三金
 * @Date 2021/12/21 0021 11:37
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_LoopLinkIntersect {


    /**
     * head01 和 head02 都是无环链表，返判断两个链表是否相交，如果相交则返回相交的第一个节点
     */
    public static SingleNode<Integer> noLoop (SingleNode<Integer> head01, SingleNode<Integer> head02) {
        if (head01 == null || head02 == null) {
            return null;
        }
        int length01 = 1;
        int length02 = 1;
        SingleNode<Integer> current01 = head01;
        SingleNode<Integer> current02 = head02;
        if (current01.getNext() != null) {
            length01++;
            current01 = current01.getNext();
        }
        if (current02.getNext() != null) {
            length02++;
            current02 = current02.getNext();
        }
        if (current01 != current02) {
            // 结束节点不相同，则不会相交
            return null;
        }
        // 把更长的链表赋值给 current01
        current01 = length01 > length02 ? head01:head02;
        // 把更短的链表赋值给 current02
        current02 = length01 < length02 ? head01:head02;

        // 让更长的链表先走 n 步
        int n = Math.abs(length01 - length02);
        while (n != 0) {
            current01 = current01.getNext();
            n--;
        }
        while (current01 != current02) {
            current01 = current01.getNext();
            current02 = current02.getNext();
        }
        return current01;
    }


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
