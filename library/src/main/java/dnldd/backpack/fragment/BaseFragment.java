package dnldd.backpack.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import butterknife.ButterKnife;
import dnldd.backpack.contract.OnBackPressedInterface;
import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.LogUtils;


public class BaseFragment extends RxFragment implements OnBackPressedInterface {
    protected Bundle savedState;
    protected FragmentManager manager;
    protected Context context;

    public Bundle saveState(){ return savedState; }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) { savedState = bundle; }
        else {
            savedState = this.getArguments();
            if (savedState == null) { savedState = new Bundle(); }
        }

        manager = getChildFragmentManager();
        context = getActivity().getApplicationContext();
        LogUtils.log(LogUtils.INFO_LOG_TYPE, "fragment " + ((Object) this).getClass().getName() + " created.");
    }

    @Override
    public void onResume(){
        super.onResume();
        ContextUtils.getApp(context).setCurrentFragment(this);
    }
    @Override
    public void onBackPressed() { getActivity().getFragmentManager().popBackStack(); }

    public View inject(LayoutInflater inflater, int layout, ViewGroup container){
        View view = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(), enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        /* extend this to handle onActivityResult use cases */
    }

    public static android.support.v4.app.Fragment createFragment(String fragmentName) {
        try {
            Class<?> cls = Class.forName(fragmentName);
            Constructor<?> ctr = cls.getConstructor();
            return (android.support.v4.app.Fragment) ctr.newInstance();
        }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        catch (NoSuchMethodException e) { e.printStackTrace(); }
        catch (java.lang.InstantiationException e) { e.printStackTrace(); }
        catch (InvocationTargetException e) { e.printStackTrace(); }
        catch (IllegalAccessException e) { e.printStackTrace(); }

        return null;
    }
}