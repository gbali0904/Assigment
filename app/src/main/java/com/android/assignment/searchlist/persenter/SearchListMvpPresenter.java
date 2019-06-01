package com.android.assignment.searchlist.persenter;

import com.android.assignment.api.RetrofitImp;
import com.android.assignment.base.BasePresenter;
import com.android.assignment.searchlist.model.ModelForSearchList;
import com.android.assignment.searchlist.view.SearchListView;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SearchListMvpPresenter<V extends SearchListView> extends BasePresenter<V> implements SearchListPresenter<V>
{
    @Inject
    RetrofitImp retrofitImp;
    @Inject
    public SearchListMvpPresenter() {
        super();
    }

    @Override
    public void getSearchList(String type, String language, int page) {
        getMvpView().showLoading();


        String data=type+"+language:"+language;
        String sort="stars";
        String order="desc";
        String per_page="10";


        retrofitImp.getRetrofitServices().getSearchList(data,sort,order,""+page,per_page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(retrofitImp.defaultSubscribeScheduler())
                .subscribe(new Subscriber<ModelForSearchList>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();
                        handleApiError(e);
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext( ModelForSearchList  modelForSearchList) {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (modelForSearchList != null && modelForSearchList.getItems() != null) {
                            getMvpView().getSearchListSuccess(modelForSearchList.getItems());
                        }
                        getMvpView().hideLoading();
                    }
                });
    }

    @Override
    public void getLoadSearchList(String type, String language, int page) {
        String data=type+"+language:"+language;
        String sort="stars";
        String order="desc";
        String per_page="10";
        retrofitImp.getRetrofitServices().getSearchList(data,sort,order,""+page,per_page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(retrofitImp.defaultSubscribeScheduler())
                .subscribe(new Subscriber<ModelForSearchList>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (!isViewAttached()) {
                            return;
                        }
                        handleApiError(e);
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext( ModelForSearchList  modelForSearchList) {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (modelForSearchList != null && modelForSearchList.getItems() != null) {
                            getMvpView().getLoadSearchListSuccess(modelForSearchList.getItems());
                        }

                    }
                });
    }
}
