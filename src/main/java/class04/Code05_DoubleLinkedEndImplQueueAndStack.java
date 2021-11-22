package class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 链表实现双端队列和栈
 * 双端队列就是 既可以从头进从头出，也可以从头进，从头出， 维护两个指针，一个 head， 一个 tail
 * @Author 张三金
 * @Date 2021/11/22 0022 11:51
 * @Company jzb
 * @Version 1.0.0
 */
public class Code05_DoubleLinkedEndImplQueueAndStack {

    public static class DoubleEnds<T> {

        public DoubleNode<T> head;

        public DoubleNode<T> tail;

        public void addFromHead (T data) {
            DoubleNode<T> current = new DoubleNode<>(data);
            if (head == null) {
                head = current;
                tail = head;
            } else {
                current.setNext(head);
                head.setLast(current);
                head = current;
            }
        }

        public void addFromTail (T data) {
            DoubleNode<T> current = new DoubleNode<>(data);
            if (head == null) {
                head = current;
                tail = head;
            } else {
                current.setLast(tail);
                tail.setNext(current);
                tail = current;
            }
        }

        public T popFromHead () {
            if (head == null) {
                return null;
            }
            DoubleNode<T> current = head;
            if (head.getNext() == null) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setLast(null);
                current.setNext(null);
            }
            return current.getValue();
        }

        public T popFromTail () {
            if (head == null) {
                return null;
            }
            DoubleNode<T> current = tail;
            if (tail.getLast() == null) {
                head = null;
                tail = null;
            } else {
                tail = tail.getLast();
                tail.setNext(null);
                current.setLast(null);
            }
            return current.getValue();
        }

        public boolean isEmpty () {
            return head == null;
        }
    }

    public static class MyStack05<T> {
        public DoubleEnds<T> node;

        public MyStack05 () {
            node = new DoubleEnds<>();
        }

        public void push (T data) {
            node.addFromHead(data);
        }

        public T pop () {
            return node.popFromHead();
        }

        public boolean isEmpty () {
            return node.isEmpty();
        }
    }

    public static class MyQueue05<T> {
        public DoubleEnds<T> node;

        public MyQueue05 (){
            node = new DoubleEnds<>();
        }
        public void push (T data) {
            node.addFromHead(data);
        }

        public T poll () {
            return node.popFromTail();
        }

        public boolean isEmpty () {
            return node.isEmpty();
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            MyStack05<Integer> myStack = new MyStack05<>();
            Stack<Integer> stack = new Stack<>();
            MyQueue05<Integer> myQueue = new MyQueue05<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTimeNums; j++) {
                int nums = (int) (Math.random() * range) + 1;
                if (myStack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("Opps0000001!!!!");
                        }
                    }
                }

                int numq = (int) (Math.random() * range) + 1;
                if (myQueue.isEmpty()) {
                    myQueue.push(numq);
                    queue.add(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.add(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("Opps000002!!!!");
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
