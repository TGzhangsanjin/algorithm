package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

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
    private int size;

    /**
     * 堆元素的比较器
     */
    private Comparator<Inner<T>> comparator;

    /**
     * 手写堆的  poll 方法， pull方法，  heapInsert 方法， heapify 方法，  peek 方法， getAllElement方法
     */

    /**
     * @param c 比较器
     */
    public HeapGreater(Comparator<Inner<T>> c) {
        // 这里后续可以修改为初始化大小为10，后续动态的去增加数组的大小
        heap = new Inner[1000];
        indexMap = new HashMap<>();
        size = 0;
        comparator = c;
    }

    /**
     * 往堆中，插入一个数据，向上遍历，将元素放到正确的位置
     * @param inner
     * @param index
     */
    public void heapInsert(Inner<T> inner, int index) {
        //
        while (comparator.compare(heap[(index - 1) / 2], heap[index]) < 0) {
            // 交换两个位置上的数，同时反向索引表中的索引也需要处理
        }
    }

}
