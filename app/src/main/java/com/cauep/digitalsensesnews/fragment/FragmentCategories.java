package com.cauep.digitalsensesnews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cauep.digitalsensesnews.R;
import com.cauep.digitalsensesnews.model.Category;

import java.util.ArrayList;

/**
 * Created by mario on 27/05/2017.
 */

public class FragmentCategories extends Fragment {

    private ArrayList<Category> myItens = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categories, container, false);

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.category_rv);

        // improve performance when you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        // Custom adapter
        CategoryItemAdapter adapter = new CategoryItemAdapter(myItens);
        mRecyclerView.setAdapter(adapter);


        return v;
    }
}
