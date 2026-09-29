package com.ed.lewischase.BinaryTree;

import com.ed.lewischase.LinkedNode.BinaryTreeNode;
import com.ed.lewischase.queue.EmptyQueueException;
import com.ed.lewischase.queue.LinkedQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LevelOrderIterator<T> implements Iterator<T> {

    private BinaryTreeNode<T> root;
    private LinkedQueue<BinaryTreeNode<T>> elements;

    public LevelOrderIterator(BinaryTreeNode<T> root) {
        this.root = root;
        elements = new LinkedQueue<>();
        levelOrderer(root);
    }
    private void levelOrderer( BinaryTreeNode<T> root){
        if(root != null){
            elements.enqueue(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !elements.isEmpty();
    }

    @Override
    public T next() {
        if(!hasNext())throw new NoSuchElementException();
        try{
            BinaryTreeNode<T> current = elements.dequeue();

            if(current.getLeft() != null)elements.enqueue(current.getLeft());
            if(current.getRight() != null)elements.enqueue(current.getRight());
            return current.getElement();
        }catch (EmptyQueueException e){
            throw new RuntimeException(e);
        }
    }
}
