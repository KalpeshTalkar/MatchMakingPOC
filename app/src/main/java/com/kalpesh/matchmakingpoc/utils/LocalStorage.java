//
// Copyright © 2017 Kalpesh Talkar. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// For support: https://gist.github.com/KalpeshTalkar/eebb69a3fee4fc8ffcf4eac25757b435
//

package com.kalpesh.matchmakingpoc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.kalpesh.matchmakingpoc.App;
import com.kalpesh.matchmakingpoc.BuildConfig;

/**
 * A wrapper/abstraction layer over SharedPreferences which features storage/retrieving data from/to SharedPreferences.
 * Created by Kalpesh Talkar on 17/08/17.
 */
public class LocalStorage {

    /**
     * Shared preferences name.
     */
    private static final String APP_PREFS = "AppPrefs";

    /**
     * Get instance of shared preferences.
     *
     * @return SharedPreferences private mode instance.
     */
    private static SharedPreferences getSharedPrefs() {
        return App.sharedContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    /**
     * Get instance of the shared preferences editor.
     *
     * @return Editor instance.
     */
    private static SharedPreferences.Editor getEditor() {
        return getSharedPrefs().edit();
    }

    /**
     * Get value from shared preferences.
     *
     * @param key          Key for accessing the value.
     * @param defaultValue Default value if the value does not exists.
     * @param type         Data type of the value.
     * @param <T>          Data type of the value.
     * @return Value against the key.
     * The value can be null if it does not exist or cannot be casted to the desired data type
     */
    public static <T> T getValue(String key, Object defaultValue, Class<T> type) {
        Object value = null;
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (type.equals(String.class)) {    // String
            String defVal = "";
            if (defaultValue instanceof String) {
                defVal = (String) defaultValue;
            }
            try {
                value = sharedPrefs.getString(key, defVal);
            } catch (Exception e) {
                logError("Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Integer.class)) {    // Integer
            int defVal = 0;
            if (defaultValue instanceof Integer) {
                defVal = (int) defaultValue;
            }
            try {
                value = sharedPrefs.getInt(key, defVal);
            } catch (Exception e) {
                logError("Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Float.class)) {  // Float
            float defVal = 0;
            if (defaultValue instanceof Float) {
                defVal = (float) defaultValue;
            }
            try {
                value = sharedPrefs.getFloat(key, defVal);
            } catch (Exception e) {
                logError("Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Boolean.class)) {    // Boolean
            boolean defVal = false;
            if (defaultValue instanceof Boolean) {
                defVal = (boolean) defaultValue;
            }
            try {
                value = sharedPrefs.getBoolean(key, defVal);
            } catch (Exception e) {
                logError("Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Long.class)) {   // Long
            long defVal = 0;
            if (defaultValue instanceof Long) {
                defVal = (long) defaultValue;
            }
            try {
                value = sharedPrefs.getLong(key, defVal);
            } catch (Exception e) {
                logError("Error getting value for key: " + key + "\nError: " + e.getMessage());
            }
        }
        return type.cast(value);
    }

    /**
     * Store data in shared preferences.
     *
     * @param value Value to be store.
     * @param key   Key for the value.
     */
    public static void putValue(Object value, String key) {
        SharedPreferences.Editor editor = getEditor();
        if (value instanceof String) {    // String
            editor.putString(key, (String) value);

        } else if (value instanceof Integer) {    // Integer
            editor.putInt(key, (int) value);

        } else if (value instanceof Float) {  // Float
            editor.putFloat(key, (float) value);

        } else if (value instanceof Boolean) {    // Boolean
            editor.putBoolean(key, (boolean) value);

        } else if (value instanceof Long) {   // Long
            editor.putLong(key, (long) value);

        }
        editor.commit();
    }

    /**
     * Print error logs only in debug variant.
     *
     * @param message Message to be printed in the logs.
     */
    private static void logError(@NonNull String message) {
        if (BuildConfig.DEBUG) Log.e("LocalStorage", message);
    }

}
