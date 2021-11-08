package old.sort;

import java.util.Date;

/**
 * 二分法：
 * （1）在一个有序数组中，找到某个数是否存在
 * （2）在一个有序数组中，找>= 某个数最左侧的位置
 * （3）在一个有序数组中，找<=,某个数最右侧的位置
 * （4）局部最小值问题(一个无序数组，且任意两个数不相等)
 *
 * @Author 张三金
 * @Date 2020/12/18 0018 20:49
 * @Company jzb
 * @Version 1.0.0
 */
public class Divide {

    public static void main(String[] args) {
        int [] A = new int[10];
        for (int i = 1; i < A.length - 1; i++) {
            A[i] = (int) (Math.random() * 1000);
        }
        Date startDate = new Date();
        SelectSort.selectSort(A);
        Date endDate = new Date();
        System.out.println("old.sort need time: " + (endDate.getTime() - startDate.getTime()) + "ms");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ",");
        }
        System.out.println();
        System.out.println(exist(A, 345));
        System.out.println();
        System.out.println(greaterLeft(A, 569));
    }

    /**
     * 在一个有序数组中，找到某个数是否存在
     * @param arr 有序数组
     * @param A 判断数是否存在
     * @return 存在-true; 不存在-false
     */
    public static boolean exist(int[] arr, int A) {
        if (null == arr) {
            return false;
        }
        int left = 0;
        int right = arr.length;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (A > arr[middle]) {
                left++;
            } else if (A < arr[middle]) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }
    /**
     * 在一个有序数组中，找>= 某个数最左侧的位置
     * @param arr 有序数组
     * @param A 判断数是否存在
     * @return 存在-true; 不存在-false
     */
    public static int greaterLeft(int[] arr, int A) {
        if (null == arr) {
            return -1;
        }
        int left = 0;
        int right = arr.length;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (A > arr[middle]) {
                left ++;
            } else {
                right --;
            }
        }
        return middle - 1;
    }

}
