package dnldd.backpack.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import dnldd.backpack.activity.BaseActivity;

public class ImageUploadUtils {
    public  static final int SELECT_PICTURE = 0;
    protected static Bitmap decodedBitmap;

    public static void selectPictureViaFragment(android.support.v4.app.Fragment fragment) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public static void selectPictureViaActivity(BaseActivity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public static Bitmap decodeBitmap(Context context, Uri uri) {
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static File getBitmapFile(Uri uri) {
        File file = new File(uri.getPath());
        return file;
    }
}
