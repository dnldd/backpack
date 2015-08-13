package dnldd.backpack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import dnldd.backpack.contract.InflateDialogInterface;
import rx.Observable;
import rx.android.lifecycle.LifecycleEvent;
import rx.subjects.BehaviorSubject;

public class BaseDialogFragment extends android.support.v4.app.DialogFragment implements InflateDialogInterface {
    protected Context context;
	protected int dialogResId; /* set the dialogResID before calling inflateDialog() */

    private final BehaviorSubject<LifecycleEvent> lifecycleSubject = BehaviorSubject.create();
    public Observable<LifecycleEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }
	
	public View inflateDialog( LayoutInflater inflater, ViewGroup container){
        this.context = getActivity().getApplicationContext();
		return inflater.inflate(dialogResId, container);
	}

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(LifecycleEvent.ATTACH);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(LifecycleEvent.START);
    }

	@Override
	public void onResume() {
		super.onResume();
        lifecycleSubject.onNext(LifecycleEvent.RESUME);
	}

    @Override
    public void onPause() {
        lifecycleSubject.onNext(LifecycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(LifecycleEvent.STOP);
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(LifecycleEvent.CREATE);
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle bundle) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		return inflateDialog(inflater, container);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(LifecycleEvent.CREATE_VIEW);
	}

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(LifecycleEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(LifecycleEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(LifecycleEvent.DETACH);
        super.onDetach();
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