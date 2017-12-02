package net.louis.algs.sort;

import java.util.Comparator;

public class SorterFactory <T>{

    public Sorter<T> selectionSorter(Comparator<T> comparator)
    {
        Sorter<T> sorter = new SelectionSorter<>();
        sorter.setComparator(comparator);
        return sorter;

    }
}
