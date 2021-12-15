package class12;

/**
 * 包含父节点引用的二叉树节点
 * @Author 张三金
 * @Date 2021/12/15 0015 17:26
 * @Company jzb
 * @Version 1.0.0
 */
public class SuccessorNode<T> {

    /**
     * 值
     */
    private T value;

    /**
     * 左孩子
     */
    private SuccessorNode<T> left;

    /**
     * 右孩子
     */
    private SuccessorNode<T> right;

    /**
     * 父节点
     */
    private SuccessorNode<T> parent;

    public SuccessorNode (T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SuccessorNode<T> getLeft() {
        return left;
    }

    public void setLeft(SuccessorNode<T> left) {
        this.left = left;
    }

    public SuccessorNode<T> getRight() {
        return right;
    }

    public void setRight(SuccessorNode<T> right) {
        this.right = right;
    }

    public SuccessorNode<T> getParent() {
        return parent;
    }

    public void setParent(SuccessorNode<T> parent) {
        this.parent = parent;
    }
}
