package net.louis.collection.linked;

import net.louis.collection.Collection;

public class Deque<T> implements Collection<T> {

    private DoubleLinkedList<T> innerCollection = new DoubleLinkedList<>();

    @Override
    public boolean isEmpty() {
        return innerCollection.isEmpty();
    }

    @Override
    public int size() {
        return innerCollection.size();
    }

    public void pushLeft(T t)
    {
        innerCollection.addHead(t);
    }

    public void pushRigh(T t)
    {
        innerCollection.addTail(t);
    }

    public T popLeft()
    {
        return innerCollection.removeHead();
    }

    public T popRight()
    {
        return  innerCollection.
               removeTail();
    }
}
