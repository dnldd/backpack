package dnldd.backpack.contract;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.viewholder.ViewHolder;

/**
 * Created by dnldd on 8/12/15.
 */
public interface InflateInterface {
    ViewHolder inflateItem(BaseApplication app, int option, ViewGroup viewGroup, LayoutInflater layoutInflater);
}
