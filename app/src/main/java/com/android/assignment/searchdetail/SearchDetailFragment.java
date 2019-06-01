package com.android.assignment.searchdetail;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.assignment.R;
import com.android.assignment.base.BaseFragment;
import com.android.assignment.searchlist.model.ModelForSearchList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchDetailFragment extends BaseFragment {


    private static ModelForSearchList.ItemsBean item_data;
    @BindView(R.id.detailData)
    TextView detailData;
    Unbinder unbinder;


    public static SearchDetailFragment newInstance(ModelForSearchList.ItemsBean itemdata) {
        item_data = itemdata;
        return new SearchDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void setUp(View view) {
        detailData.setText( Html.fromHtml("id:"+item_data.getId()+
                "<br> Node Id:"+item_data.getNode_id()+"</br>"+
                "<br> name:"+item_data.getName()+"</br>"+
                "<br> full_name:"+item_data.getFull_name()+"</br>"+
                "<br> private:"+item_data.isPrivateX()+"</br>"
        ));
    }
}