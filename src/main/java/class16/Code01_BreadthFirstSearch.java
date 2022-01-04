package class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先遍历 BFS
 * 思路：
 *  (1) 和树的BFS是一样的，使用一个辅助队列，
 *  (2) 但是还需要一个辅助SET，用于存在已经遍历过的数据，入队列时，如果节点在SET中存在，则不入队列，
 *      因为图是有可能出现环路的，如果不这么处理的话，则可能出现死循环，
 * @Author 张三金
 * @Date 2022/1/4 0004 14:51
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_BreadthFirstSearch {

    // 从某个节点开始进行BFS
    public static void bfs (GraphNode<Integer> start) {
        if (start == null) {
            return;
        }
        Queue<GraphNode<Integer>> queue = new LinkedList<>();
        HashSet<GraphNode<Integer>> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            GraphNode<Integer> current = queue.poll();
            // 出队列的时候打印（出队列或者入队列的时候打印都行，反正队列时先进先出的）
            System.out.println(current.getValue());
            for (GraphNode<Integer> next : current.getNexts()) {
                // 判断是否能够入队列
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
