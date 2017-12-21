package net.louis.collection.graph;

/**
 * Created by Louis on 2017/12/21.
 */
public class GraphComponent extends AbstractGraphSearch {

    private int gcMap[];
    private int gcCnt;

    private boolean[] binaryColor;

    private  boolean hasCycle;

    private  boolean isBipColorG;

    @Override
    public void build(BasicGraph bg, int stp) {

        this.bg = bg;
        this.stp = stp;
        this.gcCnt =0;
        connected = new boolean[bg.getVSize()];
        gcMap = new int[bg.getVSize()];
        binaryColor  = new boolean[bg.getVSize()];
        for(int i=0;i< bg.getVSize();i++)
            connected[i] = Boolean.FALSE;

        for(int i=0;i< bg.getVSize();i++)

        {
            if(!isConnected(i))
            {
                search(this.bg,i);
                gcCnt++;
            }
        }

        for(int i=0;i< bg.getVSize();i++)
            connected[i] = Boolean.FALSE;

        for(int i=0;i< bg.getVSize();i++)

        {
            if(!isConnected(i))
            {
                searchCycle(this.bg,i,i);
            }
        }

        for(int i=0;i< bg.getVSize();i++)
            connected[i] = Boolean.FALSE;

        for(int i=0;i< bg.getVSize();i++)

        {
            if(!isConnected(i))
            {
                searchCycle(this.bg,i,i);
            }
        }
    }

    @Override
    public void search(BasicGraph bg, int stp) {

       connected[stp] =true;
       gcMap[stp]=gcCnt;

        for(int w:bg.adj(stp))
        {
            if(!isConnected(w))
            {
                binaryColor[w] = !binaryColor[stp];
                search(bg,w);
            }

            else
            {
                isBipColorG = !(binaryColor[w] == binaryColor[stp]);
            }
        }

    }

    public void searchCycle(BasicGraph bg, int ctp,int bstp) {

        connected[stp] =true;

        for(int w:bg.adj(stp))
        {
            if(!isConnected(w))
            {
                searchCycle(bg,w,bstp);

            }

            else
            {
                hasCycle = (w !=bstp);

            }
        }

    }


}
