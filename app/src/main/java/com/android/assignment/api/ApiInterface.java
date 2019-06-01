package com.android.assignment.api;

import com.android.assignment.searchlist.model.ModelForSearchList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {


    //https://api.github.com/search/repositories?q=android+language:java&sort=stars&order=desc&page=1&per_page=10


    @GET("repositories")
    Observable<ModelForSearchList> getSearchList(@Query("q") String q, @Query("sort") String sort,
                                                 @Query("order") String order, @Query("page")  String page,
                                                 @Query("per_page") String per_page);


}
