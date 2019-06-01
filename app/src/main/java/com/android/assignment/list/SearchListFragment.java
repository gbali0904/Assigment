package com.android.assignment.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.assignment.R;
import com.android.assignment.base.BaseFragment;
import com.android.assignment.di.component.ActivityComponent;
import com.android.assignment.list.adapter.SearchListAdapter;
import com.android.assignment.list.model.ModelForSearchList;
import com.android.assignment.list.persenter.SearchListPresenter;
import com.android.assignment.list.view.SearchListView;
import com.android.assignment.utility.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchListFragment extends BaseFragment implements SearchListView {
    @Inject
    SearchListPresenter<SearchListView> mPresenter;


    @Inject
    SearchListAdapter adapter;

    @Inject
    LinearLayoutManager mLayoutManager;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.edProjectType)
    EditText edProjectType;
    @BindView(R.id.edProjectLanguage)
    EditText edProjectLanguage;
    Unbinder unbinder;

    private boolean isLoading = false;
    private Bundle bundle;
    private String language;
    private String type;
    private int page = 1;
    private List<ModelForSearchList.ItemsBean> items;

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
            bundle = this.getArguments();
            type = bundle.getString(Constants.TYPE);
            language = bundle.getString(Constants.LANGUAGE);
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
    public void getSearchListSuccess(ModelForSearchList modelForSearchList) {
        items = modelForSearchList.getItems();
        adapter.setList(items);
    }

    @Override
    public void getLoadSearchListSuccess(ModelForSearchList modelForSearchList) {
        items.remove(items.size() - 1);
        int scrollPosition = items.size();
        adapter.notifyItemRemoved(scrollPosition);
        items.addAll(modelForSearchList.getItems());
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
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == items.size() - 1 && page != 101) {
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

}
