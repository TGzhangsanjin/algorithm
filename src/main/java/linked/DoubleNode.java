package linked;

/**
 * 双链表节点
 * @Author 张三金
 * @Date 2021/1/23 0023 13:32
 * @Company jzb
 * @Version 1.0.0
 */
public class DoubleNode<T> {

    /**
     * 节点内容
     */
    private T t;

    /**
     * 下一个节点
     */
    private DoubleNode next;

    /**
     * 上一个节点
     */
    private DoubleNode last;

    public DoubleNode() {
        super();
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public DoubleNode(T t) {
        this.t = t;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public DoubleNode getLast() {
        return last;
    }

    public void setLast(DoubleNode last) {
        this.last = last;
    }
    /**
     * 输出 node 节点
     * @param node 头结点
     */
    public static void printNode(DoubleNode node) {
        do {
            System.out.print(node.getT() + "-->");
            node = node.getNext();
        }while(node.getNext() != null);
        System.out.println(node.getT());
    }

    /**
     * 构造一个单链表
     * @param size 单链表的大小
     * @return 单链表的头结点
     */
    public static DoubleNode buildDoubleNode(int size, int range) {
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * range) + 1);
        DoubleNode pre = head;
        while(size != 0) {
            DoubleNode current = new DoubleNode((int) (Math.random() * range) + 1);
            pre.setNext(current);
            current.setLast(pre);
            pre = current;
            size --;
        }
        return head;
    }

    /**
     * 双链表的反转
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        DoubleNode node = buildDoubleNode(10, 100);
        printNode(node);
        printNode(reverseDoubleNode(node));
    }
}
