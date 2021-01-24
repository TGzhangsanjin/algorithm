package class03;


import org.omg.CORBA.Object;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 队列的环形数组实现
 * @Author 张三金
 * @Date 2021/1/24 0024 13:14
 * @Company jzb
 * @Version 1.0.0
 */
public class RingArrayToQueue {

    public static class MyQueue {

        public int[] queue;

        public int size = 10;

        // 当前队里中值的个数
        public int count = 0;
        // 队列头部的下标
        public int beginIndex = 0;

        // 队列尾部的下标
        public int endIndex = 0;

        public MyQueue() {
        }

        /**
         * 生成一个固定大小的队列, 默认是10
         * @param size 队列的大小
         */


        public MyQueue(int size) {
            if (size < 10) {
                size = 10;
            } else {
                this.size = size;
            }
            this.queue =  new int[size];
        }

        public boolean push(int t) {
            if (count == size) {
                return false;
            }
            count ++;
            // 小技巧，先给endIndex 赋值，在去处理endIndex, endIndex 上的值不需要去关心，只是一个标记而已
            queue[endIndex] = t;
            endIndex = endIndex < size - 1 ? endIndex++: 0;
            return true;
        }

        public int pop() {
            if (count == 0) {
                return -1;
            }
            int value = queue[beginIndex];
            count--;
            beginIndex = beginIndex < size - 1 ? beginIndex++: 0;
            return value;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTimeTestNums = 1000;
        int randomRange = 10000;
        lable1:for (int i = 0; i < testTimes; i++) {
            MyQueue myQueue = new MyQueue(10);
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTimeTestNums; j++) {
                int nums = (int) (Math.random() * randomRange) + 1;
                if (queue.isEmpty()) {
                    myQueue.push(nums);
                    queue.add(nums);
                }else {
                    if (Math.random() < 0.5) {
                        if (myQueue.push(nums)) {
                            // 因为myQueue是固定大小的， 所有只有当myQueue添加成功了，采取LinkedList中进行添加元素
                            queue.add(nums);
                        }
                    } else {
                        if (myQueue.count == 0) {
                            // 只有myQueue 中有元素的时候，才一起弹出一个元素来
                            if (!isEqual(myQueue.pop(), queue.poll())) {
                                System.out.println("出错了！！！！！");
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isEqual(int v1, Integer v2) {
        if (v1 == -1 && v2 == null) {
            return true;
        } else if(v1 == -1 || v2 == null) {
            return false;
        }
        return v1 == v2.intValue();
    }
}
