package dnldd.backpack.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import dnldd.backpack.TypefaceSpan;
import dnldd.backpack.contract.LifecycleInterface;
import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.fragment.BaseFragment;
import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.SystemUiHelper;
import rx.Observable;
import rx.android.lifecycle.LifecycleEvent;
import rx.subjects.BehaviorSubject;

public class BaseActivity extends AppCompatActivity implements LifecycleInterface {
    protected NotificationCompat.Builder notificationBuilder;
    protected TaskStackBuilder stackBuilder;
    protected NotificationManager notificationManager;
    protected SystemUiHelper uiHelper;

    private final BehaviorSubject<LifecycleEvent> lifecycleSubject = BehaviorSubject.create();

    public Observable<LifecycleEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }
    public SystemUiHelper getUiHelper(){ return uiHelper; }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        lifecycleSubject.onNext(LifecycleEvent.CREATE);
        ContextUtils.getApp(getApplicationContext()).setCurrentActivity(this);
        notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        stackBuilder = TaskStackBuilder.create(getApplicationContext());
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        uiHelper = new SystemUiHelper( this, SystemUiHelper.LEVEL_LOW_PROFILE, 0);
        uiHelper.show();
    }


    @Override
    public void onDestroy(){
        lifecycleSubject.onNext(LifecycleEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onStart(){
        super.onStart();
        lifecycleSubject.onNext(LifecycleEvent.START);
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(LifecycleEvent.STOP);
        super.onStop();
    }

    @Override
    public void onPause(){
        lifecycleSubject.onNext(LifecycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        lifecycleSubject.onNext(LifecycleEvent.RESUME);
        ContextUtils.getApp(getApplicationContext()).setCurrentActivity(this);
    }

    @Override
    public void onBackPressed() {
        BaseFragment fragment = ((BaseApplication) getApplication()).getCurrentFragment();
        if(fragment !=null){ fragment.onBackPressed(); }
        else { super.onBackPressed(); }
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        /* extend this to handle onActivityResult use cases */
    }

    public void styleTitle(String title, String typefaceName, int colorResID, int size){
        SpannableString titleSpan = new SpannableString(title);
        titleSpan.setSpan(new TypefaceSpan(typefaceName), 0, title.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        if(colorResID > 0) {
            titleSpan.setSpan(new ForegroundColorSpan(getResources().getColor(colorResID)), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if(size > 0){
            titleSpan.setSpan(new AbsoluteSizeSpan(size, true), 0, title.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }

        getSupportActionBar().setTitle(titleSpan);
    }
}