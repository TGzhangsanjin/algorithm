package others;

//import co.paralleluniverse.fibers.Fiber;
//import co.paralleluniverse.fibers.SuspendExecution;
//import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.concurrent.ExecutionException;

/**
 * 纤程的效率高的小例子
 * @Author 张三金
 * @Date 2021/3/28 0028 14:01
 * @Company jzb
 * @Version 1.0.0
 */
public class MyFiber<V> {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        myFiber();
        myThread();
    }



    public static void myThread() throws InterruptedException {
        long start = System.currentTimeMillis();

        int size = 10000;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                calc();
            }
        };

        Thread[] threads = new Thread[size];

        for (int i = 0; i < size; i++) {
            threads[i].start();
        }

        for (int i = 0; i < size; i++) {
            threads[i].join();
        }
        
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }


    public static void myFiber () throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        int size = 10000;
//        Fiber<Void>[] fibers = new Fiber[size];
//        for (int i = 0; i < fibers.length; i++) {
//            fibers[i] = new Fiber<Void>(new SuspendableRunnable() {
//                @Override
//                public void run() throws SuspendExecution, InterruptedException {
//                    calc();
//                }
//            });
//        }
//
//        for (int i = 0; i < fibers.length; i++) {
//            fibers[i].start();
//        }
//        for (int i = 0; i < fibers.length; i++) {
//            fibers[i].join();
//        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }



    static void calc() {
        int result = 0;
        for (int m = 0; m < 10000; m++) {
            for (int i = 0; i < 200; i++) {
                result += i;
            }
        }
    }
}
