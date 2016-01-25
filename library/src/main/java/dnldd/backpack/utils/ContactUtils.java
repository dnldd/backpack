package dnldd.backpack.utils;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import dnldd.backpack.model.Contact;

public class ContactUtils {
    // requires <uses-permission android:name="android.permission.READ_CONTACTS" />
    public static List<Contact> getContacts(Context context) {
        ArrayList<Contact> contactsList = new ArrayList<Contact>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Data.DISPLAY_NAME);

        assert cursor != null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String thumbnail = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));

                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor numberCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id}, null);

                    assert numberCursor != null;
                    while (numberCursor.moveToNext()) {
                        String number = numberCursor.getString(numberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Boolean found = false;

                        for(Contact entry : contactsList){
                            if(entry.getName().equals(name)){
                                found = true;
                                entry.addNumber(number);
                                if((entry.getThumbnailURI() == null) && (thumbnail != null)) {
                                    entry.setThumbnailURI(thumbnail);
                                }

                                break;
                            }
                        }

                        if(!found){
                            contactsList.add(new Contact(name, number, thumbnail));
                        }
                    }
                    numberCursor.close();
                }
            }
            cursor.close();
        }

        return contactsList;
    }
}
