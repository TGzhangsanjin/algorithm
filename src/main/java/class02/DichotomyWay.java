package class02;

/**
 * 二分法
 * 常见问题是在一个有序数组中展开搜索，单使用二分法并不一定需要保证数组有序
 * @Author 张三金
 * @Date 2021/11/9 0009 9:00
 * @Company jzb
 * @Version 1.0.0
 */
public class DichotomyWay {

    /**
     * @param array 已经排好序的数组
     * @param m 需要查找的值
     * @return 返回数组下标，如果没有找到，则直接返回 -1
     */
    public static int findOne (int[] array, int m) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (left < 0 || right >= array.length) {
                break;
            }
            int middle = left + ((right - left) >> 1);
            if (array[middle] == m) {
                return middle;
            } else if (m > array[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        if (array[left] == m) {
            return left;
        }
        return -1;
    }

    /**
     * 在有序（顺序）的数组 array 中，找出 >= m 最左侧的位置
     * 一直二分去找，分到最后的那个数，即时要找的数
     * @param array 有序（顺序）数组
     * @param m 某个数
     * @return 指定的数组下标
     */
    public static int greaterThanLeft (int[] array, int m) {
        int left = 0;
        int right = array.length - 1;
        int index = -1; // 记录最左侧的位置
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (array[middle] >= m ) {
                index = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return index;
    }

    /**
     * 在有序（顺序）的数组array中，找出 <= m 最右侧的位置
     * @param array 有序（顺序）数组
     * @param m 指定的某个数
     * @return 指定的数组下标
     */
    public static int lessThanRight (int[] array, int m) {
        int left = 0;
        int right = array.length - 1;
        int index = -1; // 记录最右侧的数的位置
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (array[middle] <= m) {
                index = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        checkFindOne();
        checkGreaterThanLeft();
        checkLessThanRight();
    }

    /**
     * 获取到一个已经排好序的数组
     * @size 大小
     * @range 范围
     * @return 一个已经排好序的数组
     */
    public static int[] sortedArray (int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * range) + 1;
        }
        InsertSort.insert(array);
        return array;
    }

    /**
     * 校验 findOne 方法的对数器
     */
    public static void checkFindOne () {
        int testTimes = 1000;
        int arraySize = 1000;
        int arrayRange = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] sortedArray = sortedArray(arraySize, arrayRange);
            int m = (int)(Math.random() * 1000) + 1;
            int findOneIndex = findOne(sortedArray, m);
            if (findOneIndex > -1) {
                if (m != sortedArray[findOneIndex]) {
                    System.out.println("出错了！！");
                }
            } else {
                for (int i1 = 0; i1 < sortedArray.length; i1++) {
                    if (sortedArray[i1] == m) {
                        findOneIndex = i1;
                    }
                }
                if (findOneIndex > -1) {
                    System.out.println("出错了");
                }
            }
        }

        System.out.println("结束了！！");
    }

    // For Test
    public static void checkGreaterThanLeft () {
        int testTimes = 10000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array = sortedArray(oneTimeNums, range);
            // 申请一个真命天子
            int king = (int)(Math.random() * range) + 1;
            int index = greaterThanLeft(array, king);
            int testIndex = -1;
            for (int j = 0; j < array.length; j++) {
                if (array[j] >= king) {
                    testIndex = j;
                    break;
                }
            }
            if (index != testIndex) {
                System.out.println("Opps!!!!!");
            }
        }
    }

    // For Test
    public static void checkLessThanRight () {
        int testTimes = 10000;
        int oneTimeNums = 1000;
        int range = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array = sortedArray(oneTimeNums, range);
            // 申请一个真命天子
            int king = (int)(Math.random() * range) + 1;
            int index = lessThanRight(array, king);
            int testIndex = -1;
            if (array[array.length - 1] <= king) {
                testIndex = array.length - 1;
            } else {
                for (int j = 0; j < array.length; j++) {
                    if (array[j] > king) {
                        if (j > 0) {
                            testIndex = j - 1;
                        }
                        break;
                    }
                }
            }

            if (index != testIndex) {
                System.out.println("Opps!!!!! ==== testIndex ===" + testIndex + ", index ===" + index);
            }
        }
    }
}
