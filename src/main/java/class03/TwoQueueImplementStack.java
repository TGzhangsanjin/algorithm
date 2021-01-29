package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 通过队列实现栈， 准备两个队列，
 * （1）push()，往有数据的队列中push一个数据 （当两个队列都为空时，随便往其中一个队列放入一个数据）
 * （2）pop() 时，将有数据的队列A中的数据除了最后一个元素，放到另一个队列B中，然后返回 A.poll()
 *  (3) peek() 时, 将遍历有数据的队列A，将A中排在最后的一个元素返回
 * @Author 张三金
 * @Date 2021/1/29 0029 7:51
 * @Company jzb
 * @Version 1.0.0
 */
public class TwoQueueImplementStack {

    public static class TwoQueueStack {

        public Queue<Integer> queue1;

        public Queue<Integer> queue2;

        public TwoQueueStack() {
            this.queue1 = new LinkedList<>();
            this.queue2 = new LinkedList<>();
        }

        /**
         * 压入一个元素
         * @param value
         */
        public void push(Integer value) {
            if(!queue1.isEmpty()) {
                queue1.add(value);
            } else {
                queue2.add(value);
            }
        }

        /**
         * 弹出一个元素，需要将有数据的队列中的数据放到另一个空队列中去
         * @return
         */
        public Integer pop() {
            if (queue1.isEmpty() && queue2.isEmpty()) {
                return null;
            }
            if (!queue1.isEmpty()) {
                while (queue1.size() > 1) {
                    queue2.add(queue1.poll());
                }
                return queue1.poll();
            } else {
                while(queue2.size() > 1) {
                    queue1.add(queue2.poll());
                }
                return queue2.poll();
            }
        }

        public Integer peek() {
            if (this.isEmpty()) {
                return null;
            }
            Integer value = null;
            if (!queue1.isEmpty()) {
                while (queue1.size() > 1) {
                    queue2.add(queue1.poll());
                }
                value = queue1.peek();
                queue2.add(queue1.poll());
            } else {
                while(queue2.size() > 1) {
                    queue1.add(queue2.poll());
                }
                value = queue2.peek();
                queue1.add(queue2.poll());
            }
            return value;
        }

        public boolean isEmpty() {
            if (this.queue1.isEmpty() && this.queue2.isEmpty()) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTestNums = 1000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            TwoQueueStack twoQueueStack = new TwoQueueStack();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < oneTestNums; j++) {
                int nums = (int) (Math.random() * range) + 1;
                if (twoQueueStack.isEmpty()) {
                    twoQueueStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        twoQueueStack.push(nums);
                        stack.push(nums);
                    } else if (Math.random() < 0.75) {
                        if (!isEqual(twoQueueStack.pop(), stack.pop())) {
                            System.out.println("出错了！");
                        }
                    } else {
                        if (!isEqual(twoQueueStack.peek(), stack.peek())) {
                            System.out.println("出错了！");
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
