package summators;

import com.nix.jtc.generics.Summator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigDecimalSummator implements Summator<BigDecimal> {

    /**
     * @param data data to calculate the sum based on keys
     * @return map of (key, value) = (key as String, value as Double)
     */
    @Override
    public Map<String, Double> sum(Map<String, List<BigDecimal>> data) {

        if(data == null || data.isEmpty()) {
            return null;
        }

        Map<String, Double> result = new HashMap<>();
        BigDecimal sum;
        for(Map.Entry<String, List<BigDecimal>> entry : data.entrySet()) {
            sum = BigDecimal.ZERO;
            for(BigDecimal value : entry.getValue()) {
                sum = sum.add(value);
            }
            result.put(entry.getKey(), sum.doubleValue());
        }
        return result;
    }
}
