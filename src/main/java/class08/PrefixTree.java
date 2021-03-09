package class08;

/**
 * 前缀树的处理
 * @Author 张三金
 * @Date 2021/3/8 0008 8:23
 * @Company jzb
 * @Version 1.0.0
 */
public class PrefixTree {

    /**
     * 定义一个节点类型
     */
    public class Node1{
        // 经过该节点的次数
        public int pass;

        // 以该节点作为字符串结束的次数
        public int end;


        // 节点指向的所有节点
        public Node1[] next;

        public Node1() {
            this.pass = 0;
            this.end = 0;
            // 这里为什么是26呢？因为我们暂且默认数组里面的字符串都是小写字母（对于包含其它字符的情况，后续再讨论）
            /**
             * 0  ....  a
             * 1  ....  b
             * 2  ....  c
             * 3  ....  d
             * .  ..... .
             * .  ..... .
             * 25 ..... z
             *
             * 其中  next[i] == null ，表示 i 方向的路不存在    next[i] != null 表示 i 方向的路存在
             * 例如：next[3] != null, 则表示有一条路径，且路径代表的值即为字符  d
             */
            this.next = new Node1[26];
        }
    }

    public Node1 root;

    /**
     * 往链表中插入一个字符串
     * @param word 待插入的字符串
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return ;
        }
        char[] words = word.toCharArray();
        if (root == null) {
            root = new Node1();
        }
        Node1 node = root;
        node.pass++;
        int path = 0;
        for (int i = 0; i < words.length; i++) {
            path = words[i] - 'a';
            if (node.next[path] == null) {
                node.next[path] = new Node1();
            }
            node.next[path].pass++;
        }
        node.next[path].end++;
    }

    /**
     * 返回word单词加入过几次
     * @param word
     * @return
     */
    public int search(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        char[] words = word.toCharArray();
        Node1 node = root;
        int path = 0;
        for (int i = 0; i < words.length; i++) {
            path = words[i] - 'a';
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }
        // end 的个数，就表示链表中包含word 的个数
        return node.end;
    }

    /**
     * 返回包含指定前缀单词的个数
     * @param prefix 前缀
     * @return 包含个数
     */
    public int prefixNumbers(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return 0;
        }
        char[] words = prefix.toCharArray();
        Node1 node = root;
        int path = 0;
        for (int i = 0; i < words.length; i++) {
            path = words[i] - 'a';
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }
        // 最后一条路径指向的pass节点的数值，即表示有prefix是多少个单词的前缀
        return node.pass;
    }

    /**
     * 往链表中删除一个字符串，所以删除之前要先判断word是否在里面
     * @param word 待插入的字符串
     */
    public void delete(String word) {
        if (search(word) != 0) {
            char[] words = word.toCharArray();

            Node1 node = root;
            node.pass--;
            int path = 0;
            // 如果word不在链表里面是否考虑到
            for (int i = 0; i < word.length(); i++) {
                path = words[i] - 'a';
                // 直接在判断语句里面就把 pass 的值给减了，，
                if (--node.next[path].pass == 0) {
                    // pass减掉后如果为0了，则说明后面的都不需要考虑了，直接删除掉当前path指向的节点即可
                    node.next[path] = null;
                }
                node = node.next[path];
            }
            node.end--;
        }
    }

}
