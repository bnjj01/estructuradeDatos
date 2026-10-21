package com.ed.lewischase.tps.practica;

import com.ed.lewischase.heap.LinkedHeap;

public class PruebaHeap {
    public static void main(String[] args) {
        LinkedHeap<Integer> cumulo = new LinkedHeap<>();

        cumulo.addElement(23);
        cumulo.addElement(3);
        cumulo.addElement(2);
        cumulo.addElement(45);
        cumulo.addElement(65);

        System.out.println("Elemento Mínimo: ");
        System.out.println(cumulo.findMin());

        System.out.println("Elimino un elemento: ");
        System.out.println("Elemento eliminado ("+cumulo.removeMin()+")");


    }
}
