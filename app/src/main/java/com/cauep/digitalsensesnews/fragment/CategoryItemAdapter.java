package com.cauep.digitalsensesnews.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauep.digitalsensesnews.R;
import com.cauep.digitalsensesnews.model.Category;

import java.util.ArrayList;

/**
 * Created by mario on 27/05/2017.
 */

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ItemViewHolder> {

    private ArrayList<Category> myItens;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public ItemViewHolder(final View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.txt_category);

        }
    }

    // Constructor
    public CategoryItemAdapter(ArrayList<Category> myItens){
        this.myItens = myItens;
    }

    @Override
    public CategoryItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category, viewGroup, false);

        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        Category item = myItens.get(position);
        itemViewHolder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return myItens.size();
    }

}
