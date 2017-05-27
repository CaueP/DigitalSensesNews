package com.cauep.digitalsensesnews;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cauep.digitalsensesnews.controller.NewsPagerAdapter;
import com.cauep.digitalsensesnews.fragment.FragmentNewsPager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //Fragment mContent = RecyclerViewActivity_Task3.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentNewsPager())
                    .commit();
        }
    }
}
