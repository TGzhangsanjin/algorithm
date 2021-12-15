package class12;

import class11.BinaryNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的反序列化，
 *  说明：参数 Code02_TreeSerialize 的说明即可
 * @Author 张三金
 * @Date 2021/12/13 0013 10:45
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_TreeDeSerialize {

    /**
     *  先序序列 -- 反序列化
     */
    public static BinaryNode<Integer> buildByPre (String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        String[] array = str.split(",");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            queue.add(array[i]);
        }
        return preb(queue);
    }

    public static BinaryNode<Integer> preb (Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String value = queue.poll();
        if ("null".equals(value)) {
            return null;
        }
        BinaryNode<Integer> node = new BinaryNode<>(Integer.valueOf(value));
        node.setLeft(preb(queue));
        node.setRight(preb(queue));
        return node;
    }

    public static void main(String[] args) {
        BinaryNode<Integer> node = buildByPre("1,2,4,null,null,5,null,null,3,6,7,null,null,null,8,null,null");
        System.out.println();
    }


}
