package old.class08;

import java.util.HashMap;

/**
 * 前缀树的处理
 * @Author 张三金
 * @Date 2021/3/8 0008 8:23
 * @Company jzb
 * @Version 1.0.0
 */
public class PrefixTree {

   public static class Trie01{

       public Trie01() {
           this.root = new Node1();
       }

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
//               node.pass++;
               path = words[i] - 'a';
               if (node.next[path] == null) {
                   node.next[path] = new Node1();
               }
               node.next[path].pass++;
               node = node.next[path];
           }
           node.end++;
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
           // 最后一条路径指向的节点的 pass 的数值，即表示prefix是多少个单词的前缀
           return node.pass;
       }

       /**
        * 往链表中删除一个字符串，所以删除之前要先判断word是否在里面
        * @param word 待插入的字符串
        */
       public void delete(String word) {
           // 单词word出现的次数，可能会出现多次
           int wordTime = search(word);
           if (wordTime > 0) {
               char[] words = word.toCharArray();

               Node1 node = root;
               node.pass--;
               int path = 0;
               for (int i = 0; i < words.length; i++) {
                   path = words[i] - 'a';
                   node.next[path].pass -= wordTime;
                   if (node.next[path].pass == 0) {
                       // pass减掉后如果为0了，则说明后面的都不需要考虑了，直接删除掉当前path指向的节点即可
                       node.next[path] = null;
                       return ;
                   }
                   node = node.next[path];
               }
               node.end = 0;
           }
       }
   }

    /**
     * 这是一个对数器的实现
     */
   public static class Right{

       private HashMap<String, Integer> container;

        public Right() {
            this.container = new HashMap<>();
        }
        public void insert (String word) {
            if (!container.containsKey(word)) {
                container.put(word, 1);
            } else {
                container.put(word, container.get(word) + 1);
            }
        }
        public void delete (String word) {
            container.remove(word);
        }

        public int search (String word) {
            if (container.get(word) == null) {
                return 0;
            }
            return container.get(word);
        }

        public int prefixNumbers (String word) {
            int count = 0;
            for (String s : container.keySet()) {
                if (s.startsWith(word)) {
                    count += container.get(s);
                }
            }
            return count;
        }
    }


    /**
     * For Test 随机生成一个小写字母
     * @return
     */
    private static char generateRandomChar() {
        int ss = (int) (Math.random() * 26) + 97;
        return (char) ss;
    }

    /**
     * For Test 生成随机的字符串
     */
    private static String generateRandomString(int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomChar();
        }
        return String.valueOf(ans);
    }

    /**
     * For Test 生成一个随机的字符串数组
     * @param size  字符串数组的大小范围
     */
    private static String[] generateRandomStringArray (int size) {
        String[] strings = new String[(int) (Math.random() * size)];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = generateRandomString((int) (Math.random() * 10) + 1);
        }
        return strings;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNumbers = 100;
        int range = 100;
        for (int i = 0; i < testTimes; i++) {
            Trie01 trie01 = new Trie01();
            Right right = new Right();
            String[] strings = generateRandomStringArray(oneTimeNumbers);
            for (int j = 0; j < strings.length; j++) {
                if (Math.random() < 0.7) {
                    trie01.insert(strings[j]);
                    right.insert(strings[j]);
                } else {
                    trie01.delete(strings[j]);
                    right.delete(strings[j]);
                }
            }
            String[] strings1 = generateRandomStringArray(oneTimeNumbers);
            for (int k = 0; k < strings1.length; k++) {
                if (Math.random() < 0.5) {
                    int s1 = trie01.search(strings1[k]);
                    int s2 = right.search(strings1[k]);
                    if (s1 != s2) {
                        System.out.println("出错了11111！");
                    }
                } else {
                    int s1 = trie01.prefixNumbers(strings1[k]);
                    int s2 = right.prefixNumbers(strings1[k]);
                    if (s1 != s2) {
                        System.out.println(s1 - s2);
//                        System.out.println("出错了2222222222！");
                    }
                }
            }
        }
    }

}
