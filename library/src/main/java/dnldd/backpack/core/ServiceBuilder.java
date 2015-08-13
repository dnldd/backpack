package dnldd.backpack.core;

import android.content.Context;

import com.google.gson.Gson;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

public class ServiceBuilder {
    protected static String API_URL = "";
    protected RestAdapter.Builder builder;
    protected RestAdapter adapter;
    protected RequestInterceptor interceptor;
    protected Context context;

    public RestAdapter getServiceAdapter() { return adapter; }

    public ServiceBuilder(final Context context, Gson gson) {
        this.context = context;

        interceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                /* add your constants and checks here */
            }
        };

        builder = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(interceptor)
                .setLog(new AndroidLog("RETROFIT"));

        adapter = builder.build();
    }
}
