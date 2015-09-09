package dnldd.backpack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.trello.rxlifecycle.components.support.RxDialogFragment;

import dnldd.backpack.contract.InflateDialogInterface;

public class BaseDialog extends RxDialogFragment implements InflateDialogInterface {
    protected Context context;
    protected View view;
	protected int layoutResID;

    public void setLayout(int layoutResID){  this.layoutResID = layoutResID; }

	public View inflateDialog(LayoutInflater inflater, ViewGroup container){
        this.context = getActivity().getApplicationContext();
		view = inflater.inflate(this.layoutResID, container);
        bind(view);
        return view;
	}

    @Override
    public void bind(View view) {
        /* bind to the widgets and elements in your dialog here */
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle bundle) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflateDialog(inflater, container);
	}

	@Override
	public void show(FragmentManager manager, String tag) {
		try { super.show(manager, tag); }
		catch (IllegalStateException e){ e.printStackTrace(); }
	}

    @Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		return AnimationUtils.loadAnimation(getActivity(), enter ? android.R.anim.fade_in : android.R.anim.fade_out);
	}
}