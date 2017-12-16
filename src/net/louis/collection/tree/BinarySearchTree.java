package net.louis.collection.tree;

import java.security.Key;
import java.time.temporal.ValueRange;
import java.util.LinkedList;
import java.util.Queue;

import static net.louis.CompareUtil.compare;

public class BinarySearchTree <K,V> {

    private TreeNode<K,V> root;

    private int size(TreeNode<K,V> treeNode)
    {
        if(treeNode == null)
            return 0;
        else
            return treeNode.treeSize;
    }

    public int size()
    {
        return size(root);
    }

    public V get(K key)
    {
        return  get(root,key);
    }

    private V get(TreeNode<K,V> node,K key)
    {
        if(node == null)
            return null;
        if(compare(key,node.key)==0)
            return node.value;
        else if(compare(key,node.key) < 0)
            return  get(node.left,key);
        else
            return get(node.right,key);
    }

    public  void put(K key,V value)
    {
        root =put(key,value,root,0);
    }

    private TreeNode<K,V> put (K key, V value,TreeNode<K,V> node,int height)
    {
        if(node == null)
        {
            node = new TreeNode<>(key,value,1,height+1);
            return node;
        }

        int cmpResult = compare(key,node.key);

        if(cmpResult==0)
        {
            node.value = value;
        }

        else if(cmpResult > 0)
        {
            node.right= put(key,value,node.right,node.height);
        }

        else if(cmpResult < 0)
        {
            node.left = put(key,value,node.left,node.height);
        }

        node.treeSize = size(node.left)+size(node.right) + 1;

        return  node;

    }

    public K min()
    {
        return min(root).key;
    }


    public TreeNode<K,V> min(TreeNode<K,V> node)

    {
        if(node.left == null)
            return node;
        else
            return min(node.left);
    }

    public K max()
    {
        return max(root).key;
    }

    public TreeNode<K,V> max(TreeNode<K,V> node)

    {
        if(node.right == null)
            return node;
        else
            return min(node.right);
    }


    public K floor(K key)
    {
        return floor(key,root).key;
    }

    public TreeNode<K,V> floor(K key ,TreeNode<K,V> node)
    {
        if(node == null) return null;

        int cr = compare(key,node.key);

        if(cr == 0) return node;
        else if (cr < 0) return floor(key,node.left);
        else
        {
            TreeNode<K,V> right = floor(key,node.right);

            if(right == null)
                return node;
            else
                return right;


        }
    }


    public K celling(K key)
    {
        return celling(key,root).key;
    }

    public TreeNode<K,V> celling(K key ,TreeNode<K,V> node)
    {
        if(node == null) return null;

        int cr = compare(key,node.key);

        if(cr == 0) return node;
        else if (cr > 0) return celling(key,node.right);
        else
        {
            TreeNode<K,V> left = celling(key,node.left);

            if(left == null)
                return node;
            else
                return left;


        }
    }

    public K select(int i)
    {
        TreeNode<K,V> node = select(root,i-1);
        if(node == null)
            return null;
        else
            return node.key;
    }

    private  TreeNode<K,V> select(TreeNode<K,V> node,int i)
    {
        if(i > node.treeSize)
            return null;

        else
        {
            int leftSz = size(node.left);
            if(leftSz > i)
                return select(node.left,i);
            else if(leftSz == i)
                return node;
            else
                return select(node.right,i-leftSz-1);


        }

    }

    public int rank(K key)
    {
        return rank(root,key) +1;
    }
    private int rank(TreeNode<K,V> node,K key)
    {
        if(node == null) return 0;

        int cr =compare(key,node.key);

        if(cr == 0) return size(node.left);
        else if(cr >0) return size(node.left)+rank(node.right,key)+1;
        else return rank(node.left,key);

    }

    private TreeNode<K,V> deleteMin(TreeNode<K,V> node)
    {
        if(node == null) return null;

        if(node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.treeSize = size(node.left)+size(node.right) +1;

        return node;

    }

    public void deleteMin()
    {
        root = deleteMin(root);
    }

    private TreeNode<K,V> deleteMax(TreeNode<K,V> node)
    {
        if(node == null) return null;

        if(node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.treeSize = size(node.left)+size(node.right) +1;

        return node;

    }



    public void deleteMax()
    {
        root = deleteMax(root);
    }


    private TreeNode<K,V> delete(TreeNode<K,V>node,K key)
    {
        if(node == null) return null;
        int cr = compare(key,node.key);
        if(cr >0 )  node.right =   delete(node.right,key);
        else if(cr <0) node.left =  delete(node.left,key);
        else
        {
            if(node.left == null)
                return node.right;
            if(node.right == null)
                return node.left;

            TreeNode<K,V> tbd = node;
            node = min(tbd.right);
            node.right =deleteMin(tbd.right);
            node.left = tbd.left;
        }


        node.treeSize = size(node.left)+size(node.right) +1;

        return node;
    }

    public void delete(K key)
    {
        root = delete(root,key);
    }
    public void printWithMidLoop()
    {
        printWithMidLoop(root);
    }

    public void printWithMidLoop(TreeNode<K,V> node)
    {
        if(node == null)
            return;

        printWithMidLoop(node.left);

        printWithMidLoop(node.right);
        System.out.println(node.value);

    }

    public void printWithLevel()
    {

        TreeNode<K,V> tn  = null;

        Queue<TreeNode<K,V>> holder = new LinkedList<>();


        if(root !=null)
            holder.offer(root);

        int prevHeight = root.height ;

        String line = "";

        while(!holder.isEmpty())
        {
            tn = holder.poll();
            if(tn !=null)
            {
                if(tn.height > prevHeight)
                {
                    System.out.println(line);
                    line = tn.key.toString();
                }

                else
                    line += tn.key;

                prevHeight = tn.height;
            }

            if(tn.left!=null)
                holder.offer(tn.left);

            if(tn.right!=null)
                holder.offer(tn.right);

        }

        System.out.println(line);

    }

    public static void main(String args[])
    {
        BinarySearchTree<Integer,String> bst =new BinarySearchTree<>();
        bst.put(5,"5");
        bst.put(1,"1");
        bst.put(6,"6");

       /* bst.put(3,"3");
        bst.put(0,"0");
        bst.put(4,"4");
        bst.put(7,"7");*/

        bst.printWithLevel();
       /* System.out.println(bst.min());
        System.out.println(bst.max());
        System.out.println(bst.celling(2));
        System.out.println(bst.select(5));
        System.out.println(bst.rank(6));

       bst.delete(1);
        System.out.println(bst.size());
       bst.printWithMidLoop();*/


    }

}
