package class08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 *  描述：增加一个反向索引，为了快速的找到一个对象在数组中的位置，
 *
 *
 *  ps: 如果 T 是非基础类型的话，其实就可以不需要包 HeapInner 者一层了
 */
public class HeapGreater<T> {

    /**
     * 存储数据的集合
     */
    private ArrayList<T> heap;

    /**
     * 反向索引表
     */
    private HashMap<T, Integer> indexMap;

    private int heapSize;

    /**
     * 使用者用于定义堆的比较器
     */
    private Comparator<? super T> comp;

    public boolean isEmpty () {
        return heapSize == 0;
    }

    public int size () {
        return heapSize;
    }

    public boolean contains (T data) {
        return indexMap.get(data) != null;
    }

    public T peek () {
        return heap.get(0);
    }

    public void push (T data) {
        // 这里data的索引并不是最终的结果，在 heapInsert中同时会调整索引
        indexMap.put(data, heapSize);
        heap.add(data);
        heapInsert(heapSize++);
    }

    public T pop () {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    public void remove (T data) {
        // 将要删除的数和最后一个位置数进行交换，
        // 删除最后一个位置的数
        // 调整位置
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(data);
        indexMap.remove(data);
        heap.remove(--heapSize);
        if (data != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(data);
        }
    }

    public void resign (T data) {
        int index = indexMap.get(data);
        heapInsert(index);
        heapify(index);
    }

    public List<T> getAllElements () {
        List<T> ans = new ArrayList<>();
        for (T c: heap) {
            ans.add(c);
        }
        return ans;
    }


    private void heapInsert (int index) {
        // 这个比较的含义就是，如果index 应该排在 (index - 1)/2 的前面，则需要循环往下走
        while (comp.compare(heap.get(index), heap.get((index - 1) >> 1)) < 0) {
            swap(index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    private void heapify (int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 右孩子存在，并且右孩子从排序的角度看是排在左孩子的前面， 则best就是右孩子的下标，否则是左孩子的下标
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1: left;
            // 再一次找出应该排在前面的下标
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best:index;
            if (index == best) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    private void swap (int i, int j) {
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        // set 方法表示更新指定位置上的值，而不是插入
        heap.set(i, t2);
        heap.set(j, t1);
        indexMap.put(t2, i);
        indexMap.put(t1, j);
    }
}
