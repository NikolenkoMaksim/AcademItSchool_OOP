package ru.oop.nikolenko.my_grath;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class MyGrath {
    private final int[][] myGrath;

    public MyGrath(int[][] myGrath) {
        this.myGrath = myGrath;
    }

    public String getStringByVisitingInDeep() {
        if (myGrath == null) {
            return "{}";
        }

        boolean[] visited = new boolean[myGrath.length];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{{");

        Deque<Integer> stack = new LinkedList<>();
        stack.addLast(0);

        while (true) {
            if (stack.isEmpty()) {
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
                int notVisited = getNotVisitedVertex(visited);

                if (notVisited == -1) {
                    break;
                }

                stack.addLast(notVisited);
                stringBuilder.append(", {");
            }

            int currentVertexNumber = stack.removeLast();

            if (visited[currentVertexNumber]) {
                continue;
            }

            stringBuilder.append(currentVertexNumber).append(", ");
            visited[currentVertexNumber] = true;

            for (int vertexNumber = myGrath[currentVertexNumber].length - 1; vertexNumber >= 0; vertexNumber--) {
                if (myGrath[currentVertexNumber][vertexNumber] == 1 && !visited[vertexNumber]) {
                    stack.add(vertexNumber);
                }
            }
        }

        return stringBuilder.append("}").toString();
    }

    private int getNotVisitedVertex(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }

        return -1;
    }

    public void visitInDeep(Consumer<Integer> consumer) {
        if (myGrath == null) {
            return;
        }

        boolean[] visited = new boolean[myGrath.length];

        Deque<Integer> stack = new LinkedList<>();
        stack.addLast(0);

        while (true) {
            if (stack.isEmpty()) {
                int notVisited = getNotVisitedVertex(visited);

                if (notVisited != -1) {
                    break;
                }

                stack.addLast(notVisited);
            }

            int currentVertexNumber = stack.removeLast();

            if (visited[currentVertexNumber]) {
                continue;
            }

            consumer.accept(currentVertexNumber);
            visited[currentVertexNumber] = true;

            for (int vertexNumber = myGrath[currentVertexNumber].length - 1; vertexNumber >= 0; vertexNumber--) {
                if (myGrath[currentVertexNumber][vertexNumber] == 1 && !visited[currentVertexNumber]) {
                    stack.add(vertexNumber);
                }
            }
        }
    }

    public String getStringByVisitingInWidth() {
        if (myGrath == null) {
            return "{}";
        }

        boolean[] visited = new boolean[myGrath.length];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{{");

        Queue<Integer> stack = new LinkedList<>();
        stack.add(0);

        while (true) {
            if (stack.isEmpty()) {
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
                int notVisited = getNotVisitedVertex(visited);

                if (notVisited == -1) {
                    break;
                }

                stack.add(notVisited);
                stringBuilder.append(", {");
            }

            int currentVertexNumber = stack.remove();

            if (visited[currentVertexNumber]) {
                continue;
            }

            stringBuilder.append(currentVertexNumber).append(", ");
            visited[currentVertexNumber] = true;

            for (int vertexNumber = 0; vertexNumber < myGrath[currentVertexNumber].length; vertexNumber++) {
                if (myGrath[currentVertexNumber][vertexNumber] == 1) {
                    stack.add(vertexNumber);
                }
            }
        }

        return stringBuilder.append("}").toString();
    }

    public void visitInWidth(Consumer<Integer> consumer) {
        if (myGrath == null) {
            return;
        }

        boolean[] visited = new boolean[myGrath.length];

        Queue<Integer> stack = new LinkedList<>();
        stack.add(0);

        while (true) {
            if (stack.isEmpty()) {
                int notVisited = getNotVisitedVertex(visited);

                if (notVisited == -1) {
                    break;
                }

                stack.add(notVisited);
            }

            int currentVertexNumber = stack.remove();

            if (visited[currentVertexNumber]) {
                continue;
            }

            consumer.accept(currentVertexNumber);
            visited[currentVertexNumber] = true;

            for (int vertexNumber = 0; vertexNumber < myGrath[currentVertexNumber].length; vertexNumber++) {
                if (myGrath[currentVertexNumber][vertexNumber] == 1) {
                    stack.add(vertexNumber);
                }
            }
        }
    }

    public String getStringByVisitingInDeepWithRecursion() {
        if (myGrath == null) {
            return "{}";
        }

        boolean[] visited = new boolean[myGrath.length];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int vertexNumber = 0; vertexNumber < myGrath.length; vertexNumber++) {
            if (!visited[vertexNumber]) {
                stringBuilder.append("{").append(getStringByVisitingInDeepWithRecursion(vertexNumber, visited));
                stringBuilder.append("}, ");
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    private String getStringByVisitingInDeepWithRecursion(int beginningVertexNumber, boolean[] visited) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(beginningVertexNumber);
        visited[beginningVertexNumber] = true;

        for (int vertexNumber = 0; vertexNumber < myGrath[beginningVertexNumber].length; vertexNumber++) {
            if (myGrath[beginningVertexNumber][vertexNumber] == 1 && !visited[vertexNumber]) {
                stringBuilder.append(", ").append(getStringByVisitingInDeepWithRecursion(vertexNumber, visited));
            }
        }

        return stringBuilder.toString();
    }

    public void visitInDeepWithRecursion(Consumer<Integer> consumer) {
        if (myGrath == null) {
            return;
        }

        boolean[] visited = new boolean[myGrath.length];

        for (int vertexNumber = 0; vertexNumber < myGrath.length; vertexNumber++) {
            if (!visited[vertexNumber]) {
                visitInDeepWithRecursion(vertexNumber, visited, consumer);
            }
        }
    }

    private void visitInDeepWithRecursion(int beginningVertexNumber, boolean[] visited, Consumer<Integer> consumer) {
        consumer.accept(beginningVertexNumber);
        visited[beginningVertexNumber] = true;

        for (int vertexNumber = 0; vertexNumber < myGrath[beginningVertexNumber].length; vertexNumber++) {
            if (myGrath[beginningVertexNumber][vertexNumber] == 1 && !visited[vertexNumber]) {
                visitInDeepWithRecursion(vertexNumber, visited, consumer);
            }
        }
    }
}
