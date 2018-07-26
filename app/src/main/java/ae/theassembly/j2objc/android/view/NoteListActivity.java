package ae.theassembly.j2objc.android.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ae.theassembly.j2objc.R;
import ae.theassembly.j2objc.core.presenter.NoteListPresenter;
import ae.theassembly.j2objc.core.view.NoteListView;

public class NoteListActivity extends AppCompatActivity implements NoteListView {

    private NoteListPresenter mPresenter;

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter<NoteSummaryViewHolder> mRecyclerAdapter;

    private class NoteSummaryViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public NoteSummaryViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_summary_text);
        }
    }

    private class NoteSummaryViewAdapter extends RecyclerView.Adapter<NoteSummaryViewHolder> {

        private List<String> noteSummaryArr;

        public NoteSummaryViewAdapter(List<String> noteSumamries) {
            this.noteSummaryArr = noteSumamries;
        }

        @NonNull
        @Override
        public NoteSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(NoteListActivity.this).inflate(
                    R.layout.item_summary, parent, false);
            return new NoteSummaryViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull NoteSummaryViewHolder holder, int position) {
            String noteSummary = noteSummaryArr.get(position);
            holder.textView.setText(noteSummary);
        }

        @Override
        public int getItemCount() {
            return noteSummaryArr.size();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        mRecyclerView = findViewById(R.id.activity_note_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPresenter = new NoteListPresenter(this);
        mPresenter.onCreate();
        findViewById(R.id.activity_note_list_new_button).setOnClickListener(
                (view) -> mPresenter.handleClickNewNote());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onStart();
    }

    @Override
    public void setNoteList(List<String> noteSummaries) {
        mRecyclerAdapter = new NoteSummaryViewAdapter(noteSummaries);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}
