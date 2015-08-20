package dnldd.backpack.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;

import dnldd.backpack.R;

public class GrayscaleTransformation implements Transformation {

    private final Picasso picasso;

    public GrayscaleTransformation(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
        Bitmap noise;
        try {
            noise = picasso.load(R.drawable.noise).get();
        } catch (IOException e) {
            throw new RuntimeException("Failed to apply transformation! Missing resource.");
        }

        BitmapShader shader = new  BitmapShader(noise, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(filter);

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(source, 0, 0, paint);

        paint.setColorFilter(null);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        source.recycle();
        noise.recycle();

        return result;
    }

    @Override
    public String key() {
        return "grayscaleTransformation()";
    }
}
