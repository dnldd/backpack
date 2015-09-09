package dnldd.backpack.core;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import dnldd.backpack.activity.BaseActivity;
import dnldd.backpack.utils.LogUtils;

public class ServiceCalls {
    protected Context context;

    /* add @DebugLog annotations to your methods to get detailed logs
        for methods [method, time, parameters] in your debug builds  */

    public ServiceCalls(Context context) {
        this.context = context;
    }

    public void giveFeedback(final BaseActivity activity, final String message){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void giveFeedback(final Fragment fragment, final String message){
        if (fragment.getActivity() != null) {
            fragment.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(fragment.getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else { LogUtils.log(LogUtils.INFO_LOG_TYPE, "Parent activity is null."); }
    }

    public void giveFeedback(final DialogFragment fragment, final String message){
        if (fragment.getActivity() != null) {
            fragment.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(fragment.getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else { LogUtils.log(LogUtils.INFO_LOG_TYPE, "Parent activity is null."); }
    }
}
