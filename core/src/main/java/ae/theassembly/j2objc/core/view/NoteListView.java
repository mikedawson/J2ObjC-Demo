package ae.theassembly.j2objc.core.view;

import java.util.List;

public interface NoteListView {

    String VIEW_NAME = "NoteList";

    void setNoteList(List<String> noteSummaries);

}
