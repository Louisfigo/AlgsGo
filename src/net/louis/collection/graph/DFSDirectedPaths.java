package net.louis.collection.graph;

import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/23.
 */
public class DFSDirectedPaths extends  DirectedDFS{

    public DFSDirectedPaths(Digraph dg) {
        super(dg);
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
        LinkedStack<Integer> path = new LinkedStack<Integer>();
        for (int x = v; x != stp; x = edgeTo[x])
            path.push(x);
        path.push(stp);
        return path;
    }
}
