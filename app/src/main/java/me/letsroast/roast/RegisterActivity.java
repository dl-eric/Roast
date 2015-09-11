package me.letsroast.roast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity
{
    private String mUsername;
    private String mEmail;
    private String mPassword;

    private EditText username;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        Parse.initialize(this, "ITBX0xVookLOeoIB7FTKm8OFrxa5D7oZ22Meij4I", "ymB28zhf7GOe6YjsFEuij6mVfJabk3Mq9OpQM2vx");

        Button button = (Button) findViewById(R.id.registerButton);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                registerClick();
            }
        });
    }

    public void registerClick()
    {
        mUsername = username.getText().toString();
        mEmail = email.getText().toString();
        mPassword = password.getText().toString();

        if(isUsernameValid(mUsername) && isEmailValid(mEmail) && isPasswordValid(mPassword))
        {
            RegisterTask registerTask = new RegisterTask(mUsername, mEmail, mPassword);
            registerTask.register();
        }
    }

    public boolean isUsernameValid(String username)
    {
        //TODO: Replace
        return username.length() < 20;
    }

    private boolean isEmailValid(String email)
    {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password)
    {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    public class RegisterTask
    {
        private final String mEmail;
        private final String mPassword;
        private final String mUsername;

        public RegisterTask(String username, String email, String password)
        {
            mUsername = username;
            mEmail = email;
            mPassword = password;
        }
        public void register()
        {
            ParseUser user = new ParseUser();
            user.setUsername(mUsername);
            user.setPassword(mPassword);
            user.setEmail(mEmail);

            user.signUpInBackground(new SignUpCallback()
            {
                public void done(ParseException e)
                {
                    if (e == null)
                    {
                        // Hooray! Let them use the app now.
                    } else
                    {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                    }
                }
            });
        }
    }
}
