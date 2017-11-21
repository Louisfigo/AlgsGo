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

    public void deleteTail()
    {
       if(head == null)
           throw new RuntimeException("Queue is Empty,no tail could be deleted");
       else
       {
           Node<T> cp = head;

           while(cp.next != null)
           {
               if(cp.next.next == null)
               {
                   cp.next = null;
                   tail = cp;
               }
               else
               {
                   cp = cp.next;
               }
           }
       }
    }

    public void deleteBySequence(int k)
    {
        if(head == null)
            throw  new RuntimeException("Queue is Empty,no tail could be deleted");
        else
        {
            int i =1 ;
            Node<T> cp = head;
            Node<T> pcp = null;

            while(i<k)
            {
                pcp = cp;
                cp = cp.next;
                if(cp == null)
                    throw new RuntimeException("K doesn't exists");
                i++;
            }

            if(cp == tail)
            {
                pcp.next = null;
                tail = pcp;
            }
            else
            {
                pcp.next =cp.next;
            }


        }
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
