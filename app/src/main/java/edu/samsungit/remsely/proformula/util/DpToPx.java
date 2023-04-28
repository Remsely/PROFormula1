package edu.samsungit.remsely.proformula.util;

import android.content.res.Resources;

public class DpToPx {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
