package ae.theassembly.j2objc.core.presenter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ae.theassembly.j2objc.core.view.NoteDetailView;
import ae.theassembly.j2objc.core.view.NoteListView;
import ae.theassembly.j2objc.impl.SystemWrapper;

public class NoteListPresenter {

    public static final String PREFKEY_NOTES = "notes";

    public static final int SUMMARY_LENGTH = 20;

    private NoteListView view;

    public NoteListPresenter(NoteListView view) {
        this.view = view;
    }

    public void onCreate() {

    }

    public void onStart() {
        String savedNotesStr = SystemWrapper.getInstance().getPrefkey(view, PREFKEY_NOTES);
        JSONArray notesArr = null;

        if(savedNotesStr != null) {
            try {
                notesArr = new JSONArray(savedNotesStr);
            }catch(JSONException e) {
                e.printStackTrace();
            }
        }else {
            notesArr = new JSONArray();
        }

        List<String> summaries = new ArrayList<>();
        for(int i = 0; i < notesArr.length(); i++) {
            String noteVal = notesArr.getString(i);
            noteVal = noteVal.length() > SUMMARY_LENGTH ? noteVal.substring(0, 20) : noteVal;
            summaries.add(noteVal);
        }

        view.setNoteList(summaries);
    }

    public void onDestroy() {

    }

    public void handleClickNewNote(){
        Hashtable args = new Hashtable();
        args.put(NoteDetailPresenter.ARG_NOTE_INDEX, -1);
        SystemWrapper.getInstance().go(view, NoteDetailView.VIEW_NAME, args);
    }


}
