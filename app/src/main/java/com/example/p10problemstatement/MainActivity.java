package com.example.p10problemstatement;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Button btnRead;
    ArrayList<Fragment> fragments;
    MyFragmentPagerAdapter adapter;
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        btnRead = findViewById(R.id.btnRead);

        FragmentManager fm = getSupportFragmentManager();
        fragments = new ArrayList<Fragment>();
        fragments.add(new frag1());
        fragments.add(new frag2());
        fragments.add(new frag3());

        adapter = new MyFragmentPagerAdapter(fm,fragments);
        viewPager.setAdapter(adapter);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5);

                //Create a new PendingIntent and add it to the AlarmManager
                Intent intent = new Intent(MainActivity.this,
                        MainActivity.class);
                int reqCode = 12345;
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(MainActivity.this,
                                reqCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                // Get AlarmManager instance
                am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);

                // Set the alarm
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }

        });


    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("currentFrag", viewPager.getCurrentItem());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        int currFrag = sp.getInt("currentFrag",0);
        viewPager.setCurrentItem(currFrag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.actionNext:
                int max = viewPager.getChildCount() - 1;
                if (viewPager.getCurrentItem() < max){
                    int nextPage = viewPager.getCurrentItem()+ 1;
                    viewPager.setCurrentItem(nextPage,true);
                } else {
                    Toast.makeText(this, "This is the last page!", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.actionPrevious:
                if (viewPager.getCurrentItem() > 0){
                    int previousPage = viewPager.getCurrentItem() - 1;
                    viewPager.setCurrentItem(previousPage, true);
                } else{
                    Toast.makeText(this, "This is the first page!", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.actionRandom:
                ArrayList<Integer> intList = new ArrayList<>();
                intList.add(0);
                intList.add(1);
                intList.add(2);
                Collections.shuffle(intList);
                viewPager.setCurrentItem(intList.get(0), true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
