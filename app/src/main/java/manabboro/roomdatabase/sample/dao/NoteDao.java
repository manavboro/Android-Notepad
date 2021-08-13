package manabboro.roomdatabase.sample.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import manabboro.roomdatabase.sample.models.Note;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY note_taken desc")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM Note WHERE uid IN (:userIds)")
    List<Note> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Note WHERE uid LIKE :uid  LIMIT 1")
    Note findById(String uid);

    @Insert
    void insertAll(Note... notes);

    @Insert
    void insert(Note word);

    @Update
    void update(Note word);

    @Delete
    void delete(Note note);

}
