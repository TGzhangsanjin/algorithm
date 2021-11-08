package old.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 * 描述：给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：1）线段的开始和结束位置一定都是整数值 2）线段重合区域的长度必须>=1
 *  返回线段最多重合区域中，包含了几条线段
 * @Author 张三金
 * @Date 2021/3/1 0001 7:21
 * @Company jzb
 * @Version 1.0.0
 */
public class MaxLineCoincide {

    /**
     * 暴力方法：
     *  先求出所有线段开始的最小值和结束的最大值，然后在最小值和最大值之间遍历所有的中点，然后查看有多少根线段覆盖了该中点，求出最大值
     */
    public static int maxCover01(int [][] lines) {
//        int min = Integer.MIN_VALUE;
//        int max = Integer.MAX_VALUE;
        int min = 0;
        int max = 0;

        for (int i = 0; i < lines.length; i++) {
            min = Math.min(lines[i][0], min);
            max = Math.max(lines[i][1], max);
        }
        int cover = 0;
        for (double j = min + 0.5; j < max; j++) {
            int cur = 0;
            for (int k = 0; k < lines.length; k++) {
                if (lines[k][0] < j && lines[k][1] > j) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    /**
     *  思路：构造小根堆，遍历每一条线段，将线段的终点值放入最小堆中，并将堆中小于线段开始值的数据弹出，每次遍历时堆的大小记录下来，最后求最大值
     */
    public static int maxCover02(int[][] lines) {

        Line[] lineArrays = new Line[lines.length];
        for (int i = 0; i < lineArrays.length; i++) {
            lineArrays[i] = new Line(lines[i][0], lines[i][1]);
        }
        // 先对线段按照开始位置的顺序进行排序
        Arrays.sort(lineArrays, new StartComparator());
        // 小根堆
        PriorityQueue<Integer> queue = new PriorityQueue();
        int cover = 0;
        for (int i = 0; i < lineArrays.length; i++) {
            // 把 <= cur.start 的数据都从堆中弹出来
            while(!queue.isEmpty() && queue.peek() <= lineArrays[i].start) {
                queue.poll();
            }
            queue.add(lineArrays[i].end);
            cover = Math.max(cover, queue.size());
        }
        return cover;
    }

    public static int[][] generateLines (int size, int left, int right) {
        // 线段的起点
        int[][] array = new int[size][2];
        for (int i = 0; i < array.length; i++) {
            int a = (int)(Math.random() * (right - left + 1));
            int b = (int)(Math.random() * (right - left + 1));
            if (a == b) {
                b = a + 1;
            }
            array[i][0] = Math.min(a, b);
            array[i][1] = Math.max(a, b);
        }
        return array;
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int oneTimeNum = 100;
        int range = 100;

        for (int i = 0; i < 1; i++) {
            int[][] lines = generateLines(oneTimeNum, 0, 100);
            int ans1 = maxCover01(lines);
            int ans2 = maxCover02(lines);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println("Oops!");
                System.out.println(ans2);
            }
        }

    }

    public static class Line {
        public int start;
        public int end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartComparator implements Comparator<Line> {
        /**
         * 按照线段的开始位置进行顺序排序
         */
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }
}
