package me.letsroast.roast;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity
{
    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        Parse.initialize(this, "ITBX0xVookLOeoIB7FTKm8OFrxa5D7oZ22Meij4I", "ymB28zhf7GOe6YjsFEuij6mVfJabk3Mq9OpQM2vx");

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);

        TextView tx = (TextView)findViewById(R.id.roastText);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "rollogli.ttf");

        tx.setTypeface(custom_font);

        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                login(mUsername.getText().toString(), mPassword.getText().toString());
            }
        });
    }
    public void login(final String username, final String password)
    {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null && isUsernameValid(username) && isPasswordValid(password)) {
                    // Hooray! The user is logged in.
                    Intent intent = new Intent(LoginActivity.this, RoastActivity.class);
                    startActivity(intent);
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }

    private boolean isUsernameValid(String username)
    {
        //TODO: Replace this with your own logic
        return username.length() < 20;
    }

    private boolean isPasswordValid(String password)
    {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    public void toRegister(View view)
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.exit(0);
    }
}