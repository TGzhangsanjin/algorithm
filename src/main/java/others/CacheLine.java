package others;

import sun.misc.Contended;

/**
 *
 * 验证缓存行对齐
 * @Author 张三金
 * @Date 2021/3/27 0027 15:20
 * @Company jzb
 * @Version 1.0.0
 */
public class CacheLine {

    public static volatile long[] arr1 = new long[2];

    public static volatile long[] arr2 = new long[16];


    public static void method01 () throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_0000_0000L; i++) {
                arr1[0] = i;
            }
        });


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_0000_0000L; i++) {
                arr1[1] = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }

    public static void method02 () throws InterruptedException {

        // 因为在这台机器上一个缓存行的大小时64字节，而arr[0]和arr[8]隔了64个字节，所以肯定不在同一个缓存行中
        // t1 和 t2 操作的是不同的缓存行数据，不会存在伪共享的问题，所以时间上会更快一些
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_0000_0000L; i++) {
                arr2[0] = i;
            }
        });


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_0000_0000L; i++) {
                arr2[8] = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }

    public static void main(String[] args) throws InterruptedException {
//        method01();
        method02();
    }

}
