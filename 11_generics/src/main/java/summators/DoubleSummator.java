package summators;

import com.nix.jtc.generics.Summator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoubleSummator implements Summator<Double> {
    /**
     * @param data data to calculate the sum based on keys
     * @return map of key as String and value as sum of the list
     */
    @Override
    public Map<String, Double> sum(Map<String, List<Double>> data) {

        if(data == null || data.isEmpty()) {
            return null;
        }

        Map<String, Double> result = new HashMap<>();
        Double sum;
        for(Map.Entry<String, List<Double>> entry : data.entrySet()) {
            sum = 0.0;
            for(Double value : entry.getValue()) {
                sum += value;
            }
            result.put(entry.getKey(), sum);
        }
        return result;
    }
}
