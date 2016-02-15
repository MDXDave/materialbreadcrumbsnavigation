/**
 * Android Library for displaying Breadcrumbs Navigation in Material Design - sample app
 *
 * Copyright (C) 2015-2016 MDXDave
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package de.mdxdave.materialbreadcrumbsnavigationdemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivityFragment extends Fragment {

    private String text;

    public MainActivityFragment() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_main, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            this.text = bundle.getString("Title");
            int position = bundle.getInt("materialBreadcrumbsPosition");
            //((MainActivity)getActivity()).materialBreadcrumbsNavigation.replaceTitle(position, "New Title for pos "+position);
        }
        TextView textView = (TextView) v.findViewById(R.id.text);
        textView.setText(text);

        return v;
    }
}
