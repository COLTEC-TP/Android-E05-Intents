package com.example.a2016951790.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        startActivityForResult(intent,0);


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // contents contains whatever was encoded
                String contents = intent.getStringExtra("SCAN_RESULT");

                Toast.makeText(this, contents,
                        Toast.LENGTH_LONG).show();


            }
        }

    }

}
