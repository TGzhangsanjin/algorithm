package class03;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双链表实现栈和队列
 * @Author 张三金
 * @Date 2021/1/24 0024 10:40
 * @Company jzb
 * @Version 1.0.0
 */
public class DoubleLinkedListToStackAndQueue {

    private static class DoubleNode<T> {

        private T value; // 节点内容
        private DoubleNode previous; // 上一个节点
        private DoubleNode next; // 下一个节点

        public DoubleNode(T value) {
            this.value = value;
        }
    }

    /**
     * 双链表结构以及相关的方法API
     * @param <T> 双链表节点的具体内容
     */
    private static class DoubleLinkedQueue<T> {
        public DoubleNode<T> head;
        public DoubleNode<T> tail;

        /**
         * 往双链表的头部添加一个节点
         * @param value 节点的内容
         */
        private void addFromHead(T value) {
            DoubleNode<T> current = new DoubleNode<>(value);
            if (head == null) {
                head = current;
                tail = current;
            } else {
                current.next = head;
                head.previous = current;
                head = current;
            }
        }

        /**
         * 往双链表的尾部添加一个节点
         * @param value 添加的节点的具体内容
         */
        private void addFromTail(T value) {
            DoubleNode<T> current = new DoubleNode<>(value);
            if (tail != null) {
                current.previous = tail;
                tail.next = current;
            }
            tail = current;
        }

        /**
         * 从双链表的头部弹出一个节点，并返回头部节点的内容
         * @return 头部节点的内容
         */
        private T popFromHead() {
            if (head == null) {
                return null;
            }
            DoubleNode<T> current = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.previous = null;
            }
            return current.value;
        }

        /**
         * 从双链表的尾部部弹出一个节点，并返回尾部节点的内容
         * @return 头部节点的内容
         */
        private T popFromTail() {
            if (tail == null) {
                return null;
            }
            DoubleNode<T> current = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.previous;
                tail.next = null;
            }
            return current.value;
        }

        private boolean isEmpty() {
            return head == null;
        }
    }

    /**
     * 双链表实现的链表结构
     * @param <T>
     */
    private static class MyQueue<T> {

        public DoubleLinkedQueue<T> queue;

        public MyQueue() {
            this.queue = new DoubleLinkedQueue<T>();
        }
        public void push(T value) {
            queue.addFromHead(value);
        }
        public T pop() {
            return queue.popFromTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /**
     * 双链表实现的栈结构
     * @param <T>
     */
    private static class MyStack<T> {

        private DoubleLinkedQueue<T> stack;

        public MyStack() {
            this.stack = new DoubleLinkedQueue<T>();
        }
        public void push(T value) {
            stack.addFromHead(value);
        }
        public T pop() {
            return stack.popFromHead();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }


    public static void main(String[] args) {
        // 测试的次数
        int testTimes = 1000;
        // 每次测试的数字个数
        int oneTestDataNum = 1000;
        // 数字的范围
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            MyQueue<Integer> myQueue = new MyQueue<>();
            MyStack<Integer> myStack = new MyStack<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = getRandom(range);
                if (myQueue.isEmpty()) {
                    myQueue.push(nums);
                    queue.add(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(nums);
                        queue.add(nums);
                    } else {
                        if (!isEqual(myQueue.pop(), queue.poll())) {
                            System.out.println("出错了！");
                        }
                    }
                }

                if (myStack.isEmpty()) {
                    myStack.push(nums);
                    stack.add(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.add(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("出错了！");
                        }
                    }
                }
            }
        }

        System.out.println("game over!!!!!!");
    }


    public static boolean isEqual(Integer v1, Integer v2) {
        if (v1 == null && v2 == null) {
            return true;
        } else if(v1 == null || v2 == null) {
            return false;
        }
        return v1.equals(v2);
    }

    public static int getRandom(int range) {
        return (int)(Math.random() * range) + 1;
    }
}
