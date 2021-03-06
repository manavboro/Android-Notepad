package manabboro.roomdatabase.sample.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import manabboro.roomdatabase.sample.dao.NoteDao;
import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.roomDb.NoteDatabase;

public class NoteRepository {

    private static NoteRepository instance = null;
    private final NoteDao mNoteDao;
    private final LiveData<List<Note>> mNotes;
    private final MutableLiveData<Note> notes;


    public NoteRepository(Application application) {
        NoteDatabase db = NoteDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mNotes = db.noteDao().getAllNotes();
        notes = new MutableLiveData<>();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.insert(note);
        });
    }

    public void update(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.update(note);
        });
    }

    public void delete(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.delete(note);
        });
    }

    public LiveData<Note> getNote(int noteId) {
        NoteDatabase.databaseWriteExecutor.execute(()->{
            notes.postValue(mNoteDao.findById(noteId));
        });
        return notes;
    }
}
