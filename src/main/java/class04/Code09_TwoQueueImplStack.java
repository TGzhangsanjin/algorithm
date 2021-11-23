package class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 问题来源： 使用队列实现图的深度优先遍历
 * 两个队列实现栈
 * 思路：
 *      1. 准备两个队列A和B， push时，往有数据的一个队列中push一个数据
 *      2. pop时，将有数据的其中一个队列将数据倒入另外一个队列中，留着最后一个数弹出
 * @Author 张三金
 * @Date 2021/11/23 0023 9:36
 * @Company jzb
 * @Version 1.0.0
 */
public class Code09_TwoQueueImplStack {

    public static class MyStack10<T> {

        public Queue<T> queueA;

        public Queue<T> queueB;

        public MyStack10 () {
            queueA = new LinkedList<>();
            queueB = new LinkedList<>();
        }

        public void push (T data) {
            if (queueB.isEmpty()) {
                queueA.add(data);
            } else {
                queueB.add(data);
            }
        }

        public T pop () {
            if (isEmpty()) {
                return null;
            }
            if (!queueA.isEmpty()) {
                while (queueA.size() > 1) {
                    queueB.add(queueA.poll());
                }
                return queueA.poll();
            } else {
                while (queueB.size() > 1) {
                    queueA.add(queueB.poll());
                }
                return queueB.poll();
            }
        }

        public boolean isEmpty () {
            return queueA.isEmpty() && queueB.isEmpty();
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNums = 10000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            MyStack10<Integer> myStack = new MyStack10<>();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < oneTimeNums; j++) {
                int king = (int)(Math.random() * range) + 1;
                int chance = (int) Math.random();
                if (myStack.isEmpty()) {
                    myStack.push(king);
                    stack.add(king);
                } else {
                    if (Math.random() < chance) {
                        myStack.push(king);
                        stack.push(king);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("Opps");
                        }
                    }
                }
            }
        }
    }





    public static boolean isEqual (Integer o1, Integer o2) {
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
