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
    private DoubleNode previous;

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

    public DoubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode previous) {
        this.previous = previous;
    }

    /**
     * 获取最后一个节点
     * @param head 传入的头结点
     * @return 返回尾节点
     */
    public static DoubleNode getLastOne(DoubleNode head) {
        // 1、先找到最后一个节点的值
        while(head.getNext() != null) {
            head = head.getNext();
        }
        return head;
    }

    /**
     * 顺序打印 双链表的值
     * @param node 头结点
     */
    public static void printNodeAsc(DoubleNode node) {
        if (node == null) {
            System.out.println("这是一个空的节点！");
            return;
        }
        System.out.print(node.getT() + "-->");
        while(node.getNext() != null) {
            node = node.getNext();
            System.out.print(node.getT() + "-->");
        }
        System.out.println();
    }


    /**
     * 逆序打印双链表的值
     * @param node 头结点
     */
    public static void printNodeDesc(DoubleNode node) {
        // 1、先找到最后一个节点的值
        node = getLastOne(node);
        // 2、反向打印
        do {
            System.out.print(node.getT() + "-->");
            node = node.getPrevious();
        }while(node.getPrevious() != null);
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
            current.setPrevious(pre);
            pre = current;
            size --;
        }
        return head;
    }

    /**
     * 删除单链表中的指定值
     * @param head 待删除的链表
     * @param deleteValue 待删除的值
     * @return 返回删后的链表
     */
    public static DoubleNode delete(DoubleNode head, Integer deleteValue) {
        // 1、第一步，需要先处理头部
        while(head != null) {
            if (!head.getT().equals(deleteValue)) {
                break;
            }
            head = head.getNext();
        }
        // 2、遍历列表中，除了头部外，需要删除的节点
        DoubleNode pre = head;
        DoubleNode cur = head;
        while(cur.getNext() != null) {
            cur = cur.getNext();
            if (cur.getT().equals(deleteValue)) {
                pre.setNext(cur.getNext());
                if (cur.getNext() != null) {
                    cur.getNext().setPrevious(pre);
                }
            }
            pre = cur;
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
            head.previous = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
//        DoubleNode node = buildDoubleNode(10, 100);
//        printNode(node);
//        printNode(reverseDoubleNode(node));

        // 测试双链表删除给定值
        DoubleNode node = buildDoubleNode(40, 10);
        System.out.println("打印的初始值：");
        printNodeAsc(node);
        node = delete(node, 10);
        System.out.println("打印的删除后的顺序值：");
        printNodeAsc(node);
        System.out.println("打印的删除后的逆序值：");
        printNodeDesc(node);

    }
}
