# Material Breadcrumbs Navigation

[![Build status][travis-image]][travis-url] [![Release](https://jitpack.io/v/de.mdxdave/materialbreadcrumbsnavigation.svg?style=flat-square)](https://jitpack.io/#de.mdxdave/materialbreadcrumbsnavigation)

##Import
Add to your ``build.gradle`` file:

```gradle
repositories {
	maven { url "https://jitpack.io" }
}
```

Add to _dependencies_:

```gradle
compile 'de.mdxdave:materialbreadcrumbsnavigation:1.1.1'
```


##Usage
Place into your layout xml:

```xml
<de.mdxdave.materialbreadcrumbsnavigation.MaterialBreadcrumbsNavigation
        android:id="@+id/materialBreadcrumbsNavigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:backgroundColor="?attr/colorPrimary"
        />
```

```java
// Get view
MaterialBreadcrumbsNavigation materialBreadcrumbsNavigation = (MaterialBreadcrumbsNavigation) findViewById(R.id.materialBreadcrumbsNavigation);
```

You can use with Fragment:
```java
// Set root item
materialBreadcrumbsNavigation.addRootItem(new NavigationItem("Internal storage", fragment));
// Set-up fragment
Fragment fragment = new MainActivityFragment();
FragmentManager manager = getSupportFragmentManager();
materialBreadcrumbsNavigation.setupFragment(R.id.fragment, manager);
// Add optional items
PhotoFragment photoFragment = new PhotoFragment();
materialBreadcrumbsNavigation.addItem(new NavigationItem("Photos", photoFragment));
```

Alternative use with OnItemClickListner:
```java
materialBreadcrumbsNavigation.addItem(new NavigationItem("Root", myCustomObject));
materialBreadcrumbsNavigation.setBreadcrumbClickListener(new BreadcrumbClickListener() {
            @Override
            public void onClick(NavigationItem navigationItem) {
                // Do something with navigationItem
                MyCustomObject myCustomObject = navigationItem.getObject();
                [...]
            }
        });
```

Use ```setBackgroundColor(int Color)``` and ```setTextColor(int Color)``` to set color of MaterialBreadcrumbsNavigation programmatically.

##License
```
Android Library for displaying Breadcrumbs Navigation in Material Design
Copyright (C) 2015-2016 MDXDave

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
```

[travis-image]: https://img.shields.io/travis/MDXDave/materialbreadcrumbsnavigation/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/MDXDave/materialbreadcrumbsnavigation

 
