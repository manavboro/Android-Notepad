package manabboro.roomdatabase.sample.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.ui.add.NewNoteActivity;

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
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, GRID_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NoteAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
            }
        });

        //To get notes from room database
        getNotes();
    }

    /**
     * Method to get notes from room database
     */
    private void getNotes() {

    }

    /**
     *
     */
    private void showNoDataLayout() {
        mRecyclerView.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}