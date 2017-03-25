package com.hackingbuzz.firebaseseries3login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);



    }
    // giving logout button in this Activity on Toolbar...
    // you  have to create these method here on this activity...
    //  so that you could...get overflow menu in your toolbar... (basically create this menu on that activity where you want to logout )..

    // creating a menu for Loging out of account

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            FirebaseAuth ff = FirebaseAuth.getInstance();

                    ff.signOut();
                 //   finish();   // choose any way...
            startActivity(new Intent(this, MainActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

}
