package class11;

/**
 *  一个证明：
 *      x 是二叉树中的某个节点，该树先序遍历 x 之前的节点集合 A 和该树后序遍历x之后的节点集合 B 的交集  是且仅是 x 节点的所有祖先节点
 *
 *  整个二叉树，根据 x，可以划分为五类，
 *      a. x 所有的父亲节点
 *      b. x 自己
 *      c. x 所有的子节点
 *      d. x 作为左树姿态下的右兄弟们
 *      e. x 作为右树姿态下的左兄弟们
 *  证明：
 *      要证明A和B的交集是且仅是 x 节点所有祖先节点，那么就是要证明上面的 a 在 A和B的交集中， 然后另外四种情况不再A和B的交集中
 *      (1) x 的所有父亲节点，在先序遍历中一定出现在x的前面，即集合A中，后续遍历一定出现在x的后面，即集合B中，所有 x 的所有父亲节点会出现在 A和B的交集中
 *      (3) 先序遍历时，x的所有孩子节点一定出现在 x 之后，也就是不会出现在集合A中，即不会出现在 A和B的交集中
 *      (4) 先序遍历时，"x 作为左树姿态下的右兄弟们" 一定出现在x 的后面，即就是不会出现在集合A中，即不会出现在 A和B的交集中
 *      (5) 后续遍历时，“x 作为右树姿态下的左兄弟们”一定出现在 x 的前面，即不会出现在集合B中，即不会出现在 A和B的交集中
 *
 *
 *
 * @Author 张三金
 * @Date 2021/12/11 0011 9:56
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_OneProveNoCode {
}