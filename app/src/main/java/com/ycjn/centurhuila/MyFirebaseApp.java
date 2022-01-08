package com.ycjn.centurhuila;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Norbey on 16/05/2018.
 */

public class MyFirebaseApp extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
    /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

}
