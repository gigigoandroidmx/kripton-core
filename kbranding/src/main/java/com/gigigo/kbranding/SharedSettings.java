package com.gigigo.kbranding;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Defines a configuration class for accessing,modifying, serializing and deserializing preference data
 *
 * @author Juan Godínez Vera - 5/30/2017
 * @version 1.0.0
 * @since 1.0.0
 */
public class SharedSettings {
    private Context context;
    private SharedPreferences sharedPreferences;

    private Gson gson;

    public Context getContext() {
        return context;
    }

    private SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedSettings setContext(Context context) {
        this.context = context;
        gson = new Gson();
        return this;
    }

    public SharedSettings setSharedPreferencesName(String name) {
        sharedPreferences = getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return this;
    }

    public boolean settingExist(String key) {
        boolean exist;

        try {
            exist = getSharedPreferences().contains(key);
        } catch (Exception exception) {
            exist = false;
        }

        return  exist;
    }

    public String getSetting(String key, String otherwise) {
        return getSharedPreferences().getString(key, otherwise);
    }

    public void setSetting(String key, String data, boolean replaceIfExist) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();

        if(replaceIfExist) {
            editor.putString(key, data);
            editor.commit();
        }
        else {
            if(!settingExist(key)) {
                editor.putString(key, data);
                editor.commit();
            }
        }
    }

    public <T> T getSettingFromJson(String key, Class<T> typeClass, T otherwise) {
        T setting;

        if(settingExist(key)) {
            String jsonPreference = getSetting(key, null);
            setting = deserialize(jsonPreference, typeClass);
        }
        else {
            setting = otherwise;
        }

        return setting;
    }

    public <T> void setSettingToJson(String key, T data, boolean replaceIfExist) {
        String json = serialize(data);
        setSetting(key, json, replaceIfExist);
    }

    public <T> String serialize(T data) {
        String json;

        try {
            json = gson.toJson(data);
        } catch (JsonSyntaxException e) {
            json = null;
        }

        return json;
    }

    public <T> T deserialize(String json, Class<T> typeClass) {
        T data;

        try {
            data = gson.fromJson(json, typeClass);
        } catch (JsonSyntaxException e) {
            data = null;
        }

        return data;
    }
}