package ru.nsu.fit.g18208.Sofronova;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree<T> implements Iterator<T> {
    TreeNode<T> root;
    TreeNode<T> current;
    Queue<TreeNode<T>> bfsQueue;

    Tree(T r) {
        root = new TreeNode<T>(r);
        bfsQueue = new Queue<TreeNode<T>>();
        current = root;
    }

    Tree() {
        root = null;
        bfsQueue = new Queue<TreeNode<T>>();
        current = null;
    }

    void setRoot(T r) {
        root = new TreeNode<T>(r);
        current = root;
    }

    void add(T p, T c) {
        while (p != current.value) {
            nextBFS();
        }
        current.addChild(c);
        current = root;
        cleanQueue();

    }

    void remove(T c) {
        current=root;
        while (c != current.value) {
            nextBFS();
        }
        current = null;
        current = root;
    }

    /*
    ??????List<T> getSubThree(){


    }
     void addSubThree(T p,List<T> t){

    }*/
    void treeBFS() {
        bFS(root);
    }

    void bFS(TreeNode<T> e) {
        if (e == null){
            cleanQueue();
            return;
        }
        System.out.println(e.value);
        for (int i = 0; i < e.children.size(); i++)
            bfsQueue.push(e.children.get(i));
        bFS(bfsQueue.pop());


    }
void cleanQueue(){
    bfsQueue = new Queue<TreeNode<T>>();
}
    TreeNode<T> nextBFS() {
        for (int i = 0; i < current.children.size(); i++)
            bfsQueue.push(current.children.get(i));
        return current = bfsQueue.pop();
    }

    /*TreeNode<T> nextDFS(TreeNode<T> e){
    }*/
    void treeDFS() {
        dFS(root);

    }

    void dFS(TreeNode<T> e) {
        System.out.println(e.value);
        for (int i = 0; i < e.children.size(); i++)
            dFS(e.children.get(i));
    }

    boolean hasNext(TreeNode<T> e) {
        return e.children != null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}

class TreeNode<T> {
    T value;
    List<TreeNode<T>> children;

    TreeNode() {
        value = null;
        children = new LinkedList<>();
    }

    TreeNode(T e) {
        value = e;
        children = new LinkedList<>();

    }

    void addChild(T e) {
        TreeNode<T> n = new TreeNode<T>(e);
        children.add(n);
    }

}