package manabboro.roomdatabase.sample.ui.addNotes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.roomDb.NoteRepository;

public class NewNoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText noteEditText;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleEditText = findViewById(R.id.title);
        noteEditText = findViewById(R.id.note);
        dateTextView = findViewById(R.id.date);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        else if (item.getItemId() == R.id.action_save) saveNote();
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String note = noteEditText.getText().toString();
        String title = titleEditText.getText().toString();

        if (note.isEmpty()) {
            Toast.makeText(this, "Please enter something in your note", Toast.LENGTH_SHORT).show();
            return;
        }

        Note noteModel = new Note();
        noteModel.setTitle(title);
        noteModel.setNote(note);
        noteModel.setDateTaken(System.currentTimeMillis());

        NoteRepository mRepository = new NoteRepository(getApplication());
        mRepository.insert(noteModel);
        finish();
    }
}