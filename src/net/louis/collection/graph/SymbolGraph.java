package net.louis.collection.graph;

import net.louis.collection.tree.RedBlackBinarySearchTree;

/**
 * Created by Louis on 2017/12/21.
 */
public class SymbolGraph <K>  {

    private RedBlackBinarySearchTree<K,Integer> st;

    private K[] reverseIdx;

    private BasicGraph bg;

    public SymbolGraph() {
        this.st = new RedBlackBinarySearchTree<K,Integer>();
        reverseIdx = (K[]) new Object[st.size()];
        for(K sk:st.keys())
        {
            reverseIdx[st.get(sk)] =sk;
        }

        bg = new BasicGraph(reverseIdx.length);

        //add edge by input
    }

    public boolean contains(K key)
    {
        return st.contains(key);
    }

    public int rIdx(K key)
    {
        return st.get(key);
    }

    public K symbol(int v)
    {
        return reverseIdx[v];
    }

    public void DegreesOfSeparation(K source ,K dest)
    {
        if(!contains(source))
            return;

        if(!contains(dest))
            return;

        int sidx = rIdx(source);

        Bfs bfs = new Bfs();

        bfs.build(bg,sidx);

        int dIdx = rIdx(dest);

        if (bfs.hasPathTo(dIdx))
        {
            for(int v:bfs.pathTo(dIdx))
            {
                System.out.println("-"+symbol(v));
            }
        }


    }
}
