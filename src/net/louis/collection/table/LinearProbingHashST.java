package net.louis.collection.table;

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
        LinearProbingHashST<String,Integer> sst = new LinearProbingHashST<>();
        sst.put("G",1);
        sst.put("L",2);
        sst.put("A",3);
        sst.put("W",4);
        sst.printKeys();
        System.out.println("-----------------------------");
        sst.delete("G");
        sst.printKeys();
    }
}
