package manabboro.roomdatabase.sample.roomDb;

import android.app.Application;

import java.util.List;

import manabboro.roomdatabase.sample.models.Note;

public class NoteRepository {

    private NoteDao mNoteDao;
    private List<Note> mNotes;

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
   public List<Note> getAllNotes() {
       mNotes = mNoteDao.getAllNotes();
       return mNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.insert(note);
        });
    }
}
