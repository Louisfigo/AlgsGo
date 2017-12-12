package net.louis.collection.table;

public interface Table<K ,V> {

    public void put(K key,V value);

    public V get(K key);

    public void delete (K key);

    public boolean contains(K key);

    public int size();

    public Iterable<K> keys();

    public boolean isEmpty();


}
