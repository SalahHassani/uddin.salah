package streams;

import com.nix.jtc.streamoptional.StringTransformUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StringTransformers implements StringTransformUtils {
    /**
     * @param strings array of strings
     * @return a list of distinct words but in Capital letters
     */
    @Override
    public List<String> findDistinctToUpperCase(String[] strings) {

        if (strings == null || strings.length == 0) {
            return null;
        }

        return Arrays.stream(strings)
                .distinct()
                // we can use lambda function as well...
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    /**
     * @param integers list of integers
     * @return total number of negative numbers in a list
     */
    @Override
    public long countNegativeIntegers(List<Integer> integers) {

        if(integers == null || integers.isEmpty()) {
            return 0;
        }

        return integers.stream()
                .filter(nums -> nums < 0)
                .count();
    }

    /**
     * @param words       list of strings
     * @param wordToCount string that the number of occurrences need to be
     *                    counted for
     * @return the count of the give word in the given list
     */
    @Override
    public long countWordsInList(List<String> words, String wordToCount) {

        if(words == null || words.isEmpty()) {
            return 0;
        }

        return words.stream()
                .filter(word -> Objects.equals(word, wordToCount))
                .count();
    }

    /**
     * @param stringsArrays list of string arrays
     * @return the cnt of the distinct words in all lists in give list of list
     */
    @Override
    public List<String> toDistinctList(List<String[]> stringsArrays) {

        if(stringsArrays == null || stringsArrays.isEmpty()) {
            return null;
        }

        return stringsArrays.stream()
                // we can also use lambda function like "arrays -> Arrays.stream(arrays)"
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * @param strings            list of strings
     * @param numberOfCharacters number of characters
     * @return a boolean value according to the length of all words
     * in the given list and the number
     */
    @Override
    public boolean isAllStringsLongerThen(List<String> strings, long numberOfCharacters) {

        if(numberOfCharacters < 0 ) {
            throw new IllegalArgumentException("Number of characters must be a positive number");
        }

        if(strings == null || strings.isEmpty() || numberOfCharacters == 0) {
            return false;
        }

        return strings.stream()
                .allMatch(word -> word.length() > numberOfCharacters);
    }

    /**
     * @param numbers array of integers
     * @return the largest 5 values in a list
     */
    @Override
    public List<Integer> getMaxFiveNumbers(int[] numbers) {

        if( numbers == null || numbers.length < 5) {
            throw new IllegalArgumentException("Length of given array must be a greater then 4 and should not be null");
        }

        return Arrays.stream(numbers)
                .boxed()
                .sorted((a, b) ->  b - a) // Descending order so no need to extra filter it
                .limit(5)                      // since we are taking the 1st 5 values which are the largest.
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * @param numbers array of integers
     * @return the List of Integers as String
     */
    @Override
    public String getStringOfNumbers(int[] numbers) {

        if(numbers == null || numbers.length == 0) {
            return "";
        }

        return Arrays.stream(numbers)
                // Learn this from remote resource
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

    /**
     * @param strings list of strings
     * @return return a string made up of 1st
     * characters from all words in a list
     */
    @Override
    public String getFirstCharactersAsString(List<String> strings) {

        if(strings == null || strings.isEmpty()) {
            return "";
        }

        return strings.stream()
                .map(word -> word.substring(0, 1))
                .collect(Collectors.joining(""));
    }

    /**
     * @param strings list of strings
     * @return a map of key as integer and values as a list of String
     * with same length which are equal to the key
     */
    @Override
    public Map<Integer, List<String>> groupByLength(List<String> strings) {

        if(strings == null || strings.isEmpty()) {
            return null;
        }

        return strings.stream()
                .collect(Collectors.groupingBy(String::length));
    }
}
