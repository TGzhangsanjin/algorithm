package class07;

import utils.ArrayUtil;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 大根堆 -- 每一颗子树最大值都是头部的值
 * 有一个数组，想象成完全二叉树的结构，并且每一颗子树最大值都是头部的值
 * 完全二叉树的一些性质：
 *  (1) 对于下标为 i 的节点， 如果有的话，则其左孩子的下标是 2*i+1, 其右孩子的下标时 2*i+2
 *  (2) 对于下标为 i 的节点， 如果有的话，则其父节点的下标是 (i-1)/2 向下取整
 * @Author 张三金
 * @Date 2021/11/30 0030 10:55
 * @Company jzb
 * @Version 1.0.0
 */
public class MyMaxHeap {

    private int[] array;

    /**
     * 当前堆的大小
     */
    private int heapSize;

    /**
     * 数组的容量
     */
    private int heapLimit;

    public MyMaxHeap (int limit) {
        this.heapLimit = limit;
        this.array = new int[limit];
        this.heapSize = 0;
    }

    public void add (int data) {
        if (this.heapSize >= this.heapLimit) {
            return;
        }
        this.array[this.heapSize] = data;
        this.heapInsert(this.array, this.heapSize++);
    }

    public int poll () {
        int data = this.array[0];
        ArrayUtil.swapTwoNum(this.array, 0, --this.heapSize);
        // 相当于释放最后一个数的内存
//        array[heapSize] = 0;
        this.heapify(this.array, 0, this.heapSize);
        return data;
    }

    public int peek () {
        return this.array[0];
    }

    public boolean isEmpty () {
        return this.heapSize == 0;
    }


    /**
     * 数组 arr中现在新加进来的数停在了 index 位置, 依次往上移动
     * 移动到干不掉自己的父节点或者移动到 0， 停止！
     */
    private void heapInsert (int[] arr, int index) {
        while (index > 0) {
            if (arr[index] > arr[(index - 1) >> 1]) {
                ArrayUtil.swapTwoNum(arr, index, (index - 1) >> 1);
                index = (index - 1) >> 1;
            } else {
                break;
            }
        }
    }

    /**
     * 数组中，index位置所在的数往下沉，当它的孩子不比它大时，停！
     */
    private void heapify (int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找出左孩子和右孩子中的大者
            int largest = (left + 1) < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 找出孩子和父亲的大者
            largest = arr[largest] > arr[index] ? largest: index;
            if (largest == index) {
                break;
            }
            ArrayUtil.swapTwoNum(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;

        for (int i = 0; i < testTimes; i++) {
            // 大根堆 PriorityQueue 不传比较器默认是小根堆
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new MyTestCompare());
            MyMaxHeap myMaxHeap = new MyMaxHeap(1000);
            for (int j = 0; j < oneTimeNums; j++) {
                int king = (int) (Math.random() * range) + 1;
                if (myMaxHeap.isEmpty()) {
                    myMaxHeap.add(king);
                    priorityQueue.add(king);
                } else {
                    if (Math.random() < 0.5) {
                        myMaxHeap.add(king);
                        priorityQueue.add(king);
                    } else {
                        Integer a1 = myMaxHeap.poll();
                        Integer a2 = priorityQueue.poll();
                        if (!isEqual(a1, a2)) {
                            System.out.println("Oppsss!!!!!! myMaxHeap.poll() == " + a1 + ", priorityQueue.poll() == " + a2);
                        }
                    }
                }
            }
        }
    }

    private static class MyTestCompare implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
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
