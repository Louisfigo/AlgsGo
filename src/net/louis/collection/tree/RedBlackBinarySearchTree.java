package net.louis.collection.tree;

import net.louis.CompareUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static net.louis.CompareUtil.compare;

public class RedBlackBinarySearchTree <K,V> {

    private static final boolean RED = Boolean.TRUE;
    private static final boolean BLACK =Boolean.FALSE;


    private  RBTreeNode<K,V> root ;

    private  class RBTreeNode<K,V>
    {
        K key;
        V value;
        boolean color;
        int treeSize;

        RBTreeNode<K,V> left;
        RBTreeNode<K,V> right;


        public RBTreeNode(K key, V value, boolean color, int treeSize) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.treeSize = treeSize;
        }
    }

    private boolean isRed(RBTreeNode<K,V> tn)
    {
        if(tn == null) return  false;
        else  return tn.color;
    }

    private RBTreeNode<K,V> rtleft(RBTreeNode<K,V> ltn)
    {
        RBTreeNode<K,V>  rtn = ltn.right;
        ltn.right = rtn.left;
        rtn.left = ltn;

        rtn.color = ltn.color;
        ltn.color =RED;
        rtn.treeSize = ltn.treeSize;
        ltn.treeSize = size(ltn.left)+size(ltn.right) +1;
        return rtn;
    }


    private RBTreeNode<K,V> rtRight(RBTreeNode<K,V> rtn)
    {
        RBTreeNode<K,V>  ltn = rtn.left;
        rtn.left = ltn.right;
        ltn.right = rtn;

        ltn.color = rtn.color;
        rtn.color =RED;
        ltn.treeSize = rtn.treeSize;
        rtn.treeSize = size(rtn.left)+size(rtn.right) +1;
        rtn.treeSize = size(rtn.left)+size(rtn.right) +1;
        return rtn;
    }
    private int size(RBTreeNode<K,V> treeNode)
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

    public void flipColor(RBTreeNode<K,V> tn)
    {
        tn.color = RED;
        tn.left.color = BLACK;
        tn.right.color = BLACK;
    }

    public void put (K key,V value)
    {
        root = put(root,key,value);
    }

    private RBTreeNode<K,V>  put (RBTreeNode<K,V> tn,K key,V value)
    {
        if(tn == null)
            return new RBTreeNode<>(key,value,RED,1);

        int cmp = CompareUtil.compare(key,tn.key);
        if (cmp < 0) tn.left = put(tn.left,key,value);
        else if(cmp >0) tn.right = put(tn.right,key,value);
        else  tn.value =value;

        if(isRed(tn.right) && !isRed(tn.left)) tn = rtleft(tn);
        if(isRed(tn.right) && isRed(tn.left.left)) tn = rtRight(tn);
        if(isRed(tn.right) && isRed(tn.left)) flipColor(tn);

        tn.treeSize = size(tn.left) + size(tn.right) +1;

        return tn;
    }

    public V get(K key)
    {
        return  get(root,key);
    }

    private V get(RBTreeNode<K,V> node,K key)
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

    public void printWithMidLoop()
    {
        printWithMidLoop(root);
    }

    public void printWithMidLoop(RBTreeNode<K,V> node)
    {
        if(node == null)
            return;

        printWithMidLoop(node.left);

        printWithMidLoop(node.right);
        System.out.println(node.key + "|" +node.value);

    }
    public  static  void main(String args[])
    {
        RedBlackBinarySearchTree<String,Integer> sst = new RedBlackBinarySearchTree<>();
        String fileName = "D:\\j2eeWp\\algs4-data\\leipzig1M.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(s->
            {
                String []oneLine = s.split(" ");
                  for(String s1:oneLine)
                {

               if(sst.get(s1)!=null)
                   {
                       int i = sst.get(s1) +1;
                       sst.put(s1,i);
                   }

                   else
                   {
                       sst.put(s1,1);
                   }

                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        sst.printWithMidLoop();

    }
}
