package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button barCode = findViewById(R.id.bar_code);

        barCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, 0);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // contents contains whatever was encoded
                String contents = intent.getStringExtra("SCAN_RESULT");

                // Format contains the type of code i.e. UPC, EAN, QRCode etc...
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                TextView codeLabel = findViewById(R.id.code_label);
                codeLabel.setText(contents);
            }
        }

    }
}