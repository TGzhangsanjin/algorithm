package class01;

import utils.Util01;

/**
 * 认识二分法，在一个有序数组中，找到某个数是否存在
 * @Author 张三金
 * @Date 2021/4/5 0005 19:08
 * @Company jzb
 * @Version 1.0.0
 */
public class KnowDichotomy {


    /**
     * 在一个有序数组中，找到某个数是否存在
     * @param array 有序数组
     * @param number 待判断的是否存在的数
     */
    public static boolean exercise01(int[] array, int number) {
        if (array == null || array.length == 0) {
            return false;
        }
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] == number){
                return true;
            } else if (number > array[middle]) {
                left = middle + 1;
            } else if (number < array[middle]) {
                right = middle - 1;
            }
        }
        return false;
    }

    /**
     * 在一个有序数组中，找 >= 某个数最左侧的位置；
     * @param array 有序数组
     * @param number 某个数
     */
    public static int exercise02 (int[] array, int number) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int index = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] >= number) {
                index = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return index;
    }

    /**
     * 在一个有序数组中，找 <= 某个数最右侧侧的位置；
     * @param array 有序数组
     * @param number 某个数
     */
    public static int exercise03 (int[] array, int number) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int index = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] <= number) {
                index = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return index;
    }

    /**
     * For Test
     */
    public static boolean test01(int[] array, int number) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * For Test
     */
    public static int test02(int[] array, int number) {
        if (array == null || array.length == 0) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= number) {
                return i;
            }
        }
        return -1;
    }

    /**
     * For Test
     */
    public static int test03(int[] array, int number) {
        if (array == null || array.length == 0) {
            return -1;
        }
        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] <= number) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array01 = Util01.randomArray(oneTimeNums, range);
            SelectedSort.sorted(array01);
            int number = (int)(Math.random() * range);
            // 验证 exercise01
//            if (exercise01(array01, number) != test01(array01, number)) {
//                System.out.println("OOPS!!!!!");
//            }
            // 验证 exercise02
//            if (exercise02(array01, number) != test02(array01, number)) {
//                System.out.println("OOPS!!!!!");
//            }
            // 验证 exercise03
            if (exercise03(array01, number) != test03(array01, number)) {
                System.out.println("OOPS!!!!!");
            }
        }
    }
}
