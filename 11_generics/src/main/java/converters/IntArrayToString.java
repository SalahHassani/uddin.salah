package converters;

import com.nix.jtc.generics.Converter;

public class IntArrayToString implements Converter<String, Integer[]> {

    /**
     * @param value data to be converted
     * @return String value after converting all values inside the int
     *         array to String while separating them through a space.
     */
    @Override
    public String get(Integer[] value) {
        if(value.length == 0 || value == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            result.append(value[i].toString());
            if(i < value.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
