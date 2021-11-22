package class04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 双向链表实现队列
 * @Author 张三金
 * @Date 2021/11/22 0022 10:31
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_DoubleLinkedImplQueue {

    public static class MyStack<T> {

        public DoubleNode<T> head;

        public void push (T data) {
            DoubleNode<T> current = new DoubleNode<>(data);
            if (head == null) {
                head = current;
                return;
            }
            DoubleNode<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(current);
            current.setLast(temp);
        }

        public T pop () {
            if (head == null) {
                return null;
            }
            T t = head.getValue();
            if (head.getNext() == null) {
                head = null;
            } else {
                head = head.getNext();
                head.setLast(null);
            }
            return t;
        }

        public boolean isEmpty () {
            return head == null;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int ontTimeNums = 1000;
        int range = 1000;

        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < ontTimeNums; j++) {
                int king = (int) (Math.random() * range) + 1;
                if (myStack.isEmpty()) {
                    myStack.push(king);
                    queue.add(king);
                } else {
                    if (Math.random() < 0.75) {
                        myStack.push(king);
                        queue.add(king);
                    } else {
                        if (!isEqual(myStack.pop(), queue.poll())) {
                            System.out.println("Opps!!!!");
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
