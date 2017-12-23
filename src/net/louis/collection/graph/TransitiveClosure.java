package net.louis.collection.graph;

/**
 * Created by Louis on 2017/12/23.
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(Digraph dg) {

        all = new DirectedDFS[dg.getVSize()];

        for (int i=0;i< dg.getVSize();i++)
        {
            all[i] = new DirectedDFS(dg);
            all[i].search(i);
        }
    }

    public boolean reachable(int v,int w)
    {
        return all[v].isConnected(w);
    }
}
