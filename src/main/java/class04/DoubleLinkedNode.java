package class04;

/**
 * 双链表
 * @Author 张三金
 * @Date 2021/11/11 0011 14:18
 * @Company jzb
 * @Version 1.0.0
 */
public class DoubleLinkedNode {

    public static class Node<T> {

        // 值
        private T value;

        // 前置节点
        private Node previous;

        // 后置节点
        private Node next;

        public Node (T data) {
            this.value = data;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

//    public static Node reverse (Node head) {
//
//    }
}
