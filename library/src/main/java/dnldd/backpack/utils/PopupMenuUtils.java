package dnldd.backpack.utils;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.View;

public class PopupMenuUtils {
    public static PopupMenu createPopupMenu(Context context, View parent, int menuResID){
        PopupMenu popUp = new PopupMenu(context, parent);
        popUp.getMenuInflater().inflate(menuResID, popUp.getMenu());
        return popUp;
    }
}
