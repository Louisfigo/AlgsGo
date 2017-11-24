package net.louis.collection.linked;

public class DoubleNode<T> {

    T data;

    DoubleNode<T> prev;

    DoubleNode<T> next;

    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
