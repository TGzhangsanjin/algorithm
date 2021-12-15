package class12;

import java.util.List;

/**
 * 多叉树节点
 * @Author 张三金
 * @Date 2021/12/11 0011 9:38
 * @Company jzb
 * @Version 1.0.0
 */
public class TreeNode<T> {

    /**
     * 节点值
     */
    private T value;

    /**
     * 孩子
     */
    private List<TreeNode<T>> children;


    public TreeNode(T data) {
        this.value = data;
    }


    public TreeNode(T value, List<TreeNode<T>> children) {
        this.value = value;
        this.children = children;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }
}
