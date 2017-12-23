package net.louis.collection.graph;

import net.louis.collection.linked.LinkedBag;

/**
 * Created by Louis on 2017/12/23.
 */
public class Digraph {
    private int vSize;

    private int eSize;

    private LinkedBag<Integer>[] adjTbl;

    public Digraph(int vSize) {

        this.vSize =vSize;
        this.eSize = 0;

        adjTbl = (LinkedBag<Integer>[]) new LinkedBag[vSize];

        for(int i = 0;i< vSize;i++)
        {
            adjTbl[i] = new LinkedBag<>();
        }
    }

    public int getVSize() {
        return vSize;
    }


    public int getESize() {
        return eSize;
    }

    public void addEdge(int leftV, int rightV)
    {
        adjTbl[leftV].add(rightV);

        eSize++;
    }

    public Iterable<Integer> adj(int v)
    {
        return  adjTbl[v];
    }

    @Override
    public String toString() {
        String s = vSize + " vertices " + eSize + " edges\n";
        for(int v=0;v<vSize;v++)
        {
            s += v + ":";
            for(int w:adj(v))
                s += v + "|";
            s += v + "\n";
        }

        return "s";
    }

    public Digraph reverse()
    {
        Digraph rdig = new Digraph(vSize);

        for (int v = 0;v<vSize;v++)
            for(int w:rdig.adj(v))
                rdig.addEdge(w,v);
        return rdig;
    }
}
