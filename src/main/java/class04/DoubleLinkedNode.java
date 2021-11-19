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
            } else {
                pre = current;
            }
            current =current.getNext();
        }
        return head;
    }

    public static void main(String[] args) {
        checkReverse();
        checkRemoveValue();
    }

    /**
     * For Test
     * 校验双链表的删除
     */
    public static void checkRemoveValue () {
        int testTimes = 100;
        int oneTimeNums = 10;
        int range = 10;
        for (int i = 0; i < testTimes; i++) {
            List<Integer> list = generateRandomList(oneTimeNums, range);
            Node node = generateDoubleLinked(list);
            // 准备需要删除的一个真命天子
            int theOne = (int)( Math.random() * range ) + 1;
            System.out.println(theOne);
            list = listDeleteValue(list, theOne);
            node = removeValue(node, theOne);

            int temp = 0;
            for (int j = 0; j < list.size(); j++) {
                // 这里是用来判断 next 节点有没有连错
                if (!node.getValue().equals(list.get(j))) {
                    System.out.println("出错了！！！！node.value ==" + node.getValue() + ",list.get(j)==" + list.get(j) + ", j===" + j);
                    temp++;
                }
                // 这里是用来按断 previous 节点有没有连错
//                if (j != 0) {
//                    if (!node.getPrevious().getValue().equals(list.get(j - 1))) {
//                        System.out.println("出错了！！！！");
//                    }
//                }
                node = node.getNext();
            }
            System.out.println(temp);

        }
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
}
