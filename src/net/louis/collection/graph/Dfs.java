package net.louis.collection.graph;

import net.louis.collection.linked.LinkedStack;

import java.util.Optional;

/**
 * Created by Louis on 2017/12/20.
 */
public class Dfs extends AbstractGraphSearch{

    @Override
    public void build(BasicGraph bg, int stp) {
        this.stp = stp;
        this.bg =bg;
        connected = new boolean[bg.getVSize()];
        edgeTo = new int[bg.getVSize()];
        for(int i=0;i< bg.getVSize();i++)
            connected[i] = Boolean.FALSE;

        search(bg,stp);

    }

    public void search(BasicGraph bg,int v)
    {
        connected[v] = true;
        countConnected++;
        for(int w:bg.adj(v))
        {
            if(!isConnected(w))
            {
                edgeTo[w] =v;
                search(bg,w);

            }
        }
    }


}
