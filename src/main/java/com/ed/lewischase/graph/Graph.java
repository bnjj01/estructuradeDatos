package com.ed.lewischase.graph;

import java.util.Iterator;
import com.ed.lewischase.list.UnorderedList;
import com.ed.lewischase.list.UnorderedListADT;
import com.ed.lewischase.queue.EmptyQueueException;
import com.ed.lewischase.queue.LinkedQueue;
import com.ed.lewischase.queue.QueueADT;
import com.ed.lewischase.stack.EmptyStackException;
import com.ed.lewischase.stack.LinkedStack;
import com.ed.lewischase.stack.StackADT;

/**
 * Graph representa una implementación de un grafo no dirigido
 * utilizando una matriz de adyacencia (Lewis & Chase, Cap. 24).
 */
public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; // Número de vértices en el grafo
    protected boolean[][] adjMatrix; // Matriz de adyacencia
    protected T[] vertices; // Valores de los vértices
    protected int modCount;

    /**
     * Constructor: crea un grafo vacío.
     */
    @SuppressWarnings("unchecked")
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
        modCount = 0;
    }

    /**
     * Retorna el número de vértices en el grafo.
     */
    @Override
    public int size() {
        return numVertices;
    }

    /**
     * Informa si el grafo se encuentra vacío o no.
     */
    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    /**
     * Informa si el grafo está conectado.
     * Según Lewis & Chase: un grafo es conexo si y solo si el número de
     * vértices en el recorrido BFS es igual al número total de vértices.
     */
    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> it = iteratorBFS(0);
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }

        return (count == numVertices);
    }

    /**
     * Agrega un vértice al grafo, expandiendo la capacidad si es necesario.
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
        modCount++;
    }

    /**
     * Remueve un vértice del grafo.
     */
    @Override
    public void removeVertex(T vertex) {
        removeVertex(getIndex(vertex));
    }

    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            // 1. Desplazar los vértices en el arreglo
            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }
            vertices[numVertices] = null;

            // 2. Desplazar las filas de la matriz hacia arriba
            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j <= numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            // 3. Desplazar las columnas de la matriz hacia la izquierda
            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
                }
            }

            // Limpiar la última fila y columna
            for (int i = 0; i <= numVertices; i++) {
                adjMatrix[numVertices][i] = false;
                adjMatrix[i][numVertices] = false;
            }

            modCount++;
        }
    }

    /**
     * Agrega una arista entre dos vértices (simétrica por ser no dirigido).
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
            modCount++;
        }
    }

    /**
     * Elimina una arista entre dos vértices de un grafo.
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
            modCount++;
        }
    }

    /**
     * Retorna un iterador con el recorrido en anchura (BFS) desde startVertex.
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        QueueADT<Integer> traversalQueue = new LinkedQueue<Integer>();
        UnorderedListADT<T> resultList = new UnorderedList<T>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        try {
            while (!traversalQueue.isEmpty()) {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x]);

                // Buscar todos los vértices adyacentes a x no visitados
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[x][i] && !visited[i]) {
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            }
        } catch (EmptyQueueException e) {
            // Excepción controlada: el bucle verifica !isEmpty() previamente
        }

        return resultList.iterator();
    }

    /**
     * Retorna un iterador con el recorrido en profundidad (DFS) desde startVertex.
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        StackADT<Integer> traversalStack = new LinkedStack<Integer>();
        UnorderedListADT<T> resultList = new UnorderedList<T>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        try {
            while (!traversalStack.isEmpty()) {
                x = traversalStack.peek();
                found = false;

                // Buscar un vértice adyacente a x no visitado y apilarlo
                for (int i = 0; (i < numVertices) && !found; i++) {
                    if (adjMatrix[x][i] && !visited[i]) {
                        traversalStack.push(i);
                        resultList.addToRear(vertices[i]);
                        visited[i] = true;
                        found = true;
                    }
                }
                if (!found && !traversalStack.isEmpty()) {
                    traversalStack.pop();
                }
            }
        } catch (EmptyStackException e) {
            // Excepción controlada: el bucle verifica !isEmpty() previamente
        }

        return resultList.iterator();
    }

    /**
     * Retorna un iterador que contiene el camino más corto entre dos vértices.
     * Implementado según la Sección 24.4 de Lewis & Chase (variación de BFS
     * almacenando el predecesor de cada vértice).
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        UnorderedListADT<T> resultList = new UnorderedList<T>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return resultList.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
        while (it.hasNext()) {
            resultList.addToRear(vertices[it.next()]);
        }
        return resultList.iterator();
    }

    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index = startIndex;
        int[] predecessor = new int[numVertices];
        QueueADT<Integer> traversalQueue = new LinkedQueue<Integer>();
        UnorderedListADT<Integer> resultList = new UnorderedList<Integer>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        predecessor[startIndex] = -1;

        try {
            while (!traversalQueue.isEmpty() && (index != targetIndex)) {
                index = traversalQueue.dequeue();

                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[index][i] && !visited[i]) {
                        predecessor[i] = index;
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            }
        } catch (EmptyQueueException e) {
            // Excepción controlada
        }

        // Si no se llegó al vértice destino, no hay camino posible
        if (index != targetIndex) {
            return resultList.iterator();
        }

        // Reconstruir el camino desde targetIndex hacia startIndex usando una pila
        StackADT<Integer> stack = new LinkedStack<Integer>();
        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        try {
            while (!stack.isEmpty()) {
                resultList.addToRear(stack.pop());
            }
        } catch (EmptyStackException e) {
            // Excepción controlada
        }

        return resultList.iterator();
    }

    // =========================================================================
    // MÉTODOS AUXILIARES PROTEGIDOS
    // =========================================================================

    /**
     * Duplica la capacidad del arreglo de vértices y de la matriz de adyacencia.
     */
    @SuppressWarnings("unchecked")
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        boolean[][] largerAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    /**
     * Retorna el índice del vértice especificado, o -1 si no existe.
     */
    protected int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica si un índice es válido dentro del número actual de vértices.
     */
    protected boolean indexIsValid(int index) {
        return ((index >= 0) && (index < numVertices));
    }

    /**
     * Retorna una representación de la matriz de adyacencia en formato de String.
     */
    @Override
    public String toString() {
        if (numVertices == 0) {
            return "El grafo está vacío.";
        }

        StringBuilder result = new StringBuilder("Matriz de Adyacencia:\n\t");
        for (int i = 0; i < numVertices; i++) {
            result.append(vertices[i]).append("\t");
        }
        result.append("\n");

        for (int i = 0; i < numVertices; i++) {
            result.append(vertices[i]).append("\t");
            for (int j = 0; j < numVertices; j++) {
                result.append(adjMatrix[i][j] ? "T" : "F").append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
