package old.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * 两个栈实现队列
 * 使用一个push栈和一个pop栈
 * 实现思路：
 * (1) push时，直接往push栈中压入一个值
 * (2) 弹出时，有一个主要方法，pushToPop() 判断当pop栈为空时，就将push栈中的所有元素放到pop栈中，不为空，则直接从pop栈中弹出一个值
 * @Author 张三金
 * @Date 2021/1/29 0029 7:25
 * @Company jzb
 * @Version 1.0.0
 */
public class TwoStackImplementQueue {

    public static class TwoStackQueue{

        public Stack<Integer> pushStack;

        public Stack<Integer> popStack;

        public TwoStackQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        /**
         * 压入方法，直接往push栈中压入一个值即可
         * @param value
         */
        public void add(Integer value) {
            this.pushStack.push(value);
        }

        /**
         * 弹出前先执行pushToPop 方法
         * @return
         */
        public Integer poll() {
            pushToPop();
            if (popStack.isEmpty()) {
                return null;
            }
            return popStack.pop();
        }

        /**
         * peek方法，返回一个值，但不从栈里面弹出
         * @return
         */
        public Integer peek() {
            if (this.popStack.isEmpty() && this.pushStack.isEmpty()) {
                return null;
            }
            pushToPop();
            return popStack.peek();
        }

        /**
         * 当pop栈为空时，将push栈中的所有元素压入pop栈中
         */
        public void pushToPop() {
            if (!this.popStack.isEmpty()) {
                return;
            }
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTestNums = 1000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            TwoStackQueue twoStackQueue = new TwoStackQueue();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestNums; j++) {
                int nums = (int) (Math.random() * range) + 1;
                if (twoStackQueue.peek() == null) {
                    twoStackQueue.add(nums);
                    queue.add(nums);
                } else {
                    if (Math.random() < 0.9) {
                        twoStackQueue.add(nums);
                        queue.add(nums);
                    } else {
                        if (!isEqual(twoStackQueue.poll(), queue.poll())) {
                            System.out.println("出错了！");
                        }
                    }
                }

            }
        }

    }


    public static boolean isEqual(Integer v1, Integer v2) {
        if (v1 == null && v2 == null) {
            return true;
        } else if(v1 == null || v2 == null) {
            return false;
        }
        return v1.equals(v2);
    }


}
