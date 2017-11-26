package net.louis.algs;

import edu.princeton.cs.algs4.StdIn;
import net.louis.IOUtil;

import java.util.Arrays;
import java.util.List;

public class UnionFind {

    int []conn;
    int connCnt;
    int[] connSz;

    int singleCost = 0;

    public UnionFind(int totalPoint)
    {
        connCnt = totalPoint;
        conn = new int[totalPoint];
        connSz = new int[totalPoint];

        for(int i=0;i<totalPoint;i++)
        {
            conn[i] = i;
            connSz[i] =1;
        }

    }

    public int getConnCnt() {
        return connCnt;
    }

    public int find(int p)
    {
        int root = p;
        while(root!=conn[p])
        {
           root =conn[root];
            singleCost ++;
        }

        while (p!=root)
        {
            int newp = conn[p];
             conn[p] = root;
             p = conn[newp];
        }

        return  root;

    }

    public void union(int p,int q)
    {
        singleCost = 0;
        int pRoot = find(p);
        int qRoot = find(q);

       if(pRoot==qRoot) return;

       if(connSz[pRoot] <connSz[qRoot])
       {
           conn[pRoot] = qRoot;
           connSz[qRoot] += connSz[pRoot];
       }

       else
       {
           conn[qRoot] = pRoot;
           connSz[pRoot] += connSz[qRoot];
       }
        connCnt --;
    }

    public boolean isConnected(int p, int q)
    {
       // singleCost = 0;
        return find(p) == find(q);
    }

    public static void main(String args[])
    {
        long startMs = System.currentTimeMillis();
        List<String> input = IOUtil.readLineFromFile("/Users/zhangweiguang/IdeaProjects/AlgsGo/lib/algs4-data/tinyUF.txt");
        System.out.println(Arrays.toString(input.toArray()));

        UnionFind uf = new UnionFind(Integer.parseInt(input.get(0)));

        int startIndex =1;
        while(startIndex<input.size())
        {
            int p = Integer.valueOf(input.get(startIndex));
            startIndex++;
            int q = Integer.valueOf(input.get(startIndex));
            startIndex ++;

            if(!uf.isConnected(p,q))
            {
                uf.union(p,q);
                System.out.println("singc" + uf.singleCost);
            }

        }
        long endMs = System.currentTimeMillis();
        System.out.println(uf.getConnCnt() + " Compoments !" + " passed time " + ((endMs-startMs)/1000));


    }
}
