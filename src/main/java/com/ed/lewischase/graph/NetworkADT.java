package com.ed.lewischase.graph;

/**
 * NetworkADT define la interfaz para una red.
 *
 */
public interface NetworkADT<T> extends GraphADT<T> {
    /**
     * Inserta una arista con peso entre dos vértices del grafo.
     *
     * @param vertex1 el primer vértice (origen)
     * @param vertex2 el segundo vértice (destino)
     * @param weight el peso o costo de la arista
     */
    public void addEdge(T vertex1, T vertex2, double weight);

    /**
     * Retorna el peso total del camino más económico en esta red.
     *
     * @param vertex1 el vértice de origen
     * @param vertex2 el vértice de destinos
     * @return el peso/costo del camino mínimo
     */
    public double shortestPathWeight(T vertex1, T vertex2);
}