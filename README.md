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
        app:dashPathGap="12dp"
        app:pathColor="@color/colorAccent"
        app:pathStrokeWidth="4dp"
        app:drawable="@drawable/your_drawable"
        app:enableDashPath="true"
        app:drawableAminationSpeed="5"
        app:repeatable="true" />
```
In your activity implement the ``OnPathListener``
```java
public class MainActivity extends AppCompatActivity implements OnPathListener {

    private Animator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animator = findViewById(R.id.la_view);
        animator.startAnimateArrow();
    }

    @Override
    public Path setOnPathUpdateListener(int bitmapPositionX, int bitmapPositionY){
        Path p = new Path();
        p.moveTo(animator.getWidth() / 2, 0);
        p.cubicTo(0, animator.getHeight() / 2, animator.getWidth(), animator.getHeight() / 2, animator.getWidth() / 2, animator.getHeight());
        //or
        //p.addCircle(...);
        //p.addArc(...);
        //p.quadTo(...);
        //just add an return your custom path
        return p;
    }

    @Override
    public void setOnAnimationCompleteListener() {
        // completed the animation
    }
}

```
