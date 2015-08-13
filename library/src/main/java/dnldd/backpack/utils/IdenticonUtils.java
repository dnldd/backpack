package dnldd.backpack.utils;

import android.graphics.Bitmap;

import dnldd.backpack.utils.helper.IdenticonGenerator;
import dnldd.backpack.utils.helper.MessageDigestHashGenerator;

public class IdenticonUtils {
    private static  IdenticonGenerator identiconGenerator = new IdenticonGenerator();
    private static MessageDigestHashGenerator hashGenerator = new MessageDigestHashGenerator("SHA");

    public static Bitmap generateIdenticon(String input){
        return  identiconGenerator.generate(input,hashGenerator );
    }
}
