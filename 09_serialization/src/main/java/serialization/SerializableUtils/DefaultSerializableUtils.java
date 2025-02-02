package serialization.SerializableUtils;

import com.nix.jtc.serializable.SerializableUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class DefaultSerializableUtils implements SerializableUtils {
    @Override
    public void serialize(OutputStream out, Object obj) {

        if(out == null || obj == null) {
            throw new NullPointerException("Output stream or Object is null");
        }

        try(ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
            outputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize object", e);
        }
    }

    @Override
    public <T> T deserialize(InputStream in, Class<T> clazz) {
        if(in == null || clazz == null) {
            throw new NullPointerException("Input stream or Object is null");
        }

        try(ObjectInputStream inputStream = new ObjectInputStream(in)) {
            return clazz.cast(inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to deserialize object", e);
        }

    }
}
