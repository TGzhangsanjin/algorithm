package class16;

/**
 * 将任意的图的表示结构转化成通用的图的表示结构
 * @Author 张三金
 * @Date 2022/1/4 0004 14:39
 * @Company jzb
 * @Version 1.0.0
 */
public class GraphGenerate {

    /**
     * matrix 是一个 N*3 的矩阵
     * matrix[i] 这一行表示：matrix[0] 权重， matrix[1]和matrix[2] 表示 matrix[1]指向matrix[2]
     * [weight, from节点的值， to节点的值]
     * 例如：
     *  [5, 0, 7]
     *  [3, 0, 1]
     */
    public static Graph<Integer> createGraph (int[][] matrix) {
        Graph<Integer> graph = new Graph<>();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            Integer fromValue = matrix[i][1];
            Integer toValue = matrix[i][2];
            // 两个if确保 graph.nodes 已经存在对应的节点
            if (!graph.getNodes().containsKey(fromValue)) {
                graph.getNodes().put(fromValue, new GraphNode<>(fromValue));
            }
            if (!graph.getNodes().containsKey(toValue)) {
                graph.getNodes().put(toValue, new GraphNode<>(toValue));
            }
            GraphNode<Integer> from = graph.getNodes().get(fromValue);
            GraphNode<Integer> to = graph.getNodes().get(toValue);
            GraphEdge<Integer> edge = new GraphEdge<>(weight, from, to);

            from.setOut(from.getOut() + 1);
            from.getNexts().add(to);
            from.getEdges().add(edge);

            to.setIn(to.getIn() + 1);

            graph.getEdges().add(edge);
        }
        return graph;
    }
}
