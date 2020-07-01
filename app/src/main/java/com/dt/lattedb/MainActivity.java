package com.dt.lattedb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dt.lattedb.fido.FingerPrintActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private IntentIntegrator qrScan;
    private Intent intent;


    public static Variable variable = new Variable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginbtn);

        qrScan = new IntentIntegrator(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                qrScan.setPrompt("스캔 중...");
                qrScan.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(MainActivity.this, "스캔 취소", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "스캔 완료", Toast.LENGTH_SHORT).show();

                variable.qrId = result.getContents();
                Log.d("TEST", "qrId : " + variable.qrId);
                //Todo FingerPrintActivity 시작
                intent = new Intent(this, FingerPrintActivity.class);
                startActivity(intent);
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
