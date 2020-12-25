package sort;

import java.util.*;

/**
 * 有一个无序数组，且数组中任意两个数都不相等，返回数组中的一个局部最小值，局部最小值的概念就是某个数小于其相邻的数
 * @Author 张三金
 * @Date 2020/12/22 0022 8:31
 * @Company jzb
 * @Version 1.0.0
 */
public class LocalMin {

    public static void main(String[] args) {

        int length = 20;
        HashSet<Integer> hashMap = new HashSet<>();
        for (int i = 0; i < length; i++) {
            int key = (int )(Math.random() * length + 1);
            hashMap.add(key);
        }
        List<Integer> list = new ArrayList<Integer>();
        for (Integer key: hashMap) {
            list.add(key);
        }
        int [] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        // 打乱
        for (int i = 0; i < arr.length; i++) {
            int index = (int )(Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        System.out.println("打乱后");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("=======");
        System.out.println(localMin(arr));
    }


    /**
     * 有一个无序数组，且数组中任意两个数都不相等，返回数组中的一个局部最小值，局部最小值的概念就是某个数小于其相邻的数
     */
    public static int localMin (int[] arr) {

        if (null == arr) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr[0] < arr[1]) {
            return arr[0];
        }
        // 如果第一个数小于第二个数，或者最后一个数小于倒数第二个数，则直接返回第一个数或者最后一个数
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }
        int left = 0;
        int right = arr.length - 1;
        int middle = 0;
        while (left < right) {
            middle = left + (right - left) / 2; // 中间数
            if (arr[middle] > arr[middle - 1]) {
                right --;
            } else if (arr[middle] > arr[middle + 1]) {
                left ++;
            } else {
                return arr[middle];
            }
        }

        return 0;
    }
}
