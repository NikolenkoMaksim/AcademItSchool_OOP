import javax.swing.*;
import java.util.*;
import java.util.function.UnaryOperator;

public class TestMain {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Object[] array3 = {8, 9, 10, 47, 78, 7, 8};
        Double[] array2 = {1.1, 2.2};
        Object[] array1 = list.toArray(array3);
        System.out.println(Arrays.toString(array1));

        UnaryOperator<Integer> uo = z -> z + 2;
        list.replaceAll(uo);
        System.out.println("К коллекции 6 применили метод \"replaceAll(uo)\" с тернальным оператором, прибавляющим 2 к каждому члену.");
        System.out.println("Коллекция 6:");
        System.out.println(list);

        Comparator<Integer> comparator = Integer::compare;

        list.sort(comparator);
        System.out.println("К коллекции 6 применили метод \"sort(comparator)\".");
        System.out.println("Коллекция 6:");
        System.out.println(list);

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.toArray();

    }


}
