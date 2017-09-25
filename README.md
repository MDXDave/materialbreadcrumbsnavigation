# Material Breadcrumbs Navigation

[![Build status][travis-image]][travis-url] [![Release](https://jitpack.io/v/de.mdxdave/materialbreadcrumbsnavigation.svg?style=flat-square)](https://jitpack.io/#de.mdxdave/materialbreadcrumbsnavigation)

## Import
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


## Usage
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

## License
```
Copyright (C) 2015-2016 David Kurz

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

[travis-image]: https://img.shields.io/travis/MDXDave/materialbreadcrumbsnavigation/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/MDXDave/materialbreadcrumbsnavigation

 
