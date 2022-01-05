package class16;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * OJ链接：https://www.lintcode.com/problem/topological-sorting
 *  计算节点的长度肯定是比计算节点的点次要好的，因为length 只要申请int类型，而 nodeCount 需要申请 long类型
 * 思路就是 Code03_TopologySort
 * @Author 张三金
 * @Date 2022/1/4 0004 16:23
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_TopologyOrderDFS1 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        // 就是当前节点的值
        public int label;
        // 相当于nexts，指向的所有节点
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 用于记录每个节点的长度
    public static class Record {
        public DirectedGraphNode node;

        public int length;

        public Record (DirectedGraphNode node, int length) {
            this.node = node;
            this.length = length;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort (ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        // 1. 计算好每个节点的长度
        for (DirectedGraphNode current : graph) {
            f(current, order);
        }
        ArrayList<Record> records = new ArrayList<>();
        for (Record value : order.values()) {
            records.add(value);
        }
        // 2. 对每个节点按照长度的逆序进行排序
        records.sort((a, b) -> b.length - a.length);

        // 3. 排序的顺序就是图的一个拓扑排序
        ArrayList<DirectedGraphNode> rsp = new ArrayList<>();
        for (Record record : records) {
            rsp.add(record.node);
        }
        return rsp;
    }

    // current 当前节点
    // order 记录每个节点的长度
    public static Record f(DirectedGraphNode current, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(current)) {
            return order.get(current);
        }
        int follow = 0;
        for (DirectedGraphNode neighbor : current.neighbors) {
            // 看看走哪条路能够获取最大的长度
            follow = Math.max(follow, f(neighbor, order).length);
        }
        Record record = new Record(current, follow + 1);
        order.put(current, record);
        return record;
    }
}
