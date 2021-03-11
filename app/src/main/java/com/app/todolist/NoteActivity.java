package com.app.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.todolist.models.Note;

public class NoteActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static final String TAG = "NoteActivity";
    LinedEditText mEtNotesText;
    EditText mEtTitle;
    TextView mTvTitle;
    RelativeLayout viewContainer, editContainer;
    ImageButton imgArrow, imgCheck;

    private static final int EDIT_MODE=1;
    private static final int VIEW_MODE=2;
    private int mCurrentMode;


    Note mInitialNote;
    GestureDetector mGestureDetector;
    boolean isNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mEtNotesText = findViewById(R.id.et_notes);
        mEtTitle = findViewById(R.id.et_title);
        mTvTitle = findViewById(R.id.tv_title);
        viewContainer = findViewById(R.id.img_back_container);
        editContainer = findViewById(R.id.img_check_container);
        imgArrow = findViewById(R.id.img_back);
        imgCheck = findViewById(R.id.img_check);

        if (getIncomingIntent()) {
            setNewNote();
        } else {
            setNote();
        }

        setListner();
    }

    private void enableEdit(){
        viewContainer.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.GONE);

        editContainer.setVisibility(View.VISIBLE);
        mEtTitle.setVisibility(View.VISIBLE);
    }

    private void disableEdit(){
        viewContainer.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);

        editContainer.setVisibility(View.GONE);
        mEtTitle.setVisibility(View.GONE);
    }


    private void setListner(){
        mEtNotesText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this,this);

    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            isNewNote = false;
            return false;
        }

        isNewNote = true;
        return true;
    }


    private void setNote() {
        mTvTitle.setText(mInitialNote.getTitle());
        mEtTitle.setText(mInitialNote.getTitle());
        mEtNotesText.setText(mInitialNote.getContent());
        disableEdit();
    }

    private void setNewNote() {
        mEtTitle.setText("Enter title");
        mTvTitle.setText("Enter title");
        enableEdit();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.d(TAG, "onDoubleTap: double tapped");
        enableEdit();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}