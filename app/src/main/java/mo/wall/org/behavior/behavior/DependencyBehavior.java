package mo.wall.org.behavior.behavior;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * https://blog.csdn.net/King1425/article/details/61923877
 */
public class DependencyBehavior extends CoordinatorLayout.Behavior<View> {

    public DependencyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Button;
//        return dependency.getId() == R.id.depentent;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getTop();
        ViewCompat.offsetTopAndBottom(child, -offset);
        return true;
    }
}