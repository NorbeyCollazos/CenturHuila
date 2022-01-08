package com.ycjn.centurhuila.utilidades;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Norbey on 15/06/2018.
 */

public class InstanceidService extends FirebaseInstanceIdService {

    public static final String TAG = "NOTICIAS";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Token: " + token);

        enviarTokenAlServidor(token);
    }

    private void enviarTokenAlServidor(String token) {
        // Enviar token al servidor
    }
}
