package manabboro.roomdatabase.sample.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "note_title")
    public String title;

    @ColumnInfo(name = "note_desc")
    public String note;

    @ColumnInfo(name = "note_taken")
    public long dateTaken;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public int getUid() {
        return uid;
    }
}
