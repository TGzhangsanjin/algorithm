package class09;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 *  说明：
 *     (1) 单个字符串中，字符由前到后的加到一棵多叉树上
 *     (2) 字符放在路上（即由路径代表），节点上有专属的数据项。（常见的是pass值和end值）
 *     (3) 所有样本都这样添加，如果没有路就新建，如果有路就复用
 *     (4) 沿途节点的pass值增 +1，每个字符串结束时来到的节点的end值 +1
 * @Author 张三金
 * @Date 2021/12/9 0009 9:17
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_TrieTree {

    public static class TrieNode {
        public int pass;
        public int end;
        public TrieNode[] next;

        public TrieNode () {
            this.pass = 0;
            this.end = 0;
            // 表示这里只考虑 26个小写英文字母
            // 其中 next[0] 表示 小写字母 a, next[1] 表示小写字母b. next[2] 表示小写字母c
            // next[i] == null 表示 i 方向的路不存在， next[i] != null 表示 i 方向的路存在
            this.next = new TrieNode[26];
        }
    }

    public static class Trie {
        public TrieNode head;

        public Trie () {
            this.head = new TrieNode();
        }

        public void insert (String word) {
            if (word == null || "".equals(word)) {
                return;
            }
            char[] chars = word.toCharArray();
            head.pass++;
            TrieNode current = head;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (current.next[index] == null) {
                    current.next[index] = new TrieNode();
                }
                current.next[index].pass++;
                current = current.next[index];
            }
            current.end++;
        }

        /**
         * 查询 word 字符串之前插入过几次
         */
        public int search (String word) {
            if (word == null || "".equals(word)) {
                return 0;
            }
            char[] chars = word.toCharArray();
            TrieNode current = head;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (current.next[index] == null) {
                    return 0;
                }
                current = current.next[index];
            }
            // end 的值就是给定字符串出现的次数
            return current.end;
        }

        /**
         * 判断已 prefix 为前缀的字符串有多少个
         * 和 search 代码几乎一样，只是一个search 返回的是所遍历最后一个节点的end值，prefixSearch 返回的是pass值
         */
        public int prefixSearch (String prefix) {
            if (prefix == null || "".equals(prefix)) {
                return 0;
            }
            char[] chars = prefix.toCharArray();
            TrieNode current = head;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (current.next[index] == null) {
                    return 0;
                }
                current = current.next[index];
            }
            return current.pass;
        }

        /**
         * 删除给定字符串, 有多个只会删除一个
         */
        public void delete (String word) {
            if (search(word) < 1) {
                // 先判断保证肯定有，所以后面的循环就不用考虑出现 null 的情况
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode current = head;
            current.pass--;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (--current.next[index].pass == 0) {
                    // 这个直接设置为空的目的是为了防止内存泄漏的问题，如果不设置为空，Trie中就可能会出现很多 pass 和 end 都为 0的数据
                    current.next[index] = null;
                    return;
                }
                current = current.next[index];
            }
            current.end--;
        }

        /**
         * 删除给定字符串, 有多个也全部删除
         *
         */
        public void deleteAll (String word) {
            int times = search(word);
            if (times < 1) {
                // 先判断保证肯定有，所以后面的循环就不用考虑出现 null 的情况
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode current = head;
            current.pass -= times;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                current.next[index].pass -= times;
                if (current.next[index].pass == 0) {
                    current.next[index] = null;
                    return;
                }
                current = current.next[index];
            }
            current.end -= times;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int maxLength = 10;
        for (int i = 0; i < testTimes; i++) {
            Map<String, Integer> map = new HashMap<>();
            Trie trie = new Trie();

            for (int j = 0; j < oneTimeNums; j++) {
                String word = generateString(maxLength);
                double random = Math.random();
                if (random < 0.5) {
//                    System.out.println("插入前trie中的： " + word + "====" + trie.search(word));
//                    System.out.println("插入前map中的： " + word + "====" + map.get(word));
                    trie.insert(word);
                    map.putIfAbsent(word, 0);
                    map.put(word, map.get(word) + 1);
//                    System.out.println("插入后trie中的： " + word + "====" + trie.search(word));
//                    System.out.println("插入后map中的： " + word + "====" + map.get(word));
//                    System.out.println("======================");
                } else {
//                    System.out.println("删除前trie中的： " + word + "====" + trie.search(word));
//                    System.out.println("删除前map中的： " + word + "====" + map.get(word));
                    trie.delete(word);
                    if (map.get(word) != null && map.get(word) > 1) {
                        map.put(word, map.get(word) - 1);
                    } else if(map.get(word) != null && map.get(word) == 1){
                        map.remove(word);
                    }
//                    System.out.println("删除后trie中的： " + word + "====" + trie.search(word));
//                    System.out.println("删除后map中的： " + word + "====" + map.get(word));
//                    System.out.println("======================");
                }
                Integer ans01 = trie.search(word);
                Integer ans02 = map.get(word);
                if (!isEqual(ans01, ans02)) {
                    System.out.println("Opps!!!!");
                    break;
                }
            }


        }
    }

    /**
     *  生成一个随机的字符串
     * @param maxLength 字符串的最大长度
     */
    public static String generateString (int maxLength) {
        String str = "";
        int length = (int)(Math.random() * maxLength) + 1;
        for (int i = 0; i < length; i++) {
            str += (char)(Math.random() * 26 + 'a');
        }
        return str;
    }

    public static boolean isEqual (Integer o1, Integer o2) {
        if (o1 == 0 && o2 == null) {
            return true;
        }
        if (o1 == null && o2 == 1) {
            return true;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }
}
