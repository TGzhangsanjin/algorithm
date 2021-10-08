import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author 张三金
 * @Date 2021/9/26 0026 13:55
 * @Company jzb
 * @Version 1.0.0
 */
public class MulitVsSingle_ContextSwitch {

    private static double[] nums = new double[100_000_000];
    private static Random r = new Random();
    private static DecimalFormat df = new DecimalFormat("0.00");

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble();
        }
    }

    /**
     * 单线程的执行
     */
    private static void m1 () {

        long start = System.currentTimeMillis();

        double result = 0.0;
        for (int i = 0; i < nums.length; i++) {
            result += nums[i];
        }

        long end = System.currentTimeMillis();
        System.out.println("m1: " + (end - start) + " result = " + df.format(result));
    }
    static double result1 = 0.0, result2 = 0.0, result = 0.0;

    /**
     * 分成两个线程
     * @throws Exception
     */
    private static void m2 () throws Exception{

        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < nums.length / 2; i++) {
                result1 += nums[i];
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = nums.length / 2; i < nums.length; i++) {
                result1 += nums[i];
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        result = result1 + result2;
        long end = System.currentTimeMillis();
        System.out.println("m2: " + (end - start) + " result = " + df.format(result));
    }

    private static void m3() throws Exception {

        final int threadCount = 64;
        Thread[] threads = new Thread[threadCount];
        double[] results = new double[threadCount];
        final int segmentCount = nums.length / threadCount;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int m = i;

            threads[i] = new Thread(() -> {
                for (int j = m * segmentCount; j < (m + 1) * segmentCount && j < nums.length; j++) {
                    results[m] += nums[j];
                }
                latch.countDown();
            });

        }

        double resultM3 = 0.0;

        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }

        latch.await();
        for (int i = 0; i < results.length; i++) {
            resultM3 += results[i];
        }


        long end = System.currentTimeMillis();

        System.out.println("m3: " + (end - start) + " result = " + df.format(resultM3));
    }


    public static void main(String[] args) throws Exception {
        m1();
        m2();
        m3();
    }
}
