package net.louis.algs.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSorter<T> extends SorterTemplate<T>{


    private static final int CUT_OFF = 10;

    private InsertionSorter<T> insertionSorter = new InsertionSorter<>();

    @Override
    public void sort(T[] input) {
        StdRandom.shuffle(input);
        insertionSorter.setComparator(comparator);

    }


    private void sortLow(T[] input,int low,int hi)
    {
        if(hi<=low+CUT_OFF)
        {
            insertionSorter.sort(input,low,hi);
            return;
        }

        int partitionIdx = partition(input,low,hi);

        sortLow(input,low,partitionIdx-1);
        sortLow(input,partitionIdx+1,hi);


    }


    private void sort3Way(T[] input,int low,int hi)
    {
        if(hi<=low+CUT_OFF)
        {
            insertionSorter.sort(input,low,hi);
            return;
        }

        int lowPt = low, hiPt = hi+1;
        int Leq = low, hieq = hi+1;
        T v = input[low];
        while (true) {

            while(less(input[++lowPt],v)) if(lowPt == hi) break;
            while(less(v,input[--hiPt])) if(hiPt == low) break;

            if(lowPt==hiPt && eq(input[lowPt],v))
                exch(input,low,lowPt);

            if(lowPt>=hiPt)
                break;

            exch(input,lowPt,hiPt);

            if(eq(input[lowPt],v))
                exch(input,++Leq,lowPt);

            if(eq(input[hiPt],v))
                exch(input,--hieq,hiPt);


        }


        lowPt= hiPt + 1;
        for (int k = low; k <= Leq; k++)
            exch(input, k, hiPt--);
        for (int k = hi; k >= hieq; k--)
            exch(input, k, lowPt++);

        sort3Way(input, low, hiPt);
        sort3Way(input, lowPt, hi);



    }

    private boolean eq(T left, T right)
    {
        return comparator.compare(left,right) ==0;
    }

    private int partition(T[]input,int low,int hi)
    {
        int lowPt = low;
        int hiPt = hi+1;

        T pv = input[low];
        while(true)
        {
            while(less(input[++lowPt],pv)) if(lowPt == hi) break;
            while(less(pv,input[--hiPt])) if(hiPt==low) break;
            if(lowPt>=hiPt) break;

            exch(input,lowPt,hiPt);

        }

        exch(input,low,hiPt);

        return  hiPt;

    }

}
