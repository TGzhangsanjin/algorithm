package class04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 数组实现队列
 * @Author 张三金
 * @Date 2021/11/22 0022 11:50
 * @Company jzb
 * @Version 1.0.0
 */
public class Code07_ArrayImplQueue {

    public static class MyQueue<T> {

        // 记录数组当前存在的记录数
        int size;

        // 记录数据开始的下标
        int start;

        // 记录数据结束的下标
        int end;

        // 数组的大小
        int limit;

        // 数组
        Object[] array;

        public MyQueue (int limit) {
            array = new Object[limit];
            this.limit = limit;
            this.size = 0;
            this.start = 0; // start 是最开始入队列的一个数
            this.end = 0;
        }

        public boolean push (T data) {
            if (size == limit) {
                return false;
            }
            size++;
            array[end] = data; // 代表end - 1 是最后入队列的一个数
            end = end < limit - 1 ? end + 1 : 0;
            return true;
        }

        public T peek () {
            if (size == 0) {
                return null;
            }
            return (T) array[start];
        }

        public T pop () {
            if (size == 0) {
                return null;
            }
            T data = (T) array[start];
            size--;
            start = start < limit - 1 ? start + 1 : 0;
            return data;
        }

        public boolean isEmpty () {
            return size == 0;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int ontTimeNums = 1000;
        int range = 1000;

        for (int i = 0; i < testTimes; i++) {
            MyQueue<Integer> myQueue = new MyQueue<>(100);
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < ontTimeNums; j++) {
                int king = (int) (Math.random() * range) + 1;
                if (myQueue.isEmpty()) {
                    myQueue.push(king);
                    queue.add(king);
                    if (!isEqual(myQueue.peek(), queue.peek())) {
                        System.out.println("Opps0000000000!!!! myQueue ===" + myQueue.peek() + ", queue==" + queue.peek());
                    }
                } else {
                    if (queue.size() >= 100) {
                        if (!isEqual(myQueue.pop(), queue.poll())) {
                            System.out.println("Opps0000000000!!!! myQueue ===" + myQueue.peek() + ", queue==" + queue.peek());
                        }
                    } else {
                        if (Math.random() < 1) {
                            myQueue.push(king);
                            queue.add(king);
                        } else {
                            if (!isEqual(myQueue.pop(), queue.poll())) {
                                System.out.println("Opps!!!!myQueue ===" + myQueue.peek() + ", queue==" + queue.peek());
                            } else {
                                System.out.println("Opps!!!!myQueue ===" + myQueue.peek() + ", queue==" + queue.peek());
                            }
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
