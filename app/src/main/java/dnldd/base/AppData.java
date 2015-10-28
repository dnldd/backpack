package dnldd.base;

import android.content.Context;

import java.util.List;

import dnldd.backpack.model.Contact;
import dnldd.backpack.utils.ContactUtils;
import dnldd.backpack.utils.LogUtils;

public class AppData extends dnldd.backpack.core.AppData {
    protected AppData(Context context) {
        super(context);

        List<Contact> contacts = ContactUtils.getContacts(context);
        LogUtils.log(LogUtils.INFO_LOG_TYPE, "size: " + contacts.size());

        /* a little demo of disk LRU cache */
        /*try {
            DiskLruCache.Editor firstEditor = diskLruCache.edit("name");
            firstEditor.set(0, "Donald");
            firstEditor.commit();

            String value = diskLruCache.get("name").getString(0);

            DiskLruCache.Editor secondEditor = diskLruCache.edit("name");
            secondEditor.set(0, "Haha");
            secondEditor.commit();

            value = diskLruCache.get("name").getString(0);
            LogUtils.log(LogUtils.ASSERT_LOG_TYPE, "Done");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
