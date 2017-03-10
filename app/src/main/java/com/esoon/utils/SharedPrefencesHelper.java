package com.esoon.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/7.
 */

public class SharedPrefencesHelper {

    SharedPreferences   sp;
    SharedPreferences.Editor    editor;
    Context context;
    public SharedPrefencesHelper(Context    c,String    name){

        context=c;
        sp=context.getSharedPreferences(name,0);
        editor=sp.edit();

    }
    public void putValue(String key,String  value){
        editor=sp.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public String   getValue(String key){
        return sp.getString(key,null);

    }

}
