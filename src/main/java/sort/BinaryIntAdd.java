package sort;

/**
 * @Author 张三金
 * @Date 2020/11/15 0015 10:32
 * @Description  计算连个二进制的整数
 * @Version 1.0.0
 */
public class BinaryIntAdd {

    public static void main(String[] args) {
        int[] A = {1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0};
        int[] B = {1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0};
        int[] C = new int[A.length + 1];
        binaryIntAdd(A,B,C);
        for (int i : A) {
            System.out.print(i);
        }
        System.out.print("+");
        for (int i : B) {
            System.out.print(i);
        }
        System.out.print("=");
        for (int i : C) {
            System.out.print(i);
        }
    }

    public static void binaryIntAdd(int[] A, int[] B, int[] C) {
        // 寄存的加数
        int registerAdd = 0;
        for (int i = C.length - 1; i > 0; i--) {
            if (A[i-1] == B[i-1]) {
                // 两个加数相同
                C[i] = registerAdd;
                // 或者registerAdd = B[i];
                registerAdd = A[i-1];
            } else {
                // 两个加数一个为0，一个为1
                C[i] = 1 - registerAdd;
            }
        }
        C[0] = registerAdd;

    }
}
