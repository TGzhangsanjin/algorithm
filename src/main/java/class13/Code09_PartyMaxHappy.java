package class13;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对最大快乐值问题
 * 问题说明：
 *  员工信息的定义如下:
 *  class Employee {
 *     public int happy; // 这名员工可以带来的快乐值
 *     List<Employee> subordinates; // 这名员工有哪些直接下级
 *  }
 *  公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。树的头节点是公司唯一的老板。
 *  除老板之外的每个员工都有唯一的直接上级。 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 *  这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 *      1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 *      2.派对的整体快乐值是所有到场员工快乐值的累加
 *      3.你的目标是让派对的整体快乐值尽量大
 *  给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 *
 *  1. 分析可能性，对于节点x，假设其右 a，b，c三个子节点
 *      (1). x来的最大快乐值 = x.happy + a不来情况下a子树的最大快乐值 + b不来情况下的b子树的最大快乐值 + c不来情况下的c子树的最大快乐值
 *      (2). x不来的最大快乐值 = max{a来, a不来} + max{b来，b不来} + max{c来，c不来}
 *
 *  2. info 信息
 *      (1) x不来情况下的最大快乐值
 *      (2) x来情况下的最大快乐值
 */
public class Code09_PartyMaxHappy {

    public static class Employee {
        int happy;
        List<Employee> subordinates;
        public Employee(int happy) {
            this.happy = happy;
            subordinates = new ArrayList<>();
        }
    }

    public static int maxHappy (Employee head) {
        Info info = process(head);
        return Math.max(info.yes, info.no);
    }

    public static class Info {
        // 来的情况下最大快乐值
        int yes;
        // 不来情况下的最大快乐值
        int no;
        public Info (int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Info process (Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int yes = employee.happy;
        int no = 0;
        if (employee.subordinates != null && employee.subordinates.size() > 0) {
            for (Employee subordinate : employee.subordinates) {
                Info subInfo = process(subordinate);
                yes += subInfo.no;
                no += Math.max(subInfo.yes, subInfo.no);
            }
        }
        return new Info(yes, no);
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int maxLevel = 4;
        int maxValue = 1000;
        // 每个节点最多右多少个孩子
        int maxSize = 5;
        for (int i = 0; i < testTimes; i++) {
            Employee head = generateTree(maxLevel, maxValue, maxSize);
            long start = System.currentTimeMillis();
            int a = maxHappy(head);
            long end = System.currentTimeMillis();
            System.out.println("好的方法耗时：" + (end - start) + "ms");
            int b = maxHappyTest(head);
            end = System.currentTimeMillis();
            System.out.println("差的方法耗时：" + (end - start) + "ms");
            if (a != b) {
                System.out.println("Opps!!!!!!");
            }
        }
    }

    public static int maxHappyTest (Employee employee) {
        if (employee == null) {
            return 0;
        }
        return process2(employee, false);
    }

    // up 的含义表示 employee 的上级来或者不来的情况下能够提供的最大快乐值
    public static int process2 (Employee employee, boolean up) {
        if (up) {
            // 上级来，当前节点肯定不能参加
            int ans = 0;
            for (Employee subordinate : employee.subordinates) {
                // 既然当前不参加，那么下级就可参加可不参加
                ans += Math.max(process2(subordinate, true), process2(subordinate, false));
            }
            return ans;
        } else {
            // 上级不来，当前节点可来可不来
            int yes = employee.happy;
            int no = 0;
            for (Employee subordinate : employee.subordinates) {
                yes += process2(subordinate, true);
                no += process2(subordinate, false);
            }
            return Math.max(yes, no);
        }
    }

    public static Employee generateTree (int maxLevel, int maxValue, int maxSize) {
        return generate(1, maxLevel, maxValue, maxSize);
    }

    // level 当前高度
    // maxLevel 最大高度
    // maxValue 最大值
    // maxSize 最多右多少个孩子
    public static Employee generate (int level, int maxLevel, int maxValue, int maxSize) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Employee employee = new Employee((int)(Math.random() * maxValue));
        int size = (int)(Math.random() * maxSize);
        for (int i = 0; i < size; i++) {
            Employee child = generate(level, maxLevel, maxValue, maxSize);
            if (child != null) {
                employee.subordinates.add(child);
            }
        }
        return employee;
    }
}
