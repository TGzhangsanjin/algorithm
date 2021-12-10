package class10;

import class04.SingleNode;
import utils.ArrayUtil;

/**
 * 快慢指针（求链表中点）的问题：
 * (1) 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * (2) 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * (3) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * (4) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 * @Author 张三金
 * @Date 2021/12/10 0010 11:44
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_SpeedPointer {

    /**
     * 返回链表的中点
     * (1) 如果有奇数个节点，则返回中间的一个
     * (2) 如果有偶数个节点，则返回上中点（即中间两个中的前面一个）
     */
    public static SingleNode<Integer> findMiddle (SingleNode<Integer> head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            // 小于3个节点直接返回头部
            return head;
        }
        SingleNode<Integer> slow = head;
        SingleNode<Integer> fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * 返回链表的中点的前一个
     */
    public static SingleNode<Integer> findMiddlePre (SingleNode<Integer> head) {
        if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
            // 小于3个节点直接返回头部
            return head;
        }
        SingleNode<Integer> slow = head;
        // 让 fast 先走一步，那么最终找到的slow就是中点的前一个节点
        SingleNode<Integer> fast = head.getNext().getNext();
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        // 数组的最小长度
        int sizeMin = 1;
        // 数组的最大长度
        int sizeMax = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array = ArrayUtil.generateRandomArray(sizeMin, sizeMax, range);
            SingleNode<Integer> head = generateNodeByArray(array);
            int middleIndex = (array.length - 1) >> 1;
            if (array[middleIndex] != findMiddle(head).getValue()) {
                System.out.println("Opps!!!!!");
            }
            int middlePreIndex = middleIndex - 1;
            if (middlePreIndex < 0) {
                middlePreIndex = 0;
            }
            if (array[middlePreIndex] != findMiddlePre(head).getValue()) {
                System.out.println("Opps!!!!!");
            }
        }

    }

    /**
     *  通过一个数组生成一个链表
     */
    public static SingleNode<Integer> generateNodeByArray (int[] array) {
        SingleNode<Integer> head = new SingleNode<>(array[0]);
        SingleNode<Integer> current = head;
        for (int i = 1; i < array.length; i++) {
            current.setNext(new SingleNode<>(array[i]));
            current = current.getNext();
        }
        return head;
    }
}
