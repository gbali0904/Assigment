package com.android.assignment.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.assignment.R;
import com.android.assignment.base.BaseViewHolder;
import com.android.assignment.list.model.ModelForSearchList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<ModelForSearchList.ItemsBean> modelForSearchList;
    public void setList(List<ModelForSearchList.ItemsBean> modelForSearchList) {
        this.modelForSearchList = modelForSearchList;
        notifyDataSetChanged();
    }
    public  SearchListAdapter ()
    {
        modelForSearchList=new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (viewType == VIEW_TYPE_ITEM) {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_list, parent, false);
            return new SearchListViewHolder(itemView);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_dialog, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder basicviewHolder, int i) {

        if (basicviewHolder instanceof SearchListViewHolder) {
            ((SearchListViewHolder) basicviewHolder).bind(modelForSearchList.get(i));
        } else if (basicviewHolder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) basicviewHolder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return modelForSearchList == null ? 0 : modelForSearchList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return modelForSearchList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class SearchListViewHolder extends BaseViewHolder {
        @BindView(R.id.txt_repo_name)
        TextView repo_name;
        @BindView(R.id.lay)
        LinearLayout lay;
        public SearchListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        public void bind(ModelForSearchList.ItemsBean itemsBean) {
            repo_name.setText(itemsBean.getName());
        }
    }

    public class LoadingViewHolder extends BaseViewHolder {
        @BindView(R.id.pb_loading)
        ProgressBar pbLoading;

        public LoadingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
        }

        public void bind() {
        }
    }
}