package serialization.SerializableUtils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nix.jtc.serializable.SerializableUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XmlSerializationUtils implements SerializableUtils {

    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public void serialize(OutputStream out, Object obj) {
        if(out == null || obj == null) {
            throw new NullPointerException("Output stream or Object is null");
        }

        try {
            xmlMapper.writeValue(out, obj);
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
            return xmlMapper.readValue(in, clazz);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to deserialize object", e);
        }
    }
}
