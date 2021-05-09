package com.example.farshid.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farshid.myapplication.DataBase.Note;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.viewHolder> {

    Context context;
    List<Note> noteList;
    OnDeleteNote onDeleteNote;

    public void setOnDeleteNote(OnDeleteNote onDeleteNote) {
        this.onDeleteNote = onDeleteNote;
    }

    public NoteAdapter(Context context, List<Note> noteList, OnDeleteNote onDeleteNote) {
        this.context = context;
        this.noteList = noteList;
        this.onDeleteNote = onDeleteNote;
    }

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item_row, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {
        final Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.des.setText(note.getDes());
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/irsans.ttf");
        holder.title.setTypeface(tf);
        holder.des.setTypeface(tf);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteList.remove(note);
                onDeleteNote.OnNoteDelete(position, note);


            }
        });
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.swing_up_left);
        holder.cardview.startAnimation(animation);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(context, ShowDes.class);
                in.putExtra("Title", note.getTitle());
                in.putExtra("Des", note.getDes());
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView des;
        ImageView delete;
        CardView cardview;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_row);
            cardview = itemView.findViewById(R.id.cardview);
            des = itemView.findViewById(R.id.des_row);
            delete = itemView.findViewById(R.id.note_delete);
        }
    }

    public interface OnDeleteNote {
        void OnNoteDelete(int position, Note note);
    }
}
