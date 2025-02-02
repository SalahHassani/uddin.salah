package serializationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import serialization.SerializableBeanImpl;
import serialization.SingletonBeanImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SingletonBeanImplTest {
    @Test
    public void testSingletonBean() throws IOException, ClassNotFoundException {
        // given
        String filePath = "09_serialization/src/resources/singleton.txt";
        SingletonBeanImpl singletonBeanA = SingletonBeanImpl.getInstance();

        // when
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(singletonBeanA);
        }

        SingletonBeanImpl singletonBeanB;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            singletonBeanB = (SingletonBeanImpl) objectInputStream.readObject();
        }

        // then
        Assertions.assertEquals(singletonBeanA, singletonBeanB);
    }
}
