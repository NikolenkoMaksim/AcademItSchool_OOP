package WorkingWithRange2;

import java.util.Scanner;

import ClassRange.Range;

public class WorkingWithRange2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число диапазона 1:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное число диапазона 1:");
        double to = scanner.nextDouble();

        Range range1 = new Range(from, to);

        range1.formatRangeEnds();

        System.out.println("Введите начальное число диапазона 2:");
        from = scanner.nextDouble();

        System.out.println("Введите конечное число диапазона 2:");
        to = scanner.nextDouble();

        Range range2 = new Range(from, to);

        range2.formatRangeEnds();

        System.out.println("Выберите команду:");
        System.out.println("1 - найти пересечение диапозонов 1 и 2");
        System.out.println("2 - найти объединение диапозонов 1 и 2");
        System.out.println("3 - найти разность диапозона 1 и диапозона 2");
        System.out.println("4 - найти разность диапозона 2 и диапозона 1");
        System.out.println("другой символ - закрыть программу");

        scanner.nextLine();

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("1")) {
                Range intersection = new Range(0, 0);
                intersection.getIntersection(range1, range2);

                from = intersection.getFrom();
                to = intersection.getTo();

                if (from > to) {
                    System.out.println("Перечесечений нет");
                } else {
                    System.out.println("Пересечение диапозонов: [" +
                            from + ", " + to + "]");
                }
            } else if (command.equals("2")) {
                Range[] union = range1.getUnion(range2);

                double from1 = union[0].getFrom();
                double to1 = union[0].getTo();

                if (union[1] != null) {
                    double from2 = union[1].getFrom();
                    double to2 = union[1].getTo();
                    System.out.println("Объединение диапозонов равно [" +
                            from1 + ", " + to1 + "] + [" + from2 + ", " + to2 + "]");
                } else {
                    System.out.println("Объединение диапозонов равно [" +
                            from1 + ", " + to1 + "]");
                }

            } else if (!(command.equals("3") || command.equals("4"))) {
                break;
            } else {
                Range[] difference;

                if (command.equals("3")) {
                    difference = range1.getDifference(range2);
                    System.out.print("Разность диапозонов 1 и 2 равна ");
                } else {
                    difference = range2.getDifference(range1);
                    System.out.print("Разность диапозонов 2 и 1 равна ");
                }

                if (difference[0] == null) {
                    System.out.println("пустому множеству");
                } else {
                    double from1 = difference[0].getFrom();
                    double to1 = difference[0].getTo();

                    if (difference[1] == null) {
                        System.out.println("[" + from1 + ", " + to1 + "]");
                    } else {
                        double from2 = difference[1].getFrom();
                        double to2 = difference[1].getTo();
                        System.out.println("[" + from1 + ", " + to1 + "] + ["
                                + from2 + ", " + to2 + "]");
                    }
                }
            }

            System.out.println("Введите команду:");
        }
    }
}
