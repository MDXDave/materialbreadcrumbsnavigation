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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import de.mdxdave.materialbreadcrumbsnavigation.MaterialBreadcrumbsNavigation;
import de.mdxdave.materialbreadcrumbsnavigation.NavigationItem;

public class MainActivity extends AppCompatActivity {

    MaterialBreadcrumbsNavigation materialBreadcrumbsNavigation;
    MainActivityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        fragment = new MainActivityFragment();
        Bundle bn = new Bundle();
        bn.putString("Title", "Internal storage");
        fragment.setArguments(bn);
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();

        materialBreadcrumbsNavigation = (MaterialBreadcrumbsNavigation) findViewById(R.id.materialBreadcrumbsNavigation);
        materialBreadcrumbsNavigation.addRootItem(new NavigationItem("Internal storage", fragment));
        materialBreadcrumbsNavigation.setupFragment(R.id.fragment, manager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_remove) {
            materialBreadcrumbsNavigation.removeItem(1);
        }else if (id == R.id.action_move) {
            materialBreadcrumbsNavigation.setActive(2);
        }else if(id == R.id.action_replace_root){
            materialBreadcrumbsNavigation.replaceRootItem(new NavigationItem("Root-Storage", fragment));
        }else if(id == R.id.action_replace_title){
            materialBreadcrumbsNavigation.replaceTitle(materialBreadcrumbsNavigation.getCurrent(), "Current position "+materialBreadcrumbsNavigation.getCurrent());
        }else if(id == R.id.action_replace_item){
            MainActivityFragment fragment_photos = new MainActivityFragment();
            Bundle bn2 = new Bundle();
            bn2.putString("Title", "Photos");
            fragment_photos.setArguments(bn2);
            materialBreadcrumbsNavigation.replaceItem(2, new NavigationItem("Photos", fragment_photos));
        }else if(id == R.id.action_add){



            MainActivityFragment fragment_pictures = new MainActivityFragment();
            Bundle bn1 = new Bundle();
            bn1.putString("Title", "Pictures");
            fragment_pictures.setArguments(bn1);
            materialBreadcrumbsNavigation.addItem(new NavigationItem("Pictures", fragment_pictures));

            MainActivityFragment fragment_photos = new MainActivityFragment();
            Bundle bn2 = new Bundle();
            bn2.putString("Title", "Photos");
            fragment_photos.setArguments(bn2);
            materialBreadcrumbsNavigation.addItem(new NavigationItem("Photos", fragment_photos));

            ScrollFragment scrollFragment = new ScrollFragment();
            materialBreadcrumbsNavigation.addItem(new NavigationItem("ScrollFragment", scrollFragment));

        }

        return super.onOptionsItemSelected(item);
    }

}
