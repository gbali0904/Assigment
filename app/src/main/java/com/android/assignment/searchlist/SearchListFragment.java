package com.android.assignment.searchlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.assignment.R;
import com.android.assignment.base.BaseFragment;
import com.android.assignment.di.component.ActivityComponent;
import com.android.assignment.searchlist.adapter.SearchListAdapter;
import com.android.assignment.searchlist.model.ModelForSearchList;
import com.android.assignment.searchlist.persenter.SearchListPresenter;
import com.android.assignment.searchlist.view.SearchListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchListFragment extends BaseFragment implements SearchListView {

    @Inject
    SearchListPresenter<SearchListView> mPresenter;
    @Inject
    SearchListAdapter adapter;
    @Inject
    LinearLayoutManager mLayoutManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private boolean isLoading = false;
    private static  String language;
    private static String type;
    private int page = 1;
    private List<ModelForSearchList.ItemsBean> items;

    public static SearchListFragment newInstance(String project_type, String project_language) {
        type=project_type;
        language=project_language;
        return new SearchListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mPresenter.getSearchList(type, language, page);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        loadMoreDataRecyclerviewScroller();
    }

    @Override
    public void getSearchListSuccess(List<ModelForSearchList.ItemsBean> modelForSearchList) {
        items = modelForSearchList;
        adapter.setList(items);
    }

    @Override
    public void getLoadSearchListSuccess(List<ModelForSearchList.ItemsBean> modelForSearchList) {
        items.remove(items.size() - 1);
        int scrollPosition = items.size();
        adapter.notifyItemRemoved(scrollPosition);
        items.addAll(modelForSearchList);
        adapter.notifyDataSetChanged();
        isLoading = false;
    }


    private void loadMoreDataRecyclerviewScroller() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == items.size() - 1 && page <= 100) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        items.add(null);
        adapter.notifyItemInserted(items.size() - 1);
        page = page + 1;
        mPresenter.getLoadSearchList(type, language, page);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    public void loadNewData(String projectType, String projectLanguage) {
        page = 1; // reset page to 1
        mPresenter.getSearchList(projectType, projectLanguage, page);
    }
}
