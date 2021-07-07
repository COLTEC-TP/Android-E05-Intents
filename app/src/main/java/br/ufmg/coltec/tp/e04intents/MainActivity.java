package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends Activity {

    private static int SCAN_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = findViewById(R.id.btn_scan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new IntentIntegrator(MainActivity.this).initiateScan();

                Intent scanner = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(scanner, SCAN_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SCAN_CODE && resultCode == Activity.RESULT_OK){

            String cod = data.getStringExtra("SCAN_RESULT");
//            Log.d("debug",cod);
            Toast.makeText(this, "Codigo: " + cod, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Erro na leitura do código de barras", Toast.LENGTH_LONG).show();
        }

//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {//
//                Toast.makeText(this, "Erro na leitura", Toast.LENGTH_LONG).show();//
//            } else {
//                Toast.makeText(this, "Codigo: " + result.getContents(), Toast.LENGTH_LONG).show();//
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }
}
