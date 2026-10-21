package com.ed.lewischase.tps.practica;

import com.ed.lewischase.heap.HeapSort;
import com.ed.lewischase.heap.LinkedHeap;

import java.util.Arrays;

public class PruebaHeap {
    public static void main(String[] args) {
        LinkedHeap<Integer> cumulo = new LinkedHeap<>();
        System.out.println("Añado los elementos: 23, 3, 2, 45, 65");
        cumulo.addElement(23);
        cumulo.addElement(3);
        cumulo.addElement(2);
        cumulo.addElement(45);
        cumulo.addElement(65);


        System.out.println("- Cantidad de elementos: "+cumulo.size());
        System.out.println("- Busco el elemento Mínimo: "+cumulo.findMin());

        System.out.println("--Elimino un elemento con removeMin()-- ");
        System.out.println("- Elemento eliminado ("+cumulo.removeMin()+")");

        System.out.println("Prueba de inciso 7 tp5");

        Integer[] listaDesordenada = {23,34,54,2,3,5,90};

        System.out.println("Lista inicial: "+ Arrays.toString(listaDesordenada));

        HeapSort<Integer> ordenador = new HeapSort<>();
        ordenador.HeapSort(listaDesordenada,0,listaDesordenada.length-1);

        System.out.println("Lista ordenada: "+Arrays.toString(listaDesordenada));
    }
}
