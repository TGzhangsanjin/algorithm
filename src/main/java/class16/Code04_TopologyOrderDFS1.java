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
    public class Record {
        public DirectedGraphNode node;

        public int length;

        public Record (DirectedGraphNode node, int length) {
            this.node = node;
            this.length = length;
        }
    }

    // current 当前节点
    // order 记录每个节点的长度
    public static void f(DirectedGraphNode current, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(current)) {

        }
    }
}
