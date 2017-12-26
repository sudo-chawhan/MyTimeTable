package com.example.sudh.mytimetable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.sudh.mytimetable.data.courseContract.courseEntry;
import com.example.sudh.mytimetable.data.courseDbHelper;


/**
 * Created by sudo-chawhan on 26/12/17.
 */

public class EditorActivity extends AppCompatActivity {

    private String TAG = "EditorActivity";
    
    EditText mName = (EditText) findViewById(R.id.edit_course_name);
    EditText mCode = (EditText) findViewById(R.id.edit_course_code);
    EditText mRoom = (EditText) findViewById(R.id.edit_course_room);
    EditText mProf = (EditText) findViewById(R.id.edit_course_prof);
    EditText mNotes = (EditText) findViewById(R.id.edit_course_notes);
    Intent mIntent;
    courseDbHelper mDbHelper = new courseDbHelper(this);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Log.d(TAG, "onCreate: halelujahya");
        mIntent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.string.action_save:
                updateCourse();
                finish();
                return true;
            case R.string.action_delete:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void updateCourse(){

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        String name = mName.getText().toString().trim();
        String code = mCode.getText().toString().trim();
        String room = mRoom.getText().toString().trim();
        String prof = mProf.getText().toString().trim();
        String notes = mNotes.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(courseEntry.COLUMN_NAME,name);
        values.put(courseEntry.COLUMN_CODE,code);
        values.put(courseEntry.COLUMN_ROOM,room);
        values.put(courseEntry.COLUMN_PROF,prof);
        values.put(courseEntry.COLUMN_NOTES,notes);

        int slot_id = mIntent.getIntExtra(courseEntry.COLUMN_SLOT_ID,-1);
        int day_id = mIntent.getIntExtra(courseEntry.COLUMN_DAY_ID,-1);

        String[] selectionArgs = new String[]{
                String.valueOf(slot_id), String.valueOf(day_id)
        };

        database.update(courseEntry.TABLE_NAME,values,courseEntry.COLUMN_SLOT_ID + "= ? AND "+courseEntry.COLUMN_DAY_ID +"= ?",selectionArgs);

    }
}
