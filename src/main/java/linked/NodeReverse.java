package linked;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 单链表的反转
 * @Author 张三金
 * @Date 2021/1/23 0023 12:44
 * @Company jzb
 * @Version 1.0.0
 */
public class NodeReverse {

    public static Node reverseTest(Node head, int size) {

        int[] array = new int[size];
        int count = 0;
        while(head.getNext() != null) {
            array[count] = (int) head.getT();
            count++;
            head = head.getNext();
        }
        array[count] = (int) head.getT();

        // 反转链表
        Integer firstValue = array[0];
        Node node = null;
        Node nextNode = new Node(firstValue);
        for (int i = 1; i < array.length; i++) {
            Integer value = array[i];
            node = new Node(value);
            node.setNext(nextNode);
            nextNode = node;
        }
        return node;
    }

    /**
     * 链表反转
     * @param head 结点信息
     * @return 返回反转之后的头结点
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while(head != null) {
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 构造一个单链表
     * @param size 单链表的大小
     * @return 单链表的头结点
     */
    public static Node buildNode(int size) {
        Integer firstValue = (int) (Math.random() * 100);
        Node node = null;
        Node nextNode = new Node(firstValue);
        for (int i = 0; i < size - 1; i++) {
            Integer value = (int) (Math.random() * 100);
            node = new Node(value);
            node.setNext(nextNode);
            nextNode = node;
        }
        return node;
    }

    /**
     * 输出 node 节点
     * @param node 头结点
     */
    public static void printNode(Node node) {
        do {
            System.out.print(node.getT() + "-->");
            node = node.getNext();
        }while(node.getNext() != null);
        System.out.println(node.getT());
    }

    public static void main(String[] args) {

        Node head = buildNode(10);
        printNode(head);
        reverseTest(head, 10);
        System.out.println();
//        printNode(reverseTest(head, 10));
        printNode(reverseLinkedList(head));
    }
}
