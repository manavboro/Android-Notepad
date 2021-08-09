package manabboro.roomdatabase.sample.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.repository.NoteRepository;
import manabboro.roomdatabase.sample.ui.addNotes.NewNoteActivity;

import static manabboro.roomdatabase.sample.ui.addNotes.NewNoteActivity.EXTRA_NOTE;

public class MainActivity extends AppCompatActivity {

    private static final int GRID_COUNT = 2;
    private static final int REQ_CODE = 100;

    private RecyclerView mRecyclerView;
    private View noDataLayout;
    private NoteAdapter mAdapter;

    /**
     *
     */
    private final NoteAdapter.OnItemClickListener itemClickListener = new NoteAdapter.OnItemClickListener() {
        @Override
        public void onItemClicked(Note note) {
            Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
            intent.putExtra(EXTRA_NOTE, note);
            startActivityFromChild(MainActivity.this, intent, REQ_CODE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noDataLayout = findViewById(R.id.no_data_layout);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(GRID_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NoteAdapter(this, itemClickListener);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityFromChild(MainActivity.this, intent, REQ_CODE);
            }
        });

        //To get notes from room database
        getNotes();
    }

    /**
     * Method to get notes from room database
     */
    private void getNotes() {
        NoteRepository mRepository = new NoteRepository(getApplication());
        LiveData<List<Note>> notes = mRepository.getAllNotes();
        notes.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (notes == null || notes.isEmpty()) {
                    showNoDataLayout();
                    return;
                }

                mAdapter.updateNotes(notes);
            }
        });


    }
    
    private void showNoDataLayout() {
        mRecyclerView.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            getNotes();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}