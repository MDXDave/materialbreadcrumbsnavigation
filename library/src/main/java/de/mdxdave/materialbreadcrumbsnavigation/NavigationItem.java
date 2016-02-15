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

import android.support.v4.app.Fragment;

public class NavigationItem {
    private String Title;
    private int Id;
    private String Tag;
    private Fragment fragment;
    private Object object;

    public NavigationItem(String title){
        this(0, title, "", null);
    }

    public NavigationItem(String title, Object object){
        this.Id = 0;
        this.Title = title;
        this.object = object;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
