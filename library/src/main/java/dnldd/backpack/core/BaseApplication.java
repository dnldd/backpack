package dnldd.backpack.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import dnldd.backpack.activity.BaseActivity;
import dnldd.backpack.contract.GCMServiceInterface;
import dnldd.backpack.contract.ServiceInterface;
import dnldd.backpack.fragment.BaseFragment;
import dnldd.backpack.gcm.GCM;
import dnldd.backpack.gcm.GCMServiceBuilder;
import dnldd.backpack.utils.LogUtils;
import dnldd.backpack.utils.TypefaceUtils;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseApplication extends android.app.Application {
    protected SimpleDateFormat dateFormat;
    protected BaseActivity currentActivity;
    protected BaseFragment currentFragment;
    protected HashMap<String, android.support.v4.app.Fragment> fragments;
    protected ServiceBuilder serviceBuilder;
    protected GCMServiceBuilder gcmServiceBuilder;
    protected ServiceCalls serviceCalls;
    protected ServiceInterface service;
    protected GCM gcm;
    protected GCMServiceInterface gcmService;
    protected Gson gson;
    protected OkHttpClient client;
    protected File cacheDir;
    protected Picasso picasso;
    protected AppData appData;

    public static String PREF = "pref";
    public static final long MAX_CACHE_SIZE = 100 * 1024 * 1024;  /* 100MB max cache size */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public AppData getAppData() { return appData; }
    public ServiceCalls  getServiceCalls() { return serviceCalls; }
    public GCMServiceInterface getGcmService(){ return gcmService; }
    public GCM getGcm(){ return  gcm; }
    public Gson getGson(){ return gson; }
    public void setCurrentFragment(BaseFragment fragment){ currentFragment = fragment; }
    public BaseActivity getCurrentActivity(){ return currentActivity; }
    public BaseFragment getCurrentFragment(){ return currentFragment; }
    public SimpleDateFormat getDateFormat(){ return dateFormat; }

    public android.support.v4.app.Fragment getFragment(String key) {
        android.support.v4.app.Fragment fragment = getCurrentActivity().getSupportFragmentManager().findFragmentByTag(key);
        if(fragment == null){
            try {
                Class<?> cls = Class.forName(key);
                Constructor<?> ctr = cls.getConstructor();
                fragment = (android.support.v4.app.Fragment) ctr.newInstance();
            }
            catch (ClassNotFoundException e) { e.printStackTrace(); }
            catch (NoSuchMethodException e) { e.printStackTrace(); }
            catch (InvocationTargetException e) { e.printStackTrace(); }
            catch (InstantiationException e) { e.printStackTrace(); }
            catch (IllegalAccessException e) { e.printStackTrace(); }

            fragments.put(key, fragment);
        }

        return fragment;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        fragments = new HashMap<>();
        gson = new GsonBuilder().setDateFormat(BaseApplication.DATE_FORMAT).create();
        gcm = new GCM(getApplicationContext());
        appData = new AppData(getApplicationContext());
        serviceCalls = new ServiceCalls(getApplicationContext());
        cacheDir = getApplicationContext().getExternalCacheDir();
        client = new OkHttpClient();

        if (cacheDir == null) { cacheDir = getApplicationContext().getCacheDir(); }

        try {
                /* sets up a cache and cookie manager for okHttp & and wire it to picasso */
                Cache cache = new Cache(cacheDir, BaseApplication.MAX_CACHE_SIZE);
                client.setCache(cache);
                CookieManager cookieManager = new CookieManager();
                cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                CookieHandler.setDefault(cookieManager);
                client.setCookieHandler(cookieManager);
                client.setCache(cache);

                picasso = new Picasso.Builder(getApplicationContext()).downloader(new OkHttpDownloader(client)).build();

                /* loads custom fonts and generate typefaces */
                List<String> assets;
                assets = Arrays.asList(getApplicationContext().getResources().getAssets().list(""));
                TypefaceUtils.create(assets, getApplicationContext());
        }
        catch (IOException e){ e.printStackTrace(); }
    }

    public void setCurrentActivity(BaseActivity activity){ currentActivity = activity; }

    public <T extends ServiceInterface> void buildService(Class<T> serviceClass){
        /* builds the REST service  with the supplied service definition */
        serviceBuilder = new ServiceBuilder(getApplicationContext(), gson);
        service = serviceBuilder.getServiceAdapter().create(serviceClass);
    }

    /* call this from the main activity of the app */
    public void setupGCMService(String thirdPartyUrl){
        if(gcm.hasPlayServices(getApplicationContext())) {
            gcmServiceBuilder = new GCMServiceBuilder(getApplicationContext(), gson, thirdPartyUrl);
            gcmService = gcmServiceBuilder.getGCMService();
            gcm.callRegister().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onCompleted() {
                            LogUtils.log(BaseApplication.class.getSimpleName(), "Completed");
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.log(BaseApplication.class.getSimpleName(), "Error");
                        }

                        @Override
                        public void onNext(Object o) {
                            LogUtils.log(BaseApplication.class.getSimpleName(), "Registered");
                        }
            });
        }
    }
}
