package dnldd.backpack.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.HashMap;

import dnldd.backpack.activity.BaseActivity;

public class ImageUploadUtils {
    public  static final int SELECT_PICTURE = 0;
    protected static HashMap<String, String> pathCache  = new HashMap<String, String>();
    protected static Bitmap decodedBitmap;
    protected static String uploadKey;

    public static HashMap<String, String> getPathCache(){ return pathCache; }
    public static String getUploadKey(){ return uploadKey; }
    public static void setUploadKey(String key){ uploadKey = key; }

    public static void selectPictureViaFragment(android.support.v4.app.Fragment fragment, String key) {
        uploadKey = key;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public static void selectPictureViaActivity(BaseActivity activity, String key) {
        uploadKey = key;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public static String getSelectedFilePath(Context context, Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        String filePath = null;
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            filePath = cursor.getString(column_index);
        }
        cursor.close();
        return filePath;
    }

    public static Bitmap decodeBitmap(String path) {
        if (decodedBitmap != null){ decodedBitmap = BitmapFactory.decodeFile(path); }
        else {
            decodedBitmap.recycle();
            decodedBitmap = BitmapFactory.decodeFile(path);
        }

        return decodedBitmap;
    }
}
