package com.ed.lewischase.heap;

import com.ed.lewischase.BinaryTree.ElementNotFoundException;
import com.ed.lewischase.LinkedNode.HeapNode; // Importación obligatoria al estar en otra carpeta
import java.util.Iterator;

public class LinkedHeap<T> implements HeapADT<T> {
    protected HeapNode<T> root;
    protected HeapNode<T> lastNode;
    protected int count;

    public LinkedHeap() {
        root = null;
        lastNode = null;
        count = 0;
    }

    /**
     * Añade el elemento especificado a este cúmulo en la posición adecuada.
     */
    public void addElement(T obj) {
        HeapNode<T> node = new HeapNode<T>(obj);

        if (root == null)
            root = node;
        else {
            HeapNode<T> next_parent = getNextParentAdd();
            if (next_parent.getLeft() == null)
                next_parent.setLeft(node);
            else
                next_parent.setRight(node);

            node.setParent(next_parent);
        }
        lastNode = node;
        count++;
        if (count > 1)
            heapifyAdd();
    }

    /**
     * Devuelve el nodo que será el padre del nuevo nodo.
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.getParent().getLeft() != result))
            result = result.getParent();

        if (result != root)
            if (result.getParent().getRight() == null)
                result = result.getParent();
            else {
                result = (HeapNode<T>) result.getParent().getRight();
                while (result.getLeft() != null)
                    result = (HeapNode<T>) result.getLeft();
            }
        else
            while (result.getLeft() != null)
                result = (HeapNode<T>) result.getLeft();

        return result;
    }

    /**
     * Reordena este cúmulo después de añadir un nodo.
     */
    @SuppressWarnings("unchecked")
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;
        temp = next.getElement();

        while ((next != root) && (((Comparable<T>) temp).compareTo(next.getParent().getElement()) < 0)) {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }
        next.setElement(temp);
    }

    /**
     * Elimina y devuelve el elemento con el valor más bajo de este cúmulo.
     */
    public T removeMin() {
        if (isEmpty())
            throw new EmptyCollectionException("Cúmulo");

        T minElement = root.getElement();

        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.getParent().getLeft() == lastNode)
                lastNode.getParent().setLeft(null);
            else
                lastNode.getParent().setRight(null);

            root.setElement(lastNode.getElement());
            lastNode = next_last;
            heapifyRemove();
        }

        count--;
        return minElement;
    }

    /**
     * Devuelve el nodo que será el nuevo último nodo después de una eliminación.
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.getParent().getLeft() == result))
            result = result.getParent();

        if (result != root)
            result = (HeapNode<T>) result.getParent().getLeft();

        while (result.getRight() != null)
            result = (HeapNode<T>) result.getRight();

        return result;
    }

    /**
     * Reordena este cúmulo después de eliminar el elemento raíz.
     */
    @SuppressWarnings("unchecked")
    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = root;
        HeapNode<T> left = (HeapNode<T>) node.getLeft();
        HeapNode<T> right = (HeapNode<T>) node.getRight();
        HeapNode<T> next;

        if ((left == null) && (right == null))
            next = null;
        else if (left == null)
            next = right;
        else if (right == null)
            next = left;
        else if (((Comparable<T>) left.getElement()).compareTo(right.getElement()) < 0)
            next = left;
        else
            next = right;

        temp = node.getElement();
        while ((next != null) && (((Comparable<T>) next.getElement()).compareTo(temp) < 0)) {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();

            if ((left == null) && (right == null))
                next = null;
            else if (left == null)
                next = right;
            else if (right == null)
                next = left;
            else if (((Comparable<T>) left.getElement()).compareTo(right.getElement()) < 0)
                next = left;
            else
                next = right;
        }
        node.setElement(temp);
    }

    /**
     * Devuelve una referencia al elemento con el valor más bajo en este cúmulo.
     */
    public T findMin() {
        if (isEmpty())
            throw new EmptyCollectionException("Cúmulo");
        return root.getElement();
    }

    // Métodos heredados
    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public void removeLeftSubtree() {
        throw new UnsupportedOperationException("Operación no soportada en un cúmulo");
    }

    @Override
    public void removeRightSubtree() {
        throw new UnsupportedOperationException("Operación no soportada en un cúmulo");
    }

    @Override
    public void removeAllElements() {
        throw new UnsupportedOperationException("Operación no soportada en un cúmulo");
    }

    @Override
    public boolean contains(T targetElement) {
        return false;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        return null;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        return null;
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        return null;
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        return null;
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        return null;
    }
}