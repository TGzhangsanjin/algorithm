package class05;

/**
 * 归并排序 时间复杂度 NlogN
 *  思路： 递归实现，将数据分成左右两个部分，左边排好序，右边排好序，然后将左右两边 merge排好序
 * @Author 张三金
 * @Date 2021/11/23 0023 11:41
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_MergeSort {

    public static void mergeSort (int[] array) {
        if (array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    public static void process (int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int middle = left + ((right - left) >> 1);
        process(array, left, middle);
        process(array, middle + 1, right);
        merge(array, left, middle, right);
    }

    /**
     *  将左右两个有序数组进行归并排序处理
     */
    public static void merge (int[] array, int left, int middle, int right) {
        int[] sorted = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = middle + 1;
        int sortedIndex = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            sorted[sortedIndex++] = array[leftIndex] <= array[rightIndex] ? array[leftIndex++]:array[rightIndex++];
        }
        while(leftIndex <= middle) {
            sorted[sortedIndex++] = array[leftIndex++];
        }
        while (rightIndex <= right) {
            sorted[sortedIndex++] = array[rightIndex++];
        }
        // 将排好序的数组拷贝回去
        for (int i = left; i <= right; i++) {
            array[i] = sorted[i - left];
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = getRandomArray(oneTimeNums, range);
            int[] array02 = copyArray(array01);
            mergeSort(array01);
            insertSort(array02);
            for (int j = 0; j < array01.length; j++) {
                if (array01[j] != array02[j]) {
                    System.out.println("Opps!!!!!!");
                }
            }

        }
    }

    // ForTest
    public static void insertSort (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
                j--;
            }
        }
    }

    public static void swap (int[] array, int a, int b) {
        if (a == b || array[a] == array[b]) {
            return;
        }
        array[a] = array[a] ^ array[b];
        array[b] = array[a] ^ array[b];
        array[a] = array[a] ^ array[b];
    }

    public static int[] getRandomArray (int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * range) + 1;
        }
        return array;
    }

    public static int[] copyArray (int[] array) {
        int[] copyArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copyArray[i] = array[i];
        }
        return copyArray;
    }
}
