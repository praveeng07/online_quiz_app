package com.example.onlinetestv3;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHandler {
    private static final String PREF_NAME = "QuizAppPrefs";
    private static final String KEY_LOGIN_USERNAME = "login_username";
    private static final String KEY_LOGIN_PASSWORD = "login_password";
    private static final String KEY_QUIZ_ANSWER_PREFIX = "quiz_answer_";
    private static final String KEY_REMEMBER_ME = "remember_me";

    private static final String KEY_NEW_USERNAME = "newUserName";
    private static final String KEY_NEW_USER_PASS = "newUserPass";
    private static final String KEY_CONFIRM_PASS = "confirmPass";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHandler(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Save login details
    public void saveLoginDetails(String username, String password) {
        editor.putString(KEY_LOGIN_USERNAME, username);
        editor.putString(KEY_LOGIN_PASSWORD, password);
        editor.apply();
    }

    // Get login username
    public String getLoginUsername() {
        return sharedPreferences.getString(KEY_LOGIN_USERNAME, "user101");
    }

    // Get login password
    public String getLoginPassword() {
        return sharedPreferences.getString(KEY_LOGIN_PASSWORD, "pass101");
    }

    // Save quiz answer (correct or not) at a specific position (index)
    public void saveQuizAnswer(int index, boolean isCorrect) {
        String key = KEY_QUIZ_ANSWER_PREFIX + index;
        editor.putBoolean(key, isCorrect);
        editor.apply();
    }

    // Get quiz answer at a specific position (index)
    public boolean getQuizAnswer(int index) {
        String key = KEY_QUIZ_ANSWER_PREFIX + index;
        // If the key is not found, return false (assuming the default answer is incorrect)
        return sharedPreferences.getBoolean(key, false);
    }

    // Reset all quiz answers to false
    public void resetQuizAnswers() {
        for (int i = 0; i < 20; i++) {
            String key = KEY_QUIZ_ANSWER_PREFIX + i;
            editor.putBoolean(key, false);
        }
        editor.apply();
    }

    // Get the number of correct quiz answers
    public int getCorrectAnswersCount() {
        int count = 0;
        for (int i = 0; i < 20; i++) {
            String key = KEY_QUIZ_ANSWER_PREFIX + i;
            if (sharedPreferences.getBoolean(key, false)) {
                count++;
            }
        }
        return count;
    }

    // Get the number of wrong quiz answers
    public int getWrongAnswersCount() {
        return 20 - getCorrectAnswersCount();
    }

    public boolean getRememberMe(){
        return  sharedPreferences.getBoolean(KEY_REMEMBER_ME,false);
    }
    public void setRememberMe(boolean shouldRemember){
        editor.putBoolean(KEY_REMEMBER_ME,shouldRemember);
        editor.apply();
    }

    public void setNewUserName(String newUserName) {
        editor.putString(KEY_NEW_USERNAME, newUserName);
        editor.apply();
    }

    public void setNewUserPass(String newUserPass) {
        editor.putString(KEY_NEW_USERNAME, newUserPass);
        editor.apply();
    }

    public void setConfirmPass(String confirmPass) {
        editor.putString(KEY_NEW_USERNAME, confirmPass);
        editor.apply();
    }


    public String getNewUserName() {
        return sharedPreferences.getString(KEY_NEW_USERNAME, "");
    }

    public String getNewUserPass() {
        return sharedPreferences.getString(KEY_NEW_USER_PASS, "");
    }

    public String getConfirmPass() {
        return sharedPreferences.getString(KEY_CONFIRM_PASS, "");
    }
}


