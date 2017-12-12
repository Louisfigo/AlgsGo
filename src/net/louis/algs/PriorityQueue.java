package net.louis.algs;

import java.util.Comparator;

public class PriorityQueue<K> {

    private K[] pq;

    private int cap = 0;

    private Comparator<K> comparator;

    public  PriorityQueue( int maxCap,Comparator<K> comparator)
    {
        pq = (K[]) new Object[maxCap];
        this.comparator = comparator;

    }

    public  PriorityQueue( K[] pq,Comparator<K> comparator)
    {
        pq = pq;
        cap = pq.length;
        this.comparator = comparator;

    }

    public boolean isEmpty()
    {
        return cap == 0;
    }

    public int size()
    {
        return cap;
    }

    public boolean less(int leftIdx,int rightIdx)
    {
        return comparator.compare(pq[leftIdx],pq[rightIdx])< 0;
    }


    public void exch(int leftIdx,int rightIdx)
    {
        K kv = pq[leftIdx];
        pq[leftIdx] = pq[rightIdx];
        pq[rightIdx] = kv;
    }

    public void swim(int k)
    {
        while(k >1 && less(k/2,k))
        {
            exch(k/2,k);
            k = k/2;
        }
    }

    public  void sink(int k)
    {
        while(2*k <= cap)
        {
            int j= 2*k;
            if(j <cap && less(j,j+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }

    public void insert(K key)
    {
        pq[++cap] = key;
        swim(cap);
    }

    public K delMax()
    {
        K max = pq[1];
        exch(1,cap--);

        pq[cap+1] = null;
        sink(1);
        return max;
    }

}
