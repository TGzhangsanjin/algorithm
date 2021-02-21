package class06;

/**
 * 堆结构
 * 1） 堆结构就是用数组实现的完全二叉树结构
 * 2) 大根堆：完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 * 3) 小根堆：完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
 * 4) 堆结构的 heapInsert与 heapify 操作
 * 5) 堆结构的增大和减少
 * 6) 优先级队列结构（PriorityQueue），就是堆结构
 *
 * 对于堆中的完全二叉树结构：
 * 下标为 i 的节点的左孩子（如果有）则，左孩子的下标为 2i+1, 右孩子(如果有)的下标为（2i+2），其父节点的下标则为 (i-1)/2
 *
 * 7) 如果某个位置上的数在堆结构中的位置不对，则调用 heapInsert 和 heapify 方法就可以让该位置的数放到正确位置上，而且两个方法只有一个会真正的执行
 * @Author 张三金
 * @Date 2021/2/20 0020 20:20
 * @Company jzb
 * @Version 1.0.0
 */
public class MyHeap {

    /**
     * 大根堆结构
     */
    public static class MyMaxHeap{
        // 堆数组
        private int[] heap;
        // 堆数组中维持完全二叉树结构的数据大小
        private int heapSize;
        // 堆数组的最大容量
        private final int limit;

        public MyMaxHeap(int limit) {
            this.limit = limit;
        }

        public void push (int value) {
            if (heapSize == limit) {
                return;
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int res = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return res;
        }

        /**
         * 将数组 heap 中，index位置所在的数下沉，确保以 index 为顶点的子数组维持大根堆结构
         */
        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                // 取出两个孩子中的大者下标
                int largest = (left + 1) < heapSize && heap[left] < heap[left + 1] ? left + 1 : left;
                // 判断是当前下标大，还是孩子大
                largest = heap[largest] > heap[index] ? largest : index;
                if (largest == index) {
                    // 如果孩子不大于 当前的index所在的数则直接返回
                    break;
                }
                // 如果孩子大，则交换，并且继续下沉
                swap(heap, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        /**
         * 大根堆的插入
         */
        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }


}
