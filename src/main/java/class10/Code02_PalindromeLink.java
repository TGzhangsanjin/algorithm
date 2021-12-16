package class10;

import class04.SingleNode;

/**
 * 回文链表
 *  问题描述：给定一个链表的头部 head， 判断该链表是否为回文链表
 *  思路：
 *      (1) 先获取链表的中点或者上中点
 *      (2) 将链表的中的下一个节点设置为空，将中点之后的节点反向连接，比如： 1 -> 2 -> 3 <- 2 <- 1
 *      (3) 同时遍历头结点和尾节点，每次遍历节点的值都相同，一直能到结束，则得出该链表是回文结构
 * @Author 张三金
 * @Date 2021/12/10 0010 14:51
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_PalindromeLink {


    public static boolean palindromeLink (SingleNode<Integer> head) {
        if (head == null) {
            return false;
        }
        if (head.getNext() == null) {
            return true;
        }
        if (head.getNext().getNext() == null) {
            return head.getValue().equals(head.getNext().getValue());
        }
        return false;
    }

    public static boolean findMiddleNode (SingleNode<Integer> head) {

        SingleNode<Integer> slow = head;
        SingleNode<Integer> quick = head;

    }

}
