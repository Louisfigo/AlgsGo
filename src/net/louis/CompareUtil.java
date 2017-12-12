package net.louis;

public class CompareUtil{

    public static <T> int compare(T left,T right)
    {
        int cmp = 0;

        if(left instanceof Comparable)
            cmp = ((Comparable) left).compareTo(right);

        return cmp;
    }

}
