package class08;

/**
 * 给泛型再包一层，目的是因为对于基础类型作为key时，Map集合是按照其值传递的，
 * 这样在堆中的反向索引表中就无法插入相同元素了
 *
 * ps : 实验后发现没用，基础类型和非基础类型还是要区分一下，，或者基础类型就不让搞重复的
 */
public class HeapInner<T> {

    private T value;

    public HeapInner (T v) {
        value = v;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
