package com.cauep.digitalsensesnews.controller;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauep.digitalsensesnews.R;
import com.cauep.digitalsensesnews.fragment.FragmentCategories;
import com.cauep.digitalsensesnews.model.Category;
import com.cauep.digitalsensesnews.utils.Constants;

import java.util.ArrayList;

/**
 * Created by mario on 27/05/2017.
 */

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ItemViewHolder> {

    private final Resources resources;
    private ArrayList<Category> myItens;
    private FragmentCategories.OnCategorySelectedListener mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public ItemViewHolder(final View itemView, final FragmentCategories.OnCategorySelectedListener mListener) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.txt_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onCategorySelected(getLayoutPosition());
                }
            });

        }
    }

    // Constructor
    public CategoryItemAdapter(ArrayList<Category> myItens, FragmentCategories.OnCategorySelectedListener mListener, Resources res){
        this.myItens = myItens;
        this.mListener = mListener;
        this.resources = res;
    }

    @Override
    public CategoryItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category, viewGroup, false);


        ItemViewHolder ivh = new ItemViewHolder(v, mListener);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        Category item = myItens.get(position);

        // Build category name adapted accordingly to the system language
        String categoryName;
        switch (item.getCategory()) {
            case Constants.CATEGORY.SPORTS:
                categoryName = resources.getString(R.string.categories_sports);
                break;
            case Constants.CATEGORY.ECONOMY:
                categoryName = resources.getString(R.string.categories_economy);
                break;
            case Constants.CATEGORY.HEALTH:
                categoryName = resources.getString(R.string.categories_health);
                break;
            case Constants.CATEGORY.TECH:
                categoryName = resources.getString(R.string.categories_tech);
                break;
            case Constants.CATEGORY.LAST_NEWS:
                categoryName = resources.getString(R.string.categories_last_news);
                break;
            default:
                categoryName = "";
                break;
        }
        itemViewHolder.title.setText(categoryName);

        // Build category accessible description adapted accordingly to the system language
        String description = resources.getString(R.string.category_description, categoryName);
        itemViewHolder.title.setContentDescription(description);
    }

    @Override
    public int getItemCount() {
        return myItens.size();
    }

}
