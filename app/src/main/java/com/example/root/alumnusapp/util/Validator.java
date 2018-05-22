package com.example.root.alumnusapp.util;

import android.widget.EditText;

public class Validator {

    public static boolean isEmpty(String value){
        if(value.isEmpty()){
            return true;
        }
        return false;
    }
}
