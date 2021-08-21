package manabboro.roomdatabase.sample.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.repository.NoteRepository;

public class HomeViewModel extends ViewModel {

    private final NoteRepository noteRepository;
    private LiveData<List<Note>> notes;

    public HomeViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        notes = noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }


}