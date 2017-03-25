package com.hackingbuzz.firebaseseries3login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mLogin;
    private FirebaseAuth mAuth;  // this is the object that controls our Authenication Section for Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;   //listener listen to the state of user... through mAuth (user logged in or not)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        initViews();
        initObejcts();

       // setting listener on login button
        loginListener();

        // initilizing AuthStateListener

        initAuthListener();



    }   // end of onCreate Method..

// as we always do..we set our interface on our object like OnClickListener to Button(object)..so that it can listen when we click on  button
    //so here we are setting our authListenr to our object FirebaseAuth (mAuth)  ..so when that object excutes...lister listen to it..
    // we make it start to lister to object as our aplication starts... ( in OnStart method)
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }  // end of onStart Method...



    private void initAuthListener() {

        // auth listener listen to the state of a user...as he signed in (state changed) this method will fire up n we sending the user to next activity..

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {  // with the help of firebaseAuth object we can check if user is avail or not in our server..n more..

                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(MainActivity.this, AccountActivity.class));
                }

            }
        };

    }

    private void initObejcts() {

        mAuth = FirebaseAuth.getInstance();  // getting firebase auth object.
    }

    private void loginListener() {

        mLogin.setOnClickListener(new View.OnClickListener() {  // as we click on the login button
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }



    private void initViews() {

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.loginButton);
    }




// click on a Login button will fire up this method...
    private void startSignIn() {

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();


// basically checking if email or passwword is empty .. then print Toast else sign in  with the help of auth object
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(MainActivity.this, "Enter email or password", Toast.LENGTH_LONG).show();
        } else {

            // signing in with the help of firebase auth object..   // addOnCompleteListener will check...if user compteted sign in or not...(it is getting result as a task object n we can check if it is sucessfull or not
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "User Not Found", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

            }   // end of startSignIn method



}




