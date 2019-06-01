package com.android.assignment.list.view;

import com.android.assignment.base.MvpView;
import com.android.assignment.list.model.ModelForSearchList;

public interface SearchListView extends MvpView {
    void getSearchListSuccess(ModelForSearchList modelForSearchList);

    void getLoadSearchListSuccess(ModelForSearchList modelForSearchList);
}
