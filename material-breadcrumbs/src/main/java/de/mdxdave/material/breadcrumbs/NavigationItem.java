package de.mdxdave.material.breadcrumbs;

import android.support.v4.app.Fragment;

/**
 * (c) 2015 by David Kurz
 * ProjectName: de.mdxdave.material.breadcrumbs
 * Created on  06.07.2015.
 */
public class NavigationItem {
    private String Title;
    private int Id;
    private String Tag;
    private Fragment fragment;

    public NavigationItem(String title){
        this(0, title, "", null);
    }

    public NavigationItem(String title, Fragment fragment){
         this(0, title, "", fragment);
    }

    private NavigationItem(int id, String title, String tag, Fragment fragment){
        this.Id = id;
        this.Title = title;
        this.Tag = tag;
        this.fragment = fragment;
    }

    public void setTag(String tag){
        this.Tag = tag;
    }

    public void setId(int id){
        this.Id = id;
    }

    public Fragment getFragment(){
        return this.fragment;
    }

    public String getTitle(){
        return this.Title;
    }

    public void setTitle(String title){
        this.Title = title;
    }

    public int getId(){
        return this.Id;
    }

    public String getTag(){
        return this.Tag;
    }

}
