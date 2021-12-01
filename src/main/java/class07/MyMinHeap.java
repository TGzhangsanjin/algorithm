package class07;

import utils.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 小根堆
 *  每个子树的最小值都是头部节点的值
 *
 *  其实如果堆中的某个下标对应的位置错了，， 依次调用 heapInsert 和 heapify 方法就能将其调对（两个方法实际只会执行一个）
 * @Author 张三金
 * @Date 2021/11/30 0030 15:57
 * @Company jzb
 * @Version 1.0.0
 */
public class MyMinHeap {

    private int[] array;

    /**
     * 堆的大小
     */
    private int heapSize;

    /**
     * 堆的最大容量
     */
    private int heapLimit;

    public MyMinHeap (int limit) {
        this.array = new int[limit];
        this.heapSize = 0;
        this.heapLimit = limit;
    }

    public void add (int data) {
        if (this.heapSize == this.heapLimit) {
            return;
        }
        this.array[heapSize++] = data;
        this.heapInsert(this.array, heapSize - 1);
    }

    public int poll () {
        int data = this.array[0];
        ArrayUtil.swapTwoNum(this.array, 0, --heapSize);
        heapify(this.array, 0,  this.heapSize);
        return data;
    }

    public boolean isEmpty () {
        return this.heapSize == 0;
    }

    /**
     * 小根堆： 指定数组中 index 位置的数，然后为了保持堆结构，不断的往上移动
     */
    public void heapInsert (int[] arr, int index) {
        while (index > 0) {
            if (arr[index] < arr[(index - 1) >> 1]) {
                ArrayUtil.swapTwoNum(arr, index, (index - 1) >> 1);
                index = (index - 1) >> 1;
            } else {
                break;
            }
        }
    }

    /**
     *  小根堆： 指定数组中 index 位置的数，为了保持堆的结构，不断的往下沉，
     */
    public void heapify (int[] arr, int index, int heapSize) {
        int left = (index << 1) + 1;
        while (left < heapSize) {
            int minimum = (left + 1) < heapSize && arr[left + 1] < arr[left] ? arr[left + 1]:arr[left];
            minimum = arr[minimum] < arr[index] ? minimum: index;
            if (minimum == index) {
                break;
            }
            ArrayUtil.swapTwoNum(arr, index, minimum);
            index = minimum;
            left = (index << 1) + 1;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            MyMinHeap myMinHeap = new MyMinHeap(10001);
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int j = 0; j < oneTimeNums; j++) {
                int king = (int) (Math.random() * range) + 1;
                if (myMinHeap.isEmpty()) {
                    myMinHeap.add(king);
                    priorityQueue.add(king);
                } else {
                    if (Math.random() < 0.5) {
                        myMinHeap.add(king);
                        priorityQueue.add(king);
                    } else {
                        if (!isEqual(myMinHeap.poll(), priorityQueue.poll())) {
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
