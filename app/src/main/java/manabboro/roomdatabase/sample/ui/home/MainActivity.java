package manabboro.roomdatabase.sample.ui.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.databinding.ActivityMainBinding;
import manabboro.roomdatabase.sample.repository.NoteRepository;
import manabboro.roomdatabase.sample.ui.addNotes.NewNoteActivity;
import manabboro.roomdatabase.sample.viewmodel.HomeViewModel;

import static manabboro.roomdatabase.sample.ui.addNotes.NewNoteActivity.EXTRA_NOTE_ID;


public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE = 100;

    private NoteAdapter mAdapter;
    private HomeViewModel mViewModel;
    private ActivityMainBinding mBinding;
    /**
     *
     */
    private final NoteAdapter.OnItemClickListener itemClickListener = note -> {
        Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, note.uid);
        startActivityFromChild(MainActivity.this, intent, REQ_CODE);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);

        mBinding.recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(getGridCount(), StaggeredGridLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NoteAdapter(this, itemClickListener);
        mBinding.recyclerView.setAdapter(mAdapter);

        //subscribeUI
        NoteRepository mRepository = new NoteRepository(getApplication());
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HomeViewModel(mRepository);
            }
        };
        mViewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);
        mViewModel.getNotes().observe(this, notes -> {
            mAdapter.updateNotes(notes);
            showNoDataLayout(notes.size());
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
            startActivityFromChild(MainActivity.this, intent, REQ_CODE);
        });
    }

    private void showNoDataLayout(int listSize) {
        mBinding.recyclerView.setVisibility(listSize == 0 ? View.GONE : View.VISIBLE);
        mBinding.noDataLayout.setVisibility(listSize == 0 ? View.VISIBLE : View.GONE);
    }


    private int getGridCount() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return 4;
        } else {
            return 2;
        }
    }


}