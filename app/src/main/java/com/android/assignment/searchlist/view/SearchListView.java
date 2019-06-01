package com.android.assignment.searchlist.view;

import com.android.assignment.base.MvpView;
import com.android.assignment.searchlist.model.ModelForSearchList;

import java.util.List;

public interface SearchListView extends MvpView {
    void getSearchListSuccess(List<ModelForSearchList.ItemsBean> modelForSearchList);

    void getLoadSearchListSuccess(List<ModelForSearchList.ItemsBean> modelForSearchList);
}
