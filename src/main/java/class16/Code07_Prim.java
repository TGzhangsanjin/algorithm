package class16;

import java.util.*;

/**
 * Prim 算法解决最小生成树问题
 */
public class Code07_Prim {

    public static Set<GraphEdge<Integer>> primMST (Graph<Integer> graph) {
        PriorityQueue<GraphEdge<Integer>> queue = new PriorityQueue<>((a, b) -> {return a.getWeight() - b.getWeight();});
        List<GraphNode<Integer>> nodes = new ArrayList<>(graph.getNodes().values());
        Set<GraphEdge<Integer>> ans = new HashSet<>();
        Set<GraphNode<Integer>> selectedNodes = new HashSet<>();
        for (GraphNode<Integer> node : nodes) {
            if (!selectedNodes.contains(node)) {
                queue.addAll(node.getEdges());
            }
            while (!queue.isEmpty()) {
                GraphEdge<Integer> cur = queue.poll();
                if (!selectedNodes.contains(cur.getTo())) {
                    // 之前没有选过，那么这条边就是最小生成树中的一条边，然后去找下一个节点，把下一个节点的所有指向边放到队列中去
                    ans.add(cur);
                    selectedNodes.add(cur.getTo());
                    queue.addAll(cur.getTo().getEdges());
                }
            }
            // 这个break是不能够加的，有两种情况需要考虑
            // 1. 遍历到途中某个节点没有指向了，可能会少边
            // 2. 如果出现森林的情况，加了这个break就会出现问题，会少边
//            break;
        }
        return ans;

    }
}
