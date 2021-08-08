package manabboro.roomdatabase.sample.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private LayoutInflater mInflater;
    private List<Note> notes=new ArrayList<>();

    public NoteAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_note_layout, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
