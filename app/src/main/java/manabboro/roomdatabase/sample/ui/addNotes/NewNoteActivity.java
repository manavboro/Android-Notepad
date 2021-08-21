package manabboro.roomdatabase.sample.ui.addNotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.databinding.ActivityNewNoteBinding;
import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.repository.NoteRepository;
import manabboro.roomdatabase.sample.util.ColorUtils;
import manabboro.roomdatabase.sample.viewmodel.AddNoteViewModel;

public class NewNoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID = "_note_id";

    private EditText titleEditText;
    private EditText noteEditText;
    private TextView dateTextView;

    private int _noteId = -1;
    private AddNoteViewModel mViewModel;
    ActivityNewNoteBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_note);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleEditText = findViewById(R.id.title);
        noteEditText = findViewById(R.id.note);
        dateTextView = findViewById(R.id.date);

        if (getIntent() != null) {
            _noteId = getIntent().getIntExtra(EXTRA_NOTE_ID, -1);
        }

        //subscribeUI
        NoteRepository mRepository = new NoteRepository(getApplication());
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new AddNoteViewModel(mRepository, _noteId);
            }
        };
        mViewModel = new ViewModelProvider(this, factory).get(AddNoteViewModel.class);
        mViewModel.getNote().observe(this, note -> mBinding.setViewModel(note));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // add delete menu if intent data is not null
        menu.findItem(R.id.action_delete).setVisible(mViewModel.getNote() != null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        else if (item.getItemId() == R.id.action_save) saveNote();
        else if (item.getItemId() == R.id.action_delete) deleteNote();
        return super.onOptionsItemSelected(item);
    }


    private void deleteNote() {
        if (mViewModel.deleteNote()) {
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void saveNote() {
        String note = noteEditText.getText().toString();
        String title = titleEditText.getText().toString();

        if (note.isEmpty()) {
            Toast.makeText(this, "Please enter something in your note", Toast.LENGTH_SHORT).show();
            return;
        }

        Note noteModel;
        if (mViewModel.getNote().getValue() == null) {
            noteModel = new Note();
            noteModel.setTitle(title);
            noteModel.setNote(note);
            noteModel.setBgColor(ColorUtils.generateRandomColor());
            noteModel.setDateTaken(System.currentTimeMillis());
            mViewModel.insert(noteModel);

        } else {
            noteModel=mViewModel.getNote().getValue();
            noteModel.setTitle(title);
            noteModel.setNote(note);
            noteModel.setDateTaken(System.currentTimeMillis());
            mViewModel.update(noteModel);
        }

        Intent intent = new Intent();
        intent.setFlags(RESULT_OK);
        setIntent(intent);
        finish();
    }
}