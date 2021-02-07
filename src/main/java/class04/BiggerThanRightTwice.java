package class04;

/**
 * 一个数组arr，求每一个arr[i]的右边有多少个数乘以2之后比arr[i] 小
 * 思路： 本质上就是一个逆序对的问题
 * 同样的： 递归求解，关键在于merge，  merge时需要降序遍历，当右边的数乘以2小于左边的数时，则命中一个，如果相等，则拷贝到help数组中
 * @Author 张三金
 * @Date 2021/2/6 0006 7:29
 * @Company jzb
 * @Version 1.0.0
 */
public class BiggerThanRightTwice {

    public static int getRightTwice(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        return process(array, 0 , array.length - 1);
    }

    public static int process(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        return process(array, left, middle) + process(array, middle + 1, right)
                + merge(array, left, middle, right);
    }

    public static int merge(int[] array, int left, int middle, int right) {

        // 可以在合并之前计算值
        int count = 0;
        int p2 = middle + 1;
//        for (int p1 = left; p1 <= middle; p1++) {
//            while(p2 <= right && array[p1] > (array[p2] * 2)) {
//                p2++;
//            }
//            count += p2 - middle - 1;
//        }

        int[] help = new int[right - left + 1];
        int windowLeft = middle;
        int windowRight = right;
        int helpIndex = help.length - 1;
        while (windowLeft >= left && windowRight > middle) {
            count += array[windowLeft] > array[windowRight] * 2 ? windowRight - middle : 0;
            help[helpIndex--] = array[windowLeft] > array[windowRight] ? array[windowLeft--]: array[windowRight--];
        }
        while (windowLeft >= left){
            help[helpIndex--] = array[windowLeft--];
        }
        while (windowRight > middle) {
            help[helpIndex--] = array[windowRight--];
        }
        // 数组拷贝
        for (int i = left; i <= right; i++) {
            array[i] = help[i - left];
        }
        return count;
    }

    /**
     * For Test
     */
    public static int test(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[i] * 2) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 2000;
        int range = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] array1 = new int[oneTimeNums];
            for (int j = 0; j < oneTimeNums; j++) {
                array1[i] = (int) (Math.random() * range) + 1;
            }
            int[] array2 = copyArray(array1);
            if (getRightTwice(array1) != test(array2)) {
                System.out.println("出错了！！！！！！");
            }
        }
    }

    public static int[] copyArray(int[] array1) {
        int[] array2 = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[i];
        }
        return array2;
    }
}
