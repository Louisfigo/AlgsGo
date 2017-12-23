package net.louis.collection.graph;

/**
 * Created by Louis on 2017/12/23.
 */
public class DirectedDFS {

    protected int stp;
    protected Digraph dg;
    protected boolean[] connected;
    protected int countConnected;
    protected int[] edgeTo;

    public DirectedDFS(Digraph dg) {
        this.dg = dg;
        edgeTo = new int[dg.getVSize()];
        resetConnectedArry();
    }

    public void search(int stp)
    {
        this.stp = stp;
        resetConnectedArry();
        search(dg,stp);
    }

    public void search(Iterable<Integer> stps)
    {
        resetConnectedArry();
       stps.forEach(stp->
       {
           if(!isConnected(stp))
           search(dg,stp);
       });
    }

    private void search(Digraph dg ,int v)
    {
        connected[v] = true;
        countConnected++;
        for(int w:dg.adj(v))
        {
            if(!isConnected(w))
            {
                edgeTo[w] =v;
                search(dg,w);

            }
        }
    }

    public boolean isConnected(int v) {
        return connected[v];
    }

    private void resetConnectedArry()
    {
        if(connected == null)
            connected = new boolean[dg.getVSize()];

        else
        {
          for(int i=0;i< connected.length;i++)
              connected[i] =false;
        }
    }

}
