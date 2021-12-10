package class04;

/**
 * 单链表
 * @Author 张三金
 * @Date 2021/12/10 0010 13:40
 * @Company jzb
 * @Version 1.0.0
 */
public class SingleNode<T> {

    private T value;

    private SingleNode<T> next;

    public SingleNode (T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SingleNode<T> getNext() {
        return next;
    }

    public void setNext(SingleNode<T> next) {
        this.next = next;
    }
}
