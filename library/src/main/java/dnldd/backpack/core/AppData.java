package dnldd.backpack.core;

import android.content.Context;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

import dnldd.backpack.BuildConfig;

public class AppData {
    protected Context context;
    protected DiskLruCache diskLruCache;
    protected File cacheDir;

    protected String CACHE_FILENAME = "cache";
    protected int VALUE_COUNT = 1;

    /* add @DebugLog annotations to your methods to get detailed logs
        for methods [method, time, parameters] in your debug builds  */
    public DiskLruCache getDiskLruCache() { return diskLruCache; }

    protected AppData(Context context){
        this.context = context;

        /* sets up the disk LRU cache with 100MB space */
        cacheDir = context.getExternalCacheDir();
        if (cacheDir == null) { cacheDir = context.getCacheDir(); }

        String  path = cacheDir.getAbsolutePath() + File.separator + CACHE_FILENAME;
        try {
            this.diskLruCache = DiskLruCache.open(new File(path), BuildConfig.VERSION_CODE, VALUE_COUNT, BaseApplication.MAX_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
