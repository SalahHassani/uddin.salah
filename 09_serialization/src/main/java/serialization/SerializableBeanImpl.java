package serialization;

import com.nix.jtc.serializable.SerializableBean;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SerializableBeanImpl implements SerializableBean {

    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private int zip;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getZip() {
        return zip;
    }

    @Override
    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String data = name + ";" + email + ";" + zip;
        out.writeUTF(data);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String deserializeData = in.readUTF();

        String[] data = deserializeData.split(";");
        if(data.length == 3) {
            name = data[0];
            email = data[1];
            zip = Integer.parseInt(data[2]);
        }
        else {
            throw new IOException("invalid data format");
        }
    }
}
