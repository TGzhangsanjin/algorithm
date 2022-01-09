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

    /**
     *  优化解法，使用一个加强堆处理，来维护接下来需要处理的节点
     */
    public Map<GraphNode<Integer>, Integer> dijkstra02 (GraphNode<Integer> from, int size) {
        NodeHeap heap = new NodeHeap(size);
        heap.addOrUpdateOrIgnore(from, 0);
        Map<GraphNode<Integer>, Integer> resualt = new HashMap<>();
        while (!heap.isEmpty()) {
            NodeReacord reacord = heap.poll();
            GraphNode<Integer> node = reacord.node;
            for (GraphEdge<Integer> edge : node.getEdges()) {
                heap.addOrUpdateOrIgnore(edge.getTo(), reacord.distance + edge.getWeight());
            }
            resualt.put(node, reacord.distance);
        }
        return resualt;
    }

    public class NodeHeap {

        // 正常的堆的维护数组
        private GraphNode<Integer>[] nodes;

        // 反向索引表，用于记录数据在堆中的位置
        // 注意，这里有一个小技巧点，如果数据从堆里弹出，不要remove，将value 设置为 -1， 这样 addOrUpdateOrIgnore 方法中的 ignore 就可以处理了
        private Map<GraphNode<Integer>, Integer> heapIndexMap;

        // 当前节点的距离
        private Map<GraphNode<Integer>, Integer> distanceMap;

        // 堆的大小
        private int size;

        public NodeHeap (int size) {
            this.nodes = new GraphNode[size];
            this.heapIndexMap = new HashMap<>(size);
        }

        public void addOrUpdateOrIgnore (GraphNode<Integer> node, int distance) {
            if (inHeap(node)) {
                // 在堆里面
                // update
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                int index = heapIndexMap.get(node);
                heapInsert(index);
            }
            if (!isEnter(node)) {
                // 没有进入过堆里面
                // add
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(size++);
            }
            // ignore
        }

        // 弹出
        public NodeReacord poll () {
            NodeReacord nodeRecord = new NodeReacord(nodes[0], distanceMap.get(nodes[0]));
            nodes[0] = nodes[--size];
            nodes[size] = null;
            // 注意，反向索引表也需要更新
            heapIndexMap.put(nodeRecord.node, -1);
            heapIndexMap.put(nodes[0], 0);
            heapify(0);
            return nodeRecord;
        }

        public boolean isEmpty () {
            return size == 0;
        }

        // 判断节点当前是否就在堆里面
        private boolean inHeap (GraphNode<Integer> node) {
            return heapIndexMap.get(node) != null && heapIndexMap.get(node) != -1;
        }

        // 判断节点是否进入过堆里
        private boolean isEnter(GraphNode<Integer> node) {
            return heapIndexMap.get(node) == null;
        }

        // 上浮index下标对应的数据
        private void heapInsert (int index) {
            while (distanceMap.get(nodes[((index - 1) / 2)]) > distanceMap.get(nodes[index])) {
                swap(((index - 1) / 2), index);
                index = ((index - 1) / 2);
            }
        }

        // 下沉 index 下标对应的数据
        private void heapify (int index) {
            int left = index * 2 + 1;
            while (left < size) {
                int small = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1: left;
                small = distanceMap.get(nodes[small]) < distanceMap.get(nodes[index]) ? small: index;
                if (small == index) {
                    break;
                }
                swap(small, index);
                index = small;
                left = index * 2 + 1;
            }
        }


        private void swap (int i, int j) {
            if (i == j) {
                return;
            }
            GraphNode<Integer> nodeI = nodes[i];
            GraphNode<Integer> nodeJ = nodes[j];
            heapIndexMap.put(nodeI, j);
            heapIndexMap.put(nodeJ, i);
            nodes[i] = nodeJ;
            nodes[j] = nodeI;
        }

    }


    /**
     * 放在加强堆中的元素
     */
    public class NodeReacord {
        public GraphNode<Integer> node;
        public int distance;

        public NodeReacord (GraphNode<Integer> node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
