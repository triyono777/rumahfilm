package id.ac.dutabangsa.rumahfilm.transformation;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Triyono on 10/6/2018.
 */

public class ZoomOutTransformation implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.65f;
    private static final float MIN_ALPHA = 0.3f;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            page.setAlpha(0);

        } else if (position <= 1) { // [-1,1]

            page.setScaleX(Math.max(MIN_SCALE, 1 - Math.abs(position)));
            page.setScaleY(Math.max(MIN_SCALE, 1 - Math.abs(position)));
            page.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position)));

        } else {
            page.setAlpha(0);

        }


    }
}