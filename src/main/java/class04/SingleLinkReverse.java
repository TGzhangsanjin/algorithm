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
    public static SingleLinkedNodeBak reverse (SingleLinkedNodeBak head) {
        SingleLinkedNodeBak pre = null;
        SingleLinkedNodeBak next = null;
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
            SingleLinkedNodeBak node = generateRandomLinkedList02(list);
            SingleLinkedNodeBak reverseNode = reverse(node);

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
    public static SingleLinkedNodeBak generateRandomLinkedList (int size, int range) {
        SingleLinkedNodeBak head = new SingleLinkedNodeBak((int) (Math.random() * range) + 1);
        SingleLinkedNodeBak pre = head;
        while (size != 0) {
            SingleLinkedNodeBak current = new SingleLinkedNodeBak((int) (Math.random() * range) + 1);
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
    public static SingleLinkedNodeBak generateRandomLinkedList02 (List<Integer> list) {
        SingleLinkedNodeBak head = new SingleLinkedNodeBak(list.get(0));
        SingleLinkedNodeBak pre = head;
        for (int i = 1; i < list.size(); i++) {
            SingleLinkedNodeBak current = new SingleLinkedNodeBak(list.get(i));
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
