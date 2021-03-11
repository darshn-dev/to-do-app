package com.app.todolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.todolist.R;
import com.app.todolist.models.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<Note> mNotes = new ArrayList<>();
    onNoteClickListener onNoteClickListener;

    public NoteAdapter(ArrayList<Note> mNotes, onNoteClickListener onNoteClickListener) {
        this.mNotes = mNotes;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item,parent, false);
        return new ViewHolder(view, onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.timestamp.setText(mNotes.get(position).getTimeStamp());
        holder.title.setText(mNotes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, timestamp;
        onNoteClickListener onNoteClickListener;

        public ViewHolder(@NonNull View itemView, onNoteClickListener onNoteClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            timestamp = itemView.findViewById(R.id.tv_timestamp);
            this.onNoteClickListener = onNoteClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteClickListener.onNotedClicked(getAdapterPosition());
        }
    }


    public interface onNoteClickListener{
        void onNotedClicked(int position);
    }
}
