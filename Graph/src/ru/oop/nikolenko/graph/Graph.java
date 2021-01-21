package ru.oop.nikolenko.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private final int[][] edgesMatrix;

    public Graph(int[][] edgesMatrix) {
        if (edgesMatrix == null) {
            throw new IllegalArgumentException("edgesMatrix is null");
        }

        if (edgesMatrix.length != edgesMatrix[0].length) {
            throw new IllegalArgumentException("edgesMatrix not square");
        }

        for (int i = 0; i < edgesMatrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    if (edgesMatrix[i][j] != 0) {
                        throw new IllegalArgumentException("edgesMatrix[" + i + "][" + j + "] must be 0");
                    }

                    continue;
                }

                if (edgesMatrix[i][j] != edgesMatrix[j][i]) {
                    throw new IllegalArgumentException("edgesMatrix[" + i + "][" + j + "] must be equal edgesMatrix[" + j + "][" + i + "]");
                }
            }
        }

        this.edgesMatrix = edgesMatrix;
    }

    public void visitInDeep(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[edgesMatrix.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                Deque<Integer> stack = new LinkedList<>();
                stack.addLast(i);

                while (!stack.isEmpty()) {
                    int currentVertexNumber = stack.removeLast();

                    if (visited[currentVertexNumber]) {
                        continue;
                    }

                    consumer.accept(currentVertexNumber);
                    visited[currentVertexNumber] = true;

                    for (int vertexNumber = edgesMatrix[currentVertexNumber].length - 1; vertexNumber >= 0; vertexNumber--) {
                        if (edgesMatrix[currentVertexNumber][vertexNumber] == 1) {
                            stack.add(vertexNumber);
                        }
                    }
                }
            }
        }
    }

    public void visitInWidth(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[edgesMatrix.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                Queue<Integer> stack = new LinkedList<>();
                stack.add(i);

                while (!stack.isEmpty()) {
                    int currentVertexNumber = stack.remove();

                    if (visited[currentVertexNumber]) {
                        continue;
                    }

                    consumer.accept(currentVertexNumber);
                    visited[currentVertexNumber] = true;

                    for (int vertexNumber = 0; vertexNumber < edgesMatrix[currentVertexNumber].length; vertexNumber++) {
                        if (edgesMatrix[currentVertexNumber][vertexNumber] == 1) {
                            stack.add(vertexNumber);
                        }
                    }
                }
            }
        }
    }

    public void visitInDeepWithRecursion(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[edgesMatrix.length];

        for (int vertexNumber = 0; vertexNumber < edgesMatrix.length; vertexNumber++) {
            if (!visited[vertexNumber]) {
                visitInDeepWithRecursion(vertexNumber, visited, consumer);
            }
        }
    }

    private void visitInDeepWithRecursion(int beginningVertexNumber, boolean[] visited, Consumer<Integer> consumer) {
        consumer.accept(beginningVertexNumber);
        visited[beginningVertexNumber] = true;

        for (int vertexNumber = 0; vertexNumber < edgesMatrix[beginningVertexNumber].length; vertexNumber++) {
            if (edgesMatrix[beginningVertexNumber][vertexNumber] == 1 && !visited[vertexNumber]) {
                visitInDeepWithRecursion(vertexNumber, visited, consumer);
            }
        }
    }
}