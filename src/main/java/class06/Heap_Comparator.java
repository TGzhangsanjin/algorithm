package class06;

import java.util.*;

/**
 * 比较器
 * @Author 张三金
 * @Date 2021/2/20 0020 19:31
 * @Company jzb
 * @Version 1.0.0
 */
public class Heap_Comparator {


    public static class Student{
        private String name;
        private int age;
        private int code;
        public Student(String name, int code, int age) {
            this.name = name;
            this.age = age;
            this.code = code;
        }
    }

    public static class MyComparator implements Comparator<Student> {

        /**
         * code小的排前面，code相同时，age大的排在前面
         *
         * 任何比较器
         * compare 方法里面，遵循一个统一的标准
         * 返回负数的时候，认为第一个参数应该排在前面
         * 返回正数的时候，认为第一个参数应该排在后面
         * 返回为0的时候，认为无所谓谁放在前面
         */
        @Override
        public int compare(Student o1, Student o2) {
            return o1.code == o2.code ? (o2.age - o1.age) : (o1.code - o2.code);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("A", 3, 41);
        Student s2 = new Student("B", 1, 48);
        Student s3 = new Student("C", 2, 71);
        Student s4 = new Student("D", 3, 18);
        Student s5 = new Student("E", 3, 27);
        Student s6 = new Student("F", 3, 6);
        int[] intArray = new int[] {3,4,5,7,123,2,456};
        // 如果是系统无法比较的自定义的类型数组，参数中需要传入一个比较器
        Arrays.sort(intArray);
        System.out.println("第一次打印：");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + ",");
        }
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);
        studentList.add(s6);
        studentList.sort(new MyComparator());
        System.out.println();
        System.out.println("第二次打印：");
        for (Student s: studentList) {
            System.out.println(s.name + "," + s.code + ", " + s.age);
        }

        System.out.println("第三次打印：");
        s1 = new Student("A", 3, 41);
        s2 = new Student("B", 1, 48);
        s3 = new Student("C", 2, 71);
        s4 = new Student("D", 3, 18);
        s5 = new Student("E", 3, 27);
        s6 = new Student("F", 3, 27);
        TreeMap<Student, String> treeMap = new TreeMap<>(new MyComparator());
        treeMap.put(s1, "A");
        treeMap.put(s2, "B");
        treeMap.put(s3, "C");
        treeMap.put(s4, "D");
        treeMap.put(s5, "E");
        treeMap.put(s6, "F");
        // 注意: 当往treeMap中加入数据时，如果 MyComparator 判断treeMap 中已经存在一条相同的记录时，则系统则不会加入该条记录
        // 注意：这里和 HashMap 不同，HashMap 是覆盖，TreeMap 这里则不会覆盖
        for (Student s: treeMap.keySet()) {
            System.out.println(s.name + "," + s.code + ", " + s.age);
        }
    }
}
