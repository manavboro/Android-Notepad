package manabboro.roomdatabase.sample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.repository.NoteRepository;

public class AddNoteViewModel extends ViewModel {

    private final NoteRepository noteRepository;
    private LiveData<Note> note;

    public AddNoteViewModel(NoteRepository noteRepository, int _noteId) {
        this.noteRepository = noteRepository;
        note = noteRepository.getNote(_noteId);
    }

    public LiveData<Note> getNote() {
        return note;
    }


    public boolean deleteNote() {
        if (note.getValue() == null) return false;
        noteRepository.delete(note.getValue());
        return true;
    }

    public void insert(Note noteModel) {
        noteRepository.insert(noteModel);

    }

    public void update(Note noteModel) {
        noteRepository.update(noteModel);
    }
}