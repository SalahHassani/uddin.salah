
import summators.*;
import converters.*;

import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static <T> void printMap(Map<String, T> map) {

        for (Map.Entry<String, T> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
    }

    public static void main(String[] args) {

        // Testing summators package classes.
        DoubleSummator doubleSummator = new DoubleSummator();

        Map<String, List<Double>> mapOfDoubleArray = new HashMap<>();
        mapOfDoubleArray.put("Key 1", Arrays.asList(1.5, 2.3, 5.7, 0.8));
        mapOfDoubleArray.put("Key 2", Arrays.asList(0.0, 1.1, 2.2, 3.3, 4.4));

        Map<String, Double> convertedMap = doubleSummator.sum(mapOfDoubleArray);
        System.out.println("Before Conversion:");
        printMap(mapOfDoubleArray);
        System.out.println("\nAfter Conversion: Map(String, List<Double>) -> Map(String, Double):");
        printMap(convertedMap);



        BigDecimalSummator bigDecimalSummater = new BigDecimalSummator();
        Map<String, List<BigDecimal>> mapOfBigDecimalArray = new HashMap<>();
        mapOfBigDecimalArray.put("Key 1", Arrays.asList(BigDecimal.valueOf(5.5), BigDecimal.valueOf(5.3), BigDecimal.valueOf(5.0)));
        mapOfBigDecimalArray.put("Key 2", Arrays.asList(BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.1), BigDecimal.valueOf(2.5), BigDecimal.valueOf(3.3)));

        convertedMap = bigDecimalSummater.sum(mapOfBigDecimalArray);

        System.out.println("\n\nBefore Conversion:");
        printMap(mapOfBigDecimalArray);
        System.out.println("\nAfter Conversion: Map(String, List<BigDecimal>) -> Map(String, Double):");
        printMap(convertedMap);


        //Testing converters package classes...
        FloatToDouble floatToDouble = new FloatToDouble();
        System.out.println("\n\nFloat to Double: " + floatToDouble.get(new Float(5.1234567)));

        Integer[] myArray = {1, 2, 3, 4, 5};
        IntArrayToString intArrayToString = new IntArrayToString();
        System.out.println("\nInt array to String: " + intArrayToString.get(myArray));

    }
}
