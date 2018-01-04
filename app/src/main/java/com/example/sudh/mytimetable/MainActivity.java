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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.sudh.mytimetable.data.courseDbHelper;
import com.example.sudh.mytimetable.data.courseContract.courseEntry;


public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    courseDbHelper mDbHelper = new courseDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        DaysManager adapter = new DaysManager(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        //TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

       // tabLayout.setupWithViewPager(viewPager);
    }


}
