package net.louis.algs;

public class BinarySearch {

    public static int rank(int key, int[] input)
    {
        int low =0;
        int high = input.length-1;

        while(low<=high)
        {
            int mid = low+high/2;
            if(key< input[mid])
            {
                high = mid-1;

            }
            else if(key>input[mid])
            {
                low = mid+1;
            }
            else
                return  mid;

        }

        return  -1;
    }
}
