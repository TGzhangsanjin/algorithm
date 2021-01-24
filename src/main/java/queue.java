import linked.DoubleNode;

/**
 * 队列结构
 * @Author 张三金
 * @Date 2021/1/23 0023 20:44
 * @Company jzb
 * @Version 1.0.0
 */
public class queue {

    /**
     * 生成一个随机数组
     * @param size 数组大小
     * @param range 数组范围
     * @return 返回生成的数组
     */
    public static Integer[] randomArray(int size, int range) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {

            array[i] = (int) (Math.random() * range) + 1;
        }
        return array;
    }

    /**
     * 双链表实现队列
     * @param head 队列
     * @param value 待插入的节点的值
     */
    public static DoubleNode doubleLinkedPush(DoubleNode head, Integer value) {
        if (value == null) {
            return null;
        }
        DoubleNode newNode = new DoubleNode(value);
        if (head == null) {
            head = newNode;
        } else {
            DoubleNode current = head;
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrevious(current);
        }
        return head;
    }

    /**
     * 取出头结点的值，然后将头结点删除，头结点的下一个节点变成头结点
     * @param head 队列
     * @return 返回处理后的队列
     */
    public static DoubleNode doubleLinkedPop(DoubleNode head) {
        if (head == null || head.getNext() == null) {
            return null;
        }
        head.getNext().setPrevious(null);
        head = head.getNext();
        return head;
    }

    public static void main(String[] args) {
        // 1、生成一个数组(将数组中的值打印出来)
        Integer[] array = randomArray(10, 100);
        for (Integer integer : array) {
            System.out.print(integer + "-->");
        }
        // 2、将数组中的值一个个push到队里中
        DoubleNode head = null;
        for (Integer integer : array) {
            head = doubleLinkedPush(head, integer);
        }
        System.out.println();
        // 3、打印双链表队列的值（正向打印和反向打印）
        DoubleNode.printNodeAsc(head);
        System.out.println();
        DoubleNode.printNodeDesc(head);

        // 4、验证 pop 的方法
        System.out.println();
        DoubleNode.printNodeAsc(head);
        System.out.println("开始验证pop方法：");

//        head = pop(head);
//        DoubleNode.printNodeAsc(head);
        for (Integer integer : array) {
            head = doubleLinkedPop(head);
            DoubleNode.printNodeAsc(head);
            System.out.println();
        }
    }




}
