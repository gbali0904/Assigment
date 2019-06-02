package com.android.assignment.searchdetail;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
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
    private String license_spanned="";
    private String owner_spanned="";


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
        loadNewData(item_data);
    }
    public void loadNewData(ModelForSearchList.ItemsBean itemdata) {


        if (null != itemdata.getLicense())
        {
            license_spanned = "<h2><br><b>LICENSE: </b></br></h2>" +
                    "<b> Key: </b>" + itemdata.getLicense().getKey() +
                    "<br> <b> Name: </b>" + itemdata.getLicense().getName() + "</br>" +
                    "<br> <b> URL: </b>" + itemdata.getLicense().getUrl() + "</br>" +
                    "<br> <b> Node ID: </b>" + itemdata.getLicense().getNode_id() + "</br>";

        }
        if (null != itemdata.getOwner())
        {

            owner_spanned = "<h2><br> <b>OWNER: </b></br></h2>" +
                " <b> ID: </b>" + itemdata.getOwner().getId() + "" +
                "<br> <b> Login: </b>" + itemdata.getOwner().getLogin() + "</br>" +
                "<br> <b> Type: </b>" + itemdata.getOwner().getType() + "</br>";
        }
        detailData.setText(Html.fromHtml(
                "<h2><br><b>DETAIL: </b>" + itemdata.getName() + "</br></h2>" +
                        "<b>id:</b>" + itemdata.getId() +
                        "<br> <b>Node Id:</b>" + itemdata.getNode_id() + "</br>" +
                        "<br> <b>Name:</b>" + itemdata.getName() + "</br>" +
                        "<br> <b>Languages:</b>" + itemdata.getLanguage() + "</br>" +
                        "<br> <b>Full Name:</b>" + itemdata.getFull_name() + "</br>" +
                        "<br> <b>Private: </b>" + itemdata.isPrivateX() + "</br>" +
                        "<br> <b>Description: </b>" + itemdata.getDescription() + "</br>" +
                        "<br> <b>URL: </b>" + itemdata.getUrl() + "</br>" +
                        "<br> <b>Languages URL: </b>" + itemdata.getLanguages_url() + "</br>" +
                        "<br> <b>Created At: </b>" + itemdata.getCreated_at() + "</br>" +
                        "<br> <b>Updated At: </b>" + itemdata.getUpdated_at() + "</br>" +
                        "<br> <b>Size: </b>" + itemdata.getSize() + "</br>"+license_spanned+owner_spanned));

    }
}