package class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先遍历 DFS
 * 思路：
 *    (1) 利用栈实现，和树的深度优先遍历不同的是，同样的需要准备一个 set 集合，
 *    (2) 遍历的时候相当于时走完一条完整的路，再去走另外一条路
 *    (3) 栈里面存放着是一条完整的路径
 *    (4) 入栈的时候即打印
 * @Author 张三金
 * @Date 2022/1/4 0004 15:02
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_DepthFirstSearch {

    public static void dfs (GraphNode<Integer> start) {
        if (start == null) {
            return;
        }
        Stack<GraphNode<Integer>> stack = new Stack<>();
        HashSet<GraphNode<Integer>> set = new HashSet<>();
        stack.push(start);
        // 入栈即打印
        System.out.println(start.getValue());
        set.add(start);
        while (!stack.isEmpty()) {
            GraphNode<Integer> current = stack.pop();
            for (GraphNode<Integer> next : current.getNexts()) {
                if (!set.contains(next)) {
                    // 下一个节点之前没处理过，则把current也放进去，再把当前节点也放进去，
                    // 确保栈中存放的是一条完整的路径
                    stack.push(current);
                    stack.push(next);
                    // 入栈即打印
                    System.out.println(next.getValue());
                    set.add(next);
                    // 先去遍历完这一条路，其它路等回去的时候再遍历
                    break;
                }
            }
        }
    }

}
