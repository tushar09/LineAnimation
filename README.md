# LineAnimation
<img src="https://raw.githubusercontent.com/tushar09/LineAnimation/development/gif.gif" width="200">

Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
    ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency
 
```gradle
dependencies {
    implementation 'com.github.tushar09:LineAnimation:1.1.9'
}
```

Add this in your layout:

```xml
<com.captaindroid.lineanimation.Animator
        android:id="@+id/la_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:dashPathSize="12dp"
        app:enableDashPath="true"
        app:drawableAminationSpeed="5"
        app:repeatable="true"
        app:dashPathGap="12dp" />
```
