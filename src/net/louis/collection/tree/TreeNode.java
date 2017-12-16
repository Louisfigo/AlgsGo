package net.louis.collection.tree;

public class TreeNode <K,V> {

    K key;

    V value;

    TreeNode<K,V> left;

    TreeNode<K,V> right;

    int treeSize;

    int height;


    public TreeNode(K key, V value, int treeSize,int height) {
        this.key = key;
        this.value = value;
        this.treeSize = treeSize;
        this.height = height;
    }
}
