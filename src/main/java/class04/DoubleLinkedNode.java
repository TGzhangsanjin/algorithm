package class04;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表
 * @Author 张三金
 * @Date 2021/11/11 0011 14:18
 * @Company jzb
 * @Version 1.0.0
 */
public class DoubleLinkedNode {

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

    public static 

    public static void main(String[] args) {
        checkReverse();
    }

    /**
     * For Test
     * 校验双链表的反转
     */
    public static void checkReverse () {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            List<Integer> list = generateRandomList(oneTimeNums, range);
            Node node = generateDoubleLinked(list);
            Node reverseNode = reverse(node);
            for (int j = list.size() - 1; j >= 0; j--) {
                // 这里是用来判断 next 节点有没有连错
                if (!reverseNode.getValue().equals(list.get(j))) {
                    System.out.println("出错了！！！！");
                }
                // 这里是用来按断 previous 节点有没有连错
                if (j != list.size() - 1) {
                    if (!reverseNode.getPrevious().getValue().equals(list.get(j + 1))) {
                        System.out.println("出错了！！！！");
                    }
                }
                reverseNode = reverseNode.getNext();
            }

        }
    }

    /**
     * For Test
     * 将 list 转换成一个双链表结构
     * @param list
     * @return
     */
    private static Node generateDoubleLinked (List<Integer> list) {
        Node head = new Node(list.get(0));
        Node pre = head;
        for (int i = 0; i < list.size(); i++) {
            Node current = new Node(list.get(i));
            pre.setNext(current);
            current.setPrevious(pre);
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
