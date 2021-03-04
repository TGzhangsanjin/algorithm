package class07;

/**
 * 注意： 因为基础类型的作为HashMap 的键时判断是否重复是根据值来判断的，所以泛型T外面还要在包一层
 * @Author 张三金
 * @Date 2021/3/1 0001 8:44
 * @Company jzb
 * @Version 1.0.0
 */
public class Inner<T> {
    public T value;

    public Inner(T value) {
        this.value = value;
    }
}
