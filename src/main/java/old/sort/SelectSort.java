package old.sort;

import java.util.Date;

/**
 * @Author 张三金
 * @Date 2020/11/21 0021 19:36
 * @desciption 选择排序
 * @Version 1.0.0
 */
public class SelectSort {

    public static void main(String[] args) {
        int [] A = new int[100];
        for (int i = 1; i < A.length - 1; i++) {
            A[i] = (int) (Math.random() * 100);
        }
        Date startDate = new Date();
        selectSort(A);
        Date endDate = new Date();
        System.out.println("old.sort need time: " + (endDate.getTime() - startDate.getTime()) + "ms");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ",");
        }
    }

    /**
     * 1、
     */
    public static void selectSort (int [] A) {
        for (int i = 0; i < A.length - 1; i++) {
            int minIndex = findMin(A, i, A.length - 1);
            if (minIndex != i) {
                // 交换minIndex 和 i 两个位置的值
                int middle = A[i];
                A[i] = A[minIndex];
                A[minIndex] = middle;
            }
        }
    }

    /**
     * 找出数组A[start]到A[end] 之间最小元素的下标
     * @return
     */
    public static int findMin(int [] A, int start, int end) {
        if (start == end) {
            return start;
        } else if (start < end) {
            int minNumber = A[start];
            int minIndex = start;
            for (int i = start; i <= end; i++) {
                if (A[i] < minNumber) {
                    minNumber = A[i];
                    minIndex = i;
                }
            }
            return minIndex;
        }
        return -1;
    }
}
