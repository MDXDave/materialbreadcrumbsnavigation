package de.mdxdave.breadcrumbsnavigationdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * (c) 2015 by David Kurz
 * ProjectName: de.mdxdave.breadcrumbsnavigationdemo
 * Created on  07.07.2015.
 */

public class ScrollFragment extends ListFragment {

    public ScrollFragment() {    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        String[] trades = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, trades));

        Bundle bn = getArguments();
        if(bn != null){
            int materialBreadrcumbsItem = bn.getInt("materialBreadcrumbsPosition");
            Toast.makeText(getActivity(), String.valueOf(materialBreadrcumbsItem), Toast.LENGTH_LONG).show();
            //((MainActivity)getActivity()).materialBreadcrumbsNavigation.setupListView(getListView());
        }




    }

}

