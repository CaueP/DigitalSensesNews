package com.cauep.digitalsensesnews.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cauep.digitalsensesnews.R;
import com.cauep.digitalsensesnews.controller.CategoryItemAdapter;
import com.cauep.digitalsensesnews.model.Category;
import com.cauep.digitalsensesnews.utils.Constants;

import java.util.ArrayList;

/**
 * Created by mario on 27/05/2017.
 */

public class FragmentCategories extends Fragment {
    final static String TAG = "FragmentCategories";

    private ArrayList<Category> myItens = null;

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

        // Restore News from Bundle of arguments
        Bundle args = getArguments();
        if (args != null) {
            myItens = (ArrayList<Category>) args.getSerializable(Constants.KEY.CATEGORIES_LIST);
        } else {
            Log.d(TAG, "Bundle com lista de Categorias vazia");
        }

        // Custom adapter
        CategoryItemAdapter adapter = new CategoryItemAdapter(myItens, mListener, getResources());
        mRecyclerView.setAdapter(adapter);

        return v;
    }


    /**
     * Interface implemented by MainActivity to call the news fragment
     */
    public interface OnCategorySelectedListener {
        /**
         * Method to send the news item selected
         *
         * @param itemPosition Item position on the list
         */
        void onCategorySelected(int itemPosition);
    }

    // Interface listener
    public OnCategorySelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "Entered in onAttach");
        super.onAttach(context);
        try {
            mListener = (FragmentCategories.OnCategorySelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() +
                    "must implement OnListItemSelectedListener");
        }
    }
}
