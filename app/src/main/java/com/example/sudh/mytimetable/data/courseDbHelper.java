package com.example.sudh.mytimetable.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.example.sudh.mytimetable.data.courseContract.courseEntry;
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
