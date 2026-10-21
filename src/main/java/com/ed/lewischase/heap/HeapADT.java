package com.ed.lewischase.heap;

import com.ed.lewischase.BinaryTree.BinaryTreeADT;

public interface HeapADT<T> extends BinaryTreeADT<T> {
    void addElement(T obj);

    T removeMin();

    T findMin();
}
