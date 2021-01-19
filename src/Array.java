public class Array<E> {
    private E[] data;
    private int size;

    // 构造函数，传入数组的容量 capacity 构造 Array
    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }
    // 无参数的构造函数，默认的数组容量为 capacity: 10
    public Array() {
        this(10);
    }
    // 获取数组中元素个数
    public int getSize() {
        return size;
    }
    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }
    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    // 向所有元素后添加一个元素
    public void addLast(E e) {
        add(size, e);
    }
    // 在数组的头部添加一个新的元素
    public void addFirst(E e) {
        add(0, e);
    }
    // 在第 index 位置插入一个新元素 e
    public void add(int index, E e) {
        if (size == data.length)
            throw new IllegalArgumentException("Add failed. Array is full.");
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        for (int i = size -1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size ++;
    }
    // 获取 index 索引位置的元素
    E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }
    // 修改 index 索引位置的元素为 e
    void set(int index, E e) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }
    // 查找数组中是否有元素 e
    public boolean contains(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }
    // 查找数组中元素 e 所在的索引，如果不存在元素e, 则返回 -1
    public int findIndex(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }
        return -1;
    }
    // 查找数组中元素 e 所在的所有索引，如果不存在元素e, 则返回 [-1]
    // TODO 不能动态改变数组容量，暂时写死为 3
    public int[] findIndexAll(E e) {
        int[] ids = {-1, -1, -1};
        int idsN = 0;
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                ids[idsN++] = i;
        }
        if (ids.length == 0)
            ids[0] = -1;
        return ids;
    }
    // 删除对应索引位置的元素 e
    public E remove(int index) {
        System.out.println(index);
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index >= 0 and index < size.");
        E ret = data[index];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size --;
        data[size] = null;
        return ret;
    }
    // 移除数组中的首位元素
    public E removeFirst() {
        return remove(0);
    }
    // 移除数组的最后一个元素
    public E removeLast() {
        return remove(size -1);
    }
    // 从数组中删除元素 e
    public boolean removeElement(E e) {
        boolean ret = false;
        int index = findIndex(e);
        if (index != -1) {
            remove(index);
            ret = true;
        }
        return ret;
    }
    // 从数组中删除所有的元素 e
    // TODO 删除第一个元素后 size 减小 1，导致 bug
    public boolean removeAllElement(E e) {
        boolean ret = false;
        int[] ids = findIndexAll(e);
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != -1) {
                remove(ids[i]);
                ret = true;
            }
        }
        return ret;
    }
    // 打印数组
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i != size -1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}
