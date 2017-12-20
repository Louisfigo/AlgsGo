package net.louis.collection.graph;

/**
 * Created by Louis on 2017/12/20.
 */
public class GraphHelper {

    public static int avgDegree(BasicGraph bg, int v)
    {

        return (bg.getESize()/bg.getVSize())*2;
    }

    public static int degree(BasicGraph bg, int v)
    {
        int degree =0 ;
       for(int w:bg.adj(v))
           degree++;

       return degree;
    }

    public static int maxDegree(BasicGraph bg)
    {
        int max=0;

        for(int v=0;v< bg.getVSize();v++)
        {
            if(degree(bg,v) >max)
                max = degree(bg,v);
        }

        return  max;
    }


    public static int numOfSelfLoops(BasicGraph bg)
    {
        int cnt=0;

        for(int v=0;v< bg.getVSize();v++)
        {
            for(int w:bg.adj(v))
                if (v==w) cnt++;
        }

        return  cnt;
    }
}


