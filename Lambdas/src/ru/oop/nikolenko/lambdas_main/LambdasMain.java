package ru.oop.nikolenko.lambdas_main;

import ru.oop.nikolenko.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdasMain {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Татьяна", 24),
                new Person("Антон", 49),
                new Person("Никита", 5),
                new Person("Василина", 58),
                new Person("Алина", 35),
                new Person("Василина", 13),
                new Person("Виктор", 25),
                new Person("Светлана", 87),
                new Person("Ивкакий", 15),
                new Person("Антон", 15),
                new Person("Генадий", 68),
                new Person("Алина", 18),
                new Person("Евгений", 45),
                new Person("Дмитрий", 20)
        );

        System.out.println("Список людей:");
        System.out.println(persons);

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Список уникальных имен:");
        System.out.println(uniqueNames);

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Уникальные имена: ", "."));

        System.out.println(uniqueNamesString);

        List<Person> personsUnder18 = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println("Список людей младше 18:");
        System.out.println(personsUnder18);

        double personsUnder18AverageAge = personsUnder18.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0);

        System.out.println("Средний возраст людей младше 18: " + personsUnder18AverageAge);

        Map<String, Double> personsByNameWithAverageAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Список уникальных имен и средний возраст их носитилей");
        System.out.println(personsByNameWithAverageAge);

        List<Person> personsFrom20To45 = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .collect(Collectors.toList());

        System.out.println("Перечень имен людей с возрастом от 20 до 45 в порядке убывания возраста:");
        personsFrom20To45.forEach(p -> System.out.println(p.getName()));
    }
}
