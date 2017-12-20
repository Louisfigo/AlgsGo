package net.louis.collection.graph;

import net.louis.collection.Queue;
import net.louis.collection.linked.LinkedQueue;
import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/20.
 */
public class Bfs implements GraphSearch {

    private  int stp ;
    private BasicGraph bg;
    private boolean[] connected;
    private int countConnected;
    private int[] edgeTo;

    @Override
    public void build(BasicGraph bg, int stp) {
        this.bg = bg;
        this.stp = stp;
        connected = new boolean[bg.getVSize()];
        edgeTo = new int[bg.getVSize()];
        for(int i=0;i< bg.getVSize();i++)
            connected[i] = Boolean.FALSE;

        bfSearch(this.bg,this.stp);
    }

    private void bfSearch(BasicGraph bg,int stp)
    {
        Queue<Integer> queue = new LinkedQueue<>();
        connected[stp] = true;
        queue.enqueue(stp);
        countConnected++;
        while(!queue.isEmpty())
        {
            int v = queue.dequeue();
            for(int w:bg.adj(v))
            {
                if(!isConnected(w))
                {
                    edgeTo[w] =v;
                    connected[w] = true;
                    queue.enqueue(w);

                }
            }

        }
    }


    public boolean hasPathTo(int v)
    {
        return isConnected(v);
    }

    public Iterable<Integer> pathTo(int v)
    {
        if(!hasPathTo(v)) return  null;
        LinkedStack<Integer> path = new LinkedStack<>();


        for(int x=v;x!=stp;x=edgeTo[x])
        {
            path.push(x);
        }
        path.push(stp);

        return path;
    }
    @Override
    public boolean isConnected(int v) {
        return connected[v];
    }

    @Override
    public int countConnectedV() {
        return countConnected;
    }
}
