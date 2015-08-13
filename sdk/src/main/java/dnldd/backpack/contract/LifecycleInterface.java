package dnldd.backpack.contract;

import rx.Observable;
import rx.android.lifecycle.LifecycleEvent;

public interface LifecycleInterface {
    public Observable<LifecycleEvent> lifecycle();
}
