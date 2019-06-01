package com.android.assignment.search.persenter;

import com.android.assignment.base.BasePresenter;
import com.android.assignment.search.view.SearchView;

import javax.inject.Inject;

public class SearchMVPPersenter <V extends SearchView> extends BasePresenter<V> implements SearchPersenter<V> {

    @Inject
    public SearchMVPPersenter() {
        super();
    }
}
