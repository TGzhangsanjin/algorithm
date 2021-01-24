import linked.DoubleNode;

/**
 * 栈结构
 * @Author 张三金
 * @Date 2021/1/23 0023 21:32
 * @Company jzb
 * @Version 1.0.0
 */
public class Stack {


    /**
     * 往栈中放入一个元素（双链表实现）
     */
    public static DoubleNode doubleLinkedPush(DoubleNode head, Integer value) {
        DoubleNode current = new DoubleNode(value);
        if (head == null) {
            return current;
        }
        current.setNext(head);
        return current;
    }

    /**
     * 弹出栈中的顶端元素（双链表实现）
     */
    public static DoubleNode doubleLinkedPop(DoubleNode head) {
        if (head == null || head.getNext() == null) {
            return null;
        }
        head.getNext().setPrevious(null);
        head = head.getNext();
        return head;
    }

}
