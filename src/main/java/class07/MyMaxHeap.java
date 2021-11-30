package class07;

import utils.ArrayUtil;


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
public class MyMaxHeap<T extends Comparable<T>> {

    private T[] array;

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
        this.array = (T[]) new Object[limit];
        this.heapSize = 0;
    }

    public void add (T data) {
        if (this.heapSize >= this.heapLimit) {
            return;
        }
        this.array[this.heapSize] = data;
        this.heapInsert(this.array, this.heapSize++);
    }

    public T poll () {
        T data = this.array[0];
        ArrayUtil.swapTwoNum(this.array, 0, --this.heapSize);
        this.heapify(this.array, 0, this.heapSize);
        return data;
    }


    /**
     * 数组 arr中现在新加进来的数停在了 index 位置, 依次往上移动
     * 移动到干不掉自己的父节点或者移动到 0， 停止！
     */
    private void heapInsert (T[] arr, int index) {
        while ((index - 1) / 2 > 0) {
            if (arr[index].compareTo(arr[(index - 1) / 2]) > 0) {
                ArrayUtil.swapTwoNum(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * 数组中，index位置所在的数往下沉，当它的孩子不比它大时，停！
     */
    private void heapify (T[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找出左孩子和右孩子中的大者
            int largest = (left + 1) < heapSize && arr[left + 1].compareTo(arr[left]) > 0 ? left + 1 : left;
            // 找出孩子和父亲的大者
            if (arr[largest].compareTo(arr[index]) > 0) {
                ArrayUtil.swapTwoNum(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            } else {
                break;
            }
        }
    }

}
