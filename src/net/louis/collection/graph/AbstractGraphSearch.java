package net.louis.collection.graph;

import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/20.
 */
public abstract class AbstractGraphSearch implements GraphSearch {

    protected   int stp ;
    protected BasicGraph bg;
    protected boolean[] connected;
    protected int countConnected;
    protected int[] edgeTo;

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


    public boolean hasPathTo(int v)
    {
        return isConnected(v);
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
