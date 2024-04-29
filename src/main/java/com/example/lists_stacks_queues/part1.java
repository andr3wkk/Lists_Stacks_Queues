package com.example.lists_stacks_queues;

import java.util.Iterator;

public class part1<T> implements Iterable<T> {

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void add(T e) {
        addLast(e);
    }

    public void add(int i, T e) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            Node<T> succ = getNode(i);
            Node<T> pred = succ.prev;
            Node<T> newNode = new Node<>(pred, e, succ);
            pred.next = newNode;
            succ.prev = newNode;
            size++;
        }
    }

    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        Node<T> node = getNode(i);
        return node.data;
    }

    public void remove(T e) {
        Node<T> node = first;
        while (node != null) {
            if (node.data.equals(e)) {
                unlink(node);
                return;
            }
            node = node.next;
        }
    }

    public void remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        Node<T> node = getNode(i);
        unlink(node);
    }

    public void removeAll(T e) {
        Node<T> node = first;
        while (node != null) {
            Node<T> next = node.next;
            if (node.data.equals(e)) {
                unlink(node);
            }
            node = next;
        }
    }

    public void addFirst(T e) {
        if (size == 0) {
            first = last = new Node<>(null, e, null);
        } else {
            Node<T> newNode = new Node<>(null, e, first);
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> node = first;
        while (node != null) {
            sb.append(node.data);
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private void addLast(T e) {
        if (size == 0) {
            first = last = new Node<>(null, e, null);
        } else {
            Node<T> newNode = new Node<>(last, e, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void unlink(Node<T> node) {
        Node<T> pred = node.prev;
        Node<T> succ = node.next;

        if (pred == null) {
            first = succ;
        } else {
            pred.next = succ;
            node.prev = null;
        }

        if (succ == null) {
            last = pred;
        } else {
            succ.prev = pred;
            node.next = null;
        }

        node.data = null;
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>();
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> nextNode = (Node<T>) first;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements");
            }
            T data = nextNode.data;
            nextNode = nextNode.next;
            return data;
        }
    }

    public static void main(String[] args) {
        part1<Integer> list = new part1<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Original list: " + list);

        list.add(1, 5);
        System.out.println("After adding 5 at index 1: " + list);

        list.remove(2);
        System.out.println("After removing element at index 2: " + list);

        System.out.println("Element at index 1: " + list.get(1));
        System.out.println("List size: " + list.getSize());

        list.removeAll(5);
        System.out.println("After removing all occurrences of 5: " + list);

        for (Integer item : list) {
            System.out.println(item);
        }
    }
}
