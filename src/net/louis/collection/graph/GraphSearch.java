package net.louis.collection.graph;

/**
 * Created by Louis on 2017/12/20.
 */
public interface GraphSearch {

    public  void build(BasicGraph bg, int stp);

    public boolean isConnected(int v);

    public int countConnectedV();

    public void  search (BasicGraph bg,int stp);
}
