package net.louis.algs.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class MergeSorter<T> extends SorterTemplate<T>{

    private final static int SWITCH_SORT_CUT_OFF = 5;
    private T[] aux ;

    private InsertionSorter<T> insertionSorter = new InsertionSorter<>();

    private void merge(T[]input ,int low ,int mid,int hi)

    {
        int lowPt = low;
        int hiPt = mid+1;

        for(int k=low;k<=hi;k++)
        {
            aux[k] = input[k];
        }

        for(int k=low; k<=hi;k++)
        {
            if(lowPt > mid)
                input[k] = aux[hiPt++];
            else if(hiPt > hi)
                input[k] = aux[lowPt++];
            else if(less(aux[hiPt],aux[lowPt]))
                input[k] = aux[hiPt++];
            else
                input[k] = aux[lowPt++];
        }

    }

    private void mergeSort(T[] input,int low,int hi)
    {
        if(hi<=low) return;
        if(hi-low <=SWITCH_SORT_CUT_OFF)
        {
            insertionSorter.sort(input,low,hi);
            return;
        }
        int mid = low + (hi-low)/2;
        mergeSort(input,mid+1,hi);
        mergeSort(input,low,mid);
        if(!less(input[mid],input[mid+1]))
        merge(input,low,mid,hi);
    }

    private void mergeSortBu(T[] input)
    {
        for (int sz=1;sz<input.length;sz=sz+sz)
        {
            for(int low=0;low<input.length-sz;low += sz+sz)
                merge(input,low,(low+sz-1),Math.min(low+sz+sz-1,input.length-1));
        }
    }

    @Override
    public void sort(T[] input) {

        aux = (T[])new Object[input.length];
        insertionSorter.setComparator(comparator);
        mergeSort(input,0,input.length-1);

    }

    public static void main(String args[])
    {
        Random random = new Random();

        Integer[] input = new Integer[10];


        for(int i=0;i<10;i++)
        {
            input[i] = random.nextInt(10);
        }

        MergeSorter<Integer> mergeSorter = new MergeSorter<>();

        mergeSorter.setComparator(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(input));

        mergeSorter.sort(input);

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        System.out.println(Arrays.toString(input));

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        mergeSorter.sort(input);
        System.out.println(Arrays.toString(input));


    }

}
