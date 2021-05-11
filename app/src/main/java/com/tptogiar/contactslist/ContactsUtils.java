package com.tptogiar.contactslist;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.database.Cursor.FIELD_TYPE_STRING;

public class ContactsUtils {


    public static ArrayList<ContactInfo> getContactList(Context context, ArrayList<ContactInfo> addContacts) {
        final ContentResolver contentResolver = context.getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, null);
        ArrayList<ContactInfo> contactsInfos = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    //获取 id 所在列的索引
                    int contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                    //联系人id
                    String contactId = cursor.getString(contactIdIndex);
                    //联系人电话(可能包含多个)
                    final List<String> phones = getData(contentResolver, contactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    //电话为空时，不添加到列表中
                    if (phones.isEmpty()) {
                        continue;
                    } else {
                        String name;
                        //联系人名称
                        final List<String> names = getData(contentResolver, contactId, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                        if (names.isEmpty()) {
                            name = phones.get(0);
                        } else {
                            name = names.get(0);
                        }
                        //相同联系人的不同手机号视为不同联系人
                        for (String phone : phones) {
                            Log.d("phone=", phone);
                            ContactInfo info = new ContactInfo(name,contactId,phone);
                            contactsInfos.add(info);
                        }
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return contactsInfos;
    }


    private static List<String> getData(final ContentResolver contentResolver, String contactId, final String mimeType) {
        List<String> dataList = new ArrayList<>();
        Cursor dataCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Data.DATA1},
                ContactsContract.Data.CONTACT_ID + "=?" + " AND "
                        + ContactsContract.Data.MIMETYPE + "='" + mimeType + "'",
                new String[]{String.valueOf(contactId)}, null);
        if (dataCursor != null && dataCursor.getCount() > 0) {
            if (dataCursor.moveToFirst()) {
                do {
                    final int columnIndex = dataCursor.getColumnIndex(ContactsContract.Data.DATA1);
                    final int type = dataCursor.getType(columnIndex);
                    if (type == FIELD_TYPE_STRING) {
                        final String data = dataCursor.getString(columnIndex);
                        if (!TextUtils.isEmpty(data)) {
                            dataList.add(data);
                        }
                    }
                } while (dataCursor.moveToNext());
            }
            dataCursor.close();
        }
        return dataList;
    }


}
