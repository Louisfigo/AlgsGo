package net.louis.collection.table;

import net.louis.CompareUtil;
import net.louis.collection.Queue;
import net.louis.collection.linked.LinkedQueue;

public class BinarySearchTable<K,V> implements OrderedTable<K,V> {

    private K keys[];

    private V values[];

    private  int size;

    public BinarySearchTable(int capcity) {

        keys = (K[]) new Object[capcity];
        values = (V[]) new Object[capcity];

    }

    @Override
    public K min() {
        if(isEmpty())
            return  null;
        return keys[0];
    }

    @Override
    public K max() {

        if(isEmpty())
            return null;

        return keys[size-1];
    }

    @Override
    public K floor(K key) {
        if(isEmpty())
            return null;

        int kIdx = rank(key);

        if(kIdx < size && CompareUtil.compare(keys[kIdx],key) == 0)

            return  keys[kIdx];
        if(kIdx == 0)
            return null;

        return keys[rank(key)-1];
    }

    @Override
    public K celling(K key) {
        if(isEmpty())
            return null;

        return keys[rank(key)];
    }

    @Override
    public int rank(K key) {

        int low = 0;

        int high = size-1;

        while(low<=high)
        {
            int mid = low + (high-low)/2;

            int cmp = CompareUtil.compare(key,keys[mid]);

            if(cmp >0 ) low = mid+1;
            else if(cmp <0) high = mid -1;
            else return  mid;


        }

        return low;
    }

    @Override
    public K select(int k) {
        if(isEmpty())
            return  null;
        if(k >=size)
            return  null;

        return keys[k];
    }

    @Override
    public void deleteMin() {
        if(isEmpty()) return;

        delete(min());
    }

    @Override
    public void deleteMax() {
        if(isEmpty()) return;

        delete(max());
    }

    @Override
    public int sizeBetween(K low, K high) {
        return 0;
    }

    @Override
    public Iterable<K> keysRange(K low, K high) {
        Queue<K> queue = new LinkedQueue<>();
        if (CompareUtil.compare(low,high)> 0) return queue;
        for (int i = rank(low); i < rank(high); i++)
            queue.enqueue(keys[i]);
        if (contains(high)) queue.enqueue(keys[rank(high)]);
        return queue;

    }

    @Override
    public void put(K key, V value) {

        int kIdx = rank(key);

        if(kIdx<size && CompareUtil.compare(keys[kIdx],key) ==0)
        {
            values[kIdx]  = value;
            return;
        }

        for(int j=size;j>kIdx;j--)
        {
            keys[j] = keys[j-1];
            values[j] =values[j-1];
        }

        keys[kIdx] = key;
        values[kIdx] = value;
        size ++;

    }

    @Override
    public V get(K key) {
        if(isEmpty())
            return null;
        int kIdx = rank(key);

        if(kIdx< size && CompareUtil.compare(keys[kIdx],key) == 0)
            return values[kIdx];

        return null;
    }

    @Override
    public void delete(K key) {

        if(isEmpty()) return;

        int kIdx = rank(key);

        if(kIdx <size && keys[kIdx].equals(key))
        {
            for(int j=kIdx;j < size-1;j++)
            {
                keys[j] = keys[j+1];
                values[j] = values[j+1];

            }

            size --;
            keys[size] = null;
            values[size] = null;
        }


    }

    @Override
    public boolean contains(K key) {
        if(isEmpty())
            return false;
        else
            return get(key) !=  null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        return keysRange(min(),max());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String args[])
    {
        BinarySearchTable<String,String> nt = new BinarySearchTable<>(10);
        nt.put("10","ha");
        nt.put("20","ha2");
        nt.put("30","ha3");
        nt.put("40","ha4");
        nt.put("50","ha5");

                    System.out.println(nt.get("10"));
                   System.out.println(nt.get("20"));
                   System.out.println(nt.get("40"));
                   System.out.println(nt.get("30"));

        System.out.println(nt.keys());











    }
}
