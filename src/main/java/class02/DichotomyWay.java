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

    public static void main(String[] args) {
        checkFindOne();
    }


}
