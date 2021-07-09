package br.ufmg.coltec.tp.e05_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int BARCODE_SCANNER_CODE = 1;
    private static final int CAMERA_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView foto = findViewById(R.id.foto);
        TextView telefone = findViewById(R.id.telefone);
        TextView email = findViewById(R.id.email);
        Button button = findViewById(R.id.btn_codigo_barras);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_CODE);
            }
        });

        telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telefoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(telefone.getText().toString()));
                startActivity(telefoneIntent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email.getText().toString()));
                startActivity(emailIntent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent codigoBarrasIntent  = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(codigoBarrasIntent, BARCODE_SCANNER_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case 1:
                    String resultado = data.getStringExtra("SCAN_RESULT");
                    Toast.makeText(MainActivity.this, "Código: " + resultado, Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    ImageView foto = findViewById(R.id.foto);
                    Bitmap imagem = data.getExtras().getParcelable("data");
                    foto.setImageBitmap(imagem);
                    break;
                default:
                    break;
            }
        }
    }
}