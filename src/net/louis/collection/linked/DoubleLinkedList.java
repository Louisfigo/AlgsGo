package net.louis.collection.linked;

import net.louis.collection.Collection;

import java.util.Iterator;

public class DoubleLinkedList<T> implements Collection<T>,Iterable<T> {

    private DoubleNode<T> head;
    private DoubleNode<T> tail;

    private int size;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void addHead(T data)
    {
        if (head == null)
        {
            head = new DoubleNode<>(data,null,null);
            tail = head;

        }

        else
        {
            DoubleNode<T> newHead = new DoubleNode<>(data,null,head);
            head=newHead;

        }

        size ++;

    }

    public void addTail(T data)
    {
        if(tail == null)
        {
            addHead(data);
        }
        else
        {
            tail.next = new DoubleNode<>(data,tail,null);
            tail = tail.next;
        }

        size ++;
    }

    public T removeHead()
    {
        if(head == null)
            throw new RuntimeException("DL is null");
        else
        {
            T data = head.data;
            head = head.next;
            size --;
            if(size == 0)
                tail = head;
            return  data;
        }
    }

    public T removeTail()
    {
        if(tail == null)
            throw new RuntimeException("DL is null");
        else
        {
            T data = tail.data;
            tail.prev.next = null;
            tail = tail.prev;
            size --;
            if(size == 0)
                head = tail;
            return  data;
        }
    }

    public void insertBeforeNode(T newData, T sData)
    {
        if(head == null)
            throw new RuntimeException("DL is null");
        else
        {
            DoubleNode<T> currPt = head;

            while(currPt!=null)
            {
                if(currPt.data.equals(sData))
                {
                    DoubleNode<T> newDoubleNode = new DoubleNode<>(newData,currPt.prev,currPt);
                    currPt.prev.next = newDoubleNode;
                    currPt.prev =newDoubleNode;
                    size++;
                    break;
                }
                else
                {
                    currPt =currPt.next;
                }
            }
        }
    }

    public void deleteNode(T data)
    {

        if(head == null)
            throw new RuntimeException("DL is null");
        else
        {
            DoubleNode<T> currPt = head;

            while(currPt!=null)
            {
                if(currPt.data.equals(data))
                {
                    if(currPt == head)
                        removeHead();
                    else if(currPt == tail)
                        removeTail();
                    else
                    {
                        currPt.prev.next = currPt.next;
                        currPt.next.prev = currPt.prev;
                    }

                    size --;
                    break;
                }
                else
                {
                    currPt =currPt.next;
                }
            }
        }
    }

    public void insertAfterNode(T newData, T sData)
    {
        if(tail == null)
            throw new RuntimeException("DL is null");
        else
        {
            DoubleNode<T> currPt = tail;

            while(currPt!=null)
            {
                if(currPt.data.equals(sData))
                {
                    DoubleNode<T> newDoubleNode = new DoubleNode<>(newData,currPt,currPt.next);
                    currPt.next.prev = newDoubleNode;
                    currPt.next =newDoubleNode;
                    size++;
                    break;
                }
                else {
                    currPt =currPt.prev;
                }
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private DoubleNode<T> currPoint = head;
            @Override
            public boolean hasNext() {
                return currPoint!=null;
            }

            @Override
            public T next() {

                T data = currPoint.data;
                currPoint =currPoint.next;
                return data;
            }
        };
    }

    public void printDouleWayList()
    {
        Iterator<T> iterator = iterator();

        while(iterator.hasNext())
        {
            System.out.println(iterator.next());

        }
    }

    public  static void main(String args[])
    {
        DoubleLinkedList<Integer> dl = new DoubleLinkedList<>();
        int[] input = new int[]{1,2,3};

        for(int i:input)
        {
            System.out.println("cycle  begin : " + i + "~~");
            dl.addTail(i);
            dl.printDouleWayList();
            System.out.println("cycle end : " + i + "~");
        }
        System.out.println("insert before ");

        dl.insertBeforeNode(7,2);
        dl.printDouleWayList();
        System.out.println("insert after ");

        dl.insertAfterNode(9,2);
        dl.printDouleWayList();

        System.out.println("remove head ");
        dl.removeHead();
        dl.printDouleWayList();

        System.out.println("remove tail ");
        dl.removeTail();
        dl.printDouleWayList();

        dl.deleteNode(7);
        dl.printDouleWayList();

    }


}
