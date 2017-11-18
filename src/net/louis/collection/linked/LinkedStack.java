package net.louis.collection.linked;

import net.louis.collection.AStack;

import java.util.Iterator;

public class LinkedStack<T> implements AStack<T> {

    private Node<T> top;

    private int size = 0;

    @Override
    public void push(T t) {

            Node<T> oldTop = top;
            top = new Node<>(t,null);
            top.next = oldTop;
            size ++;

    }

    @Override
    public T pop() {
        if(top==null)
        {
            throw new ArrayIndexOutOfBoundsException("Linked statck is Empty");
        }
        else
        {
            T data = top.data;
            top = top.next;
            size --;
            return  data;
        }
    }

    @Override
    public T peek() {
        return top.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> currPoint = top;
            @Override
            public boolean hasNext() {
                return top !=null;
            }

            @Override
            public T next() {
                T data =top.data;
                top = top.next;
                return data;
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String args[])
    {
        AStack<String> stringAStack = new LinkedStack<>();

        stringAStack.push("1");
        stringAStack.push("2");
        stringAStack.push("3");
        stringAStack.push("4");

        while(!stringAStack.isEmpty())
            System.out.println(stringAStack.pop());
    }
}
