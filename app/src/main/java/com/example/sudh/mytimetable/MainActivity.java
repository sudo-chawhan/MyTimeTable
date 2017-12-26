package com.example.sudh.mytimetable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sudh.mytimetable.data.courseDbHelper;
import com.example.sudh.mytimetable.data.courseContract.courseEntry;


public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    courseDbHelper mDbHelper = new courseDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: i came here");
        setListeners();
        Log.d(TAG, "onCreate: i came here");
        InitTableData();
        Log.d(TAG, "onCreate: i came here");
        displayContent();
        Log.d(TAG, "onCreate: i came here");
    }

    public void setListeners(){
        // setting layout references
        RelativeLayout slot_one = (RelativeLayout) findViewById(R.id.slot_one);
        RelativeLayout slot_two = (RelativeLayout) findViewById(R.id.slot_two);
        RelativeLayout slot_three = (RelativeLayout) findViewById(R.id.slot_three);
        RelativeLayout slot_four = (RelativeLayout) findViewById(R.id.slot_four);
        RelativeLayout slot_five = (RelativeLayout) findViewById(R.id.slot_five);
        RelativeLayout slot_six = (RelativeLayout) findViewById(R.id.slot_six);

        //setting on clock listeners for each slot
        slot_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                Log.d(TAG, "onClick: hahaha");
                intent.putExtra(courseEntry.COLUMN_SLOT_ID,1);
                intent.putExtra(courseEntry.COLUMN_DAY_ID,1);
                Log.d(TAG, "onClick: hahaha");
                MainActivity.this.startActivity(intent);
            }
        });
        slot_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                intent.putExtra(courseEntry.COLUMN_SLOT_ID,2);
                intent.putExtra(courseEntry.COLUMN_DAY_ID,1);
                MainActivity.this.startActivity(intent);
            }
        });
        slot_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                intent.putExtra(courseEntry.COLUMN_SLOT_ID,3);
                intent.putExtra(courseEntry.COLUMN_DAY_ID,1);
                MainActivity.this.startActivity(intent);
            }
        });
        slot_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                intent.putExtra(courseEntry.COLUMN_SLOT_ID,4);
                intent.putExtra(courseEntry.COLUMN_DAY_ID,1);
                MainActivity.this.startActivity(intent);
            }
        });
        slot_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                intent.putExtra(courseEntry.COLUMN_SLOT_ID,5);
                intent.putExtra(courseEntry.COLUMN_DAY_ID,1);
                MainActivity.this.startActivity(intent);
            }
        });
        slot_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                intent.putExtra(courseEntry.COLUMN_SLOT_ID,6);
                intent.putExtra(courseEntry.COLUMN_DAY_ID,1);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public void InitTableData(){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        String count = "SELECT count(*) FROM "+courseEntry.TABLE_NAME;
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount==0) {                                     // initialise only if table is empty
            for (int i = 1; i <= 6; i++) {
                ContentValues values = new ContentValues();
                values.put(courseEntry.COLUMN_SLOT_ID, i);       // enter slot id (1 for 9am)
                values.put(courseEntry.COLUMN_DAY_ID, 1);        // enter day id (1 for monday)
                values.put(courseEntry.COLUMN_NAME, "");
                values.put(courseEntry.COLUMN_CODE, "");
                values.put(courseEntry.COLUMN_ROOM, "");
                values.put(courseEntry.COLUMN_PROF, "");
                values.put(courseEntry.COLUMN_NOTES, "");

                database.insert(courseEntry.TABLE_NAME, null, values);
            }
        }
    }

    public void displayContent(){
        Cursor cursor=null;
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        String[] columns = new String[]{
                courseEntry.COLUMN_NAME, courseEntry.COLUMN_ROOM
        };
        Log.d(TAG, "displayContent: i came here");
        TextView one_name = (TextView) findViewById(R.id.slot_one_course);
        TextView one_room = (TextView) findViewById(R.id.slot_one_class);
        TextView two_name = (TextView) findViewById(R.id.slot_two_course);
        TextView two_room = (TextView) findViewById(R.id.slot_two_class);
        TextView three_name = (TextView) findViewById(R.id.slot_three_course);
        TextView three_room = (TextView) findViewById(R.id.slot_three_class);
        TextView four_name = (TextView) findViewById(R.id.slot_four_course);
        TextView four_room = (TextView) findViewById(R.id.slot_four_class);
        TextView five_name = (TextView) findViewById(R.id.slot_five_course);
        TextView five_room = (TextView) findViewById(R.id.slot_five_class);
        TextView six_name = (TextView) findViewById(R.id.slot_six_course);
        TextView six_room = (TextView) findViewById(R.id.slot_six_class);

        String[] selectionArgs = new String[]{
                String.valueOf(1)                           //////// get cursor depending on which day it is
        };
        cursor = database.query(courseEntry.TABLE_NAME,columns,courseEntry.COLUMN_DAY_ID+"=?",selectionArgs,null,null,null);
        cursor.moveToFirst();
        // set the layout fields according to data values
        one_name.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_NAME)));
        one_room.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        two_name.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_NAME)));
        two_room.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        three_name.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_NAME)));
        three_room.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        four_name.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_NAME)));
        four_room.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        five_name.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_NAME)));
        five_room.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        six_name.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_NAME)));
        six_room.setText(cursor.getString(cursor.getColumnIndex(courseEntry.COLUMN_ROOM)));

        cursor.close();
    }
}
