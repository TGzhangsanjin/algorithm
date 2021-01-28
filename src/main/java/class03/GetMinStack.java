package class03;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再返回栈中最小元素的功能
 * （1）pop、push、getMin 操作的时间复杂度都是O(1)
 * （2）设计的栈类型可以使用现成的栈结构
 *
 *  思路：  提供两个栈，一个用于存放数据的正常操作数据栈A，一个用户存放最小值的栈B，往栈中压入一个元素N的同时，
 *      push操作：比较N与栈B的栈顶元素大小，如果N比B的栈顶元素小，则将N压入栈B中，反之，则往B中再压入一个刚刚取出来的栈顶元素
 *      pop操作： B中的栈顶元素就是对应的A栈中的最小值
 * @Author 张三金
 * @Date 2021/1/28 0028 8:10
 * @Company jzb
 * @Version 1.0.0
 */
public class GetMinStack {

    /**
     * 数据栈
     */
    public Stack<Integer> dataStack = new Stack<>();

    /**
     * 存放最小值的栈
     */
    public Stack<Integer> minStack  = new Stack<>();

    /**
     * 压栈操作
     * @param value 插入栈中的值
     */
    public void push(Integer value) {
        this.dataStack.push(value);
        if (!minStack.isEmpty()) {
            if (minStack.peek() < value) {
                value = minStack.peek();
            }
        }
        minStack.push(value);
    }

    public Integer pop() {
        if (dataStack.isEmpty()) {
            return -1;
        }
        minStack.pop();
        return dataStack.pop();
    }

    /**
     * 获取栈中的最小值，直接peek minStack 的值
     * @return 返回最小值
     */
    public Integer getMin() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTestNums = 1000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            GetMinStack getMinStack = new GetMinStack();
            for (int j = 0; j < oneTestNums; j++) {
                int nums = (int) (Math.random() * range) + 1;
                if (getMinStack.dataStack.isEmpty()) {
                    getMinStack.push(nums);
                } else {
                    if (Math.random() < 0.9) {
                        getMinStack.push(nums);
                    } else {
                        getMinStack.pop();
                    }
                }
                if (!getMinStack.getMin().equals(getMinTest(getMinStack.dataStack))) {
                    System.out.println("出错了！");
                }
            }
        }
    }

    /**
     * 循环遍历栈中元素求最小值
     */
    public static Integer getMinTest(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return -1;
        }
        Integer min = Integer.MAX_VALUE;
        for (Integer integer : stack) {
            if (integer < min) {
                min = integer;
            }
        }
        return min;
    }



}
