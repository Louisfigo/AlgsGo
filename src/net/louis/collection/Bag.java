package net.louis.collection;

import net.louis.collection.Collection;

public interface Bag<T> extends Iterable<T>,Collection<T> {

    public void add(T t);

}
