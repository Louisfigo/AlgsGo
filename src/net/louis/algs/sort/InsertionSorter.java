package net.louis.algs.sort;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class InsertionSorter<T> extends SorterTemplate<T>{

    @Override
    public void sort(T[] input) {

        for(int i=1;i< input.length;i++)
        {
            T tobeInerted = input[i];
            int j=i;
            while(j> 0 && less(tobeInerted,input[j-1]))
            {
                input[j] = input[j-1];
                j--;
            }

            input[j] = tobeInerted;


        }

    }

    public void sort(T[] input,int low,int hi) {

        for(int i=low+1;i<=hi;i++)
        {
            T tobeInerted = input[i];
            int j=i;
            while(j> low && less(tobeInerted,input[j-1]))
            {
                input[j] = input[j-1];
                j--;
            }

            input[j] = tobeInerted;


        }

    }


}
