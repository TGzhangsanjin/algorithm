package class01;

/**
 * 插入排序， 时间复杂度 O(n^2)
 * 思路： 将第 i+1 个数，插入到 0-i 这一有序列表中，，注意 0-i 是有序的
 * 说明：插入排序的效率依赖于数据状况，如果程序本身就是正序那么程序效率是最高的 O(n), 如果程序本身是逆序，那么效率是最低的 O(n^2)
 * @author zhangsanjin
 */
public class InsertSort {

    public static void main(String[] args) {
        // 写一个冒泡排序的对数器进行验证
        int testTimes = 10000;
        int oneTimeNums = 1000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = randomArray(oneTimeNums, range);
            int[] arr2 = copyArray(arr1);
            insert(arr1);
            BubbleSort.bubble(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println("出错了");
                }
            }
        }
        System.out.println("结束了！！！！");

    }



    public static void insert (int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j-1] > array[j]) {
                swap(array, j, (j-1));
                j--;
            }

        }
    }

    public static void swap (int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    /**
     * 生成一个大小指定、数字范围指定的随机数组
     * @param oneTimeNums 数组指定大小
     * @param range 数字返回指定
     * @return 数组
     */
    public static int[] randomArray (int oneTimeNums, int range) {
        int[] array = new int[oneTimeNums];
        for (int i = 0; i < oneTimeNums; i++) {
            array[i] = (int) (Math.random() * range) + 1;
        }
        return array;
    }

    /**
     * 拷贝一个数组
     * @param array 被拷贝的数组
     * @return 数组
     */
    public static int[] copyArray (int[] array) {
        int[] copy = new int[array.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }


}
