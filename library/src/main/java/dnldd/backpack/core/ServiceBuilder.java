package dnldd.backpack.core;

import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import org.simpleframework.xml.Serializer;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.converter.SimpleXMLConverter;

public class ServiceBuilder {
    protected static String API_URL = "";
    protected RestAdapter.Builder builder;
    protected RestAdapter adapter;
    protected Context context;

    public RestAdapter getServiceAdapter() { return adapter; }

    public ServiceBuilder(Context context, OkHttpClient okHttpClient, Gson gson) {
        this.context = context;

        builder = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("RETROFIT"));

        adapter = builder.build();
    }

    public ServiceBuilder(Context context, OkHttpClient okHttpClient, Serializer serializer){
        builder = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new SimpleXMLConverter(serializer))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        adapter = builder.build();
    }
}
