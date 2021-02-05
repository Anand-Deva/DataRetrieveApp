package com.example.dataretrieveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.mongodb.User;

import io.realm.mongodb.sync.SyncConfiguration;

public class inventoryActivity extends AppCompatActivity{
    TextView titleText;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        user = RealmSingleton.getInstance().getRealm().currentUser();
        titleText = findViewById(R.id.TitletextView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(user == null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else{
            SyncConfiguration config = new SyncConfiguration.Builder(user, "ecrf").build();

            Realm.getInstanceAsync(config, new Realm.Callback() {
                @Override
                public void onSuccess(Realm realm) {

                    Log.v("MongoDB Realm", "Successfully opened a realm");
                    RealmResults<BioInfo> results =  realm.where(BioInfo.class)
                                                    .equalTo("lastName", "Doe").findAll();
                    Log.i("MongoDB Query", results.toString());
                    Toast.makeText(getApplicationContext(),user+ ":" + results.toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}