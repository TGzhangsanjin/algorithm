package class14;

import utils.ArrayUtil;

/**
 * 一块金条切成两份，需要花费和金条数值一样的铜板的
 * 比如长度为20的金条，不管怎么切，都需要花费20个铜板，一群人想分整块金条，怎么分最省铜板？
 * 例如：给定数组[10, 20, 30], 代表一共有3个人，整块金条长度为60，今天要分成10，20，30 三个部分
 * 如果先把长度60的金条分成10和50，花费60铜板，再把50的今天分成20和30，花费50，则一共花费 110铜板
 * 如果先把长度60的金条分成30和30，花费60铜板，再把30的金条分成10和20，花费30，则一共花费 90铜板
 *
 * 输入一个数组，返回分割的最小代价
 *
 * 思路：
 *  1. 准备一个小根堆，将所有数据依次放入小根堆中
 *  2. 循环遍历小根堆，假设总代价 ans = 0 每次从小根堆中弹出两个值，两个值之和为 cur,则  ans+=sum, 再将 cur 放入小根堆中
 * @TODO 注明的哈夫曼编码和哈夫曼树
 * @Author 张三金
 * @Date 2021/12/29 0029 10:04
 * @Company jzb
 * @Version 1.0.0
 */
public class Code03_LessMoneySplitGold {

    static class MyHeap {
        // 值
        int[] array;
        // 最大容量
        int limit;
        // 当前堆的大小
        int size;
        public MyHeap (int limit) {
            this.array = new int[limit];
            this.limit = limit;
            this.size = 0;
        }

        public void add(int value) throws Exception {
            if (limit == size) {
                throw new Exception();
            }
            array[size] = value;
            heapInsert(size++);
        }

        public int poll () throws Exception{
            if (this.isEmpty()) {
                throw new Exception();
            }
            int ans = array[0];
            array[0] = array[--size];
            // 基础类型的话这一步没有必要，非基础类型的话这一步可以把没用的对象清空，降低内存
            array[size] = 0;
            heapify(0);
            return ans;
        }

        public boolean isEmpty () {
            return size == 0;
        }

        public int size () {
            return size;
        }

        private void heapInsert (int index) {
            while (array[(index - 1) / 2] > array[index]) {
                ArrayUtil.swapTwoNum(array, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify (int index) {
            int left = (index * 2) + 1;
            while (left < size) {
                // 找出左孩子和右孩子中的更小者
                int minIndex = (left + 1) < size && array[left + 1] < array[left] ? left + 1:left;
                // 当前节点、左孩子、右孩子中的最小者
                minIndex = array[index] <= array[minIndex] ? index : minIndex;
                if (minIndex == index) {
                    // 已经到了应该到的位置了
                    break;
                }
                ArrayUtil.swapTwoNum(array, index, minIndex);
                index = minIndex;
                left = (index * 2) + 1;
            }
        }
    }

    public static int lessMoney (int[] array) throws Exception {
        if (array == null || array.length < 1) {
            return 0;
        }
        MyHeap heap = new MyHeap(array.length);
        for (int i = 0; i < array.length; i++) {
            heap.add(array[i]);
        }
        int current;
        int ans = 0;
        while (heap.size() > 1) {
            int p1 = heap.poll();
            int p2 = heap.poll();
            current = p1 + p2;
            ans += current;
            // 还要再放回去
            heap.add(current);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
//        int[] array = {10,20,30};
//        System.out.println(lessMoney(array));
//        System.out.println(lessMoneyTest(array));
        int testTimes = 1000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] array = generateArray(maxSize, maxValue);
            int min01 = lessMoney(array);
            int min02 = lessMoneyTest(array);
            if (min01 != min02) {
                System.out.println("Opps!!!!!!");
            }
        }
    }


    // For Test
    public static int lessMoneyTest (int[] array) {
        return process(array, 0);
    }

    // 等待合并的数组都在 array 里面，
    // pre 表示之前的合并行为产生了多少总代价
    public static int process (int[] array, int pre) {
        // base case 小于两个数的时候，直接返回之前已经计算好的 pre
        if (array == null || array.length < 2) {
            return pre;
        }
        int min = Integer.MAX_VALUE;
        // 这两个循环就代表，从 length 长度的数组array中选两个数，一共有多少种组合，遍历每一种中和
        // 所有组合的最小组，就是当前递归返回的值
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int current = array[i] + array[j];
                min = Math.min(process(copyAndMergeTwo(array, i, j), pre + current), min);
            }
        }
        return min;
    }

    public static int[] copyAndMergeTwo(int[] array, int i, int j) {
        int index = 0;
        int[] copy = new int[array.length - 1];
        for (int k = 0; k < array.length; k++) {
            if (k != i && k != j) {
                copy[index++] = array[k];
            }
        }
        copy[array.length - 2] = array[i] + array[j];
        return copy;
    }

    public static int[] generateArray (int maxSize, int maxValue) {
        int size = (int) (Math.random() * maxSize) + 2;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int)(Math.random() * maxValue) + 1;
        }
        return array;
    }


}
