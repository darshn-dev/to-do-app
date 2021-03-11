package com.app.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;

import com.app.todolist.adapter.NoteAdapter;
import com.app.todolist.models.Note;
import com.app.todolist.util.ItemDecorator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class NoteListActivity extends AppCompatActivity implements NoteAdapter.onNoteClickListener {

    private static final String TAG = "NoteListActivity";
    //UI
    RecyclerView mNotesRecycler;


    //other var
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        fakeNotes();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

    }

    private void fakeNotes() {
        for (int i = 0; i < 100; i++) {
            mNotes.add(new Note("Title " + i, "Content " + i, "" + new Date()));
        }
        adapter.notifyDataSetChanged();
    }

    private void initUI() {
        mNotesRecycler = findViewById(R.id.rv_notesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNotesRecycler.setLayoutManager(linearLayoutManager);
        adapter = new NoteAdapter(mNotes, this);
        ItemDecorator itemDecorator = new ItemDecorator(10);
        mNotesRecycler.addItemDecoration(itemDecorator);
        mNotesRecycler.setAdapter(adapter);
    }

    @Override
    public void onNotedClicked(int position) {
        Log.d(TAG, "onNotedClicked: running");
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("selected_note",mNotes.get(position));
        startActivity(intent);
    }
}