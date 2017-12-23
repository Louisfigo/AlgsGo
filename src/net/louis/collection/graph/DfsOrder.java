package net.louis.collection.graph;

import net.louis.collection.Queue;
import net.louis.collection.linked.LinkedQueue;
import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/23.
 */
public class DfsOrder {

    protected int stp;
    protected Digraph dg;
    protected boolean[] connected;
    protected int countConnected;
    protected int[] edgeTo;

    private LinkedQueue<Integer> pre = new LinkedQueue<>();
    private LinkedQueue<Integer> post = new LinkedQueue<>();;
    private LinkedStack<Integer> reversePost = new LinkedStack<>();

    public DfsOrder(Digraph dg) {
        this.dg = dg;
        edgeTo = new int[dg.getVSize()];
        resetConnectedArry();

    }

    private void resetConnectedArry()
    {
        if(connected == null)
            connected = new boolean[dg.getVSize()];

        else
        {
            for(int i=0;i< connected.length;i++)
                connected[i] =false;
        }
    }

    public void search()
    {
       for(int v=0;v<dg.getVSize();v++)
           if(!isConnected(v))
               search(dg,v);
    }



    private void search(Digraph dg ,int v)
    {
        pre.enqueue(v);
        connected[v] = true;
        countConnected++;
        for(int w:dg.adj(v))
        {
            if(!isConnected(w))
            {
                edgeTo[w] =v;
                search(dg,w);

            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public boolean isConnected(int v) {
        return connected[v];
    }

    public LinkedQueue<Integer> getPre() {
        return pre;
    }

    public LinkedQueue<Integer> getPost() {
        return post;
    }

    public LinkedStack<Integer> getReversePost() {
        return reversePost;
    }
}
