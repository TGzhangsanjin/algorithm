package class03;


import java.util.Stack;

/**
 * 数组实现栈
 * @Author 张三金
 * @Date 2021/1/24 0024 13:14
 * @Company jzb
 * @Version 1.0.0
 */
public class ArrayToStack {

    public static class MyStack<T> {

        public T[] stack;

        public int count;

        public MyStack() {
            this.stack = (T[]) new Object[10];
        }

        /**
         * 往栈中压入一个元素
         * @param value 压入的值
         * @return 是否压入成功
         */
        public void push(T value) {
            if (count == stack.length - 1) {
                T[] newArray = (T[]) new Object[stack.length * 2];
                System.arraycopy(stack, 0, newArray, 0, stack.length);
                stack = newArray;
            }
            // 先将count的值进行+1， 然后再赋值
            stack[++count] = value;
        }

        /**
         * 栈中弹出一个值
         * @return 返回弹出的值
         */
        public T pop() {
            if (count == 0) {
                return null;
            }
            return stack[count--];
        }

    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTimeTestNums = 1000;
        int randomRange = 10000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < oneTimeTestNums; j++) {
                int nums = (int) (Math.random() * randomRange) + 1;
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {                                                       
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("出错了！！！！！");
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
