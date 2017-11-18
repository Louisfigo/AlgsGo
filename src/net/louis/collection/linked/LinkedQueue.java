package net.louis.collection.linked;

import edu.princeton.cs.algs4.ST;
import net.louis.collection.Queue;

import java.util.Iterator;

public class LinkedQueue<T> implements Queue<T> {

 private  Node<T> head;
    private Node<T> tail;
    private int size =0;

    @Override
    public void enqueue(T t) {

       Node<T> oldTail = tail;
       tail = new Node<>(t,null);
       if(isEmpty()) head = tail;
        else oldTail.next =tail;
        size ++;
    }

    @Override
    public T dequeue() {
        if(isEmpty())
            throw  new ArrayIndexOutOfBoundsException();
        T data = head.data;
        head = head.next;
        size --;
        if(isEmpty())
            tail = head;
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String args[])
    {
        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");

        while(!queue.isEmpty())
            System.out.println(queue.dequeue());
    }
}
