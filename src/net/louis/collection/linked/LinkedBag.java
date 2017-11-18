package net.louis.collection.linked;

import net.louis.collection.AStack;
import net.louis.collection.Bag;

import java.util.Iterator;

public class LinkedBag<T> implements Bag<T>{


    private AStack<T> innerStack = new LinkedStack<>();

    @Override
    public void add(T t) {
        innerStack.push(t);
    }

    @Override
    public Iterator<T> iterator() {
        return innerStack.iterator();
    }

    @Override
    public boolean isEmpty() {
        return innerStack.isEmpty();
    }

    @Override
    public int size() {
        return innerStack.size();
    }
}
