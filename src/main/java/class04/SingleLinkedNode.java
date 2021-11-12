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

    /**
     * 删除单链表中的指定值对应的节点
     * @param head 链表的头结点
     * @param data 数据值
     * @param <T> 节点汇总泛型类型
     * @return
     */
    public static <T extends Object> Node removeValue (Node head, T data) {
        // 需要先去处理头结点的情况
        while (head != null && head.getValue().equals(data)) {
            head = head.getNext();
        }
        if (head == null) {
            return head;
        }

        // 对于 java 中的引用，自己一直有一个不理解的地方，今天理解了 2021-11-12
        // 这里 current 变量一开始是指向 head 的内存地址，后续又不断的指向  head的下一个节点，下下节点，下下下节点。。。。。。
        // 所以 每次对 current 的修改，也就是相当于修改 head的下一个节点，下下节点，下下下节点。。。。。。。

        Node current = head;
        while (current.getNext() != null) {
            if (data.equals(current.getNext().getValue())) {
                current.setNext(current.getNext().getNext());
            } else {
                // 这里只是把 current 指向了下一个节点，并不会改变 head 这个变量的指向
                current = current.getNext();
            }
        }
        return head;
    }

    public static void main(String[] args) {
        checkReverse();
        checkRemoveValue();

    }

    /**
     * 校验删除数据的测试方法
     */
    private static void checkRemoveValue() {
        int testTimes = 1000;
        int oneTimesNum = 1000;
        int range = 100;
        for (int t = 0; t < testTimes; t++) {
            List<Integer> list = generateRandomList(oneTimesNum, range);
            Node node = generateRandomLinkedList02(list);
            // 一个需要删除的value
            Integer emperor = (int)(Math.random() * range) + 1;

            List<Integer> newList = listDeleteValue(list, emperor);
            Node newNode = removeValue(node, emperor);
            if (newList.size() == 0 && newNode != null) {
                System.out.println("Ops001!!!!!");
                return;
            }
            for (int i = 0; i < newList.size(); i++) {
                if (!newList.get(i).equals(newNode.getValue())) {
                    System.out.println("Ops002!!!!!");
                }
                newNode = newNode.getNext();
            }
        }
    }

    /**
     * 校验反转链表的方法
     */
    public static void checkReverse () {
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
     * 集合删除指定的值
     * @param old 旧的数组
     * @param value 需要删除的值
     * @return 删除指定值后的数组
     */
    private static List<Integer> listDeleteValue (List<Integer> old, Integer value) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < old.size(); i++) {
            if (!value.equals(old.get(i))) {
                newList.add(old.get(i));
            }
        }
        return newList;
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
