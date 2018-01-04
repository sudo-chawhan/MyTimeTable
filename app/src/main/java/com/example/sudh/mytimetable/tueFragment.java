package com.example.sudh.mytimetable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.sudh.mytimetable.data.courseDbHelper;
import com.example.sudh.mytimetable.data.courseContract;


/**
 * Created by sudo-chawhan on 04/01/18.
 */

public class tueFragment extends android.support.v4.app.Fragment {

    private String TAG = "MainActivity";
    int currentDayId=2;
    courseDbHelper mDbHelper;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.day_fragment,container,false);
        Log.d(TAG, "onCreateView: before init table");
        mDbHelper = new courseDbHelper(getActivity().getApplicationContext());
        InitTableData();
        Log.d(TAG, "onCreateView: before set listener");
        setListeners();
        Log.d(TAG, "onCreateView: before display content");
        displayContent();
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        displayContent();
    }

    public void setListeners(){
        // setting layout references
        LinearLayout slot_one = (LinearLayout) rootView.findViewById(R.id.slot_one);
        LinearLayout slot_two = (LinearLayout) rootView.findViewById(R.id.slot_two);
        LinearLayout slot_three = (LinearLayout) rootView.findViewById(R.id.slot_three);
        LinearLayout slot_four = (LinearLayout) rootView.findViewById(R.id.slot_four);
        LinearLayout slot_five = (LinearLayout) rootView.findViewById(R.id.slot_five);
        LinearLayout slot_six = (LinearLayout) rootView.findViewById(R.id.slot_six);
        if(slot_one==null) Log.d(TAG, "setListeners: after setiing up view ids *");
        //setting on clock listeners for each slot
        slot_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditorActivity.class);
                Log.d(TAG, "onClick: hahaha");
                intent.putExtra(courseContract.courseEntry.COLUMN_SLOT_ID,1);
                intent.putExtra(courseContract.courseEntry.COLUMN_DAY_ID,currentDayId);
                Log.d(TAG, "onClick: hahaha");
                tueFragment.this.startActivity(intent);
            }
        });
        slot_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),EditorActivity.class);
                intent.putExtra(courseContract.courseEntry.COLUMN_SLOT_ID,2);
                intent.putExtra(courseContract.courseEntry.COLUMN_DAY_ID,currentDayId);
                tueFragment.this.startActivity(intent);
            }
        });
        slot_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditorActivity.class);
                intent.putExtra(courseContract.courseEntry.COLUMN_SLOT_ID,3);
                intent.putExtra(courseContract.courseEntry.COLUMN_DAY_ID,currentDayId);
                tueFragment.this.startActivity(intent);
            }
        });
        slot_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditorActivity.class);
                intent.putExtra(courseContract.courseEntry.COLUMN_SLOT_ID,4);
                intent.putExtra(courseContract.courseEntry.COLUMN_DAY_ID,currentDayId);
                tueFragment.this.startActivity(intent);
            }
        });
        slot_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditorActivity.class);
                intent.putExtra(courseContract.courseEntry.COLUMN_SLOT_ID,5);
                intent.putExtra(courseContract.courseEntry.COLUMN_DAY_ID,currentDayId);
                tueFragment.this.startActivity(intent);
            }
        });
        slot_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditorActivity.class);
                intent.putExtra(courseContract.courseEntry.COLUMN_SLOT_ID,6);
                intent.putExtra(courseContract.courseEntry.COLUMN_DAY_ID,currentDayId);
                tueFragment.this.startActivity(intent);
            }
        });
    }

    public void InitTableData(){
        Log.d(TAG, "InitTableData: before getWritableDatabase");
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        Log.d(TAG, "InitTableData: here its cmae");
        String count = "SELECT count(*) FROM "+ courseContract.courseEntry.TABLE_NAME;
        Log.d(TAG, "InitTableData: problem with count");
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount==0) {
            Log.d(TAG, "InitTableData: never came hereakdalfjalf");// initialise only if table is empty
            for(int j=1;j<=5;j++){
                for (int i = 1; i <= 6; i++) {
                    ContentValues values = new ContentValues();
                    values.put(courseContract.courseEntry.COLUMN_SLOT_ID, i);       // enter slot id (1 for 9am)
                    values.put(courseContract.courseEntry.COLUMN_DAY_ID, j);        // enter day id (1 for monday)
                    values.put(courseContract.courseEntry.COLUMN_NAME, "");
                    values.put(courseContract.courseEntry.COLUMN_CODE, "");
                    values.put(courseContract.courseEntry.COLUMN_ROOM, "");
                    values.put(courseContract.courseEntry.COLUMN_PROF, "");
                    values.put(courseContract.courseEntry.COLUMN_NOTES, "");

                    database.insert(courseContract.courseEntry.TABLE_NAME, null, values);
                }
            }
        }
    }

    public void displayContent(){
        Cursor cursor=null;
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        String[] columns = new String[]{
                courseContract.courseEntry.COLUMN_NAME, courseContract.courseEntry.COLUMN_ROOM
        };
        Log.d(TAG, "displayContent: i came here");
        TextView one_name = (TextView) rootView.findViewById(R.id.slot_one_course);
        TextView one_room = (TextView) rootView.findViewById(R.id.slot_one_class);
        TextView two_name = (TextView) rootView.findViewById(R.id.slot_two_course);
        TextView two_room = (TextView) rootView.findViewById(R.id.slot_two_class);
        TextView three_name = (TextView) rootView.findViewById(R.id.slot_three_course);
        TextView three_room = (TextView) rootView.findViewById(R.id.slot_three_class);
        TextView four_name = (TextView) rootView.findViewById(R.id.slot_four_course);
        TextView four_room = (TextView) rootView.findViewById(R.id.slot_four_class);
        TextView five_name = (TextView) rootView.findViewById(R.id.slot_five_course);
        TextView five_room = (TextView) rootView.findViewById(R.id.slot_five_class);
        TextView six_name = (TextView) rootView.findViewById(R.id.slot_six_course);
        TextView six_room = (TextView) rootView.findViewById(R.id.slot_six_class);

        String[] selectionArgs = new String[]{
                String.valueOf(currentDayId)                           //////// get cursor depending on which day it is
        };
        cursor = database.query(courseContract.courseEntry.TABLE_NAME,columns, courseContract.courseEntry.COLUMN_DAY_ID+"=?",selectionArgs,null,null,null);
        cursor.moveToFirst();
        // set the layout fields according to data values
        one_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        one_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        two_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        two_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        three_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        three_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        four_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        four_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        five_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        five_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        cursor.moveToNext();
        six_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        six_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));

        cursor.close();
    }
}
