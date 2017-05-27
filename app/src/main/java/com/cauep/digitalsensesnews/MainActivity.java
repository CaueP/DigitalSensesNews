package com.cauep.digitalsensesnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cauep.digitalsensesnews.fragment.FragmentNewsPager;

/**
 * @author Caue Garcia Polimanti
 * @version 1.0
 * Created on 05/27/2017
 */
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
