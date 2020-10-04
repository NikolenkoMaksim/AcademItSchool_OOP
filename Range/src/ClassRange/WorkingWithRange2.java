package ClassRange;

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

        System.out.println("Введите конечное число диапазона 1:");
        to = scanner.nextDouble();

        Range range2 = new Range(from, to);

        range2.formatRangeEnds();

        System.out.println("Выберите команду:");
        System.out.println("1 - найти пересечение диапозона 1 и 2");
        System.out.println("2 - найти объединение");
        System.out.println("3 - найти разность диапозона 1 и диапозона 2");
        System.out.println("4 - найти разность диапозона 2 и диапозона 1");
        System.out.println("другой символ - закрыть программу");

        scanner.nextLine();

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("1")) {
                Range intersection = new Range(0,0);
                intersection.findIntersection(range1, range2);

                from = intersection.getFrom();
                to = intersection.getTo();

                if (from > to) {
                    System.out.println("Перечесечений нет");
                } else {
                    System.out.println("Пересечение диапозонов: [" +
                            from + ", " + to + "]");
                }
            } else if (!command.equals("2")) {
                break;
            } else {
                System.out.println("Введите число:");
                double number = scanner.nextDouble();

                scanner.nextLine();

                if (range1.isInside(number)) {
                    System.out.println("Число принадлежит диапазону.");
                } else {
                    System.out.println("Число не принадлежит диапазону.");
                }
            }

            System.out.println("Введите команду:");
        }
    }
}
