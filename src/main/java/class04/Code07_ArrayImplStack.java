package class04;

import java.util.Stack;

/**
 * 数组实现栈
 * @Author 张三金
 * @Date 2021/11/22 0022 11:50
 * @Company jzb
 * @Version 1.0.0
 */
public class Code07_ArrayImplStack {

    public static class MyStack<T> {
        
        // 记录数组当前的数据量
        int size = 0;
        
        // 一个只支持最大100 条数据的数组
        Object[] array = new Object[100];

        public boolean push (T data) {
            if (size >= 100) {
                return false;
            }
            array[size++] = data;
            return true;
        }
        
        public T pop () {
            if (size == 0) {
                return null;
            }
            return (T) array[--size];
        }
        
        public boolean isEmpty () {
            return size == 0;
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNums = 100;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < oneTimeNums; j++) {
                int king = (int) (Math.random() * range) + 1;
                if (myStack.isEmpty()) {
                    myStack.push(king);
                    stack.push(king);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(king);
                        stack.push(king);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("Opps!!!");
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
