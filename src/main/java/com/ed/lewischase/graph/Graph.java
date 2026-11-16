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

public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected boolean[][] adjMatrix;
    protected T[] vertices;
    protected int modCount;

    @SuppressWarnings("unchecked")
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
        modCount = 0;
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }

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

    @Override
    public void removeVertex(T vertex) {
        removeVertex(getIndex(vertex));
    }

    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }
            vertices[numVertices] = null;

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j <= numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
                }
            }

            for (int i = 0; i <= numVertices; i++) {
                adjMatrix[numVertices][i] = false;
                adjMatrix[i][numVertices] = false;
            }

            modCount++;
        }
    }

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

                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[x][i] && !visited[i]) {
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            }
        } catch (EmptyQueueException e) {
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
        }

        return resultList.iterator();
    }

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
        } catch (EmptyQueueException e) {}
        if (index != targetIndex) {
            return resultList.iterator();
        }

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

    @Override
    public UnorderedListADT<T> adyacentesA(T nodo) {
        return adyacentesA(getIndex(nodo));
    }

    public UnorderedListADT<T> adyacentesA(int index) {
        UnorderedListADT<T> adyacentes = new UnorderedList<T>();

        if (indexIsValid(index)) {
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[index][i]) {
                    adyacentes.addToRear(vertices[i]);
                }
            }
        }

        return adyacentes;
    }

    @Override
    public UnorderedListADT<T> inaccesibles() {
        UnorderedListADT<T> listaInaccesibles = new UnorderedList<T>();

        for (int j = 0; j < numVertices; j++) {
            boolean tieneEntrada = false;

            for (int i = 0; i < numVertices && !tieneEntrada; i++) {
                if (i != j && adjMatrix[i][j]) {
                    tieneEntrada = true;
                }
            }

            if (!tieneEntrada) {
                listaInaccesibles.addToRear(vertices[j]);
            }
        }

        return listaInaccesibles;
    }

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

    protected int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean indexIsValid(int index) {
        return ((index >= 0) && (index < numVertices));
    }

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
