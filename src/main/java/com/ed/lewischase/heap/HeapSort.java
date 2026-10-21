package com.ed.lewischase.heap;

public class HeapSort<T> {

    /**
     * Ordena un arreglo utilizando la lógica del cúmulo.
     * @param data los datos que se van a añadir y ordenar
     * @param min el valor del índice mínimo (generalmente 0)
     * @param max el valor del índice máximo (generalmente data.length - 1)
     */
    public void HeapSort(T[] data, int min, int max) {

        LinkedHeap<T> temp = new LinkedHeap<T>();

        for (int ct = min; ct <= max; ct++) {
            temp.addElement(data[ct]);
        }

        int count = min;
        while (!(temp.isEmpty())) {
            data[count] = temp.removeMin();
            count++;
        }
    }
}