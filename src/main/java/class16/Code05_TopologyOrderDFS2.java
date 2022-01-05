package class16;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * OJ链接：https://www.lintcode.com/problem/topological-sorting
 *
 * 思路就是 Code03_TopologySort
 * @Author 张三金
 * @Date 2022/1/4 0004 16:23
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_TopologyOrderDFS2 {

    // 不要提交这个类
    public class DirectedGraphNode {
        // 就是当前节点的值
        public int label;
        // 相当于nexts，指向的所有节点
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 用于记录每个节点的点次
    public class Record {
        public DirectedGraphNode node;

        // 这里要用long类型，因为点次是会比长度大的
        public long nodeCount;

        public Record (DirectedGraphNode node, long nodeCount) {
            this.node = node;
            this.nodeCount = nodeCount;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        // 1. 计算好每个节点的点次
        for (DirectedGraphNode current : graph) {
            f(current, order);
        }
        ArrayList<Record> records = new ArrayList<>();
        for (Record value : order.values()) {
            records.add(value);
        }
        // 2. 对每个节点按照点次的逆序进行排序
        // 这里很重要，不能直接使用 (int) (b.nodeCount - a.nodeCount) 来判断哪个更大，因为 nodeCount 定义的是long类型，如果差很大，转成 int后是会有精度溢出的
        records.sort((a, b) -> a.nodeCount == b.nodeCount ? 0 : (a.nodeCount > b.nodeCount ?-1 : 1));

        // 3. 排序的顺序就是图的一个拓扑排序
        ArrayList<DirectedGraphNode> rsp = new ArrayList<>();
        for (Record record : records) {
            rsp.add(record.node);
        }
        return rsp;
    }

    // current 当前节点
    // order 记录每个的所有下游节点, 即从 current 出发一直到结束，一共有多少个节点
    public Record f(DirectedGraphNode current, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(current)) {
            return order.get(current);
        }
        long allNodes = 0;
        for (DirectedGraphNode neighbor : current.neighbors) {
            // 获取所以的点次
            allNodes += f(neighbor, order).nodeCount;
        }
        Record record = new Record(current, allNodes + 1);
        order.put(current, record);
        return record;
    }
}
