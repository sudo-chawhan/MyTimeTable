package com.example.sudh.mytimetable.data;

import com.example.sudh.mytimetable.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.example.sudh.mytimetable.data.courseContract.courseEntry;
import android.view.View;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.LinearLayout;
import android.util.Log;



/**
 * Created by sudo-chawhan on 26/12/17.
 */

public class courseDbHelper extends SQLiteOpenHelper{

    public static final int database_version = 1;
    public static final String database_name = "class.db";


    public courseDbHelper(Context context) {
        super(context,database_name,null,database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FeedEntry.CMD_CREATE_NEW_TABLE);
        // initialise the database
        //InitTableData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static void displayContent(View rootView, courseDbHelper mDbHelper, int currentDayId, Context current){
        String TAG = "courseDbHelper";

        Cursor cursor=null;
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        String[] columns = new String[]{
                courseContract.courseEntry.COLUMN_NAME, courseContract.courseEntry.COLUMN_ROOM, courseContract.courseEntry.IS_EMPTY
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
        LinearLayout slot_one = (LinearLayout) rootView.findViewById(R.id.slot_one);
        LinearLayout slot_two = (LinearLayout) rootView.findViewById(R.id.slot_two);
        LinearLayout slot_three = (LinearLayout) rootView.findViewById(R.id.slot_three);
        LinearLayout slot_four = (LinearLayout) rootView.findViewById(R.id.slot_four);
        LinearLayout slot_five = (LinearLayout) rootView.findViewById(R.id.slot_five);
        LinearLayout slot_six = (LinearLayout) rootView.findViewById(R.id.slot_six);

        String[] selectionArgs = new String[]{
                String.valueOf(currentDayId)                           //////// get cursor depending on which day it is
        };
        cursor = database.query(courseContract.courseEntry.TABLE_NAME, columns, courseContract.courseEntry.COLUMN_DAY_ID+"=?",selectionArgs,null,null,null);
        cursor.moveToFirst();
        // set the layout fields according to data values
        one_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        one_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        if(cursor.getInt(cursor.getColumnIndex(courseContract.courseEntry.IS_EMPTY))==0) slot_one.setBackgroundColor(current.getResources().getColor(R.color.not_empty));
        cursor.moveToNext();

        two_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        two_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        if(cursor.getInt(cursor.getColumnIndex(courseContract.courseEntry.IS_EMPTY))==0) slot_two.setBackgroundColor(current.getResources().getColor(R.color.not_empty));
        cursor.moveToNext();

        three_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        three_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        if(cursor.getInt(cursor.getColumnIndex(courseContract.courseEntry.IS_EMPTY))==0) slot_three.setBackgroundColor(current.getResources().getColor(R.color.not_empty));
        cursor.moveToNext();

        four_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        four_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        if(cursor.getInt(cursor.getColumnIndex(courseContract.courseEntry.IS_EMPTY))==0) slot_four.setBackgroundColor(current.getResources().getColor(R.color.not_empty));
        cursor.moveToNext();

        five_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        five_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        if(cursor.getInt(cursor.getColumnIndex(courseContract.courseEntry.IS_EMPTY))==0) slot_five.setBackgroundColor(current.getResources().getColor(R.color.not_empty));
        cursor.moveToNext();

        six_name.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_NAME)));
        six_room.setText(cursor.getString(cursor.getColumnIndex(courseContract.courseEntry.COLUMN_ROOM)));
        if(cursor.getInt(cursor.getColumnIndex(courseContract.courseEntry.IS_EMPTY))==0) slot_six.setBackgroundColor(current.getResources().getColor(R.color.not_empty));

        cursor.close();
    }

    public static abstract class FeedEntry implements BaseColumns {
        // command to create new table
        // create table pets(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NON NULL, breed TEXT, gender INTEGER, weight INTEGER NON NULL);
        public static final String CMD_CREATE_NEW_TABLE = "CREATE TABLE " + courseEntry.TABLE_NAME + "(" +
                courseEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT" +
                "," + courseEntry.COLUMN_SLOT_ID + " INTEGER NOT NULL" +
                "," + courseEntry.COLUMN_DAY_ID + " INTEGER NOT NULL" +
                "," + courseEntry.IS_EMPTY + " INTEGER DEFAULT 1" +
                "," + courseEntry.COLUMN_NAME + " TEXT" +
                "," + courseEntry.COLUMN_CODE + " TEXT" +
                "," + courseEntry.COLUMN_ROOM + " TEXT" +
                "," + courseEntry.COLUMN_NOTES + " TEXT" +
                "," + courseEntry.COLUMN_PROF + " TEXT);";
    }
}
