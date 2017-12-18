package net.louis.collection.table;

/**
 * Created by Louis on 2017/12/18.
 */
public class SequentialSearchST <K,V> {

    private SearchNode<K,V> head;
    private class SearchNode<K,V>{

        K key ;
        V value;
        SearchNode<K,V> next;

        public SearchNode(K key, V value, SearchNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(K key,V value)
    {
        if (head == null)
            head = new SearchNode<>(key,value,null);
        else
        {
            SearchNode<K,V> currPt = head;
            while (currPt !=null)
            {
                if(currPt.key.equals(key))
                {
                    currPt.value = value;
                    break;
                }
                else
                    currPt = currPt.next;
            }

            if (currPt == null)
            {
                currPt = new SearchNode<>(key,value,head);
                head =currPt;
            }


        }
    }

    public V get(K key)
    {
        SearchNode<K,V> currPt = head;
        while (currPt !=null)
        {
            if(currPt.key.equals(key))
                break;

            else
                currPt = currPt.next;
        }

        if (currPt != null)
          return currPt.value;
        else
            return null;
    }

    public void printAll()
    {
        SearchNode<K,V> currPt = head;
        while (currPt !=null)
        {
            if(currPt.key!=null)
                System.out.println(currPt.key.toString());

                currPt = currPt.next;
        }

    }

    public static void main(String args[])
    {
        SequentialSearchST<Integer,Integer> sst = new SequentialSearchST<>();
        sst.put(1,1);
        sst.put(2,2);
        sst.put(3,3);
        sst.put(4,4);
        sst.printAll();

    }

}
