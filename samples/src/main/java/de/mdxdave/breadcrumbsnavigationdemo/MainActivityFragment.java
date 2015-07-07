package de.mdxdave.breadcrumbsnavigationdemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
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
