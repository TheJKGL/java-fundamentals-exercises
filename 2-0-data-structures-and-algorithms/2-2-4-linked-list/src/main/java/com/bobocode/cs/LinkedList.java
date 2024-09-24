package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    static class Node<T> {
        private T value;
        private Node nextNode;
        private Node prevNode;

        public Node() {
        }

        public Node(Node prevNode, T value, Node nextNode) {
            this.value = value;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        Stream.of(elements).forEach(linkedList::add);
        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> l = tail;
        Node<T> newNode = new Node<>(l, element, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.nextNode = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == size) {
            add(element);
        } else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.nextNode;
            }
            Node<T> newNode = new Node<>(node, node.value, node.nextNode);
            node.nextNode = newNode;
            node.value = element;
            size++;
        }
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (index > size || index == 0 || index == size) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == size - 1) {
            tail.value = element;
        } else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.nextNode;
            }
            node.value = element;
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == size - 1) {
            return tail.value;
        } else if (index == 0) {
            return head.value;
        } else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.nextNode;
            }
            return node.value;
        }
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return head.getValue();
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return tail.getValue();
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        throw new ExerciseNotCompletedException(); // todo: implement this method

//        if (index >= size) {
//            throw new IndexOutOfBoundsException();
//        }
//        Node<T> node = head;
//        for (int i = 0; i < index; i++) {
//            node = node.nextNode;
//        }
//        node.prevNode.nextNode = node.nextNode;
//        node.nextNode.prevNode = node.prevNode;
//        return node.value;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> node = head;
        for (int i = 0; i < size - 1; i++) {
            node = node.nextNode;
            if (node.value == element){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
