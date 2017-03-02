package com.traato.nar.traato.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.traato.nar.traato.R;
import com.traato.nar.traato.entities.CategoryEntity;
import com.traato.nar.traato.entities.GsonCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nar on 3/1/2017.
 */

public class DrawerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<CategoryEntity> mCategoryList = new ArrayList<>();

    public DrawerRecyclerAdapter(Context context)
    {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.list_item_category, parent, false);
        return new ViewHolderItemCategory(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderItemCategory viewHolderItemCategory = (ViewHolderItemCategory) holder;
        CategoryEntity categoryEntity = getCategory(position);
        GsonCategory gsonCategory = categoryEntity.getGsonCategory();

        ((ViewHolderItemCategory) holder).mTextView.setText(gsonCategory.getName());
        viewHolderItemCategory.mDivider.setVisibility(View.VISIBLE);

        if (categoryEntity.getSubCategories() == null || categoryEntity.getSubCategories().isEmpty()) {
            viewHolderItemCategory.mSubMenuIndicator.setVisibility(View.INVISIBLE);
        } else {
            viewHolderItemCategory.mSubMenuIndicator.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size() ;
    }

    public CategoryEntity getCategory(int position)
    {
        return mCategoryList.get(position-1);
    }

    public void addCategoryList(List<CategoryEntity> categoryList)
    {
        if(categoryList.isEmpty())
            return;
        mCategoryList.addAll(categoryList);
    }

    public void addCategoryItem(CategoryEntity categoryEnt)
    {
        if(categoryEnt == null)
            return;
        mCategoryList.add(categoryEnt);
    }

    //ViewHolderClasses
    public static class ViewHolderItemCategory extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mSubMenuIndicator;
        public  View mDivider;

        public CategoryEntity mCategoryEnt;

        public ViewHolderItemCategory(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.drawer_list_item_text);
            mSubMenuIndicator = (ImageView) itemView.findViewById(R.id.drawer_list_item_indicator);
            mDivider = itemView.findViewById(R.id.drawer_list_item_divider);
        }

        public void bindContent(CategoryEntity categoryEntity)
        {
            this.mCategoryEnt = categoryEntity;
        }
    }
}


