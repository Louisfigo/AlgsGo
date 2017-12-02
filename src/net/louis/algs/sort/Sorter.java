package net.louis.algs.sort;

import java.util.Comparator;

public interface Sorter<T> {

    public void sort(T[] input);

    public void setComparator(Comparator<T> comparator);

}
