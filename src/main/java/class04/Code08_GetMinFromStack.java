package class04;

import java.util.Stack;

/**
 * 问题描述： 实现一个特殊的栈，在基本功能的基础上，在实现返回栈中最小元素的功能
 *  要求：（1） pop、push、getMin()操作的时间复杂度都是 O(1)
 *       (2) 设计的栈类型可以使用现成的栈结构
 * 思路：
 *      1. 准备两个栈，一个数据栈dataStack, 一个最小栈 minStack
 *      2. push时，正常往dataStack压入数据，同时比较 minStack 栈顶的数据a与压入的数据b，谁小就往minStack中压入谁
 *      3. pop时，正常从 dataStack中弹出数据，同时丢掉 minStack中栈顶的一个数据
 *
 * ps: 单纯的用一个最小值是没法维护的
 */
public class Code08_GetMinFromStack {

    public static class MyStack08 {

        public Stack<Integer> dataStack;

        public Stack<Integer> minStack;

        public MyStack08 () {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push (Integer data) {
            if (isEmpty()) {
                minStack.push(data);
            } else {
                minStack.push(Math.min(minStack.peek(), data));
            }
            dataStack.push(data);
        }

        public Integer pop () {
            if (isEmpty()) {
                return null;
            }
            minStack.pop();
            return dataStack.pop();
        }

        public Integer getMin () {
            if (isEmpty()) {
                return null;
            }
            return minStack.peek();
        }
        
        public Integer peek () {
            return dataStack.peek();
        }

        public boolean isEmpty () {
            return dataStack.isEmpty();
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            MyStack08 myStack = new MyStack08();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < oneTimeNums; j++) {
                int nums = (int)(Math.random() * range) + 1;
                if (myStack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("Opps!!!");
                        }
                    }
                    if (!isEqual(myStack.getMin(), findMinInStack(stack))) {
                        System.out.println("Opps2222222!!!  myStack.getMin()===" + myStack.getMin());
                    }
                }

            }
        }
    }

    /**
     * ForTest
     */
    public static Integer findMinInStack (Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        Stack<Integer> copyStack = new Stack<>();
        int min = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            min = Math.min(min, stack.peek());
            copyStack.push(stack.pop());
        }
        while (!copyStack.isEmpty()) {
            stack.push(copyStack.pop());
        }
        return min;
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
