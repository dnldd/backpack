package dnldd.backpack.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

@SuppressWarnings({"ResourceType", "UnusedAssignment"})
public class LocationUtils {
    protected static Location location;

    public Location getLocation(){ return location; }

    // requires <uses-permission name="android.permission.ACCESS_COARSE_LOCATION" /> or <uses-permission name="android.permission.ACCESS_FINE_LOCATION" />
    public static void startLocationService(Context context){
        final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null) {
            if (networkInfo.isConnected()) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10,
                        new LocationListener() {
                            @Override public void onLocationChanged(Location location) {
                                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            }
                            @Override public void onStatusChanged(String s, int i, Bundle bundle) {}
                            @Override public void onProviderEnabled(String s) {}
                            @Override public void onProviderDisabled(String s) {}
                        });
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10,
                        new LocationListener() {
                            @Override public void onLocationChanged(Location location) {
                                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            }
                            @Override public void onStatusChanged(String s, int i, Bundle bundle) {}
                            @Override public void onProviderEnabled(String s) {}
                            @Override public void onProviderDisabled(String s) {}
                        });
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
    }
}
