package class04;

/**
 * 双向链表的基本结构
 * @Author 张三金
 * @Date 2021/11/22 0022 11:25
 * @Company jzb
 * @Version 1.0.0
 */
public class DoubleNode<T> {

    private T value;

    private DoubleNode<T> next;

    private DoubleNode<T> last;

    public DoubleNode (T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public DoubleNode<T> getLast() {
        return last;
    }

    public void setLast(DoubleNode<T> last) {
        this.last = last;
    }
}
