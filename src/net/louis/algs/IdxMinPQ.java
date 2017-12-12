package net.louis.algs;

import edu.princeton.cs.algs4.StdOut;
import net.louis.collection.Collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IdxMinPQ<K> implements Collection<K> {

    private int currCap;
    private  int maxCap;
    private  int[] pq;
    private  int[] qp;
    private K[] keys;

    private Comparator<K> comparator;

    public IdxMinPQ(Comparator<K> comparator,int maxCap)
    {
        this.maxCap = maxCap;
        currCap= 0;
        keys = (K[]) new Object[maxCap+1];
        pq =  new int[maxCap+1];
        qp =  new int[maxCap+1];
        this.comparator = comparator;

        for(int i=0;i<=maxCap;i++)
            qp[i] = -1;

    }



    @Override
    public boolean isEmpty() {
        return currCap ==0;
    }

    @Override
    public int size() {
        return currCap;
    }

    public boolean contains(int k)
    {
        return qp[k] != -1;
    }

    public void insert(int idx, K key)
    {
        currCap ++;
        qp[idx] = currCap;
        pq[currCap] = idx;
        keys[idx] = key;

    }

    public void swim(int k)
    {
        while(k >1 && more(k/2,k))
        {
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= currCap) {
            int j = 2*k;
            if (j < currCap && more(j, j+1)) j++;
            if (!more(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public int delMin() {
        if (currCap == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, currCap--);
        sink(1);
        assert min == pq[currCap+1];
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        pq[currCap+1] = -1;        // not needed
        return min;
    }

    public void exch(int leftIdx,int rightIdx)
    {


        int idx = pq[leftIdx];
        pq[leftIdx] = pq[rightIdx];
        pq[rightIdx] = idx;
        qp[pq[leftIdx]] = leftIdx;
        qp[pq[rightIdx]] = rightIdx;

    }
    public boolean more(int leftIdx,int rightIdx)
    {
        return comparator.compare(keys[pq[leftIdx]],keys[pq[rightIdx]]) >0;
    }


    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IdxMinPQ<K> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IdxMinPQ<K>(comparator,pq.length - 1);
            for (int i = 1; i <= currCap; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IdxMinPQ<String> pq = new IdxMinPQ<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }, strings.length);

        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq.pq) {
            StdOut.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }
}
