package dnldd.backpack.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

public class BlurUtils {
    public static Bitmap blurBitmap(Context context, Bitmap input){
        Bitmap output = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript renderScript = RenderScript.create(context);
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        Allocation inputAlloc = Allocation.createFromBitmap(renderScript, input);
        Allocation outputAlloc = Allocation.createFromBitmap(renderScript, output);
        blurScript.setRadius(25.0f); // change the blur radius here
        blurScript.setInput(inputAlloc);
        blurScript.forEach(outputAlloc);
        outputAlloc.copyTo(output);
        input.recycle();
        renderScript.destroy();

        return output;
    }
}
