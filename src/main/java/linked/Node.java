package linked;

/**
 * 单链表节点
 * @Author 张三金
 * @Date 2021/1/23 0023 13:32
 * @Company jzb
 * @Version 1.0.0
 */
public class Node<T> {

    /**
     * 节点内容
     */
    private T t;

    /**
     * 下一个节点
     */
    private Node next;

    public Node(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * 构造一个单链表
     * @param size 单链表的大小
     * @return 单链表的头结点
     */
    public static Node buildNode(int size, int range) {
        size--;
        Node head = new Node((int) (Math.random() * range) + 1);
        Node pre = head;
        while(size != 0) {
            Node current = new Node((int) (Math.random() * range) + 1);
            pre.setNext(current);
            pre = current;
            size --;
        }
        return head;
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
        printNode(buildNode(10, 100));
    }
}
