package net.louis.collection.graph;

import net.louis.collection.linked.LinkedStack;

/**
 * Created by Louis on 2017/12/23.
 */
public class Topological {

    private LinkedStack<Integer> order;

    public Topological(Digraph dg)
    {
        DirectedCycle dc = new DirectedCycle(dg);

        if(!dc.hasCycle())
        {
            DfsOrder dfsOrder = new DfsOrder(dg);
            order = dfsOrder.getReversePost();
        }

    }
}
