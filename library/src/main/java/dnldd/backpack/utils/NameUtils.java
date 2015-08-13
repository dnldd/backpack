package dnldd.backpack.utils;

import android.content.Context;

import java.io.IOException;

import dnldd.backpack.utils.helper.NameGenerator;


public class NameUtils {
    private static NameGenerator nameGenerator;
    private static int syllableCount = 2;

    public static String generateName(Context context){
        if (nameGenerator == null){
            try { nameGenerator = new NameGenerator("roman-syllables", context); }
            catch (IOException e) { e.printStackTrace();  }
        }
        return  nameGenerator.compose(syllableCount);
    }
}
