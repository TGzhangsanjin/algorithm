package class10;

import class04.SingleNode;
import utils.ArrayUtil;

/**
 * 划分链表
 *  问题描述：
 *      将单向链表按某值划分成小、中间相等、右边大的形式，
 *   思路：
 *      1. 申请 6 个额外的变量， 小于区（sHead, sTail）、等于区（eHead, eTail）、大于区（gHead, gTail）
 *      2. 遍历链表中的节点，将每个节点放到 三个区域中
 *      3. 将小于区、等于去、大于区连接起来, 注意需要考虑小于区、等于去和大于区都有可能不存在的情况
 * @Author 张三金
 * @Date 2021/12/20 0020 10:43
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_PartitionLink {

    public static SingleNode<Integer> partitionLink (SingleNode<Integer> head, int king) {
        if (head == null) {
            return null;
        }
        // 每个区域设置一个尾节点的变量的目的是为了能够快速的将新进来的节点给接上
        SingleNode<Integer> sHead = null;
        SingleNode<Integer> sTail = null;
        SingleNode<Integer> eHead = null;
        SingleNode<Integer> eTail = null;
        SingleNode<Integer> gHead = null;
        SingleNode<Integer> gTail = null;
        while (head != null) {
            if (head.getValue() < king) {
                if (sHead == null) {
                    sHead = head;
                    sTail = head;
                } else {
                    sTail.setNext(head);
                    sTail = head;
                }
            } else if(head.getValue() == king){
                if (eHead == null) {
                    eHead = head;
                    eTail = head;
                } else {
                    eTail.setNext(head);
                    eTail = head;
                }
            } else {
                if (gHead == null) {
                    gHead = head;
                    gTail = head;
                } else {
                    gTail.setNext(head);
                    gTail = head;
                }
            }
            head = head.getNext();
        }

        // 不管谁是结尾，都需要将结尾的next 设置为空
        if (gTail != null) {
            gTail.setNext(null);
        } else if(eTail != null){
            eTail.setNext(null);
        } else {
            sTail.setNext(null);
        }

        // 小于区域的尾巴连等于区域的头部， 等于区域的尾巴部连大于区域的头部
        if (sTail != null) {
            sTail.setNext(eHead);
            // 如果等于区为空，那么 eTail 就设置 sTail
            eTail = (eTail == null ? sTail : eTail);
        }
        if (eTail != null) {
            // 小于区域和等于区域不都为空，则用其尾接大于区域的头
            eTail.setNext(gHead);
        }
        return sHead != null ? sHead : (eHead != null ? eHead : gHead);
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int king = (int)(Math.random() * range);
            int[] array01 = ArrayUtil.generateRandomArray(1, oneTimeNums, range);
            int[] array = ArrayUtil.copyArray(array01);
            SingleNode<Integer> head = generateLinkedByArray(array);
            head = partitionLink(head, king);

            int[] partition = partitionArray(array, 0, array.length - 1, king);
            int index = 0;
            while (head != null) {
                if (index < partition[0]) {
                    if (!(head.getValue() < king)) {
                        System.out.println("Opps111!!!");
                    }
                } else if (index > partition[1]) {
                    if (!(head.getValue() > king)) {
                        System.out.println("Opps333!!!!");
                    }
                } else {
                    if (head.getValue() != king) {
                        System.out.println("Opps222!!!!");
                    }
                }
                head = head.getNext();
                index++;
            }
        }

    }


    // ForTest 将数组根据 king 划分为 小于区、等于区、大于区三个部分
    // 可能会出现 partition中 [1] < [0] 的情况，这个时候表示有一个区域是不存在的, 只要后面判断的时候正常判断就行，不影响后续的结果
    public static int[] partitionArray (int[] array, int left, int right, int king) {
        // 4
        //9,5,7,9,6
        int lessIndex = left - 1;
        int moreIndex = right + 1;
        int index = left;
        while (index < moreIndex) {
            if (array[index] < king) {
                ArrayUtil.swapTwoNum(array, index++, ++lessIndex);
            } else if (array[index] > king) {
                ArrayUtil.swapTwoNum(array, index, --moreIndex);
            } else {
                index++;
            }
        }
        return new int[]{lessIndex + 1, moreIndex - 1};
    }

    public static SingleNode<Integer> generateLinkedByArray (int[] array) {
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

    // For Test 将链表转换成数组
    public static int[] changeArrayToLink (SingleNode<Integer> head) {
        int length = 0;
        SingleNode<Integer> current = head;
        while (current != null) {
            length++;
            current = current.getNext();
        }
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = head.getValue();
            head = head.getNext();
        }
        return array;
    }
}
