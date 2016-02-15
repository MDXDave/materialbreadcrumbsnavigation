/**
 * Android Library for displaying Breadcrumbs Navigation in Material Design
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

package de.mdxdave.materialbreadcrumbsnavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.mdxdave.material.breadcrumbs.R;

public class MaterialBreadcrumbsNavigation extends LinearLayout {

    private View view;
    private int textColor;
    private int indicatorColor;
    private int backgroundColor;
    private Context context;
    private HorizontalScrollView horizontalScrollView;
    private ViewGroup viewGroup;
    private FragmentManager fragmentManager;
    private int fragmentLayout;
    private boolean hasRootItem = false;
    private int active = 0;
    private ArrayList<NavigationItem> list;
    private BreadcrumbClickListener breadcrumbClickListener;

    public MaterialBreadcrumbsNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MaterialBreadcrumbsNavigation, 0, 0);
        textColor = a.getColor(R.styleable.MaterialBreadcrumbsNavigation_textColor, ContextCompat.getColor(context, R.color.default_text));
        indicatorColor = a.getColor(R.styleable.MaterialBreadcrumbsNavigation_arrowColor, ContextCompat.getColor(context, R.color.default_indicator));
        backgroundColor = a.getColor(R.styleable.MaterialBreadcrumbsNavigation_backgroundColor, ContextCompat.getColor(context, R.color.default_background));
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.main_view, this, true);
        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.horizontalScrollView);
        horizontalScrollView.setBackgroundColor(backgroundColor);
        viewGroup = (ViewGroup) findViewById(R.id.parent_view);

        list = new ArrayList<>();
    }

    public void setBackgroundColor(int Color){
        horizontalScrollView.setBackgroundColor(Color);
    }

    public void setTextColor(int Color){
        textColor = Color;
    }

    public int getItemCount(){
        return list.size();
    }

    public void addItem(NavigationItem item){
        this.addItem(item, -1, false, true);
    }

    public void addItem(NavigationItem item, Boolean setActive){
        this.addItem(item, -1, false, setActive);
    }

    public void setBreadcrumbClickListener(BreadcrumbClickListener breadcrumbClickListener){
        this.breadcrumbClickListener = breadcrumbClickListener;
    }

    public void addRootItem(NavigationItem item){
        addRootItem(item, true);
    }

    public void addRootItem(NavigationItem item, boolean setActive){
        if(!hasRootItem) {
            this.hasRootItem = true;
            this.addItem(item, -1, true, setActive);
        }else{
            throw new IllegalStateException("Only one root item is allowed!");
        }
    }

    public void replaceRootItem(NavigationItem item) {
        replaceRootItem(item, false);
    }

    public void replaceRootItem(NavigationItem item, boolean setActive){
        if(hasRootItem) {
            removeOnly(0);
            hasRootItem = false;
            addRootItem(item, setActive);
        }else{
            throw new IllegalStateException("There are no root item, so you can not replace it");
        }
    }

    public void replaceItem(int child, NavigationItem item){
        replaceItem(child, item, false);
    }

    public void replaceItem(int child, NavigationItem item, boolean setActive){
        if(child > list.size()){
            throw new IndexOutOfBoundsException("There are only "+list.size()+" items, you can not replace the "+child+" item");
        }else if(child > 0){
            removeOnly(child);
            addItem(item, child, false, setActive);
        }else{
            throw new IllegalStateException("Use replaceRootItem to replace the root item");
        }
    }

    public void removeAll(){
        list.clear();
        viewGroup.removeAllViews();
        hasRootItem = false;
    }

    private void addItem(final NavigationItem item, int position, boolean isRoot, boolean setActive){
        LayoutInflater vi = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.item_view, null);

        int last;
        if(isRoot){
            last = 0;
            list.add(item);
        }else if(position == -1) {
            last = viewGroup.getChildCount();
            last = (last < 0) ? 0 : last;
            list.add(item);
        }else{
            last = position;
            list.set(position, item);
        }

        final TextView textView = (TextView) v.findViewById(R.id.breadcrumbnav_textView);
        textView.setTextColor(darkenColor(textColor));
        textView.setText(item.getTitle().toUpperCase());

        item.setId(last);

        final int finalLast = last;
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setActive(finalLast, false, true);
            }
        });

        ImageView imageView = (ImageView) v.findViewById(R.id.breadcrumbnav_imageView);
        imageView.setColorFilter(indicatorColor, PorterDuff.Mode.MULTIPLY);

        // Padding and root item
        if(isRoot) {
            imageView.setVisibility(View.GONE);
            int padding_left = 48;
            int padding_right = 12;
            int padding = 18;
            final float scale = getResources().getDisplayMetrics().density;
            int padding_left_px = (int) (padding_left * scale + 0.5f);
            int padding_right_px = (int) (padding_right * scale + 0.5f);
            int padding_px = (int) (padding * scale + 0.5f);
            textView.setPadding(padding_left_px, padding_px, padding_right_px, padding_px);
        }

        // Scroll to end

        final int finalLast1 = last;
        horizontalScrollView.post(new Runnable() {
            @Override
            public void run() {
                if(finalLast1 == viewGroup.getChildCount())
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });


        viewGroup.addView(v, last, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if(setActive || getCurrent() == position || list.size() == 0 )
            setActive( last, true );
    }

    private void removeOnly(int child){
        viewGroup.removeViewAt(child);
    }

    private void remove(int child){
        int max = viewGroup.getChildCount();
        for (int i = max; i > child; i--) {
            Log.i("Remove", String.valueOf(i)+" from "+String.valueOf(max));
            removeOnly(i-1);
            list.remove(i-1);
        }
    }

    public void removeItem(int child){
        if(child > list.size()) {
            throw new IndexOutOfBoundsException("There are only "+list.size()+" items, you can not remove the "+child+" one");
        }else if(child > 0) {
            remove(child);
            if(getCurrent() == child)
                setActive( child-1, true );
        }else {
            throw new IllegalStateException("You can not remove the root item!");
        }
    }

    public boolean isActive(int child){
        return (child == active);
    }

    public int getCurrent(){
        return active;
    }

    public NavigationItem getItem(int pos){
        return list.get(pos);
    }

    public void replaceTitle(int pos, String title){
        if(pos > list.size()){
            throw new IndexOutOfBoundsException("There are only "+list.size()+" items, you can not replace the title of the  "+pos+" one");
        }
        NavigationItem item = list.get(pos);
        item.setTitle(title);
        if(pos == 0)
            replaceRootItem(item);
        else
            replaceItem(pos, item);
    }

    public void setActive(int child){
        setActive(child, true);
    }

    public void setActive(int child, boolean scrollTo, boolean clicked){
        if(child > list.size()){
            throw new IndexOutOfBoundsException("There are only "+list.size()+" items, you can not call the "+child+" one");
        }
        this.active = child;

        for(int i=0; i<viewGroup.getChildCount(); i++){
            Log.i("view", String.valueOf(i));
            ((TextView) viewGroup.getChildAt(i).findViewById(R.id.breadcrumbnav_textView)).setTextColor(darkenColor(textColor));
        }

        try {
            final View v = viewGroup.getChildAt(child);
            TextView tv = (TextView) v.findViewById(R.id.breadcrumbnav_textView);
            tv.setTextColor(textColor);

            if (scrollTo)
                horizontalScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        horizontalScrollView.scrollTo(v.getLeft(), 0);
                    }
                });

            NavigationItem item = list.get(child);
            if (item.getFragment() != null) {
                Fragment fragment = item.getFragment();
                Bundle bn = fragment.getArguments();
                if (bn == null) {
                    bn = new Bundle();
                }
                bn.putInt("materialBreadcrumbsPosition", child-1);
                try {
                    fragment.setArguments(bn);
                } catch (IllegalStateException e) {
                    // Fragment already active...
                }
                fragmentManager.beginTransaction().replace(fragmentLayout, fragment).commit();
            }else if(item.getObject() != null && clicked){
                this.breadcrumbClickListener.onClick(item);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }

    }

    public void setActive(int child, boolean scrollTo){
        setActive(child, scrollTo, false);
    }

    public void setupFragment(int layout, FragmentManager fm){
        this.fragmentLayout = layout;
        this.fragmentManager = fm;
    }

    private static int modifyColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green( color );
        int b = Color.blue( color );

        return Color.argb(a,
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
    }

    private int lightenColor(int color){
        return modifyColor(color, 1.2f);
    }
    private int darkenColor(int color){
        return modifyColor(color, 0.7f);
    }


}
