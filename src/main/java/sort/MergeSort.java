package sort;

import java.util.Date;

/**
 * @Author 张三金
 * @Date 2020/11/13 0013 20:21
 * @Company jzb
 * @description  归并排序：递归分治处理，（1） 将数组分成划分为两个大小相同的数组 （2） 将排好序的子数组合并
 * @Version 1.0.0
 */
public class MergeSort {

    public static void main(String[] args) {
        int [] A = new int[10000000];
        for (int i = 0; i < A.length; i++) {
            A[i] = (int) (Math.random() * 100);
//            System.out.print(A[i] + ",");
        }
        Date startDate = new Date();
        mergeSort(A, 0, A.length - 1);
        Date endDate = new Date();
        System.out.println("sort need time: " + (endDate.getTime() - startDate.getTime()) + "ms");
//        for (int i = 0; i < A.length; i++) {
//            System.out.print(A[i] + ",");
//        }
    }

    /**
     * 递归方法：先分解问题，再合并问题
     * @param A 待排序的数组
     * @param left 开始下标
     * @param right 结束下标
     */
    public static void mergeSort(int [] A, int left, int right) {
        if (left < right) {
            // p小于r的时候才有分解的必要
            int middle = (left + right) / 2;
            mergeSort(A,left,middle);
            mergeSort(A,middle+1,right);
            merge(A,left,middle,right);
        }
    }

    /**
     *  输入：数组A中  A[left]到A[middle]已排好序，A[middle+1]到A[right]也已排好序，执行完此方法后，数组A[left]到数组A[right] 均已排好序
     */



    public static void merge(int[] A, int left, int middle, int right) {
        int [] leftArr = new int[middle - left + 1];
        int [] rightArr = new int[right - middle];
        // 将A[p]到A[q]的值复制到left
        for (int i = left; i <= middle; i++) {
            leftArr[i - left] = A[i];
        }
        // 将A[q+1]到A[r]的值复制到left
        for (int i = middle + 1; i <= right; i++) {
            rightArr[i - (middle + 1)] = A[i];
        }
        int i = 0;
        int j = 0;
        for (int k = left; k <= right; k++) {
            // 如果right数组遍历完，或者left的最小的值小于right的最小值
            if (j > (rightArr.length - 1)) {
                A[k] = leftArr[i];
                i++;
            } else if (i > (leftArr.length - 1)){
                A[k] = rightArr[j];
                j++;
            } else if (leftArr[i] < rightArr[j]) {
                A[k] = leftArr[i];
                i++;
            } else {
                A[k] = rightArr[j];
                j++;
            }
        }
    }


}
