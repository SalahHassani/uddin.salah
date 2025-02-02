package reflections;

import com.nix.jtc.reflection.Ignore;
import com.nix.jtc.reflection.Info;

public class NestedObject {
    @Info
    public String nestedString;
    @Info
    protected int nestedInt;
    @Ignore
    private String ignoredField = "Ignore this";

    public NestedObject(String nestedString, int nestedInt) {
        this.nestedString = nestedString;
        this.nestedInt = nestedInt;
    }

}
