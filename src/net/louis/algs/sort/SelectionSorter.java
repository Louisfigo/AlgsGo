package net.louis.algs.sort;

public class SelectionSorter<T> extends SorterTemplate<T>{


    @Override
    public void sort(T[] input) {

        for(int i=0; i<input.length;i++)
        {
            for(int j =i+1;j<input.length;j++)
            {
                if(less(input[i],input[j]))
                    exch(input,i,j);
            }
        }

    }
}
