package net.louis.collection.table;

public interface OrderedTable<K,V> extends Table<K,V> {

    public K min();

    public K max();

    public  K floor(K key);

    public K celling(K key);

    public int rank(K key);

    public K select(int k);

    public void deleteMin();

    public void deleteMax();

    public int sizeBetween(K low,K high);

    public Iterable<K> keysRange(K low,K high);


}
