package class04;

import java.util.ArrayList;
import java.util.List;

/**
 * 单链表
 * @Author 张三金
 * @Date 2021/11/11 0011 11:03
 * @Company jzb
 * @Version 1.0.0
 */
public class SingleLinkedNode {

    /**
     * 一个单链表的结构
     * @param <T>
     */
    public static class Node<T> {
        /**
         * 泛型值
         */
        private T value;

        /**
         * 指向下一个节点
         */
        private Node next;
        
        public Node (T data) {
            this.value = data;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 反转
     * @param head 传入的单链表头结点
     * @return 反转后的单链表头结点
     */
    public static Node reverse (Node head) {
        Node pre = null;
        Node next = null;
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
            Node node = generateRandomLinkedList02(list);
            Node reverseNode = reverse(node);

            for (int i = list.size() - 1; i >= 0; i--) {
                if (!list.get(i).equals(reverseNode.getValue())) {
                    System.out.println("Ops");
                }
                reverseNode = reverseNode.getNext();
            }
        }


    }


    /**
     *  For Test
     * 生成一个随机的单链表
     * @param size 单链表大小
     * @param range 数值范围
     * @return 单链表的头结点
     */
    private static Node generateRandomLinkedList (int size, int range) {
        Node head = new Node((int) (Math.random() * range) + 1);
        Node pre = head;
        while (size != 0) {
            Node current = new Node((int) (Math.random() * range) + 1);
            pre.setNext(current);
            pre = current;
            size--;
        }
        return head;
    }

    /**
     * For Test
     * 将一个数组的值转换成一个链表
     * @param list 数组
     * @return 单链表的头结点
     */
    private static Node generateRandomLinkedList02 (List<Integer> list) {
        Node head = new Node(list.get(0));
        Node pre = head;
        for (int i = 1; i < list.size(); i++) {
            Node current = new Node(list.get(i));
            pre.setNext(current);
            pre = current;
        }
        return head;
    }

    /**
     * For Test
     * 生成一个随机list 集合
     * @param size 集合的大小
     * @param range 数据的范围
     * @return 一个随机集合
     */
    private static List<Integer> generateRandomList (int size, int range) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((int) (Math.random() * range) + 1);
        }
        return list;
    }
}
