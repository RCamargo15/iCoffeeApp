package com.rcamargo15.icoffeeapp.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class AppUtils {

    public static void esconderBarrasTarefas(Activity activity) {
        View view = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(uiOptions);
    }
}
