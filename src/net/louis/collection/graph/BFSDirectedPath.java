package net.louis.collection.graph;

import net.louis.collection.linked.LinkedBag;
import net.louis.collection.linked.LinkedQueue;
import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/23.
 */
public class BFSDirectedPath  {

    private boolean[] connected;  // marked[v] = is there an s->v path?
    private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
    private int[] distTo;
    private  Digraph dg;
    private  int stp;
    public BFSDirectedPath(Digraph dg, int stp) {
        this.dg =dg;
        connected = new boolean[dg.getVSize()];
        distTo = new int[dg.getVSize()];
        edgeTo = new int[dg.getVSize()];
        for (int v = 0; v < dg.getVSize(); v++)
            distTo[v] = Integer.MAX_VALUE;
        this.stp = stp;

        bfs(this.dg, this.stp);
    }



    private void bfs(Digraph G, int s) {
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        connected[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!connected[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    connected[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    private void validateVertex(int v) {
        int V = connected.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return connected[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        int x = 0;
        LinkedStack<Integer> path = new LinkedStack<Integer>();
        for (x = v; distTo[x] !=0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}
