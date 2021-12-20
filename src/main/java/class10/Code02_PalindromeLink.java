package class10;

import class04.SingleNode;

/**
 * 回文链表
 *  问题描述：给定一个链表的头部 head， 判断该链表是否为回文链表
 *  思路：
 *      (1) 先获取链表的中点或者上中点
 *      (2) 将链表的中的下一个节点设置为空，将中点之后的节点反向连接，比如： 1 -> 2 -> 3 <- 2 <- 1
 *      (3) 同时遍历头结点和尾节点，每次遍历节点的值都相同，一直能到结束，则得出该链表是回文结构
 *      (4) 判断结束后，需要将中点之后的节点给指回去
 * @Author 张三金
 * @Date 2021/12/10 0010 14:51
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_PalindromeLink {


    public static boolean palindromeLink (SingleNode<Integer> head) {
        if (head == null) {
            return false;
        }
        if (head.getNext() == null) {
            return true;
        }
        if (head.getNext().getNext() == null) {
            return head.getValue().equals(head.getNext().getValue());
        }

        SingleNode<Integer> middle = findMiddleNode(head);

        // 中点指向空，将中点之后的节点指向反过来处理
        SingleNode<Integer> previous = null;
        SingleNode<Integer> current = middle;
        SingleNode<Integer> next = middle.getNext();
        while (current != null) {
            current.setNext(previous);
            previous = current;
            current = next;
            if (next != null) {
                next = next.getNext();
            }
        }

        // 从尾节点和头结点同时出发，判断节点值是否相同
        SingleNode<Integer> tail = previous;
        while (head != middle) {
            if (!head.getValue().equals(tail.getValue())) {
                return false;
            }
            head = head.getNext();
            tail = tail.getNext();
        }

        // 需要将链表还原
        SingleNode<Integer> pre001 = null;
        SingleNode<Integer> cur001 = previous;
        SingleNode<Integer> next001 = previous.getNext();
        while (cur001 != null) {
            cur001.setNext(pre001);
            pre001 = cur001;
            cur001 = next001;
            if (next001 != null) {
                next001 = next001.getNext();
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 5;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array = generatePalindromeArray(oneTimeNums, range);
            double random = Math.random();
            // 随机设置一个不是随机数的值
            if (random < 0.5) {
                array[array.length - 1] = -1;
            }
            SingleNode<Integer> head = generateLinkByArray(array);
            if (random < 0.5) {
                if (palindromeLink(head)) {
                    System.out.println("Opps!!!!!");
                }
            } else {
                if (!palindromeLink(head)) {
                    System.out.println("Opps!!!!!");
                }
            }
        }
    }

    public static SingleNode<Integer> findMiddleNode (SingleNode<Integer> head) {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return head;
        }
        if (head.getNext().getNext() == null) {
            return head;
        }
        SingleNode<Integer> slow = head;
        SingleNode<Integer> quick = head;
        while (quick.getNext() != null && quick.getNext().getNext() != null) {
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }

    // For Test 根据一个数组生成一个链表
    public static SingleNode<Integer> generateLinkByArray (int[] array) {
        if (array == null) {
            return null;
        }
        SingleNode<Integer> head = new SingleNode<>(array[0]);
        SingleNode<Integer> current = head;
        for (int i = 1; i < array.length; i++) {
            current.setNext(new SingleNode<>(array[i]));
            current = current.getNext();
        }
        return head;
    }

    // For Test 生成一个回文数组
    public static int[] generatePalindromeArray (int size, int range) {
        int length = (int)(Math.random() * size) + 1;
        if (Math.random() < 0.5) {
            // 数组大小为偶数
            int[] array = new int[length * 2];
            for (int i = 0; i < array.length / 2; i++) {
                array[i] = (int)(Math.random() * range);
                array[array.length - i - 1] = array[i];
            }
            return array;
        } else {
            // 数组大小为奇数
            int[] array = new int[length * 2 + 1];
            for (int i = 0; i < array.length / 2; i++) {
                array[i] = (int)(Math.random() * range);
                array[array.length - i - 1] = array[i];
            }
            // 中点的值也设置一下
            array[array.length / 2] = (int)(Math.random() * range);
            return array;
        }
    }


}
