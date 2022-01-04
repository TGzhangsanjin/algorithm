package class16;

import java.util.*;

/**
 * 拓扑排序
 *  思路：
 *      (1) 在图中找到所有入度为0的节点输出
 *      (2) 将图中所有入度为0的节点全部删除（当然同时与这些节点相关的信息都要删掉），继续找入度为0的点，周而复始
 *      (3) 图中所有节点的删除顺序，依次输出，就是图的拓扑排序
 *  另外：
 *      (1) 对于节点X，X的长度表示从A开始一直往下找，找到的所有节点数量叫做X的长度
 *      (2) 在拓扑序中，如果X排在Y的前面，那么一定有 X.length <= Y.length
 *  要求：图是有向图且图中没有环
 *  应用：事件安排、编译顺序等
 * @Author 张三金
 * @Date 2022/1/4 0004 15:17
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_TopologySort {

    /**
     * 非递归的方式实现图的拓扑排序
     */
    public static List<GraphNode<Integer>> sort (Graph<Integer> graph) {

        // key: 节点 value: 入度数量
        HashMap<GraphNode<Integer>, Integer> inMap = new HashMap<>();
        // 记录入度为0的节点
        Queue<GraphNode<Integer>> zeroNodeQueue = new LinkedList<>();
        // 初始化
        // 1. 将所有节点的入度用一个 inMap 表示
        // 2. 将入度为0 的节点放入zeroNodeQueue中
        for (GraphNode<Integer> node : graph.getNodes().values()) {
            inMap.put(node, node.getIn());
            if (node.getIn() == 0) {
                zeroNodeQueue.add(node);
            }
        }
        List<GraphNode<Integer>> result = new ArrayList<>();
        while (!zeroNodeQueue.isEmpty()) {
            GraphNode<Integer> current = zeroNodeQueue.poll();
            // 先出队列的节点，在拓扑排序中排在前面
            result.add(current);
            for (GraphNode<Integer> next : current.getNexts()) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    // 这个判断是必须有的，因为next的from节点不一定是 current，也有可能是其它节点
                    zeroNodeQueue.add(next);
                }
            }
        }
        return result;
    }
}
