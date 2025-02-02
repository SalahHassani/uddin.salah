package serializationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import serialization.SerializableBeanImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableBeanImplTest {

    @Test
    public void testSerializableBeanImpl() throws IOException, ClassNotFoundException {
        // GIVEN
        String name = "SHassani";
        String email = "shassani@gmail.com";
        int zip = 1001;

        // WHEN
        SerializableBeanImpl bean = new SerializableBeanImpl();
        bean.setName(name);
        bean.setEmail(email);
        bean.setZip(zip);

        // serialization
        FileOutputStream fileOutputStream = new FileOutputStream("09_serialization/src/resources/file.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        bean.writeExternal(objectOutputStream);
        objectOutputStream.close();
        fileOutputStream.close();

        // deserialization
        FileInputStream fileInputStream = new FileInputStream("09_serialization/src/resources/file.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        SerializableBeanImpl bean2 = new SerializableBeanImpl();
        bean2.readExternal(objectInputStream);
        objectInputStream.close();
        fileInputStream.close();


        // THEN
        Assertions.assertEquals(name, bean.getName());
        Assertions.assertEquals(email, bean.getEmail());
        Assertions.assertEquals(zip, bean.getZip());


    }

}
