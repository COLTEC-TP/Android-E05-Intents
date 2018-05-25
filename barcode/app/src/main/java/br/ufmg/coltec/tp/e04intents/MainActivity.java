package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static int FOTO_CODE = 1;
    TextView telephoneTextView;
    TextView codeResultTextView;
    ImageView photoImageView;
    ImageButton photoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codeResultTextView = findViewById(R.id.codeResult_tv);
        photoImageView = findViewById(R.id.foto_iv);
        photoButton = findViewById(R.id.imageButton);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");

            startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // contents contains whatever was encoded
                String contents = data.getStringExtra("SCAN_RESULT");

                // Format contains the type of code i.e. UPC, EAN, QRCode etc...
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");

                if (!TextUtils.isEmpty(contents)) {
                    codeResultTextView.setText(contents);
                }
            }
        }
    }
}
