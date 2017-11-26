package net.louis.algs;

import java.util.Arrays;

public class ThreeSum {

    public static int count(int[] input)
    {
        Arrays.sort(input);
        int count =0;

        for(int i=0;i< input.length;i++)
            for(int j=i=1;j<input.length;j++)
            {
                if(BinarySearch.rank(-input[i]-input[j],input) >j)
                    count ++;
            }

            return  count;
    }

    public static void main(String args[])
    {

    }
}
