package com.example.news.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.model.sharedPrefrance.SharedPreferencesData;
import com.example.news.ui.LoginActivity;
import com.example.news.ui.MainActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;

public class Profile extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private TextView profileName, profileEmail, googleEditText;
    private ImageView profileIcon;
    private SignInButton logout;
    private SharedPreferencesData.UserPreferences userPreferences;
    private final int PROFILE_SIGN_IN = 2000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        profileEmail = view.findViewById(R.id.profile_email);
        profileIcon = view.findViewById(R.id.profile_icon);
        profileName = view.findViewById(R.id.profile_name);
        logout = view.findViewById(R.id.sign_out_button);
        logout.setSize(SignInButton.SIZE_STANDARD);
        googleEditText = (TextView) logout.getChildAt(0);
        initAccountData();
    }

    private void initAccountData() {
        GoogleSignInAccount account = getActivity().getIntent().getParcelableExtra("user account");
        if (account != null) {

            profileName.setVisibility(View.VISIBLE);
            profileEmail.setVisibility(View.VISIBLE);
            profileIcon.setVisibility(View.VISIBLE);
            profileName.setText(account.getDisplayName());
            profileEmail.setText(account.getEmail());
            if (account.getPhotoUrl() == null) {
                profileIcon.setImageResource(R.drawable.profile_icon);
            } else {
                Glide.with(getActivity()).load(account.getPhotoUrl()).into(profileIcon);
            }
        } else {
            profileName.setVisibility(View.GONE);
            profileEmail.setVisibility(View.GONE);
            profileIcon.setVisibility(View.GONE);
        }
        initGoogleSettings(account);
    }

    @SuppressLint("SetTextI18n")
    private void initGoogleSettings(GoogleSignInAccount account) {
        userPreferences = SharedPreferencesData
                .UserPreferences
                .getPreferences();

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        final GoogleApiClient client = new GoogleApiClient
                .Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        if (account != null) {
            googleEditText.setText("Log out");
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View click) {
                    Auth.GoogleSignInApi.signOut(client).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            userPreferences.logOut();
                            Intent logOutIntent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(logOutIntent);
                        }
                    });
                }
            });
        } else {
            googleEditText.setText("Sign in");
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View click) {
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(client);
                    Profile.this.startActivityForResult(signInIntent, PROFILE_SIGN_IN);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PROFILE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                userPreferences.setId(account.getId());
                userPreferences.setEmail(account.getEmail());
                Intent signInFrommProfile = new Intent(getActivity(), MainActivity.class);
                signInFrommProfile.putExtra("user account", account);
                startActivity(signInFrommProfile);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}
