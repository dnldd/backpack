package dnldd.backpack.core;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import dnldd.backpack.activity.BaseActivity;
import dnldd.backpack.contract.LifecycleInterface;
import dnldd.backpack.utils.LogUtils;

public class ServiceCalls {
    protected Context context;

    public void giveFeedback(LifecycleInterface ui, final String message){
        if (ui instanceof BaseActivity){
            final BaseActivity activity =  ((BaseActivity) ui);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(ui instanceof Fragment){
            final Fragment fragment = ((Fragment) ui);
            if (fragment.getActivity() != null) { //paranoia
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

    public ServiceCalls(Context context) {
        this.context = context;
    }
}
