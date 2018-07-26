package ae.theassembly.j2objc.android.impl;

import android.os.Bundle;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class SystemUtilAndroid {

    /**
     * Convert an Android bundle to a hashtable
     * @param bundle
     * @return
     */
    public static Hashtable bundleToHashtable(Bundle bundle) {
        if(bundle == null)
            return null;

        Hashtable retVal = new Hashtable();
        Set<String> keys = bundle.keySet();
        Iterator<String> iterator = keys.iterator();

        String key;
        Object val;
        while(iterator.hasNext()) {
            key = iterator.next();
            val = bundle.get(key);
            //TODO: could this not simply be putAll?
            if(val instanceof String) {
                retVal.put(key, val);
            }else if(val instanceof Integer) {
                retVal.put(key, val);
            }else if(val instanceof String[]) {
                retVal.put(key, val);
            }
        }

        return retVal;
    }

    public static Bundle hashtableToBundle(Hashtable table) {
        if(table == null)
            return null;

        Bundle bundle = new Bundle();

        Iterator iterator = table.keySet().iterator();
        String key;
        Object val;
        while(iterator.hasNext()) {
            key = (String)iterator.next();
            val = table.get(key);
            if(val instanceof Integer) {
                bundle.putInt(key, (Integer)val);
            }else if(val instanceof String){
                bundle.putString(key, (String)val);
            }else if(val instanceof String[]) {
                bundle.putStringArray(key, (String[])val);
            }
        }
        return bundle;

    }

}
