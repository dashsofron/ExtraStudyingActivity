package ru.nsu.fit.g18208.Sofronova;

import java.util.Iterator;

/**
 * class-container, represent priority queue structure
 * @param <T1> - type of key value
 * @param <T2> - type of element value
 */
public class PriorityQueue<T1 extends Comparable,T2> implements Iterator<T2> {
    PQueueNode<T1,T2> head;//begin of a nodes list
    PQueueNode<T1,T2> current;// current node for iteration
    int size;
    PriorityQueue(T1 k,T2 v){
        head=new PQueueNode<T1,T2>(k, v);
        current=head;
        size=1;
    }
    PriorityQueue(){
        size=0;
    }

    /**
     * method for adding some pair of key/value to the queue
     *  if queue is empty, create a head of list. Otherwise just add the element in the end
     * @param k - key value
     * @param e - element value
     */
    public void Insert(T1 k,T2 e){
        if(size==0){
            head=new PQueueNode<T1,T2>(k,e);
            current=head;
        }
        else {current.next=new PQueueNode<T1,T2>(k, e);
        current=current.next;}
        size++;

    }

    /**
     * get some element value from pair with the biggest key value
     * @return if queue is empty 0. Otherwise find the biggest key value and return element value,associated with the key
     */
    public T2 extract_minimum(){
        if(isEmpty())return null;
        PQueueNode<T1,T2> out=head.next;
        current=head.next;
        while (this.hasNext()){
            current=current.next;
            if (out.key.compareTo(current.key)<0) out=current;
        }
        return out.value;
    }

    /**
     * @return next queue element in iteration or null if end was reached (+reset current to head)
     */

    public T2 next(){
        current=current.next;
        return current.value;
    }
    /**
     *
     * Iterator method which give information about queue end for iteration
     * @return true if node has next element
     */
    public boolean hasNext(){
        return current.next != null;
    }
    boolean isEmpty() {
        return size == 0;
    }
    /**
     * remove current element if queue is not empty
     */
    public void remove(){
        if(isEmpty()) return;;
        PQueueNode<T1,T2> nodeNum=current;
        current=head;
        while (current.next!=nodeNum)next();
        current.next=nodeNum.next;
        current=current.next;

    }

}

/**
 *  class-node in list for priprity queue implementation
 *  consist of key,value and next node in list
 * @param <T1> - type of key value
 * @param <T2> - type of element value
 */
 class PQueueNode<T1,T2>{
    T1 key;
    T2 value;
     PQueueNode<T1,T2> next;
     PQueueNode(){
        key=null;
        value=null;
        next=null;
    }
     PQueueNode(T1 k,T2 v){
        key=k;
        value=v;
        next=null;
    }

}