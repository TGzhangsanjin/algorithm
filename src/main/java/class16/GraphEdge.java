package class16;

/**
 * 图的通用表示边的结构
 * @Author 张三金
 * @Date 2022/1/4 0004 14:28
 * @Company jzb
 * @Version 1.0.0
 */
public class GraphEdge<T> {

    /**
     * 权重
     */
    private int weight;

    /**
     * 边的起点
     */
    private GraphNode<T> from;

    /**
     * 边的终点
     */
    private GraphNode<T> to;

    public GraphEdge (int weight, GraphNode<T> from, GraphNode<T> to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GraphNode<T> getFrom() {
        return from;
    }

    public void setFrom(GraphNode<T> from) {
        this.from = from;
    }

    public GraphNode<T> getTo() {
        return to;
    }

    public void setTo(GraphNode<T> to) {
        this.to = to;
    }
}
