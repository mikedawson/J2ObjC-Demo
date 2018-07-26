package ae.theassembly.j2objc.android.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Hashtable;

import ae.theassembly.j2objc.R;
import ae.theassembly.j2objc.core.presenter.NoteDetailPresenter;
import ae.theassembly.j2objc.core.view.NoteDetailView;
import static ae.theassembly.j2objc.core.presenter.NoteDetailPresenter.ARG_NOTE_INDEX;

public class NoteDetailActivity extends AppCompatActivity implements NoteDetailView{

    private NoteDetailPresenter mPresenter;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        Hashtable args = new Hashtable();
        args.put(ARG_NOTE_INDEX,   getIntent().getIntExtra(ARG_NOTE_INDEX, -1));
        mPresenter = new NoteDetailPresenter(this, args);
        mPresenter.onCreate();
        findViewById(R.id.activity_note_detail_save_button).setOnClickListener(
                (view) -> mPresenter.handleClickDone());

        mEditText = findViewById(R.id.activity_note_detail_edit_text);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.handleTextChanged(s.toString());
            }
        });
    }

    @Override
    public void setText(String text) {
        mEditText.setText(text);
    }
}
