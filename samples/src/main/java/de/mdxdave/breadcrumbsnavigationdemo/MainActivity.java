package de.mdxdave.breadcrumbsnavigationdemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import de.mdxdave.material.breadcrumbs.MaterialBreadcrumbsNavigation;
import de.mdxdave.material.breadcrumbs.NavigationItem;

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
        bn.putString("Title", "Interner Speicher");
        fragment.setArguments(bn);
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();

        materialBreadcrumbsNavigation = (MaterialBreadcrumbsNavigation) findViewById(R.id.materialBreadcrumbsNavigation);
        materialBreadcrumbsNavigation.addRootItem(new NavigationItem("Interner Speicher", fragment));
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
            materialBreadcrumbsNavigation.replaceTitle(materialBreadcrumbsNavigation.getCurrent(), "Current pos "+materialBreadcrumbsNavigation.getCurrent());
        }else if(id == R.id.action_replace_item){
            MainActivityFragment fragment_photos = new MainActivityFragment();
            Bundle bn2 = new Bundle();
            bn2.putString("Title", "Fotos");
            fragment_photos.setArguments(bn2);
            materialBreadcrumbsNavigation.replaceItem(2, new NavigationItem("Fotos", fragment_photos));
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
