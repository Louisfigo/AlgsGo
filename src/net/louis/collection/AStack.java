package net.louis.collection;

public interface AStack<T> extends Iterable<T>,Collection<T> {

    public  void push(T t);
    public T pop();
    public  T peek();

}
