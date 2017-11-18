package net.louis.collection;

public interface Queue<T> extends Iterable<T>,Collection<T> {

    public void enqueue(T t);
    public  T dequeue();
}
