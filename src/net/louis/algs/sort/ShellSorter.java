package net.louis.algs.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ShellSorter<T> extends SorterTemplate<T> {
    @Override
    public void sort(T[] input) {

        int alenth = input.length;
        int h =1;

        while(h < alenth/3) h=3*h +1;

        while(h >=1){

            for(int i=h;i<alenth;i++)
            {
                T tobeInerted = input[i];
                int j=i;
                while(j>=h && less(tobeInerted,input[j-h]))
                {
                    input[j] = input[j-h];
                    j-=h;;
                }

                input[j] = tobeInerted;
            }

            h=h/3;
        }


    }

    public static void main(String args[])
    {
        Random random = new Random();

        Integer[] input = new Integer[1000];

        for(int i=0;i<1000;i++)
        {
            input[i] = random.nextInt(10000);
        }

        ShellSorter<Integer> shellSorter = new ShellSorter<>();

        shellSorter.setComparator(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        shellSorter.sort(input);

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        System.out.println(Arrays.toString(input));

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }



}
