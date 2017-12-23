package net.louis.collection.graph;

import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/23.
 */
public class DirectedCycle extends DirectedDFS {

    private LinkedStack<Integer> cycle;

    public boolean hasCycle()
    {
        return  cycle !=null;
    }

    private boolean[] onStack;

    public DirectedCycle(Digraph dg) {
        super(dg);
        onStack = new boolean[dg.getVSize()];

        for (int v = 0; v < dg.getVSize(); v++)
            if (isConnected(v)) search(v);

    }

    private void dfs(Digraph dg, int v)
    {
        onStack[v] = true;
        connected[v] = true;

        for(int w:dg.adj(v))
        {
            if(cycle!=null)return;
            else if(!connected[w])
            {
                edgeTo[w] = v;
                dfs(dg,w);
            }
            else if(onStack[w])
            {
                cycle = new LinkedStack<>();

                for(int x=v;x!=w;x =edgeTo[x])
                {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

        onStack[v] =false;
    }
}
