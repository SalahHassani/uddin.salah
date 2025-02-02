import streams.StringTransformers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static <T> void print(String str, List<T> list) {
        System.out.print(str + " [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if(i < list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        StringTransformers transformers = new StringTransformers();

        String[] myStringArray = {"foo", "bar", "foo", "baz"};
        print("Distinct Words: ", transformers.findDistinctToUpperCase(myStringArray));

        List<Integer> myIntegerList = new ArrayList<>(Arrays.asList(1, -2, 3, -4, 5, -5, 6));
        System.out.println("Negative Numbers: " + transformers.countNegativeIntegers(myIntegerList));

        System.out.print("Count of Given Word in the List: ");
        System.out.println(transformers.countWordsInList(Arrays.asList(myStringArray), "foo"));

        List<String[]> myStringListOfList = new ArrayList<>(
                Arrays.asList(
                                new String[]{"foo", "bar", "baz"},
                                new String[]{"foo", "bar", "fuz"})
                             );
        print("Distinct Words From all List: ", transformers.toDistinctList(myStringListOfList));

        System.out.print("All words in String List are greater than the specified number: ");
        System.out.println(transformers.isAllStringsLongerThen(Arrays.asList(myStringArray), 3));

        print("5 Largest Numbers: ", transformers.getMaxFiveNumbers(new int[]{1, 2, 3, 7, 0, 15, 9, -1, 4, 10, 20}));

        System.out.println("Numbers to String: " + transformers.getStringOfNumbers(new int[]{1, 2, 3, 5, 6}));

        System.out.println("First Characters of each words in a List: " +
                transformers.getFirstCharactersAsString(Arrays.asList("serial", "number", "of", "turbo")));

        System.out.println("Grouping the list of words by their length:");
        Map<Integer, List<String>> map = transformers.groupByLength(Arrays.asList("Irene", "Wendy", "Seulgi", "Joy", "Yeri", "Red", "Velvet"));
        for(Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
