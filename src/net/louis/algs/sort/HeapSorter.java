package net.louis.algs.sort;

import net.louis.algs.PriorityQueue;

public class HeapSorter<T> extends SorterTemplate<T> {


    private PriorityQueue<T> priorityQueue ;

    @Override
    public void sort(T[] input) {

        priorityQueue = new PriorityQueue<>(input.length,comparator);

        int cap = input.length;

        for(int k=cap/2;k>=1;k--)
        {
            priorityQueue.sink(k);
        }

        while(cap >1)
        {
            exch(input,1,cap--);
            priorityQueue.sink(1);
        }




    }
}
