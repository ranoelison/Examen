package com.example.mada_tour.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    private Context mContext;

    public Utils(Context mContext) {
        this.mContext = mContext;
    }

    public String getToken(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        return token;
    }
}
