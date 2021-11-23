package class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 问题来源： 用栈实现图的宽度优先遍历
 * 两个栈实现队列
 *  思路：
 *      1. 准备两个栈，push栈，和 pop栈
 *      2. push操作时，直接往push栈中压入数据
 *      3. pop时，如果pop栈为空，则将push栈的数据倒入到 pop栈中，然后从pop栈弹出一个数据，并且一次倒完，
 *          如果 pop栈不为空，则直接从pop栈中取数据
 *      ps： 倒数据时遵循两点原则： 1. pop时，如果pop栈不为空，则直接从pop栈中弹出数据
 *                             2. pop时，如果pop栈为空，则将push栈中的所有数据弹出
 * @Author 张三金
 * @Date 2021/11/23 0023 9:44
 * @Company jzb
 * @Version 1.0.0
 */
public class Code10_TwoStackImplQueue {

    public static class MyQueue10<T> {

        public Stack<T> pushStack;

        public Stack<T> popStack;

        public MyQueue10 () {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void push (T data) {
            pushStack.push(data);
            pushToPop();
        }

        public T poll () {
            if (isEmpty()) {
                return null;
            }
            pushToPop();
            return popStack.pop();
        }
        /**
         * 如果pop栈为空，则将push栈中所有的数据都压入到pop栈中
         */
        public void pushToPop () {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }
        public boolean isEmpty () {
            return pushStack.isEmpty() && popStack.isEmpty();
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNums = 10000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            MyQueue10<Integer> myQueue = new MyQueue10<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTimeNums; j++) {
                int king = (int)(Math.random() * range) + 1;
                int chance = (int) Math.random();
                if (myQueue.isEmpty()) {
                    myQueue.push(king);
                    queue.add(king);
                } else {
                    if (Math.random() < chance) {
                        myQueue.push(king);
                        queue.add(king);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
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
