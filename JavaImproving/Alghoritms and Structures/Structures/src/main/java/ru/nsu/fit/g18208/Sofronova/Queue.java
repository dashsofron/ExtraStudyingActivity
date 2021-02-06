package ru.nsu.fit.g18208.Sofronova;


import java.awt.*;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * class-container, represent queue structure
 * @param <T> - type of elements in a queue
 */
public class Queue<T> implements Iterator<T> {
    QueueNode<T> head;//the most earlier added element among container elements
    QueueNode<T> current;//current element in iteration
    QueueNode<T> end;//the most latest added element among container elements
    int size;//size of a queue

    Queue() {
        size = 0;
    }

    /**
     * give information about number of elements in a queue
     *
     * @return digit type of int
     */
    public int getSize() {
        return size;
    }

    /**
     * method for getting element from a queue
     * @return the most earlier added element(FIFO) - head (type T) or null if queue is empty
     */
    public T pop() {
        if (!isEmpty()) {
            T out = head.value;
            head = head.next;
            return out;
        }
        return null;
    }

    /**
     * use for adding new element in a container
     *
     * @param e - the new element which become the end of queue
     *          if queue is empty, create a head of list. Otherwise just add the element in the end
     */
    public void push(T e) {
        if (size == 0) {
            head = new QueueNode<T>(e);
            end = head;
            current = head;
        } else {
            end.next = new QueueNode<T>(e);
            end = end.next;
        }
        size++;
    }

    /**
     * @return next queue element in iteration or null if end was reached (+reset current to head)
     */
    public T next() {
        if (hasNext()) {
            current = current.next;
            return current.value;
        }
        current = head;
        return null;
    }
    /**
     *
     * Iterator method which give information about queue end for iteration
     * @return true if node has next element
     */
    public boolean hasNext() {
        return current.next != null;
    }


    boolean isEmpty() {
        return size == 0;
    }

    /**
     * restart the current value for starting iteration from the head again
     */
    public void setStart() {
        current = head;
    }

    /**
     * remove all elements in container
     */
    public void cleanQueue() {
        while (hasNext()) remove();
    }

    /**
     * remove current element if queue is not empty
     */
    public void remove() {
        if(isEmpty())return;;
        QueueNode<T> nodeNum = current;
        current = head;
        while (current.next != nodeNum) next();
        current.next = nodeNum.next;
        current = current.next;

    }
}
/**
 * class-node in list for queue implementation
 * consist of value and next node in list
 * @param <T> - type of queue elements
 */
 class QueueNode<T> {
    T value;
    QueueNode<T> next;

    QueueNode() {
        value = null;
        next = null;
    }

    QueueNode(T e) {
        value = e;
        next = null;
    }

}
