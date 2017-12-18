package net.louis.collection.table;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Louis on 2017/12/18.
 */
public class LinearProbingHashST <K,V> {

    private int totalKeySize ;

    private int initialHashTableSize = 16;

    private K[] keys;

    private V[] values;

    public LinearProbingHashST() {
        keys = (K[]) new  Object[initialHashTableSize];
        values = (V[]) new  Object[initialHashTableSize];
    }

    public LinearProbingHashST(int initialHashTableSize) {
        this.initialHashTableSize =initialHashTableSize;
        keys = (K[]) new  Object[initialHashTableSize];
        values = (V[]) new  Object[initialHashTableSize];

    }

    public void put(K key,V value)
    {
        if(initialHashTableSize <= totalKeySize*2)
            resize(2*initialHashTableSize);

        int idx = 0;
        for(idx = hash(key);keys[idx]!=null;idx=(idx+1)%initialHashTableSize)
        {

            if(keys[idx].equals(key))
            {
                values[idx] = value;
                return;
            }
        }

        keys[idx] = key;
        values[idx] =value;
        totalKeySize ++;
    }

    public V get(K key)
    {
        int idx = 0;
        for(idx = hash(key);keys[idx]!=null;idx=(idx+1)%initialHashTableSize)
        {
            if(keys[idx].equals(key))
            {
                return values[idx] ;

            }
        }

        return null;
    }

    public  void delete(K key)
    {
        if(!contains(key)) return;
        int idx = 0;
        for(idx = hash(key);keys[idx]!=null;idx=(idx+1)%initialHashTableSize)
        {
            if(keys[idx].equals(key))
                break;
        }

        keys[idx] = null;
        values[idx] = null;

        idx=(idx+1)%initialHashTableSize;

        for(idx = hash(key);keys[idx]!=null;idx=(idx+1)%initialHashTableSize)
        {
           K kr = keys[idx];
           V vr = values[idx];
            keys[idx] = null;
            values[idx] = null;
            totalKeySize --;

            put(kr,vr);
        }

        totalKeySize --;

        if(totalKeySize > 0 && totalKeySize == initialHashTableSize/8) resize(initialHashTableSize/2);
    }

    public boolean contains(K key)
    {

        int idx = 0;
        for(idx = hash(key);keys[idx]!=null;idx=(idx+1)%initialHashTableSize)
        {
           return keys[idx].equals(key) && (values[idx]!=null);
        }

        return  false;
    }

    private  int hash(K key)
    {
        return  (key.hashCode() & 0x7fffffff)%initialHashTableSize;
    }

    private  void resize(int newSize)
    {
     LinearProbingHashST<K,V> lst = new LinearProbingHashST<>(newSize);
     for(int i=0;i< initialHashTableSize;i++)
     {
         if(keys[i]!=null)
             return;
     }
     keys = lst.keys;
     values = lst.values;
     initialHashTableSize = lst.initialHashTableSize;
    }

    public void printKeys()
    {
        for(int i=0;i<initialHashTableSize;i++)
        {

            if(keys[i] !=null)
                System.out.println(keys[i] + "|" +values[i]);
        }
    }
    public static void main(String args[])
    {
        LinearProbingHashST<String,Integer> sst = new LinearProbingHashST<>(2000000);
        long msb = System.currentTimeMillis();

        String fileName = "D:\\j2eeWp\\algs4-data\\leipzig1M.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(s->
            {
                String []oneLine = s.split(" ");
                for(String s1:oneLine)
                {

                    if(sst.contains(s1) && s1.length() >8)
                    {
                        int tc = sst.get(s1)+1;
                        sst.put(s1,tc);
                    }
                    else
                    {
                        sst.put(s1,1);

                    }
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        long msn = System.currentTimeMillis();

        System.out.println(msb-msn);

    }
}
