package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class BarcodeActivity extends Activity {

    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        Button btnIntent = findViewById(R.id.btn_intent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "SCAN_MODE");

                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                String contents = data.getStringExtra("SCAN_RESULT");
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BarcodeActivity.this);

                alertBuilder.setIcon(R.drawable.ic_launcher_foreground);
                alertBuilder.setTitle("Informações do escaneamento");
                alertBuilder.setMessage("Contents: " + contents + " / " + "Format: " + format);

                alertBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BarcodeActivity.this, "Saindo do Dialog", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}