package com.example.farshid.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import com.example.farshid.myapplication.DataBase.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteAdapter adapter;
    List<Note> noteList;
    Handler handler;
    Handler handler2;
    FloatingActionButton fab;
    TextView text_empty;
    Thread thread;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int greenColorValue = Color.parseColor("#D81B60");
        int greenColorValue2 = Color.parseColor("#930538");
        fab = findViewById(R.id.fab);
        text_empty = findViewById(R.id.text_empty);
//        new MaterialTapTargetPrompt.Builder(MainActivity.this)
//                .setTarget(findViewById(R.id.fab)).setAutoDismiss(false)
//                .setPrimaryText("افزودن یادداشت")
//                .setSecondaryText("برای افزودن یادداشت خود این دکمه را کلیک کنید !").setBackgroundColour(greenColorValue)
//                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
//                    @Override
//                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
//                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
//                            NoteAdapter noteAdapter = new NoteAdapter(MainActivity.this);
//                            new MaterialTapTargetPrompt.Builder(MainActivity.this)
//                                    .setTarget(text_empty).setAutoDismiss(false)
//                                    .setPrimaryText("لیست یادداشت ها").setSecondaryText("یادداشت های شما در این قسمت اضافه می شود.").setBackgroundColour(greenColorValue)
//                                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
//                                        @Override
//                                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
//                                            if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
//                                            }
//                                        }
//
//                                    }).show();
//
//
//                        }
//                    }
//
//                }).show();

        getNotes();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round);
                fab.startAnimation(animation);

                handler = new Handler();
                handler2 = new Handler();

                    handler2.postAtTime(new Runnable() {
                        @Override
                        public void run() {

                            myDialog dialog = new myDialog();
                            dialog.show(getSupportFragmentManager(), null);
                            dialog.setOnAddNoteClick(new myDialog.OnAddNoteClick() {
                                @Override
                                public void onAddNote(String title, String des) {
                                    Note note = new Note();
                                    note.setTitle(title);
                                    note.setDes(des);

                                    AppDatabase.getInstance(MainActivity.this).noteDao().insertAll(note);
                                    noteList.add(note);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });


                                }
                            });

                        }
                    }, 5000);


                }

        });

    }

    private void getNotes() {
        recyclerView = findViewById(R.id.rv_main);


        noteList = AppDatabase.getInstance(this).noteDao().getAllNotes();

        if (noteList.isEmpty()) {
            TextView empty = findViewById(R.id.text_empty);
            empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);


        } else {
            adapter = new NoteAdapter(this, noteList, new NoteAdapter.OnDeleteNote() {
                @Override
                public void OnNoteDelete(int position, Note note) {

                    AppDatabase.getInstance(MainActivity.this).noteDao().delete(note);
                    adapter.notifyItemRemoved(position);

                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }


    }


}

