package com.example.news.model.sharedPrefrance;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.news.NewsApp;


public class SharedPreferencesData {
    private final String SHARED_PREFERENCES = "sharedPreference";
    private static SharedPreferencesData mPreferences;
    private SharedPreferences mSharedPreferences;

    private SharedPreferencesData() {
        mSharedPreferences =
                NewsApp
                        .getInstance()
                        .getApplicationContext()
                        .getSharedPreferences
                                (SHARED_PREFERENCES,
                                        Context.MODE_PRIVATE);
    }

    public static SharedPreferencesData getPreferencesInstance() {
        if (mPreferences == null) {
            mPreferences =
                    new SharedPreferencesData();
        }
        return mPreferences;
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor =
                mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public void setInteger(String key, int value) {
        SharedPreferences.Editor editor =
                mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInteger(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public void clearUserPrefrences() {
        SharedPreferences.Editor editor =
                mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static class UserPreferences {
        private SharedPreferencesData mInstance =
                SharedPreferencesData.getPreferencesInstance();
        private static UserPreferences mUserInstance;
        private final String USER_ID = "id";
        private final String USER_EMAIL = "email";
        private final String USER_NAME = "name";
        private final String USER_TOKEN = "token";

        private UserPreferences() {

        }

        public static UserPreferences getPreferences() {
            if (mUserInstance == null) {
                mUserInstance = new UserPreferences();
            }
            return mUserInstance;
        }

        public String getId() {
            return mInstance.getString(USER_ID);
        }

        public void setId(String value) {
            mInstance.setString(USER_ID, value);
        }

        public String getEmail() {
            return mInstance.getString(USER_EMAIL);
        }

        public void setEmail(String value) {
            mInstance.setString(USER_EMAIL, value);
        }

        public String getName() {
            return mInstance.getString(USER_NAME);
        }

        public void setName(String value) {
            mInstance.setString(USER_NAME, value);
        }

        public void setToken(String token) {
            mInstance.setString(USER_TOKEN, token);
        }

        public String getToken() {
            return mInstance.getString(USER_TOKEN);
        }

        public void logOut() {
            mInstance.clearUserPrefrences();
        }
    }
}
