package com.example.sphien2011.datarealmcontact.common.service;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.sphien2011.datarealmcontact.common.model.Contact;
import com.example.sphien2011.datarealmcontact.common.model.Email;

import java.util.ArrayList;

import static com.example.sphien2011.datarealmcontact.common.service.ContactFilter.idNumber;

/**
 * Created by sphien2011 on 15/10/2016.
 */
public class ContactFilter {
    private static final String TAG = ContactFilter.class.getSimpleName();
    private Context mContext;
    private Cursor mCursor;

    public static final Uri uriEmail = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    public static final String idEmail = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
    public static final String dataEmail1 = ContactsContract.CommonDataKinds.Email.DATA;

    public static final Uri uriNumber = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    public static final String idNumber = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    public static final String dataNumber = ContactsContract.CommonDataKinds.Phone.NUMBER;


    public ContactFilter(Context mContext) {
        this.mContext = mContext;
    }

    /*public ArrayList<Contact> fetchAll() {
        ArrayList<Contact> listContacts = new ArrayList<>();

        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
        String idContact = ContactsContract.Contacts._ID;
        String displayName = ContactsContract.Contacts.DISPLAY_NAME;
        String hasPhoneNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor mCursor = contentResolver.query(contactUri, null, null, null, null);

        assert mCursor != null;
        if (mCursor.getCount() > 0) {
            while (mCursor.moveToNext()) {
                String id = mCursor.getString(mCursor.getColumnIndex(idContact));
                String name = mCursor.getString(mCursor.getColumnIndex(displayName));
                //check => only show the name contact have phone number (HAS_PHONE_NUMBER > 0)
                int hasPhone = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(hasPhoneNumber)));
                if (hasPhone > 0) {
                    Contact contact = new Contact(id, name);
                    getEmail(contact, contentResolver, id);
//                    Log.e(TAG, "fetchAll: "+ name + ": " + phoneNumber + " : " + contact.getEmails());
                    listContacts.add(contact);
                }
            }
        }
        return listContacts;
    }

    private void getEmail(Contact contact, ContentResolver contentResolver, String idContact) {

        Log.e(TAG, "getEmail: co chay qua day khong");
        String email;
        Uri emailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String emailId = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String dataEmail = ContactsContract.CommonDataKinds.Email.DATA;
        Cursor cursorEmail = contentResolver.query(emailUri, null, emailId + " = ? ", new String[]{idContact}, null);
        if (cursorEmail.moveToFirst()) {
            ArrayList<String> emails = new ArrayList<>();
            while (!cursorEmail.isAfterLast()) {

                Log.e(TAG, "getEmail: aaaaaaaaaaaaaaa");
                email = cursorEmail.getString(cursorEmail.getColumnIndex(dataEmail));
                emails.add(email);
                contact.setEmails(emails);
//                contact.addEmail(email);
//                Log.e(TAG, "getEmail: " + emails.size());
                cursorEmail.moveToNext();
            }
        }
        cursorEmail.close();
    }*/


    public ArrayList<Contact> filterContact1(Uri uri, String idEmailOrNumber, String dataEmailOrNumber) {
        ArrayList<Contact> listContacts = new ArrayList<>();
        String data;

        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
        String idContact = ContactsContract.Contacts._ID;
        String displayName = ContactsContract.Contacts.DISPLAY_NAME;
        String hasPhoneNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor mCursor = contentResolver.query(contactUri, null, null, null, null);

        assert mCursor != null;
        if (mCursor.getCount() > 0) {
            while (mCursor.moveToNext()) {
                String id = mCursor.getString(mCursor.getColumnIndex(idContact));
                String name = mCursor.getString(mCursor.getColumnIndex(displayName));

                //check => only show the name contact have phone number (HAS_PHONE_NUMBER > 0)
                int hasPhone = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(hasPhoneNumber)));
                if (hasPhone > 0) {
                    /*

//                    Contact contact = new Contact(id, name);
                    Contact contact = new Contact(id, name);
//                    contact.setId(id);
//                    contact.setName(name);

//                    Log.e(TAG, "filterContact: " + id);
                    Cursor cursorEmailOrNumber = contentResolver.query(uri, null, idEmailOrNumber + " = ? ", new String[]{idContact}, null);
                    if (cursorEmailOrNumber.moveToFirst()) {
                        ArrayList<String> string = new ArrayList<>();
                        while (!cursorEmailOrNumber.isAfterLast()) {
                            data = cursorEmailOrNumber.getString(cursorEmailOrNumber.getColumnIndex(dataEmailOrNumber));
                            string.add(data);
                            contact.addNumber(data);

//                            contact.addEmail(data);
//                            contact.getEmails().add(new Email(data));

//                            if (uri == uriEmail) {
////                                contact.setEmails(string);
////                                contact.addEmail(data);
//                                contact.getEmails().add(new Email(data));
//                            } else {
////                                contact.setNumbers(string);
//                                contact.addNumber(data);
//                            }
                            cursorEmailOrNumber.moveToNext();
                        }
                    }
                    cursorEmailOrNumber.close();

                    listContacts.add(contact);*/


                    Cursor cursorEmailOrNumber = contentResolver.query(uri, null, idEmailOrNumber + " = ? ", new String[]{idContact}, null);
                        while (cursorEmailOrNumber.moveToNext()) {
                            data = cursorEmailOrNumber.getString(cursorEmailOrNumber.getColumnIndex(dataEmailOrNumber));

                            Contact contact = new Contact(id, name);
//                            contact.addNumber(data);
                            contact.addEmail(data);
                            Log.e(TAG, "fetchAll: "+ name + ": " +data );

                            listContacts.add(contact);
                        }
                    cursorEmailOrNumber.close();
                }
            }
        }
        return listContacts;
    }


    public ArrayList<Contact> filterContact(Uri uri) {
        String phoneNumber = null;
        String email = null;

        ArrayList<Contact> listContacts = new ArrayList<>();

        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
        String idContact = ContactsContract.Contacts._ID;
        String displayName = ContactsContract.Contacts.DISPLAY_NAME;
        String hasPhoneNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String phoneId = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;
        String type = ContactsContract.CommonDataKinds.Phone.TYPE;

        ContentResolver contentResolver = mContext.getContentResolver();
        mCursor = contentResolver.query(contactUri, null, null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");

        if (mCursor.getCount() > 0) {
            while (mCursor.moveToNext()) {

                String id = mCursor.getString(mCursor.getColumnIndex(idContact));
                String name = mCursor.getString(mCursor.getColumnIndex(displayName));
//                Contact contact = new Contact();

                //check => only show the name contact have phone number (HAS_PHONE_NUMBER > 0)
                int hasPhone = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(hasPhoneNumber)));
                if (hasPhone > 0) {
                    Contact contact = new Contact(id, name);
//                    contact.setId(id);
//                    contact.setName(name);
                    // name contact = _ID, number = CONTACT_ID => using query to get number have name with id is equal
                    Cursor phone = contentResolver.query(uriNumber, null, idNumber + " = ?", new String[]{id}, null);


                    if (uri == uriEmail) {
                        while (phone.moveToNext()) {
                            phoneNumber = phone.getString(phone.getColumnIndex(dataNumber));
                            contact.addNumber(phoneNumber);

                            Cursor cursorEmail = contentResolver.query(uriEmail, null, idEmail + " = ?", new String[]{id}, null);
                            while (cursorEmail.moveToNext()) {

                                email = cursorEmail.getString(cursorEmail.getColumnIndex(dataEmail1));
                                Log.e(TAG, "fetchAll: " + name + ": " + phoneNumber);
                                contact.addEmail(email);
                                listContacts.add(contact);
                            }
                            cursorEmail.close();
                        }
                        phone.close();

                    } else {
                        while (phone.moveToNext()) {
                            phoneNumber = phone.getString(phone.getColumnIndex(dataNumber));
                            contact.addNumber(phoneNumber);
                        }
                        phone.close();

                        Cursor cursorEmail = contentResolver.query(uriEmail, null, idEmail + " = ?", new String[]{id}, null);
                        while (cursorEmail.moveToNext()) {
                            email = cursorEmail.getString(cursorEmail.getColumnIndex(dataEmail1));
                            Log.e(TAG, "fetchAll: " + name + ": " + phoneNumber);
                            contact.addEmail(email);
                        }
                        cursorEmail.close();

                        listContacts.add(contact);
                    }

                }
            }
        }
        return listContacts;
    }


    public void writeContact(String displayName, String number) {
        ArrayList contentProviderOperations = new ArrayList();
        //insert raw contact using RawContacts.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        //insert contact display name using Data.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, displayName)
                .build());
        //insert mobile number using Data.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());
        try {
            mContext.getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Contact> readContacts() {

        ArrayList<Contact> listContact = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        sb.append("......Contact Details.....");
        ContentResolver cr = mContext.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String phone = null;
        String emailContact = null;
        String emailType = null;
        String image_uri = "";
        Bitmap bitmap = null;
        Contact contact = new Contact();
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                image_uri = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    System.out.println("name : " + name + ", ID : " + id);
//                    sb.append("\n Contact Name:" + name);
                    contact.setId(id);
                    contact.setName(name);

                    ArrayList<String> arrNumber = new ArrayList<>();
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    pCur.moveToFirst();

                    while (!pCur.isAfterLast()) {
                        phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        sb.append("\n Phone number:" + phone);
                        System.out.println("phone" + phone);
                        arrNumber.add(phone);
//                        contact.setNumbers(arrNumber);
                        pCur.moveToNext();

                    }
                    listContact.add(contact);
                    pCur.close();

                   /* Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                    emailCur.moveToFirst();
                    ArrayList<String> arrEmail = new ArrayList<>();
                    while (!emailCur.isAfterLast()) {
                        emailContact = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        emailType = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
//                        sb.append("\nEmail:" + emailContact + "Email type:" + emailType);
                        System.out.println("Email " + emailContact + " Email Type : " + emailType);
                        arrEmail.add(emailContact);
                        contact.setEmails(arrEmail);
                        emailCur.moveToNext();

                    }
                    emailCur.close();*/
                }

                /*if (image_uri != null) {
                    System.out.println(Uri.parse(image_uri));
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.parse(image_uri));
                        sb.append("\n Image in Bitmap:" + bitmap);
                        System.out.println(bitmap);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                sb.append("\n........................................");*/

                Log.e(TAG, "readContacts: " +listContact.size());
            }
//            textView.setText(sb);

        }
        return listContact;
    }
}
