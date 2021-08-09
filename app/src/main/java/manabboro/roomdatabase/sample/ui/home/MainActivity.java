package manabboro.roomdatabase.sample.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.roomDb.NoteRepository;
import manabboro.roomdatabase.sample.ui.addNotes.NewNoteActivity;

public class MainActivity extends AppCompatActivity {

    private static final int GRID_COUNT = 2;
    private RecyclerView mRecyclerView;
    private View noDataLayout;
    private NoteAdapter mAdapter;

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

        mAdapter = new NoteAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityFromChild(MainActivity.this, intent, 100);
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
        List<Note> notes = mRepository.getAllNotes();

        if (notes == null || notes.isEmpty()) {
            showNoDataLayout();
            return;
        }

        mAdapter.updateNotes(notes);
    }


    private void showNoDataLayout() {
        mRecyclerView.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            getNotes();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}