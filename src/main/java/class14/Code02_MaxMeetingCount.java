package class14;

import java.util.Arrays;

/**
 * 最大会议数问题
 * 问题描述：
 *      一些项目要占用一个会议室的宣讲，会议室同一时刻不能容纳两个项目的宣讲，给你每一个项目的开始时间和结束时间，
 *      你来安排宣讲的日程，要求会议室进行宣讲的场次最多，返回最多的宣讲场次
 * 思路：
 *      贪心策略，每次都选择结束时间最早的
 * @Author 张三金
 * @Date 2021/12/28 0028 11:47
 * @Company jzb
 * @Version 1.0.0
 */
public class Code02_MaxMeetingCount {

    static class Project {
        // 项目的开始时间
        int start;
        // 项目的结束时间
        int end;
        public Project (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int maxMeetingCount (Project[] projects) {
        if (projects == null || projects.length < 1) {
            return 0;
        }
        // 按照结束时间进行排序，结束时间早的排在前面
        Arrays.sort(projects, (a, b) -> {
            return a.end - b.end;
        });
        int currentTime = 0;
        int count = 0;
        for (int i = 0; i < projects.length; i++) {
            if (currentTime <=  projects[i].start) {
                count++;
                currentTime = projects[i].end;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            Project[] projects = generateProject(maxSize, maxValue);
            int count01 = maxMeetingCount(projects);
            int count02 = masMeetingCountTest(projects);
            if (count01 != count02) {
                System.out.println("Opps!!!!!!!");
            }
        }
//        Project[] projects = {new Project(1,2), new Project(1,3)};
//        System.out.println(masMeetingCountTest(projects));
    }


    // For Test 纯暴力的方式递归遍历每一种可能
    public static int masMeetingCountTest (Project[] projects) {
        return process(projects, 0, 0);
    }

    // 这个done一定要理解清楚，递归的时候这个done不是父级去收集子级的信息，而是父级将信息传递给子级，最后再一层一层的返回
    // projects: 剩余的项目
    // done: 已经完成的项目个数
    // timeLine: 当前来到的时间点
    // 返回当前能够安排的最多项目宣讲个数
    public static int process (Project[] projects, int done, int timeLine) {
        if (projects == null || projects.length < 1) {
            return done;
        }
        int ans = done;
        for (int i = 0; i < projects.length; i++) {
            if (projects[i].start >= timeLine) {
                int currentCount = process(copyButExcept(projects, i), done + 1, projects[i].end);
                ans = Math.max(currentCount, ans);
            }
        }
        return ans;
    }

    // 去掉 projects 中 i位置的数，然后返回一个新的数组
    public static Project[] copyButExcept (Project[] projects, int i) {
        if (projects == null || projects.length < 1) {
            return null;
        }
        Project[] copy = new Project[projects.length - 1];
        int index = 0;
        for (int j = 0; j < projects.length; j++) {
            if (j != i) {
                copy[index++] = projects[j];
            }
        }
        return copy;
    }

    // 随机生成一个 project 数组
    public static Project[] generateProject (int maxSize, int maxValue) {
        int size = (int) (Math.random() * maxSize) + 1;
        Project[] projects = new Project[size];

        for (int i = 0; i < size; i++) {
            int v1 = (int) (Math.random() * maxValue) + 1;
            int v2 = (int) (Math.random() * maxValue) + 1;
            if (v1 == v2) {
                v1++;
            }
            projects[i] = new Project(Math.min(v1, v2), Math.max(v1, v2));
        }
        return projects;
    }
}
