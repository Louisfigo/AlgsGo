package net.louis.collection.graph;

/**
 * Created by Louis on 2017/12/23.
 */
public class KDGraphComponent {

    protected Digraph dg;
    protected boolean[] connected;
    protected int countComp;
    protected int[] compId;

    public KDGraphComponent(Digraph dg) {
        this.dg = dg;
        connected = new boolean[dg.getVSize()];
        compId =new int[dg.getVSize()];

        DfsOrder dfsOrder = new DfsOrder(dg.reverse());

        for(int s:dfsOrder.getReversePost())
        {
            if(!connected[s])
            {
                search(dg,s);
                countComp++;
            }
        }

    }

    private void search(Digraph dg ,int v)
    {
        connected[v] = true;
        compId[v] = countComp;
        for(int w:dg.adj(v))
        {
            if(!connected[w])
            {
                search(dg,w);

            }
        }
    }

    public  boolean isStrongConnected(int v, int w)
    {
        return compId[v] ==compId[w];
    }

}
