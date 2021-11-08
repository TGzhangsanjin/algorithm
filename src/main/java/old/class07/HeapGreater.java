package old.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 注意： 因为基础类型的作为HashMap 的键时判断是否重复是根据值来判断的，所以泛型T外面还要在包一层 (这个对加强堆的使用者来说时无感的)
 *
 * 加强堆结构：
 *  1. 首先要有一个正向索引(正常堆结构) 2. 有一个反向索引 3. 一个整型参数维护堆的大小 4. 一个比较器
 * @Author 张三金
 * @Date 2021/3/1 0001 8:37
 * @Company jzb
 * @Version 1.0.0
 */
public class HeapGreater<T> {

    /**
     * 堆结构
     */
    private Inner<T> [] heap;
    /**
     * 反向索引
     */
    private HashMap<Inner<T>, Integer> indexMap;

    /**
     * 维护堆的大小
     */
    private int heapSize;

    /**
     * 堆元素的比较器
     */
    private Comparator<Inner<T>> comparator;

    /**
     * 判断堆是否为空
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * 判断元素是否存在于堆中
     */
    public boolean contains(Inner<T> obj) {
        return indexMap.containsKey(obj);
    }

    /**
     * 堆的大小
     */
    public int size() {
        return heapSize;
    }

    public List<Inner<T>> getAllElements() {
        List<Inner<T>> ans = new ArrayList<>();
        for (Inner<T> inner : heap) {
            ans.add(inner);
        }
        return ans;
    }

    /**
     * @param c 比较器
     */
    public HeapGreater(Comparator<Inner<T>> c) {
        // 这里后续可以修改为初始化大小为10，后续动态的去增加数组的大小
        heap = new Inner[1000];
        indexMap = new HashMap<>();
        heapSize = 0;
        comparator = c;
    }

    public Inner<T> peek () {
        return heap[0];
    }

    public Inner<T> pop() {
        // 将最后一个数和第一个数交换
        Inner<T> o1 = heap[0];
        swap(0, --heapSize);
        // 还需要将元素反向索引表 indexMap 中删除
        indexMap.remove(o1);
        // 这里heap里面的元素就暂时让其放着，其实还要考虑数组的容量需要减少
        heapify(0);
        return o1;
    }

    public void push(Inner<T> object) {
        heap[heapSize++] = object;
        indexMap.put(object, heapSize - 1);
        heapInsert(heapSize - 1);
    }

    public boolean remove(Inner<T> obj) {
        Inner<T> last = heap[heapSize - 1];
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heapSize--;
        if (obj != last) {
            // 就是将最后一个元素放到被删除的那个元素的位置上，然后重新调整位置
            heap[index] = last;
            indexMap.put(last, index);
            resign(index);
        }
        return true;
    }



    /**
     * 将堆中位置为index的数据向上遍历，使其放到正确的位置上
     * @param index
     */
    private void heapInsert(int index) {
        // 自己和父节点比较，自己应该排在前面时，则交换，继续向上遍历
        while (comparator.compare(heap[index], heap[(index - 1) / 2]) < 0) {
            // 交换两个位置上的数，同时反向索引表中的索引也需要处理
            swap((index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    /**
     * 将堆中位置为index的数据向下遍历，使其放到正确的位置上
     */
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找到应该排在前面的那个值的下标
            int best = (left + 1) < heapSize && comparator.compare(heap[left + 1], heap[left]) < 0 ? left + 1: left;
            best = comparator.compare(heap[index],  heap[best]) < 0 ? index: best;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }

    }

    private void resign(int index) {
        heapInsert(index);
        heapify(index);
    }

    private void swap(int i, int j) {
        Inner<T> o1 = heap[i];
        Inner<T> o2 = heap[j];
        heap[i] = o2;
        heap[j] = o1;
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }
}
