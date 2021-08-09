package manabboro.roomdatabase.sample.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import manabboro.roomdatabase.sample.R;
import manabboro.roomdatabase.sample.models.Note;
import manabboro.roomdatabase.sample.util.ColorUtils;
import manabboro.roomdatabase.sample.util.DateUtils;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private LayoutInflater mInflater;
    private List<Note> notes = new ArrayList<>();

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
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void updateNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mNotes, mDate;
        private CardView mCardView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.tile);
            mNotes = itemView.findViewById(R.id.note);
            mDate = itemView.findViewById(R.id.date);
            mCardView = itemView.findViewById(R.id.card_view);
        }

        public void bind(Note note) {

            mTitle.setText(note.getTitle());
            mNotes.setText(note.getNote());
            mDate.setText(DateUtils.formatDate(note.getDateTaken()));

            if (note.getTitle() == null || note.getTitle().isEmpty()) {
                mTitle.setVisibility(View.GONE);
            }
            mCardView.setCardBackgroundColor((ColorUtils.generateRandomColor()));
        }
    }
}
