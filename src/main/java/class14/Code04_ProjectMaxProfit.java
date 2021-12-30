package class14;

import utils.ArrayUtil;

import java.util.Comparator;

/**
 * 输入：正数数组costs、正数数组profits、正数K、正数M  (正整数很关键)
 * costs[i] 表示 i号项目的花费
 * profits[i] 表示 i 号项目在扣除花费之后还能挣到的前（纯利润）
 * K 表示只能串行的最多做K个项目
 * M 表示初始资金
 * 说明： 每做完一个项目，马上可以获得收益，可以支持你去做下一个项目，不能并行的做项目
 * 输出： 获得的最大收益
 *
 * 贪心策略： 优先做能做的利润最大的项目（这个很明显就是一个自然智慧的策略）
 *
 * 思路：遍历K次，每次遍历，都从所有剩下的能做的项目中找出来一个收益最大的做，
 * 步骤：
 *  1. 将所有的项目按照花费排序放入一个小根堆 minHeap，，再准备一个按照profits排序的一个空的大根堆
 *  2. 开始遍历k次
 *  3. 每次遍历，从小根堆中取出能做的项目放入大根堆中，放完之后，从大根堆中弹出来的一个项目就是当前应该做的项目
 *  ps: 两个数组都是正整数数组很关键，应为这样时会保证每次做完一个任意项目后的W都是越来越多的
 * @Author 张三金
 * @Date 2021/12/29 0029 19:26
 * @Company jzb
 * @Version 1.0.0
 */
public class Code04_ProjectMaxProfit {

    static class Program {
        int cost;
        int profit;
        public Program (int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    static class MyHeap<T> {
        // 数组
        Object[] array;
        // 数组最大限制
        int limit;
        // 堆的大小
        int size;
        // 比较器
        Comparator<? super T> comparator;
        public MyHeap (int limit, Comparator<? super T> comparator) {
            this.limit = limit;
            this.size = 0;
            array = new Object[limit];
            this.comparator = comparator;
        }

        public void add (T value) {
            if (size == limit) {
                return;
            }
            array[size] = value;
            heapInsert(size++);
        }

        public T poll () {
            if (isEmpty()) {
                return null;
            }
            T ans = (T) array[0];
            array[0] = array[--size];
            // 释放内存
            array[size] = null;
            heapify(0);
            return ans;
        }

        public T peek () {
            if (isEmpty()) {
                return null;
            }
            return (T) array[0];
        }

        public int size () {
            return size;
        }

        public boolean isEmpty () {
            return size == 0;
        }

        // 往上提
        private void heapInsert (int index) {
            // 含义就是 如果 index 位置上的数应该排在 (index - 2) / 2 前面
            while(comparator.compare((T)array[index], (T) array[(index - 1) / 2]) < 0) {
                ArrayUtil.swapTwoNum(array, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 往下沉
        private void heapify (int index) {
            int left = index * 2 + 1;
            while (left < size) {
                // 左右孩子找到一个应该排在前面的下标
                int temp = left + 1 < size && (comparator.compare((T)array[left + 1], (T) array[left]) < 0) ? left + 1 : left;
                // 在和当前比，找到应该排在前面的下标
                temp = comparator.compare((T) array[temp], (T)array[index]) < 0 ? temp : index;
                if (index == temp) {
                    return;
                }
                ArrayUtil.swapTwoNum(array, index, temp);
                index = temp;
                left = index * 2 + 1;
            }
        }
    }

    // 返回最大的收益，如果一个项目都做不了，则返回初始资金
    public static int maxProfit (int[] costs, int[] profits, int k, int m) {
        // 花费的小根堆
        MyHeap<Program> minHeap = new MyHeap<>(1000, (a, b) -> {
           return a.cost - b.cost;
        });
        // 利润的大根堆
        MyHeap<Program> maxHeap = new MyHeap<>(1000, (a, b) -> {
            return b.profit - a.profit;
        });
        // 先将所有数据放入小根堆中
        for (int i = 0; i < costs.length; i++) {
            minHeap.add(new Program(costs[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            // 将所有能做的项目都放到最大堆里面
            while (!minHeap.isEmpty() && minHeap.peek().cost <= m) {
                maxHeap.add(minHeap.poll());
            }
            if (maxHeap.isEmpty()) {
                return m;
            }
            // 每次从最大堆中弹出一个
            m += maxHeap.poll().profit;
        }
        return m;
    }

    public static void main(String[] args) {
        boolean success = true;
        int testTimes = 10000;
        int size = 5;
        int maxValue = 10;
        for (int i = 0; i < testTimes; i++) {
            int[] costs = ArrayUtil.generateRandomArray03(size, maxValue);
            int[] profits = ArrayUtil.generateRandomArray03(size, maxValue);
            int k = (int)(Math.random() * 10) + 1;
            int m = (int)(Math.random() * 5) + 1;
            int profit01 = maxProfit(costs, profits, k, m);
            int profit02 = maxProfitTest(costs, profits, k, m);
            if (profit01 != profit02) {
                success = false;
                System.out.println("Opps!");
            }
        }
        if (success) {
            System.out.println("Success!!!");
        }

    }


    public static int maxProfitTest (int[] costs, int[] profits, int k, int m) {
        Program[] programs = new Program[costs.length];
        // 先将所有数据放入小根堆中
        for (int i = 0; i < costs.length; i++) {
            programs[i] = new Program(costs[i], profits[i]);
        }
        return process(programs, k, m);
    }

    // programs, 剩下的能做的项目
    // canDo 还能做多少个项目
    // m 之前累计的资金
    public static int process (Program[] programs, int canDo, int m) {
        // baseCase
        if (canDo == 0 || programs.length == 0) {
            return m;
        }
        int max = m;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].cost > m) {
                continue;
            }
            int current = process(copyExcept(programs, i), canDo - 1, m + programs[i].profit);
            max = Math.max(current, max);
        }
        return max;
    }

    public static Program[] copyExcept (Program[] programs, int i) {
        Program[] copy = new Program[programs.length - 1];
        int index = 0;
        for (int j = 0; j < programs.length; j++) {
            if (i != j) {
                copy[index++] = programs[j];
            }
        }
        return copy;
    }

}
