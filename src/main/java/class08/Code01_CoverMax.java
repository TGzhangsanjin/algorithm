package class08;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 线段最大重合数问题
 * 问题描述：
 *  给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 *  规定：1）线段的开始和结束位置一定都是整数值 2）线段重合区域的长度必须 >= 1
 *       返回线段最多重合区域中，包含了几条线段
 *
 *  思路：
 *      (1) 将所有的线段根据开始位置从小到大进行排序
 *      (2) 准备一个小根堆，这个堆用于存线段的末尾
 *      (3) 遍历排序号的每一根线段, 先弹堆中小于当前线段末尾的数据，弹完后将当前线段的末尾加入堆中，当前堆的大小就是要求 Max
 *      (4) 得到每次的 Max，返回最终的 Max
 *  时间复杂度： N * logN
 *   ps: 任何一个重合区域的左边界，必是某根线段的左边界
 * @Author 张三金
 * @Date 2021/12/7 0007 11:14
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_CoverMax {

    public static int coverMax (int[][] m) {
        // 先将二维数组转换成线段数组
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // 将线段数组根据开始位置进行排序
        Arrays.sort(lines, new StartCompare());
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // For Test
    public static int coverMaxTest (int[][] m) {
        // 找到所有线段开始位置和结束位置
        int start = 0;
        int end = 0;
        for (int i = 0; i < m.length; i++) {
            start = Math.min(m[i][0], start);
            end = Math.max(m[i][1], end);
        }
        int max = 0;
        // O(N^2)
        for (double i = start; i < end; i+=1) {
            // 遍历所有的线段，判断有多少根覆盖了 i + 0.5 这个点
            int count = 0;
            for (int j = 0; j < m.length; j++) {
                if (m[j][0] < (i + 0.5) && m[j][1] > (i + 0.5)) {
                    count ++;
                }
            }
            max = Math.max(count, max);
        }
        return max;
    }

    public static class Line {
        public int start;
        public int end;
        public Line (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartCompare implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int oneTimeNums = 1000;
        int start = 0;
        int end = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(oneTimeNums, start, end);
            int ans01 = coverMaxTest(lines);
            int ans02 = coverMax(lines);
            if (ans01 != ans02) {
                System.out.println("Opps!!!!!");
            }
        }

    }

    public static int[][] generateLines (int size, int start, int end) {
        size = (int) (Math.random() * size) + 1;
        int[][] lines = new int[size][2];
        for (int i = 0; i < lines.length; i++) {
            int a = (int) (Math.random() * (end - start + 1));
            int b = (int) (Math.random() * (end - start + 1));
            if (a == b) {
                b = a + 1;
            }
            lines[i][0] = Math.min(a, b);
            lines[i][1] = Math.max(a, b);
        }
        return lines;
    }
}
