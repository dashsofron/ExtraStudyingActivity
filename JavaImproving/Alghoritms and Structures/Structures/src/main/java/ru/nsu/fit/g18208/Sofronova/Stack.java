package ru.nsu.fit.g18208.Sofronova;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represent stack structure container for T type elements
 *
 * @param <T> - type of elements
 */
public class Stack<T> implements Iterator<T> {

    Node<T> head;//last added element, but fist to go out
    Node<T> current;//needed for iterator
    int size;//size of stack

    Stack() {
        head = new Node<T>();
        current = head;
        size = 0;
    }

    int count() {
        return size;
    }

    /**
     * method for getting element from stack
     *
     * @return the most latest added element(FILO) - head (type T) or null if stack is empty
     */
    public T pop() {
        if (size == 0) return null;
        T out = head.next.value;
        head.next = head.next.next;
        return out;
    }

    /**
     * use for adding new element in container
     *
     * @param e - the new element which become the head of stack
     */
    public void push(T e) {
        if (e == null) return;
        Node<T> newNode = new Node<T>(e);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Iterator method which give information about stack end for iteration
     *
     * @return true if node has next element
     */
    @Override
    public boolean hasNext() {
        return current.next != null;
    }

    /**
     * @return next stack element in iteration or null if end was reached (+reset current to head)
     */
    public T next() {
        if (hasNext()) {
            Node<T> out = current;
            current = current.next;
            return out.value;
        }
        current = head;
        return null;
    }

    /**
     * method for removing current element during iteration
     */
    public void remove() {
        Node<T> out = current;
        current = head;
        while (current.next != out) current = current.next;
        current.next = out.next;
        current = current.next;
    }
}

/**
 * class-node in list for stack implementation
 * consist of value and next node in list
 *
 * @param <T> - type of stach elements
 */
class Node<T> {
    T value;
    Node<T> next;

    Node() {
        value = null;
        next = null;
    }

    Node(T e) {
        value = e;
        next = null;
    }

}