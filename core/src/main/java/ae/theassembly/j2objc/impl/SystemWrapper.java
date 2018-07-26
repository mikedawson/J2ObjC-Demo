package ae.theassembly.j2objc.impl;

import java.util.Hashtable;

public abstract class SystemWrapper {

    private static SystemWrapper instance;

    public static SystemWrapper getInstance() {
        if(instance == null) {
            instance = SystemWrapperFactory.makeSystemWrapper();
        }

        return instance;
    }

    public abstract void setPrefkey(Object context, String key, String value);

    public abstract String getPrefkey(Object context, String key, String defaultVal);

    public String getPrefkey(Object context, String key) {
        return getPrefkey(context, key, null);
    }

    public abstract void go(Object context, String destination, Hashtable args);

}
