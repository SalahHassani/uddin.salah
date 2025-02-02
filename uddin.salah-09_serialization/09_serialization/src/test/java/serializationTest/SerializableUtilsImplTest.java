package serializationTest;

import com.nix.jtc.serializable.SerializableBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import serialization.SerializableBeanImpl;
import serialization.SerializableUtils.DefaultSerializableUtils;
import serialization.SerializableUtils.JsonSerializationUtils;
import serialization.SerializableUtils.XmlSerializationUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SerializableUtilsImplTest {
    @Test
    public void testDefaultSerializableUtils() throws IOException {
        DefaultSerializableUtils serializableUtils = new DefaultSerializableUtils();
        SerializableBean serializableBean = new SerializableBeanImpl();
        serializableBean.setName("Salah");
        serializableBean.setEmail("shassani@gmail.com");
        serializableBean.setZip(1001);

        String filePath = "09_serialization/src/resources/serializeUtils.txt";


        // serialization...
        OutputStream outputStream = new FileOutputStream(filePath);
        serializableUtils.serialize(outputStream, serializableBean);

        // deserialization...
        InputStream inputStream = new FileInputStream(filePath);
        SerializableBean deserializedBean = deserializedBean = serializableUtils.deserialize(inputStream, SerializableBeanImpl.class);

        Assertions.assertNotNull(deserializedBean);
        Assertions.assertEquals(serializableBean.getName(), deserializedBean.getName());
        Assertions.assertEquals(serializableBean.getEmail(), deserializedBean.getEmail());
        Assertions.assertEquals(serializableBean.getZip(), deserializedBean.getZip());
    }

    @Test
    public void testJsonSerializationUtils () throws IOException {
        JsonSerializationUtils jsonUtils = new JsonSerializationUtils();
        SerializableBean serializableBean = new SerializableBeanImpl();
        serializableBean.setName("Salah");
        serializableBean.setEmail("shassani@gmail.com");
        serializableBean.setZip(1001);

        String filePath = "09_serialization/src/resources/JsonSerializationUtils.json";

        // serialization...
        OutputStream outputStream = new FileOutputStream(filePath);
        jsonUtils.serialize(outputStream, serializableBean);

        // deserialization...
        InputStream inputStream = new FileInputStream(filePath);
        SerializableBean deserializedBean = jsonUtils.deserialize(inputStream, SerializableBeanImpl.class);

        Assertions.assertEquals(serializableBean.getName(), deserializedBean.getName());
        Assertions.assertEquals(serializableBean.getEmail(), deserializedBean.getEmail());
        Assertions.assertEquals(serializableBean.getZip(), deserializedBean.getZip());
    }

    @Test
    public void testXmlSerializationUtils () throws IOException {

        XmlSerializationUtils xmlUtils = new XmlSerializationUtils();
        SerializableBean serializableBean = new SerializableBeanImpl();
        serializableBean.setName("Salah");
        serializableBean.setEmail("shassani@gmail.com");
        serializableBean.setZip(1001);

        String filePath = "09_serialization/src/resources/XmlSerializationUtils.xml";

        // serialization...
        OutputStream outputStream = new FileOutputStream(filePath);
        xmlUtils.serialize(outputStream, serializableBean);

        // deserialization...
        InputStream inputStream = new FileInputStream(filePath);
        SerializableBean deserializedBean = xmlUtils.deserialize(inputStream, SerializableBeanImpl.class);

        Assertions.assertEquals(serializableBean.getName(), deserializedBean.getName());
        Assertions.assertEquals(serializableBean.getEmail(), deserializedBean.getEmail());
        Assertions.assertEquals(serializableBean.getZip(), deserializedBean.getZip());
    }
}
