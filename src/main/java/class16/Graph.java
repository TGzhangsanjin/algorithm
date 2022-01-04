package class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图的概念：
 *  (1) 由点的集合和边的集合构成
 *  (2) 虽然存在着有向图和无向图的概念，但实际上都可以用无向图来表示（无向图就是互相指向，A指向B同时B也指向A）
 *  (3) 边上可能带有权值
 * 图结构的表达：
 *  (1) 邻接表法
 *      设置一个Map，那么对于节点X，X指向A、B、C，即map.put(X, {A,B,C})
 *      如果还带有权值的话，即 map.put(X, { {A,len1}, {B,len2}, {C, len3}}
 *  (2) 邻接矩阵法
 *      如果图一共有 N个节点，则准备一个 N*N 的矩阵（二维数组）matrix，matrix[i][j] = 3 表示 i节点指向j节点且边的权重是3
 *  (3) 除此之外还有很多其它众多的表达方式
 *  图的解题思路：
 *  (1) 图的算法都不难，只不过 coding代价比较高，即维持图结构的代码比较多
 *  (2) 先用自己最熟练的方式，实现图的结构
 *  (3) 在自己熟练的结构上，实现所有常用的图算法作为模板
 *  (4) 把题目提供的图结构转换成自己熟悉的图结构，再调用模板或改写即可
 * @Author 张三金
 * @Date 2022/1/4 0004 13:50
 * @Company jzb
 * @Version 1.0.0
 */
public class Graph<T> {

    /**
     * 图中所有的节点
     */
    private HashMap<T, GraphNode<T>> nodes;

    /**
     * 图中所有的边
     */
    private HashSet<GraphEdge<T>> edges;

    public Graph () {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    public HashMap<T, GraphNode<T>> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<T, GraphNode<T>> nodes) {
        this.nodes = nodes;
    }

    public HashSet<GraphEdge<T>> getEdges() {
        return edges;
    }

    public void setEdges(HashSet<GraphEdge<T>> edges) {
        this.edges = edges;
    }
}
