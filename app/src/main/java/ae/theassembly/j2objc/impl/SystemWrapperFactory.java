package ae.theassembly.j2objc.impl;

import ae.theassembly.j2objc.android.impl.SystemWrapperAndroid;

public class SystemWrapperFactory {

    public static SystemWrapper makeSystemWrapper() {
        return new SystemWrapperAndroid();
    }
}
