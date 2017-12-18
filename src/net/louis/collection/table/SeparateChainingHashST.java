package net.louis.collection.table;

/**
 * Created by Louis on 2017/12/18.
 */
public class SeparateChainingHashST <K,V> {

    private int totalKeySize ;

    private int totalHashTableSize;

    private  SequentialSearchST<K,V> [] hst;

    public SeparateChainingHashST( ) {
        this.totalHashTableSize = 997;
        hst = (SequentialSearchST<K,V>[]) new SequentialSearchST[totalHashTableSize];

        for(int i=0;i< totalHashTableSize;i++)
        {
            hst[i] = new SequentialSearchST<>();
        }
    }

    private  int hash(K key)
    {
        return  (key.hashCode() & 0x7fffffff)%totalHashTableSize;
    }

    public void put(K key, V value)
    {
        int idx = hash(key);
        System.out.println(idx+"|" + key);
        hst[idx].put(key,value);

    }

    public  V get(K key)
    {
        int idx = hash(key);

        if(hst[idx] !=null)
            return hst[idx].get(key);
        else
            return null;
    }

    public void printKeys()
    {
        for(int i=0;i< totalHashTableSize;i++)
        {

            if(hst[i] !=null)
                hst[i].printAll();
        }
    }

    public static void main(String args[])
    {
        SeparateChainingHashST<String,Integer> sst = new SeparateChainingHashST<>();
        sst.put("G",1);
        sst.put("D",2);
        sst.put("C",3);
        sst.put("F",4);
        sst.printKeys();
    }
}
