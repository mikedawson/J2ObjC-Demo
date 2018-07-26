package ae.theassembly.j2objc.android.impl;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Hashtable;

import ae.theassembly.j2objc.android.view.NoteDetailActivity;
import ae.theassembly.j2objc.android.view.NoteListActivity;
import ae.theassembly.j2objc.core.view.NoteDetailView;
import ae.theassembly.j2objc.core.view.NoteListView;
import ae.theassembly.j2objc.impl.SystemWrapper;

public class SystemWrapperAndroid extends SystemWrapper {

    private static HashMap<String, Class<? extends Activity>> viewImpls = new HashMap<>();

    private SharedPreferences sharedPreferences;

    private static final String SHARED_PREFS_NAME = "notes";

    static {
        viewImpls.put(NoteDetailView.VIEW_NAME, NoteDetailActivity.class);
        viewImpls.put(NoteListView.VIEW_NAME, NoteListActivity.class);
    }

    private SharedPreferences getSharedPreferences(Context context) {
        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }

        return sharedPreferences;
    }

    @Override
    public void setPrefkey(Object context, String key, String value) {
        SharedPreferences preferences = getSharedPreferences((Context)context);

        SharedPreferences.Editor editor = preferences.edit();
        if(value != null) {
            editor.putString(key, value);
        }else {
            editor.remove(key);
        }

        editor.apply();
    }

    @Override
    public String getPrefkey(Object context, String key, String defaultVal) {
        SharedPreferences preferences = getSharedPreferences((Context)context);
        return preferences.getString(key, defaultVal);
    }

    @Override
    public void go(Object context, String destination, Hashtable args) {
        Class<? extends Activity> activityClass = viewImpls.get(destination);
        Context androidContext = (Context)context;
        Intent startActivityIntent = new Intent(androidContext, activityClass);
        androidContext.startActivity(startActivityIntent);
    }
}
