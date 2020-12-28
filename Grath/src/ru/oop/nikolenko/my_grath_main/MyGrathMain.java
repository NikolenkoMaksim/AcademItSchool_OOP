package ru.oop.nikolenko.my_grath_main;

import ru.oop.nikolenko.my_grath.MyGrath;

public class MyGrathMain {
    public static void main(String[] args) {

        int[][] edgesTable = {
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

        MyGrath grath = new MyGrath(edgesTable);

        System.out.println(grath.getStringByVisitingInDeep());
        System.out.println(grath.getStringByVisitingInDeepWithRecursion());
        System.out.println(grath.getStringByVisitingInWidth());
    }
}
