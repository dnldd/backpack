package dnldd.backpack.gcm;

import android.content.Context;

import com.google.gson.Gson;

import dnldd.backpack.contract.GCMServiceInterface;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class GCMServiceBuilder {
    protected RestAdapter.Builder builder;
    protected RestAdapter adapter;
    protected GCMServiceInterface service;

    public GCMServiceBuilder(Context context, Gson gson, String thirdPartyUrl){
        builder = new RestAdapter.Builder().setEndpoint(thirdPartyUrl).setConverter(new GsonConverter(gson));
        adapter = builder.build();
        service = adapter.create(GCMServiceInterface.class);
    }

    public GCMServiceInterface getGCMService(){
        return service;
    }
}
