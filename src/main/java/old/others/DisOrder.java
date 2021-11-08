package old.others;

/**
 * 用于验证CPU的执行时乱序执行
 * @Author 张三金
 * @Date 2021/3/27 0027 20:05
 * @Company jzb
 * @Version 1.0.0
 */
public class DisOrder {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            // 因为a = 1, x = b, b = 1, y = a; 这四条语句的执行先后顺序是不会影响程序的结果的
            // 如果CPU不是乱序执行的，那么 永远不会出现 x == 0, y == 0的情况，结果却出现了，说明CPU是乱序执行的。
            Thread one = new Thread(() -> {
               a = 1;
               x = b;
            });
            Thread two = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();
            two.start();
            one.join();
            two.join();
            String result = "第" + i + "次（" + x + "=" + x + ", y=" + "y";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            }
        }
    }

}
