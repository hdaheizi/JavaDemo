package top.daheizi.commons.stl;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 排行榜接口
 * @param <K>
 * @author daheizi
 * @Date 2017年3月11日 下午9:33:19
 */
public interface IRank<K> extends Iterable<K> {

    /**
     * 向排行榜内添加关键字
     * 若已存在相等的关键字，则添加失败
     * @param key
     * @return 是否添加成功
     * @throws NullPointerException 不接受参数key为null
     * @Date 2017年3月11日 下午9:33:58
     */
    boolean add(K key);

    /**
     * 移除与给定关键字相等的关键字
     * @param key
     * @return 是否找到并移除
     * @throws NullPointerException 不接受参数key为null
     * @Date 2017年3月11日 下午9:34:00
     */
    boolean remove(K key);

    /**
     * 是否包含与给定关键字相等的关键字
     * @param key
     * @return
     * @throws NullPointerException 不接受参数key为null
     * @Date 2017年3月11日 下午9:34:02
     */
    boolean contains(K key);

    /**
     * 获取给定关键字的名次
     * @param key
     * @return 如果包含该关键字，则返回一个正数，即当前名次
     *         如果不包含该关键字，则返回一个负数，
     *         其绝对值为插入该关键字后的名次
     * @throws NullPointerException 不接受参数key为null
     * @Date 2017年3月11日 下午9:34:09
     */
    int getRank(K key);

    /**
     * 返回对应名次的关键字
     * @param kth
     * @return
     * @throws IndexOutOfBoundsException 当名次rank越界时抛出异常
     * @Date 2017年3月11日 下午9:35:11
     */
    K getKth(int kth);

    /**
     * 返回排行榜内存储的数据量
     * @return
     * @Date 2017年3月11日 下午9:34:07
     */
    int size();

    /**
     * 清空排行榜
     * @Date 2017年3月11日 下午9:37:41
     */
    void clear();

    /**
     * 返回排行榜的第一名
     * @return
     * @Date 2017年3月11日 下午9:44:47
     */
    default K getFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return getKth(1);
    }

    /**
     * 返回排行榜的最后一名
     * @return
     * @Date 2017年3月11日 下午9:44:44
     */
    default K getLast() {
        int size = size();
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return getKth(size);
    }

    /**
     * 判断排行榜是否为空
     * @return
     * @Date 2017年3月11日 下午9:34:04
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回一个有序的迭代器
     * @see java.lang.Iterable#iterator()
     */
    Iterator<K> iterator();

    /**
     * 返回一个RankIterator
     * @return
     * @Date 2017年3月13日 下午9:21:10
     */
    RankIterator<K> rankIterator();

    /**
     * 返回一个指定起始名次的RankIterator
     * @param kth [0,size]，调用previous()时返回的第一个关键字的名次为 kth
     *                       调用next()时返回的第一个关键字的名次为 kth+1
     * @return
     * @Date 2017年3月13日 下午9:21:12
     */
    RankIterator<K> rankIterator(int kth);

    /**
     * 返回一个包含所有元素的有序数组
     * @return
     * @Date 2017年3月11日 下午10:34:33
     */
    default Object[] toArray() {
        Object[] a = new Object[size()];
        Iterator<K> it = iterator();
        int index = 0;
        while (it.hasNext()) {
            a[index++] = it.next();
        }
        return a;
    }

    /**
     * 返回一个包含所有元素的有序数组
     * @param a
     * @return
     * @Date 2017年3月11日 下午10:34:36
     */
    @SuppressWarnings("unchecked")
    default K[] toArray(K[] a) {
        int size = size();
        if (a.length < size){
            a = (K[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        Iterator<K> it = iterator();
        int index = 0;
        while (it.hasNext()) {
            a[index++] = it.next();
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
}
