package loader;

import com.nix.jtc.reflection.PathClassLoader;

import java.nio.file.Path;
import java.util.Objects;

public class PathClassLoaderImpl extends java.lang.ClassLoader implements PathClassLoader {
    Path path;

    /**
     * @return the path
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * @param path .
     */
    @Override
    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * @param name The <a href="#binary-name">binary name</a> of the class
     * @return finds the specified class and return it.
     * @throws ClassNotFoundException if the specified class is not found
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        if(path == null) {
            // setting it to current directory
            path = Path.of("");
        }
        else {
            path = Path.of(path + ".");
        }

        if (name == null || name.isEmpty()) {
            throw new ClassNotFoundException("null or empty name");
        }

        String[] parts = name.split("\\.");

         if((parts.length == 2 && !Objects.equals(parts[1], "class")) || parts.length > 2) {
             throw new ClassNotFoundException(name + " not found. Please give a valid name");
         }

         // it throws ClassNotFoundException itself no need to throw explicitly
        return Class.forName(path + parts[0]);
    }
}
