package com.dt.lattedb.fido;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.Toast;

import com.dt.lattedb.MainActivity;

@TargetApi(Build.VERSION_CODES.M)
public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {

    private Context appContext;

    public FingerPrintHandler(Context context) {
        this.appContext = context;

    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId,
                                      CharSequence errString) {
        Toast.makeText(appContext,
                "Authentication error\n" + errString,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId,
                                     CharSequence helpString) {
        Toast.makeText(appContext,
                "Authentication help\n" + helpString,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(appContext,
                "등록되지 않은 지문입니다." ,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {

        Intent intent = new Intent(appContext, MainActivity.class);
        appContext.startActivity(intent);
        Toast.makeText(appContext,"지문이 확인되었습니다.\n" , Toast.LENGTH_LONG).show();
    }
}


