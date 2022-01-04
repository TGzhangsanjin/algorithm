package class16;

import java.util.ArrayList;

/**
 * 通用的图节点结构
 * @Author 张三金
 * @Date 2022/1/4 0004 14:24
 * @Company jzb
 * @Version 1.0.0
 */
public class GraphNode<T> {
    /**
     * 值
     */
    private T value;

    /**
     * 入度，即有多少个节点指向当前节点
     */
    private int in;

    /**
     * 出度，即当前节点指向多少个节点
     */
    private int out;

    /**
     * 当前节点指向的所有节点
     */
    private ArrayList<GraphNode<T>> nexts;

    /**
     * 从当前节点出发的所有边
     */
    private ArrayList<GraphEdge<T>> edges;

    public GraphNode (T t) {
        this.value = t;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public ArrayList<GraphNode<T>> getNexts() {
        return nexts;
    }

    public void setNexts(ArrayList<GraphNode<T>> nexts) {
        this.nexts = nexts;
    }

    public ArrayList<GraphEdge<T>> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<GraphEdge<T>> edges) {
        this.edges = edges;
    }
}
