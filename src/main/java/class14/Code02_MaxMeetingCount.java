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
        Project[] projects = {new Project(1, 99), new Project(99, 100), new Project(100, 200)};
        System.out.println(maxMeetingCount(projects));
    }
}
