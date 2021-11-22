package class04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 双链表
 * @Author 张三金
 * @Date 2021/11/11 0011 14:18
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_DoubleLinkedNode {

    public static class Node<T> {

        // 值
        private T value;

        // 前置节点
        private Node previous;

        // 后置节点
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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 双链表的反转
     * @param head 传入的链表头结点
     * @return 返回反转后的链表头结点
     */
    public static Node reverse (Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.setNext(pre);
            head.setPrevious(next);
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 删除一个指定的 value 值
     * @param head 头结点
     * @param value 待删除的值
     * @param <T>
     * @return 删除给定值后返回的链表头结点
     */
    public static <T extends Object> Node removeValue (Node head, T value) {
        while (head != null && value.equals(head.getValue())) {
            head = head.getNext();
        }
        if (head != null) {
            head.setPrevious(null);
        } else {
            return head;
        }
        Node pre = head;
        Node current = head;
        while (current != null) {
            if (current.getValue().equals(value)) {
                pre.setNext(current.getNext());
                if (current.getNext() != null) {
                    current.getNext().setPrevious(pre);
                }
            } else {
                pre = current;
            }
            current = current.getNext();
        }
        return head;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTestNum = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {

            Node node01 = generateDoubleLinked(oneTestNum, range);
            List<Integer> list01 = getDoubleLinkedOriginOrder(node01);
            // 讲道理这里的 node1 在执行完上一行后应该变成 null 的，为什么node1 指向的对象却不会变？？？
            node01 = reverse(node01);
            if (!checkReverse(list01, node01)) {
                System.out.printf("Reverse Opps!!!!");
            }

            int deleteValue = (int) (Math.random() * range) + 1;
            Node node02 = generateDoubleLinked(oneTestNum, range);
            List<Integer> list02 = getDoubleLinkedOriginOrder(node02);
            list02.removeIf(e -> e.equals(deleteValue));
            node02 = removeValue(node02, deleteValue);
            if (!checkDelete(list02, node02)) {
                System.out.println("Delete Opps!!!!");
            }
        }
    }

    /**
     * For Test 校验数组反转的正确性
     */
    private static boolean checkReverse (List<Integer> origin, Node head) {
        Node end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.getValue())) {
                return false;
            }
            end = head;
            head = head.getNext();
        }

        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.getValue())) {
                return false;
            }
            end = end.getPrevious();
        }
        return true;
    }

    /**
     * For Test 校验数组删除指定值的正确性
     */
    private static boolean checkDelete (List<Integer> origin, Node head) {
        Node end = null;
        for (int i = 0; i <origin.size(); i++) {
            if (!origin.get(i).equals(head.getValue())) {
                return false;
            }
            end = head;
            head = head.getNext();
        }

        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(end.getValue())) {
                return false;
            }
            end = end.getPrevious();
        }
        return true;
    }

    /**
     * For Test 生成一个不超过 size 长度的双链表
     */
    private static Node generateDoubleLinked (int size, int range) {
        int len = (int) (Math.random() * size) + 1;
        if (len == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * range) + 1);
        Node pre = head;
        while (size != 0) {
            Node current = new Node((int) (Math.random() * range) + 1);
            pre.setNext(current);
            current.setPrevious(pre);
            pre = current;
            size--;
        }
        return head;
    }

    /**
     * For Test 获取 双链表的顺序
     */
    private static List<Integer> getDoubleLinkedOriginOrder (Node head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add((Integer)head.getValue());
            head = head.getNext();
        }
        return list;
    }
}
