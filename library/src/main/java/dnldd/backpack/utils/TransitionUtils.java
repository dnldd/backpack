package dnldd.backpack.utils;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import dnldd.backpack.R;

public class TransitionUtils {
	public static void transitionFragmentIn(final FragmentManager fragmentManager, android.support.v4.app.Fragment fragment) {
		transitionFragmentIn(fragmentManager, fragment, false);
	}
	
	public static void transitionFragmentIn(final FragmentManager fragmentManager, android.support.v4.app.Fragment fragment, final boolean allowStateLoss){
		final FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.addToBackStack(((Object) fragment).getClass().getName());
		LogUtils.log(LogUtils.INFO_LOG_TYPE, "activity_layout fragment " + ((Object) fragment).getClass().getName() + " transitioning...");
		if (allowStateLoss) { transaction.commitAllowingStateLoss(); }
		else { transaction.commit(); }
		fragmentManager.executePendingTransactions();
		LogUtils.log(LogUtils.INFO_LOG_TYPE, "activity_layout fragment " + ((Object) fragment).getClass().getName() + " transitioned to.");
	}
	
	public static void transitionChildFragmentIn(final FragmentManager childFragmentManager, android.support.v4.app.Fragment fragment, int containerID){
		FragmentTransaction transaction =  childFragmentManager.beginTransaction();
		transaction.replace(containerID, fragment);
		transaction.commit();
		childFragmentManager.executePendingTransactions();
		LogUtils.log(LogUtils.INFO_LOG_TYPE, ((Object) fragment).getClass().getName() + " transitioned to.");
	}
	
	public static void startNewActivity(ActionBarActivity parent, Class child, boolean flagNewTask){
		Intent intent = new Intent(parent.getApplicationContext(), child);
		if (flagNewTask) { intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); }
		parent.startActivity(intent);
	}
}
