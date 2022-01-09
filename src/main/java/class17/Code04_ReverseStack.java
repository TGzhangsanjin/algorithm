package class17;

import java.util.Stack;

/**
 * 翻转一个栈，不使用额外的空间复杂度
 * @Author 张三金
 * @Date 2022/1/8 0008 14:01
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_ReverseStack {

    public static void reverse (Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 将最底部的一个栈元素抽出来
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }



    // 栈底的元素移除
    // 上面的元素下沉
    // 将移除的栈底元素返回
    public static int f (Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
    }
}
