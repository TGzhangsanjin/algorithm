package class15;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集（一个支持合并和查找的结构，合并和查找的时间复杂度都是 O(1)）
 * 说明： a,b,c 各代表一个集合， 并查集结构中主要实现两个方法
 *  (1) isTheSameSet(a,c) a和c是否属于同一个集合，
 *  (2) union(a, c) 合并a和c代表的两个集合
 * 总结1：
 *  1. 有若干个样本 a,b,c,d ... 类型假设都是 T
 *  2. 在并查集中一开始认为一个样本都在单独的集合里 （每个集合一开始都只包含自己一个元素）
 *  3. 用户可以再任何使用调用如下连个方法：
 *      (1) boolean isTheSameSet(T a, T b); 查询样本 a 和样本 b 是否属于同一个集合
 *      (2) void union(T a, T b); 把 a 和 b 各自所在集合的所有样本合成同一个集合
 *  4. isTheSameSet 和 union 的代价越低越好
 * 总结2：
 *   1. 初始化时每个节点都有一个往上指的指针，一开始指向的是自己（多次操作之后是会变的）
 *   2. 节点a往上找的头结点，叫做 a 所在集合的代表节点
 *   3. 查询x和y是不是在同一个集合，就是看找到的代表节点是不是同一个
 *   4. 把x和y各自所代表的的集合合并成一个集合，只需要小集合的代表点挂在大集合代表点下方即可
 * 路径压缩：
 *  1. 节点往上找代表点的过程，把沿途的链都变成币扁平的（路径压缩）
 *  2. 小集合挂在大集合下面， 会让链的层级增长的更慢
 *  3. 如果方法调用很平凡，那么单词调用的代价是 O(1), 两个方法都是如此
 *
 *  常用应用：
 *      1. 解决两大块区域合并的问题
 *      2. 常用在图等领域中
 * @Author 张三金
 * @Date 2021/12/27 0027 9:21
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_UnionFind {

    public static class Node<T> {
        T value;

        public Node (T t) {
            this.value = t;
        }
    }

    public static class UnionSet<T> {
        // 这里对外暴露的是一个泛型结构，并查集里面内部处理时用 Node<V>对象胡处理，这样处理的话就更加的通用
        // 还有一个作用，如果是基础类型的话，比如，传了两个一样的字符串 "ss"（使用者认为是两个）, HashMap 会认为是一个，这样就会满足不了使用者的要求
        HashMap<T, Node<T>> nodes;
        // 元素的祖先是谁
        HashMap<Node<T>, Node<T>> parents;
        // 元素代表的集合大小
        HashMap<Node<T>, Integer> sizeMap;
        // 初始化并查集结构，
        public void UnionSet(List<T> values) {
            nodes = new HashMap<>(values.size());
            parents = new HashMap<>(values.size());
            sizeMap = new HashMap<>(values.size());
            for (T t : values) {
                Node<T> node = new Node<>(t);
                nodes.put(t, node);
                // 初始化时，自己的祖先都指向自己
                parents.put(node, node);
                // 初始化时，自己代表的集合的数量都是1个
                sizeMap.put(node, 1);
            }
        }

        // 找到指定元素的祖先
        public Node<T> findAncestors(Node<T> node) {
            Stack<Node<T>> stack = new Stack<>();
            while (node != parents.get(node)) {
                stack.push(node);
                node = parents.get(node);
            }
            // 如果 node和node的祖先之间还有节点，则将二者之间所有元素(包括node自己)的祖先都指向 node
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), node);
            }
            return node;
        }

        // 判断 两个元素是否在一个集合内
        public boolean isSameSet(T t1, T t2) {
            // 注意这里不能直接使用 parents.get(),, parents 中存的不是祖先，存的是直接父亲，而我们要找到是祖先
            return findAncestors(nodes.get(t1)) == findAncestors(nodes.get(t2));
        }

        public void union(T a, T b) {
            // 注意这里不能直接使用 parents.get(),, parents 中存的不是祖先，存的是直接父亲，而我们要找到是祖先
            Node<T> aHead = findAncestors(nodes.get(a));
            Node<T> bHead = findAncestors(nodes.get(b));
            if (aHead == bHead) {
                // 如果两个人的祖先指向同一个元素，就不需要处理了，说明早就已经在一起了
                return;
            }
            int aSize = sizeMap.get(aHead);
            int bSize = sizeMap.get(bHead);
            // 找出两个元素代表的结合哪个更大，哪个更小
            Node<T> big = aSize > bSize ?aHead:bHead;
            Node<T> small = big == aHead ? bHead:aHead;
            // 把小的挂到大的
            parents.put(small, big);
            // 小的挂到大的上面只有，大的size就会辩护
            sizeMap.put(big, aSize + bSize);
            // 小的size就不需要保存了
            sizeMap.remove(small);
        }
    }
}
