package serialization;

import com.nix.jtc.serializable.SingletonBean;

public class SingletonBeanImpl implements SingletonBean {
    private static final long serialVersionUID = 1L;
    private static SingletonBeanImpl instance;

    private SingletonBeanImpl() {}

    public static SingletonBeanImpl getInstance() {
        if (instance == null) {
            instance = new SingletonBeanImpl();
        }
        return instance;
    }

    public Object readResolve() {
        return getInstance();
    }
}
