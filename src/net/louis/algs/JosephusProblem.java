package net.louis.algs;

import net.louis.collection.Queue;
import net.louis.collection.linked.LinkedQueue;

public class JosephusProblem {

    public void printKillOrder(int peoplerNumber,int killsign)
    {
        Queue<Integer> JosephQueue = new LinkedQueue<>();
        for (int i=0;i<peoplerNumber;i++)
        {
            JosephQueue.enqueue(i);
        }

        int start = 1;

        while(JosephQueue.size()>1)
        {
            if(start!=killsign )
            {
                Integer thisPerson = JosephQueue.dequeue();
                JosephQueue.enqueue(thisPerson);
                start ++;
            }

            else
            {

                Integer thisPerson = JosephQueue.dequeue();
                System.out.println("dead : " + thisPerson);
                start = 1;
            }

        }
        System.out.println("survior : "+ JosephQueue.dequeue());

    }

    public static void main(String args[])
    {
        JosephusProblem jp = new JosephusProblem();
        jp.printKillOrder(7,2);

    }
}
