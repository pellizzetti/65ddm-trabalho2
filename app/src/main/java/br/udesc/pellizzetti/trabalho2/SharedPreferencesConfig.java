package br.udesc.pellizzetti.trabalho2;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesConfig {

    private static final String PREF_NAME = "Trab2";

    public static final String IS_LOGGED = "IsLogged";
    public static final String USER  = "userName";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesConfig(Context context) {
        this.context = context;
        sharedPreferences =  context.getSharedPreferences(PREF_NAME, 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLoginData(String user, boolean isLogged) {
        editor.putString(USER, user);
        editor.putBoolean(IS_LOGGED ,  isLogged);
        editor.apply();
    }

    public boolean isLogged() {
        return sharedPreferences.getBoolean(IS_LOGGED, false);
    }

    public String getUser() {
        return sharedPreferences.getString(USER, null);
    }
}
