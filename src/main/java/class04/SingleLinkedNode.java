package class04;

/**
 * 单向链表节点
 * @Author 张三金
 * @Date 2021/11/10 0010 14:11
 * @Company jzb
 * @Version 1.0.0
 */
public class SingleLinkedNode<T> {

    /**
     * 泛型值
     */
    private T value;

    /**
     * 指向下一个节点
     */
    private SingleLinkedNode next;

    public SingleLinkedNode (T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SingleLinkedNode getNext() {
        return next;
    }

    public void setNext(SingleLinkedNode next) {
        this.next = next;
    }
}
