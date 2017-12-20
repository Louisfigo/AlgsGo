package net.louis.collection.graph;

import net.louis.collection.Queue;
import net.louis.collection.linked.LinkedQueue;
import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/20.
 */
public class Bfs extends AbstractGraphSearch {



    @Override
    public void build(BasicGraph bg, int stp) {
        this.bg = bg;
        this.stp = stp;
        connected = new boolean[bg.getVSize()];
        edgeTo = new int[bg.getVSize()];
        for(int i=0;i< bg.getVSize();i++)
            connected[i] = Boolean.FALSE;

        search(this.bg,this.stp);
    }

    @Override
    public void search(BasicGraph bg, int stp) {
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






}
