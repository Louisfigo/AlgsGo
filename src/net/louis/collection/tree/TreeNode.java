package net.louis.collection.tree;

public class TreeNode <K,V> {

    K key;

    V value;

    TreeNode<K,V> left;

    TreeNode<K,V> right;

    int treeSize;

    public TreeNode(K key, V value, int treeSize) {
        this.key = key;
        this.value = value;
        this.treeSize = treeSize;
    }
}
