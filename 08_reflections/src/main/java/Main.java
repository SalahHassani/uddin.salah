import com.nix.jtc.reflection.ReflectionUtil;
import loader.PathClassLoaderImpl;
import reflections.Checker;
import reflections.NestedObject;
import reflections.ReflectionUtilsImpl;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        PathClassLoaderImpl pathClassLoader = new PathClassLoaderImpl();

        Path path = Path.of("com.nix.jtc.reflection");
        pathClassLoader.setPath(path);

        Class<?> res = pathClassLoader.findClass("ReflectionUtil.class");
        System.out.println("result: " + res);


        ReflectionUtilsImpl reflectionUtil = new ReflectionUtilsImpl();
        Checker checker = new Checker();

        System.out.println(reflectionUtil.toString(checker));
        Checker checker2 = new Checker();

        // try to change the values...
        NestedObject nested1 = new NestedObject("Nested1", 5);
        NestedObject nested2 = new NestedObject("Nested1", 5);

        System.out.println(reflectionUtil.isTheSame(nested1, nested2));

    }
}
