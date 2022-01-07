package class16;

import com.sun.javafx.geom.Edge;

import java.util.*;

/**
 * Kruskal 算法解决最小生成树问题
 *
 * 最小生成树：
 *  有一个全有权重的图，生成树就是保留连通所有节点的边，且不出现环路，
 *  很明显，会有很多棵这样的生成树，选择其中一棵权重加起来最小的就是最小生成树
 *
 * 使用并查集的思路解决这个问题,从最小的边开始，一条边一条边去找剩下的最小的
 * 使用并查集，将选择了的边的两个节点放到一个集合中，因为这些边是从小到大遍历的，最终会将所有的点都放到一个集合中（即最小生成树），后续的边就会都舍弃了
 */
public class Code06_Kruskal {

    public static class UnionFind {
        public HashMap<GraphNode<Integer>, GraphNode<Integer>> parents;
        public HashMap<GraphNode<Integer>, Integer> sizes;

        public List<GraphNode<Integer>> help;

        public UnionFind (List<GraphNode<Integer>> nodes) {
            parents = new HashMap<>(nodes.size());
            sizes = new HashMap<>(nodes.size());
            help = new ArrayList<>(nodes.size());
            for (GraphNode<Integer> node : nodes) {
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        public boolean isSameSet (GraphNode<Integer> i, GraphNode<Integer> j) {
            return findAncestor(i) == findAncestor(j);
        }

        public GraphNode<Integer> findAncestor (GraphNode<Integer> node) {
            while (parents.get(node) != node) {
                help.add(parents.get(node));
                node = parents.get(node);
            }
            for (GraphNode<Integer> cur : help) {
                parents.put(cur, node);
            }
            help.clear();
            return node;
        }

        public void union (GraphNode<Integer> i, GraphNode<Integer> j) {
            GraphNode<Integer> p1 = parents.get(i);
            GraphNode<Integer> p2 = parents.get(j);
            if (p1 != p2) {
                GraphNode<Integer> big = sizes.get(p1) > sizes.get(p2) ? p1:p2;
                GraphNode<Integer> small = big == p1 ? p2:p1;
                parents.put(small, big);
                sizes.put(big, sizes.get(big) + sizes.get(small));
            }
        }


    }

    public static Set<GraphEdge> kruskalMST (Graph<Integer> graph) {

        List<GraphNode<Integer>> nodes = new ArrayList<>(graph.getNodes().values());
        UnionFind unionFind = new UnionFind(nodes);
        PriorityQueue<GraphEdge<Integer>> queue = new PriorityQueue<GraphEdge<Integer>>(
                (a, b) -> { return a.getWeight() - b.getWeight(); });
        queue.addAll(graph.getEdges());
        Set<GraphEdge> ans = new HashSet<>();
        while (!queue.isEmpty()) {
            GraphEdge<Integer> edge = queue.poll();
            if (!unionFind.isSameSet(edge.getFrom(), edge.getTo())) {
                // 这条边对应的两个点没有在一个集合中，则放到一个集合中， 且这条边是要选择的表
                ans.add(edge);
                unionFind.union(edge.getFrom(), edge.getTo());
            }
        }
        return ans;

    }
}
