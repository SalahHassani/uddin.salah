package reflections;

import com.nix.jtc.reflection.Ignore;
import com.nix.jtc.reflection.Info;

public class Checker {
    @Info
    private int intValue = 42;
    @Info
    protected double doubleValue = 3.14;
    @Info
    public String stringValue = "Reflection";
    @Info
    private boolean booleanValue = true;
    @Info
    public long longValue = 100000L;
    @Info
    private NestedObject nestedObject = new NestedObject("Nested", 500);
    @Info
    private final double floatValue = 2.5f;
    @Ignore
    private String ignoredField = "Ignore this";

}

