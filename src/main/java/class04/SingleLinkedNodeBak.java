package class04;

/**
 * 单向链表节点
 * @Author 张三金
 * @Date 2021/11/10 0010 14:11
 * @Company jzb
 * @Version 1.0.0
 */
public class SingleLinkedNodeBak<T> {

    /**
     * 泛型值
     */
    private T value;

    /**
     * 指向下一个节点
     */
    private SingleLinkedNodeBak next;

    public SingleLinkedNodeBak(T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SingleLinkedNodeBak getNext() {
        return next;
    }

    public void setNext(SingleLinkedNodeBak next) {
        this.next = next;
    }
}
