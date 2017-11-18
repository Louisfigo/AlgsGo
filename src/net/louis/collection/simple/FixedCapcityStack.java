package net.louis.collection.simple;

import net.louis.collection.AStack;

import java.util.Iterator;

public class FixedCapcityStack<T> implements AStack<T> {

    private T[] innerArray;

    private int currPoint;


    public FixedCapcityStack(int size) {

        innerArray = (T[]) new Object[size];
        currPoint = 0;
    }



    @Override
    public void push(T t) {
        resize();
        innerArray[currPoint] =t;
        currPoint ++;

    }

    @Override
    public T pop() {
        if(isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        currPoint--;

        T ret = innerArray[currPoint];
        resize();
        return ret ;

    }

    @Override
    public T peek() {
        return  innerArray[currPoint];
    }


    @Override
    public Iterator iterator() {
        return new Iterator() {

            private  int iPoint = currPoint;

            @Override
            public boolean hasNext() {
                return iPoint > 0;
            }

            @Override
            public Object next() {
                iPoint --;
                return innerArray[iPoint];
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return currPoint==0;
    }

    public boolean isFull() {
        return currPoint!=0 && currPoint==innerArray.length;
    }

    @Override
    public int size() {
        return currPoint;
    }

    private void resize()
    {
        if(currPoint == innerArray.length)
        {
            T tmp[]  = (T[]) new Object[innerArray.length*2];

            for(int i=0;i< innerArray.length;i++)
            {
                tmp[i] = innerArray[i];
            }

            innerArray =tmp;
        }
        else if(currPoint> 0 && currPoint <=innerArray.length/4)
        {
            T tmp[]  = (T[]) new Object[innerArray.length/4];

            for(int i=0;i< innerArray.length;i++)
            {
                tmp[i] = innerArray[i];
            }

            innerArray =tmp;
        }
    }
}
