package class04;

import java.util.Stack;

/**
 * 双向列表实现栈
 * @Author 张三金
 * @Date 2021/11/22 0022 11:29
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_DoubleLinkedImplStack {

    public static class MyStack<T> {

        public DoubleNode<T> head;

        public void push (T data) {
            DoubleNode<T> needPush = new DoubleNode<>(data);
            if (head == null) {
                head = needPush;
                return;
            }
            DoubleNode<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(needPush);
            needPush.setLast(current);
        }

        /**
         * 弹出最后一个节点的值，并销毁最后一个节点
         */
        public T pop () {
            if (head == null) {
                return null;
            }
            T t = head.getValue();
            if (head.getNext() == null) {
                head = null;
            } else {
                DoubleNode<T> current = head;
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                t = current.getValue();
                current.getLast().setNext(null);
                current.setLast(null);
            }
            return t;
        }

        public boolean isEmpty () {
            return head == null;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
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
                    if (Math.random() < 0.75) {
                        myStack.push(king);
                        stack.push(king);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("Opps!!!!!!!");
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
