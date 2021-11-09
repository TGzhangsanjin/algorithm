package class01;

/**
 * 插入排序， 时间复杂度 O(n^2)
 * 思路： 将第 i+1 个数，插入到 0-i 这一有序列表中，，注意 0-i 是有序的
 * 说明：插入排序的效率依赖于数据状况，如果程序本身就是正序那么程序效率是最高的 O(n), 如果程序本身是逆序，那么效率是最低的 O(n^2)
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] array = {1,2,23,-1,222,23,900};
        insert(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }

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


}
