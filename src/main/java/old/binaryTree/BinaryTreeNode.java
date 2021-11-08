package old.binaryTree;

/**
 * 二叉树结构
 * @Author 张三金
 * @Date 2021/4/25 0025 23:49
 * @Company jzb
 * @Version 1.0.0
 */
public class BinaryTreeNode<T> {

    /**
     * 节点值
     */
    private T value;

    /**
     * 左孩子
     */
    private BinaryTreeNode<T> left;

    /**
     * 右孩子
     */
    private BinaryTreeNode<T> right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
}
