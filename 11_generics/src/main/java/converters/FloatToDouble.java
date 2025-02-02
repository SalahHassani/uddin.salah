package converters;

import com.nix.jtc.generics.Converter;

public class FloatToDouble implements Converter<Double, Float> {
    /**
     * @param value data to be converted
     * @return value after converting it to double
     */
    @Override
    public Double get(Float value) {
        if(value == null) {
            return null;
        }
        return (double) value;
    }
}
