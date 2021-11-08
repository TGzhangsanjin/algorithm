package old.sort;

import java.util.Date;

/**
 * @Author 张三金
 * @Date 2020/11/13 0013 19:58
 * @Version 1.0.0
 */
public class InsertionSort {

    public static void main(String[] args) {

        int [] A = new int[100000];
        for (int i = 1; i < A.length - 1; i++) {
            A[i] = (int) (Math.random() * 100);
        }
        Date startDate = new Date();
        insertionSort(A);
        Date endDate = new Date();
        System.out.println("old.sort need time: " + (endDate.getTime() - startDate.getTime()) + "ms");
//        for (int i = 1; i < A.length; i++) {
//            System.out.print(A[i] + ",");
//        }

    }

    public static void insertionSort (int [] A) {
        for (int i = 1; i < A.length - 1; i++) {
            int key = A[i];
            int j = i - 1;
            while (j > -1 && A[j] > key) {
                A[j+1] = A[j];
                j = j - 1;
            }
            A[j+1] = key;
        }
    }


}
