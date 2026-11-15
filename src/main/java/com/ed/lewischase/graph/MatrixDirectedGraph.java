package com.ed.lewischase.graph;

/**
 * Representa un grafo dirigido mediante matriz de adyacencia (Cap. 24, PP 24.5).
 * Hereda de Graph<T> pero sus aristas son unidireccionales (origen -> destino).
 */
public class MatrixDirectedGraph<T> extends Graph<T> {

    public MatrixDirectedGraph() {
        super();
    }

    /**
     * Inserta una arista dirigida desde vertex1 (origen) hacia vertex2 (destino).
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @Override
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true; // Solo de origen (index1) a destino (index2)
            modCount++;
        }
    }

    /**
     * Elimina la arista dirigida desde vertex1 (origen) hacia vertex2 (destino).
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false; // Solo de origen (index1) a destino (index2)
            modCount++;
        }
    }
}