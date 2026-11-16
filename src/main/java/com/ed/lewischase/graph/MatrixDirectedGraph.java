package com.ed.lewischase.graph;

import java.util.Iterator;
import com.ed.lewischase.heap.EmptyCollectionException;
import com.ed.lewischase.heap.HeapADT;
import com.ed.lewischase.heap.LinkedHeap;
import com.ed.lewischase.list.UnorderedList;
import com.ed.lewischase.list.UnorderedListADT;
import com.ed.lewischase.stack.EmptyStackException;
import com.ed.lewischase.stack.LinkedStack;
import com.ed.lewischase.stack.StackADT;

/**
 * Representa un grafo dirigido y ponderado (Red Dirigida) mediante matriz de adyacencia y de pesos.
 * Implementa el algoritmo de Dijkstra según Lewis & Chase (Cap. 24, Sección 24.4).
 */
public class MatrixDirectedGraph<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] weights; // Matriz de costos/pesos para las aristas

    /**
     * Clase interna auxiliar para almacenar pares (vértice, pesoAcumulado)
     * en el MinHeap durante la ejecución del algoritmo de Dijkstra,
     * tal como especifica la Sección 24.4 de Lewis & Chase.
     */
    private static class ParCamino implements Comparable<ParCamino> {
        int indiceVertice;
        double pesoAcumulado;

        ParCamino(int indiceVertice, double pesoAcumulado) {
            this.indiceVertice = indiceVertice;
            this.pesoAcumulado = pesoAcumulado;
        }

        @Override
        public int compareTo(ParCamino otro) {
            return Double.compare(this.pesoAcumulado, otro.pesoAcumulado);
        }
    }

    public MatrixDirectedGraph() {
        super();
        this.weights = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            for (int j = 0; j < DEFAULT_CAPACITY; j++) {
                this.weights[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
            weights[numVertices][i] = Double.POSITIVE_INFINITY;
            weights[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
        modCount++;
    }

    @Override
    protected void expandCapacity() {
        double[][] largerWeights = new double[vertices.length * 2][vertices.length * 2];
        for (int i = 0; i < largerWeights.length; i++) {
            for (int j = 0; j < largerWeights.length; j++) {
                largerWeights[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerWeights[i][j] = weights[i][j];
            }
        }
        weights = largerWeights;
        super.expandCapacity();
    }

    @Override
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            int last = numVertices - 1;

            // Desplazar filas de pesos hacia arriba
            for (int i = index; i < last; i++) {
                for (int j = 0; j <= last; j++) {
                    weights[i][j] = weights[i + 1][j];
                }
            }
            // Desplazar columnas de pesos hacia la izquierda
            for (int i = index; i < last; i++) {
                for (int j = 0; j < last; j++) {
                    weights[j][i] = weights[j][i + 1];
                }
            }
            for (int i = 0; i <= last; i++) {
                weights[last][i] = Double.POSITIVE_INFINITY;
                weights[i][last] = Double.POSITIVE_INFINITY;
            }
            super.removeVertex(index);
        }
    }

    /**
     * Si se agrega una arista sin especificar peso, se le asigna costo 1.0 por defecto.
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 1.0);
    }

    @Override
    public void addEdge(int index1, int index2) {
        addEdge(index1, index2, 1.0);
    }

    /**
     * Inserta una arista dirigida desde vertex1 hacia vertex2 con el costo/peso especificado.
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            weights[index1][index2] = weight;
            modCount++;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            weights[index1][index2] = Double.POSITIVE_INFINITY;
            modCount++;
        }
    }

    // =========================================================================
    // ALGORITMO DE DIJKSTRA (Camino de Costo Mínimo - Lewis & Chase Cap. 24)
    // =========================================================================

    /**
     * Ejecuta el algoritmo de Dijkstra desde startIndex hasta targetIndex
     * utilizando un MinHeap (LinkedHeap) de pares (vértice, pesoAcumulado),
     * y devuelve un iterador con los índices de los vértices del camino mínimo.
     */
    protected Iterator<Integer> iteratorDijkstraIndices(int startIndex, int targetIndex) {
        UnorderedListADT<Integer> resultList = new UnorderedList<Integer>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return resultList.iterator();
        }

        double[] minWeight = new double[numVertices];
        int[] predecessor = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            minWeight[i] = Double.POSITIVE_INFINITY;
            predecessor[i] = -1;
            visited[i] = false;
        }

        minWeight[startIndex] = 0.0;
        HeapADT<ParCamino> minHeap = new LinkedHeap<ParCamino>();
        minHeap.addElement(new ParCamino(startIndex, 0.0));

        try {
            while (!minHeap.isEmpty()) {
                ParCamino actual = minHeap.removeMin();
                int u = actual.indiceVertice;

                if (visited[u]) {
                    continue;
                }
                visited[u] = true;

                if (u == targetIndex) {
                    break; // Llegamos al destino por el camino más económico
                }

                // Explorar los vértices adyacentes a u
                for (int v = 0; v < numVertices; v++) {
                    if (adjMatrix[u][v] && !visited[v]) {
                        double nuevoCosto = minWeight[u] + weights[u][v];
                        if (nuevoCosto < minWeight[v]) {
                            minWeight[v] = nuevoCosto;
                            predecessor[v] = u;
                            minHeap.addElement(new ParCamino(v, nuevoCosto));
                        }
                    }
                }
            }
        } catch (EmptyCollectionException e) {
            // Excepción controlada
        }

        // Si el costo sigue siendo infinito, no existe conexión entre ambos vértices
        if (minWeight[targetIndex] == Double.POSITIVE_INFINITY) {
            return resultList.iterator();
        }

        // Reconstruir el camino desde targetIndex hacia startIndex usando una pila
        StackADT<Integer> stack = new LinkedStack<Integer>();
        int step = targetIndex;
        while (step != -1) {
            stack.push(step);
            step = predecessor[step];
        }

        try {
            while (!stack.isEmpty()) {
                resultList.addToRear(stack.pop());
            }
        } catch (EmptyStackException e) {
            // Excepción controlada
        }

        return resultList.iterator();
    }

    /**
     * Retorna el costo total del camino mínimo entre vertex1 y vertex2 usando Dijkstra.
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        int startIndex = getIndex(vertex1);
        int targetIndex = getIndex(vertex2);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }
        if (startIndex == targetIndex) {
            return 0.0;
        }

        Iterator<Integer> it = iteratorDijkstraIndices(startIndex, targetIndex);
        if (!it.hasNext()) {
            return Double.POSITIVE_INFINITY;
        }

        double total = 0.0;
        int prev = it.next();
        while (it.hasNext()) {
            int curr = it.next();
            total += weights[prev][curr];
            prev = curr;
        }
        return total;
    }

    /**
     * Devuelve un informe detallado (vértices, aristas y costos individuales/totales)
     * del camino mínimo entre startVertex y targetVertex calculado con Dijkstra.
     */
    public String obtenerDetalleCaminoMinimoDijkstra(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return "Error: Uno o ambos vértices no existen en el grafo.";
        }

        if (startIndex == targetIndex) {
            return "Camino Mínimo (Dijkstra) de " + startVertex + " a " + targetVertex + ":\n"
                    + "• Vértices: " + startVertex + "\n"
                    + "• Aristas: Ninguna (origen y destino son el mismo vértice)\n"
                    + "• Costo Total: 0.0";
        }

        Iterator<Integer> it = iteratorDijkstraIndices(startIndex, targetIndex);
        if (!it.hasNext()) {
            return "No existe un camino dirigido que conecte " + startVertex + " con " + targetVertex + ".";
        }

        StringBuilder sbVertices = new StringBuilder();
        StringBuilder sbAristas = new StringBuilder();
        double costoTotal = 0.0;

        int prev = it.next();
        sbVertices.append(vertices[prev]);

        while (it.hasNext()) {
            int curr = it.next();
            double costoArista = weights[prev][curr];
            costoTotal += costoArista;

            sbVertices.append(" -> ").append(vertices[curr]);
            sbAristas.append("   (").append(vertices[prev]).append(" -> ")
                    .append(vertices[curr]).append(") : costo ").append(costoArista).append("\n");

            prev = curr;
        }

        return "=== Camino Mínimo (Algoritmo de Dijkstra) ===\n"
                + "• Vértices del camino: " + sbVertices.toString() + "\n"
                + "• Aristas y sus costos:\n" + sbAristas.toString()
                + "• Costo Total Mínimo: " + costoTotal;
    }

    /**
     * Muestra la matriz de adyacencia indicando el costo de cada arista (o 'F' si no hay arista).
     */
    @Override
    public String toString() {
        if (numVertices == 0) {
            return "El grafo está vacío.";
        }

        StringBuilder result = new StringBuilder("Matriz de Costos / Adyacencia:\n\t");
        for (int i = 0; i < numVertices; i++) {
            result.append(vertices[i]).append("\t");
        }
        result.append("\n");

        for (int i = 0; i < numVertices; i++) {
            result.append(vertices[i]).append("\t");
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j]) {
                    result.append(weights[i][j]).append("\t");
                } else {
                    result.append("F\t");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}