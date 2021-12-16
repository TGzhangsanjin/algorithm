package class12;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 * @Author 张三金
 * @Date 2021/12/16 0016 11:52
 * @Company jzb
 * @Version 1.0.0
 */
public class Code07_PaperFolding {

    /**
     * 按层遍历打印所有节点的凹凸
     * n ：表示这颗二叉树有多少层
     * 思路：头节点（即第一个节点）时凹的，每个节点的左孩子都是凹的，右孩子是凸的
     * @param n
     */
    public static void printAllFolds (int n) {
        // 从第一个节点开始
        process(1, n, true);
    }

    public static void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        // 当前节点的左孩子是凹的
        process(i+1, n, true);
        // 打印当前节点
        System.out.println(down ? "凹":"凸");
        // 当前节点的右孩子是凸的
        process(i+1, n, false);
    }

    public static void main(String[] args) {
        printAllFolds(100);
    }
}
