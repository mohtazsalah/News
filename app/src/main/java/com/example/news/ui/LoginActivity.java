package com.example.news.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.news.R;
import com.example.news.model.sharedPrefrance.SharedPreferencesData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SignInButton mGoogleButton;
    private GoogleSignInOptions mGso;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView mWithoutSigning;
    private final int GOOGLE_SIGN_IN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI() {
        mWithoutSigning = findViewById(R.id.without_sign);
        mGoogleButton = findViewById(R.id.google_sign_in);
        mGoogleButton.setSize(SignInButton.SIZE_STANDARD);
        mGoogleButton.setOnClickListener(this);
        mWithoutSigning.setOnClickListener(this);

        mGso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_sign_in:
                signIn();
                break;
            case R.id.without_sign:
                Intent withoutIntent = new Intent
                        (LoginActivity.this,
                                MainActivity.class);
                startActivity(withoutIntent);
                finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account == null) {
            mGoogleButton.setVisibility(View.VISIBLE);
        } else {
            mGoogleButton.setVisibility(View.GONE);
            Intent goHomeIntent = new Intent(LoginActivity.this, MainActivity.class);
            goHomeIntent.putExtra("user account", account);
            startActivity(goHomeIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            SharedPreferencesData.UserPreferences userPreferences =
                    SharedPreferencesData.UserPreferences.getPreferences();
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    userPreferences.setId(account.getId());
                    userPreferences.setEmail(account.getEmail());
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("user account", account);
                    startActivity(i);
                    finish();
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}
