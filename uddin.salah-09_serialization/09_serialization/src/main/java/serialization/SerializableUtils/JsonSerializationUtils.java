package serialization.SerializableUtils;

import com.nix.jtc.serializable.SerializableUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JsonSerializationUtils implements SerializableUtils {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void serialize(OutputStream out, Object obj) {
        if(out == null || obj == null) {
            throw new NullPointerException("Output stream or Object is null");
        }

        try {
            mapper.writeValue(out, obj);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize object", e);
        }
    }

    @Override
    public <T> T deserialize(InputStream in, Class<T> clazz) {
        if(in == null) {
            throw new NullPointerException("Input stream is null");
        }

        try {
            return mapper.readValue(in, clazz);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to deserialize object", e);
        }
    }
}
