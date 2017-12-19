package net.louis.collection.tree;

import net.louis.CompareUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
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

    public void flipColor2(RBTreeNode<K,V> tn)
    {
        tn.color = !tn.color;
        tn.left.color = !tn.left.color;
        tn.right.color =  !tn.right.color;
    }

    private RBTreeNode<K,V> balance(RBTreeNode<K,V> tn) {
        // assert (h != null);

        if (isRed(tn.right))                      tn = rtleft(tn);
        if (isRed(tn.left) && isRed(tn.left.left))tn = rtRight(tn);
        if (isRed(tn.left) && isRed(tn.right))     flipColor2(tn);

        tn.treeSize = size(tn.left) + size(tn.right) + 1;
        return tn;
    }

    public boolean isEmpty()
    {
        return  root == null;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private RBTreeNode<K,V>  deleteMin(RBTreeNode<K,V>  h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    private RBTreeNode<K,V> moveRedLeft(RBTreeNode<K,V> h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColor2(h);
        if (isRed(h.right.left)) {
            h.right = rtRight(h.right);
            h = rtleft(h);
            flipColor2(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private RBTreeNode<K,V>  moveRedRight(RBTreeNode<K,V>  h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColor2(h);
        if (isRed(h.left.left)) {
            h = rtRight(h);
            flipColor2(h);
        }
        return h;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private RBTreeNode<K,V>  deleteMax(RBTreeNode<K,V>  h) {
        if (isRed(h.left))
            h = rtRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }
    public boolean contains(K key) {
        return get(key) != null;
    }
    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private RBTreeNode<K,V>  delete(RBTreeNode<K,V>  h, K key) {
        // assert get(h, key) != null;

        if (CompareUtil.compare(key,h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rtRight(h);
            if (CompareUtil.compare(key,h.key)  == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (CompareUtil.compare(key,h.key)  == 0) {
                RBTreeNode<K,V> x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    public K min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private RBTreeNode<K,V> min(RBTreeNode<K,V> x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
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
