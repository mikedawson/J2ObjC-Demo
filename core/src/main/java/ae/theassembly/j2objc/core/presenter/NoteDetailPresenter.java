package ae.theassembly.j2objc.core.presenter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Hashtable;

import ae.theassembly.j2objc.core.view.NoteDetailView;
import ae.theassembly.j2objc.impl.SystemWrapper;

public class NoteDetailPresenter {

    private NoteDetailView view;

    private Hashtable args;

    public static final String ARG_NOTE_INDEX = "note_index";

    private int noteIndex;

    private String noteText;

    public NoteDetailPresenter(NoteDetailView view, Hashtable args) {
        this.view = view;
        this.args = args;
    }

    public void onCreate() {
        noteIndex = (Integer)args.get(ARG_NOTE_INDEX);
        String allNotesStr = SystemWrapper.getInstance()
                .getPrefkey(view, NoteListPresenter.PREFKEY_NOTES);
        if(allNotesStr != null) {
            try {
                JSONArray jsonArr = new JSONArray(allNotesStr);
                view.setText(jsonArr.getString(noteIndex));
            }catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleTextChanged(String noteText) {
        this.noteText = noteText;
    }

    public void handleClickDone() {
        String allNotesStr = SystemWrapper.getInstance()
                .getPrefkey(view, NoteListPresenter.PREFKEY_NOTES);
        try {
            JSONArray jsonArr = allNotesStr != null ? new JSONArray(allNotesStr) : new JSONArray();
            if(noteIndex == -1) {
                jsonArr.put(noteText);
            }else {
                jsonArr.put(noteIndex, noteText);
            }

            SystemWrapper.getInstance().setPrefkey(view, NoteListPresenter.PREFKEY_NOTES,
                    jsonArr.toString());
            view.finish();
        }catch(JSONException e) {
            e.printStackTrace();
        }
    }

}
