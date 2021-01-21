package ru.oop.nikolenko.graph_main;

import ru.oop.nikolenko.graph.Graph;

import java.util.function.Consumer;

public class GraphMain {
    public static void main(String[] args) {
        int[][] edgesMatrix = {
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
        };

        Graph graph = new Graph(edgesMatrix);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        Consumer<Integer> consumer = x -> stringBuilder.append(x).append(", ");

        graph.visitInDeep(consumer);

        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
        } else {
            stringBuilder.append("}");
        }

        System.out.println(stringBuilder.toString());

        stringBuilder.delete(0, stringBuilder.length()).append("{");
        graph.visitInDeepWithRecursion(consumer);

        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
        } else {
            stringBuilder.append("}");
        }

        System.out.println(stringBuilder.toString());

        stringBuilder.delete(0, stringBuilder.length()).append("{");
        graph.visitInWidth(consumer);

        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
        } else {
            stringBuilder.append("}");
        }

        System.out.println(stringBuilder.toString());
    }
}
