package sort;

import java.util.Date;

/**
 *
 * 冒泡排序
 * @Author 张三金
 * @Date 2020/12/18 0018 19:55
 * @Company jzb
 * @Version 1.0.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int [] A = new int[100000];
        for (int i = 1; i < A.length - 1; i++) {
            A[i] = (int) (Math.random() * 100);
        }
        Date startDate = new Date();
        bubbleSort(A);
        Date endDate = new Date();
        System.out.println("sort need time: " + (endDate.getTime() - startDate.getTime()) + "ms");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ",");
        }
    }


    public static void bubbleSort(int [] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            // 每一次循环，都把 arr[0]...arr[arr.length - i] 中最大值推到了arr[arr.length - 1] 的位置
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }

}
