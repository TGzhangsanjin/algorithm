package old.class04;

/**
 * 归并排序
 *
 * @Author 张三金
 * @Date 2021/1/31 0031 17:04
 * @Company jzb
 * @Version 1.0.0
 */
public class MergeSort {

    /**
     * 归并排序，递归方式处理
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /**
     * 递归方法
     */
    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + ((right - left ) >> 1);
        process(arr, left, middle);
        process(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    /**
     * 合并左右两边的数组
     */
    public static void merge(int[] arr, int left, int middle,  int right) {
        int[] help = new int[right - left +1];
        // help的下标
        int i = 0;
        // 左边数组的下标
        int p1 = left;
        // 右边数组的下标
        int p2 = middle + 1;
        // p1和p2 都没有越界，则取最小值
        while (p1 <= middle && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++]: arr[p2++];
        }

        //经过上面的一个while循环之后，必有一个下标时已经越界了的，所以下面两个while循环只会有一个会执行，另外一个是不会进循环里面的
        while (p1 <= middle) {
            help[i++] = arr[p1++];
        }
        while(p2 <= right) {
            help[i++] = arr[p2++];
        }
        // 拷贝数组
        for (int j = left; j <= right; j++) {
            arr[j] = help[j - left];
        }
    }

    /**
     * 非递归，迭代的方式处理归并排序
     */
    public static void iteratorMergeSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) {
            int left = 0;
            while (left < N) {
                // 注意，每次循环过后，每mergeSize 个数肯定都是已经排好序的
                if (mergeSize >= N - left) {
                    break;
                }

                int middle = left + mergeSize - 1;
                int right = middle + Math.min(mergeSize, N - middle - 1);
                merge(arr, left, middle, right);
                left = right + 1;
            }

            // 防止溢出
            if (mergeSize > (N >> 1)) {
                break;
            }
            // 步长，每次乘以2
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {

        int testTimes = 1000;
        int arraySize = 1000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] array1 = new int[arraySize];
            int[] array2 = new int[arraySize];
            for (int j = 0; j < arraySize; j++) {
                int num = (int)(Math.random() * range) + 1;
                array1[j] = num;
                array2[j] = num;
            }
            mergeSort(array1);
            iteratorMergeSort(array2);
            for (int j = 0; j < arraySize; j++) {
                if (!(array1[j] == array2[j])) {
                    System.out.println("出错了！！");
                }
            }
        }
    }
}
