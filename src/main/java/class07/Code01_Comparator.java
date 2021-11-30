package class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 比较器
 * @Author 张三金
 * @Date 2021/11/29 0029 17:00
 * @Company jzb
 * @Version 1.0.0
 */
public class Code01_Comparator {

    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student (String name, int id, int age) {
            this.name  = name;
            this.id = id;
            this.age = age;
        }
    }

    /**
     * 任何比较器， compareTo 方法里面遵循一个统一的规范
     * 返回负数的时候，认为第一个参数排在前面
     * 返回正数的时候，认为第二个参数排在前面
     * 返回0时，无所谓谁放在前面
     */
    public static class IdAscAgeDescOrder implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            // 按照id升序，id相同则按照age降序
            return o1.id != o2.id ? o1.id - o2.id : o2.age - o1.age;
        }
    }

    public static void main(String[] args) {

        Student student01 = new Student("A", 1, 18);
        Student student02 = new Student("C", 3, 7);
        Student student03 = new Student("B", 3, 18);
        Student student04 = new Student("D", 2, 27);
        Student student05 = new Student("E", 5, 2);

        Student[] students = {student01, student02, student03, student04, student05};
        Arrays.sort(students, new IdAscAgeDescOrder());
        for (Student s: students) {
            System.out.println("姓名: " + s.name + ", id: " + s.id + ", 年龄： " + s.age);
        }

        System.out.println("第二条打印");
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student01);
        studentList.add(student02);
        studentList.add(student03);
        studentList.add(student04);
        studentList.add(student05);
        studentList.sort(new IdAscAgeDescOrder());
        for (Student s: studentList) {
            System.out.println("姓名: " + s.name + ", id: " + s.id + ", 年龄： " + s.age);
        }
        System.out.println("第三条打印");
        // 如果不告诉treeMap 怎么比大小，则会直接报错抛异常
        // reeMap 会根据比较器而不会重复加入
        TreeMap<Student, String> treeMap = new TreeMap<>((a, b) -> a.id - b.id);
        treeMap.put(student01, "我是学生A");
        treeMap.put(student02, "我是学生C");
        treeMap.put(student03, "我是学生B");
        treeMap.put(student04, "我是学生D");
        treeMap.put(student05, "我是学生E");
        for (Student s : treeMap.keySet()) {
            System.out.println("姓名: " + s.name + ", id: " + s.id + ", 年龄： " + s.age);
        }
    }
}
