package lab4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        System.out.println("list1 -> " + list1 );
        System.out.println("getAvg(list1) -> " + getAvg(list1) + "\n");

        List<String> list2 = new ArrayList<>(List.of("one", "two", "three", "four" ,"five"));
        System.out.println("list2 -> " + list2);
        System.out.println("addPrefixAndToUpper(list2) -> " + addPrefixAndToUpper(list2) + "\n");

        list1.addAll(List.of(5, 4, 6, 7, 8));
        System.out.println("list1 -> " + list1);
        System.out.println("getUniqueItemsSquares(list1) -> " + getUniqueItemsSquares(list1) + "\n");

        System.out.println("list1 -> " + list1);
        try {
            System.out.println("getLastItem(list1) -> " + getLastItem(list1));
            System.out.print("getLastItem(new Vector<Integer>()) -> ");
            getLastItem(new Vector<Integer>());
        }
        catch (Exception ex) {
            System.out.println(ex + "\n");
        }

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("arr -> " + List.of(arr));
        System.out.println("getEvenSum(arr) -> " + getEvenSum(arr) + "\n");

        list2.addAll(List.of("six", "seven", "eight", "nine", "ten"));
        System.out.println("list2 -> " + list2);
        System.out.println("getLinesMap(list2) ->" + getLinesMap(list2));
    }

    /// Метод, возвращающий среднее значение списка целых чисел
    private static double getAvg(List<Integer> list){
        return list.stream()
                .mapToInt(x -> x)
                .average()
                .orElse(0);
    }

    /// Метод, приводящий все строки в списке в верхний регистр и
    /// добавляющий к ним префикс «\_new\_»
    private static List<String> addPrefixAndToUpper(List<String> list){
        return list.stream()
                .map(x -> "_new_" + x.toUpperCase())
                .toList();
    }

    /// Метод, возвращающий список квадратов всех встречающихся
    /// только один раз элементов списка
    private static List<Integer> getUniqueItemsSquares(List<Integer> list){
        return list.stream()
                .filter(x -> list.stream()
                            .filter(y -> y.equals(x))
                        .count() == 1)
                .map(x -> x * x)
                .toList();
    }

    /// Метод, принимающий на вход коллекцию и возвращающий ее
    /// последний элемент или кидающий исключение, если коллекция
    /// пуста
    private static <T> T getLastItem(Collection<T> collection){
        return collection.stream()
                .reduce((x1, x2) -> x2)
                .orElseThrow();
    }

    /// Метод, принимающий на вход массив целых чисел, возвращающий
    /// сумму чётных чисел или 0, если чётных чисел нет
    private static Integer getEvenSum(Integer[] arr){
        return Arrays.stream(arr)
                .filter(x -> x % 2 == 0)
                .reduce(Integer::sum)
                .orElse(0);
    }

    /// Метод, преобразовывающий все строки в списке в Map, где первый
    /// символ – ключ, оставшиеся – значение;
    private static Map<Character, String> getLinesMap(List<String> list){
        return list.stream()
                .filter(x -> !x.isBlank()
                        && list.stream()
                            .filter(y -> !y.isBlank() && y.charAt(0) == x.charAt(0))
                            .toList()
                        .indexOf(x) == 0)
                .collect(Collectors.toMap(
                        x -> x.charAt(0),
                        x -> x.substring(1)
                ));
    }
}