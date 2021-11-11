package class04;

import java.util.ArrayList;
import java.util.List;

/**
 * 单链表的反转
 * @Author 张三金
 * @Date 2021/11/11 0011 9:48
 * @Company jzb
 * @Version 1.0.0
 */
public class SingleLinkReverse {


    /**
     * 反转
     * @param head 传入的单链表头结点
     * @return 反转后的单链表头结点
     */
    public static SingleLinkedNode reverse (SingleLinkedNode head) {
        SingleLinkedNode pre = null;
        SingleLinkedNode next = null;
        while (head != null) {
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {

        int testTimes = 1000;
        int oneTimesNum = 1000;
        int range = 1000;
        for (int t = 0; t < testTimes; t++) {
            List<Integer> list = generateRandomList(oneTimesNum, range);
            SingleLinkedNode node = generateRandomLinkedList02(list);
            SingleLinkedNode reverseNode = reverse(node);

            for (int i = list.size() - 1; i >= 0; i--) {
                if (!list.get(i).equals(reverseNode.getValue())) {
                    System.out.println("Ops");
                }
                reverseNode = reverseNode.getNext();
            }
        }


    }


    /**
     * 生成一个随机的单链表
     * @param size 单链表大小
     * @param range 数值范围
     * @return 单链表的头结点
     */
    public static SingleLinkedNode generateRandomLinkedList (int size, int range) {
        SingleLinkedNode head = new SingleLinkedNode((int) (Math.random() * range) + 1);
        SingleLinkedNode pre = head;
        while (size != 0) {
            SingleLinkedNode current = new SingleLinkedNode((int) (Math.random() * range) + 1);
            pre.setNext(current);
            pre = current;
            size--;
        }
        return head;
    }



    /**
     * 将一个数组的值转换成一个链表
     * @param list 数组
     * @return 单链表的头结点
     */
    public static SingleLinkedNode generateRandomLinkedList02 (List<Integer> list) {
        SingleLinkedNode head = new SingleLinkedNode(list.get(0));
        SingleLinkedNode pre = head;
        for (int i = 1; i < list.size(); i++) {
            SingleLinkedNode current = new SingleLinkedNode(list.get(i));
            pre.setNext(current);
            pre = current;
        }
        return head;
    }



    public static List<Integer> generateRandomList (int size, int range) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((int) (Math.random() * range) + 1);
        }
        return list;
    }
































}
