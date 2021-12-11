package class11;

/**
 * 二叉树节点
 * @Author 张三金
 * @Date 2021/12/11 0011 9:38
 * @Company jzb
 * @Version 1.0.0
 */
public class BinaryNode<T> {

    /**
     * 节点值
     */
    private T value;

    /**
     * 左孩子
     */
    private BinaryNode<T> left;

    /**
     * 右孩子
     */
    private BinaryNode<T> right;

    public BinaryNode (T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }
}
