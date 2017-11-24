package net.louis.collection.linked;

public class Steque<T> {

    private  DoubleLinkedList<T> innerContainer = new DoubleLinkedList<>();

    public void push(T data)
    {
        innerContainer.addHead(data);
    }

    public T pop()
    {
        return innerContainer.removeHead();
    }

    public  void enqueue(T data)
    {
        innerContainer.addTail(data);
    }

    public T dequeue()
    {
        return innerContainer.removeHead();
    }
}
