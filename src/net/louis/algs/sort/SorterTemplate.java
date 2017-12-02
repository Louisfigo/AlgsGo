package net.louis.algs.sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public abstract  class SorterTemplate<T> implements Sorter<T> {


    private Comparator<T> comparator ;

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    protected boolean less(T left,T right)
    {
        return comparator.compare(left,right) < 0;
    }

    protected void exch(T[] input,int leftIdx ,int rightIdx)
    {
        T tmpLeft = input[leftIdx];
        input[leftIdx] = input[rightIdx];
        input[rightIdx] = tmpLeft;
    }

    protected void printInput(T[] input){
        for(T t : input)
        {
            StdOut.print(t + " ");
        }
        StdOut.println();
    }

    protected boolean isSorted(T[] input)
    {
        for(int i=1;i< input.length-1;i++)
        {
            if(less(input[i-1],input[i]))
                return false;
        }


        return true;
    }

}
