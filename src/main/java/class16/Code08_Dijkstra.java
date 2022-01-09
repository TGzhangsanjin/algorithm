package class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 迪杰斯特拉算法， 求图中某个节点到其它节点的最短路径
 *
 * 输入： 图中的某个节点 from
 * 输出： from 能够到达的每个节点的最短路径
 *
 * @Author 张三金
 * @Date 2022/1/9 0009 14:06
 * @Company jzb
 * @Version 1.0.0
 */
public class Code08_Dijkstra {

    /**
     * from : 起点
     * 返回
     *  key: from 所有能够到达的节点
     *  value: 到达某个节点的最短路径
     */
    public static Map<GraphNode<Integer>, Integer> dijkstra01 (GraphNode<Integer> from) {
        Map<GraphNode<Integer>, Integer> distanceMap = new HashMap<>();
        // from 到达自己的最短路径就是 0
        distanceMap.put(from, 0);
        // 记录已经处理过的节点
        List<GraphNode<Integer>> selectedNodes = new ArrayList<>();
        // 从剩下未处理过的节点中选择一个最小的节点
        GraphNode<Integer> minNode = getMinNodeFromDistanceMap(distanceMap, selectedNodes);
        while (minNode != null) {

            for (GraphEdge<Integer> edge : minNode.getEdges()) {
                GraphNode<Integer> toNode = edge.getTo();
                if (!distanceMap.containsKey(toNode)) {
                    // toNode 之前没有记录过距离，那么from 到达 toNode 距离就是 from 到达minNode 的距离加上 边的距离
                    distanceMap.put(toNode, edge.getWeight() + distanceMap.get(minNode));
                } else {
                    distanceMap.put(toNode, Math.min(edge.getWeight() + distanceMap.get(minNode), distanceMap.get(toNode)));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinNodeFromDistanceMap(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static GraphNode<Integer> getMinNodeFromDistanceMap (Map<GraphNode<Integer>, Integer> distanceMap
            , List<GraphNode<Integer>> selectedNodes) {
        GraphNode<Integer> ans = null;
        int min = Integer.MAX_VALUE;
        for (GraphNode<Integer> key : distanceMap.keySet()) {
            if (!selectedNodes.contains(key)) {
                if (distanceMap.get(key) < min) {
                    ans = key;
                    min = distanceMap.get(key);
                }
            }
        }
        return ans;
    }
}
