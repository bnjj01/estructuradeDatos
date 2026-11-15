package com.ed.lewischase.graph;

import java.util.Iterator;

public class GraphMain {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA 1: Grafo de la Figura 24.7 (Recorridos y Camino Más Corto) ===");
        GraphADT<Integer> grafo1 = new Graph<Integer>();

        System.out.println("¿El grafo está vacío inicialmente?: " + grafo1.isEmpty());

        for (int i = 1; i <= 9; i++) {
            grafo1.addVertex(i);
        }

        grafo1.addEdge(1, 2);
        grafo1.addEdge(1, 3);
        grafo1.addEdge(1, 4);
        grafo1.addEdge(2, 4);
        grafo1.addEdge(2, 5);
        grafo1.addEdge(3, 6);
        grafo1.addEdge(4, 6);
        grafo1.addEdge(5, 7);
        grafo1.addEdge(5, 8);
        grafo1.addEdge(6, 9);
        grafo1.addEdge(7, 9);
        grafo1.addEdge(8, 9);

        System.out.println("Cantidad de vértices (size): " + grafo1.size());
        System.out.println("¿El grafo está vacío?: " + grafo1.isEmpty());
        System.out.println("¿El grafo es conexo (isConnected)?: " + grafo1.isConnected());

        System.out.print("\nRecorrido en Anchura (BFS) desde el vértice 9:\n-> ");
        imprimirIterador(grafo1.iteratorBFS(9));

        System.out.print("Recorrido en Profundidad (DFS) desde el vértice 9:\n-> ");
        imprimirIterador(grafo1.iteratorDFS(9));

        System.out.print("Camino más corto entre 9 y 1:\n-> ");
        imprimirIterador(grafo1.iteratorShortestPath(9, 1));


        System.out.println("\n=========================================================================");
        System.out.println("=== PRUEBA 2: Grafo de las Figuras 24.8 y 24.13 (Eliminación y Matriz) ===");
        GraphADT<String> grafoLetras = new Graph<String>();

        grafoLetras.addVertex("A");
        grafoLetras.addVertex("B");
        grafoLetras.addVertex("C");
        grafoLetras.addVertex("D");

        grafoLetras.addEdge("A", "B");
        grafoLetras.addEdge("A", "C");
        grafoLetras.addEdge("B", "C");
        grafoLetras.addEdge("B", "D");

        System.out.println("\nEstado inicial del grafo (Figura 24.14):");
        System.out.print(grafoLetras.toString());
        System.out.println("¿Es conexo?: " + grafoLetras.isConnected());

        System.out.println("\n--- Eliminando arista entre B y D (removeEdge) ---");
        grafoLetras.removeEdge("B", "D");
        System.out.print(grafoLetras.toString());
        System.out.println("¿Sigue siendo conexo al quedar D aislado?: " + grafoLetras.isConnected());
        System.out.print("BFS desde A (como en Figura 24.10): ");
        imprimirIterador(grafoLetras.iteratorBFS("A"));

        System.out.println("\n--- Eliminando el vértice B (removeVertex) ---");
        grafoLetras.removeVertex("B");
        System.out.println("Nuevo tamaño (size): " + grafoLetras.size());
        System.out.print(grafoLetras.toString());
    }

    /**
     * Método auxiliar para recorrer e imprimir cualquier iterador del grafo.
     */
    private static <T> void imprimirIterador(Iterator<T> it) {
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}
