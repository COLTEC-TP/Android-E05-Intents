package comd.example.a2016951618.e05_codigodebarras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton img_barcode_btn = findViewById(R.id.btn_barcode);

        img_barcode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, 0);
            }
        });

    }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {

            String content = "";
            TextView txt_resultado = findViewById(R.id.txt_resultado);

            if (requestCode == 0) {
                if (resultCode == RESULT_OK) {
                    content = intent.getStringExtra("SCAN_RESULT");
                    String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                }
            }

            if (!content.contentEquals("")) {
                txt_resultado.setText(content);
            }
        }
}
