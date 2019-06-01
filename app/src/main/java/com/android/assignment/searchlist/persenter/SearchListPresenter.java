package com.android.assignment.searchlist.persenter;

import com.android.assignment.base.MvpPresenter;
import com.android.assignment.searchlist.view.SearchListView;

public interface SearchListPresenter<V extends SearchListView> extends MvpPresenter<V> {
    void getSearchList(String type, String language, int page);

    void getLoadSearchList(String type, String language, int page);
}
