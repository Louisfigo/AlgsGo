package net.louis.collection.linked;

import net.louis.collection.Queue;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RingBuffer<T> implements Queue<T> {

    private AtomicInteger size = new AtomicInteger(0);

    private  int maxSize ;

    private Node<T> head;
    private Node<T> tail;

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public RingBuffer(int maxSize) {
        this.maxSize = maxSize;
        head = tail = null;
    }

    @Override
    public void enqueue(T t) {

        if(head == null)
        {
            head = new Node<>(t,null);
            tail = head;
        }
        else {
            while(size.get() == maxSize)
                ;
            tail.next = new Node<>(t,head);
            tail = tail.next;
        }

        size.incrementAndGet();

    }

    @Override
    public T dequeue() {

        T data = null;
        if(head == null)
        {
            while(size.get()<=0)
          ;
        }
        else
        {
            data = head.data;

            head = head.next;
            tail.next = head;
        }
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size.get() == 0;
    }

    @Override
    public int size() {
        return size.get();
    }

    public static void main(String args[])
    {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(15);

        ringBuffer.enqueue(1);
        ringBuffer.enqueue(2);
        ringBuffer.enqueue(3);

        System.out.println("head : " + ringBuffer.getHead().data) ;
        System.out.println("tail : " + ringBuffer.getTail().data) ;
        System.out.println("head : " + ringBuffer.getTail().next.data) ;
        System.out.println("head next: " + ringBuffer.getHead().next.data) ;

    }
}
