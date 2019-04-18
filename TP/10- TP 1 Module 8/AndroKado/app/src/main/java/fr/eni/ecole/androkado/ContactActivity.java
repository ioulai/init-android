package fr.eni.ecole.androkado;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.androkado.adapters.ContactAdapter;
import fr.eni.ecole.androkado.application.AndroKadoApplication;
import fr.eni.ecole.androkado.bo.Article;
import fr.eni.ecole.androkado.bo.Contact;


public class ContactActivity extends AppCompatActivity {

    public List<Contact> contacts;
    public Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        this.article = getIntent().getParcelableExtra(AndroKadoApplication.INTENT_ARTICLE);

        RecyclerView liste = findViewById(R.id.listContacts);
        LinearLayoutManager manager = new LinearLayoutManager(ContactActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        liste.setLayoutManager(manager);

        this.contacts = new ArrayList<>();

        bindContact();

        ContactAdapter adapter = new ContactAdapter(contacts, new ContactAdapter.CustomItemClickListener() {
            @Override
            public void onItemClick(View v, Contact contact) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("smsto:" + contact.getTelephone())); // This ensures only SMS apps respond
                intent.putExtra("sms_body", getString(R.string.message_body_sms, article.getNom())  );

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ContactActivity.this, getString(R.string.alert_no_application),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        liste.setAdapter(adapter);

    }

    private void bindContact(){
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.HAS_PHONE_NUMBER
                },
                null,
                null,
                null);

        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                Log.i("TAG_AND", id+" "+name+" "+hasPhone);

                if(hasPhone != null && hasPhone.equalsIgnoreCase("1")){

                    Log.i("TAG_AND", "Phone");

                    Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                            null,
                            null);

                    Log.i("TAG_AND", phone.getCount() + " nvb");
                    if(phone.moveToFirst()){
                        String number = phone.getString(phone
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                        this.contacts.add(new Contact(id, name, number));
                    }

                    phone.close();
                }
            }

            cursor.close();
        }
    }



}
