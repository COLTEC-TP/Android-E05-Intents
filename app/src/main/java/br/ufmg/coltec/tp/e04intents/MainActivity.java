package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
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

public class MainActivity extends Activity {

    private static final int FOTO_CODE = 1;
    private static final int BARRA_CODE = 2;
    private static final String ACTION_CODBARRA = "com.google.zxing.client.android.SCAN";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView foto = findViewById(R.id.perfil_foto);
        TextView texTelefone = findViewById(R.id.perfil_fone);
        TextView texEmail = findViewById(R.id.perfil_email);
        Button botao = findViewById(R.id.botao);

        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        texTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:31988700006");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        texEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:reinaldomagas@gmail.com");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri); // não funfa com ACTION_SEND
                startActivity(intent);
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_CODBARRA);
                try{
                    startActivityForResult(intent, BARRA_CODE); // A B S U R D O !!!  O Android Studio não avisa que precisa de try catch
                }
                catch (android.content.ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this,"Você não tem aplicativo de leitura de código de barra",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = findViewById(R.id.perfil_foto);

        switch (requestCode){
            case FOTO_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(photo);
                }
                break;
            case BARRA_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    int resultado = Integer.parseInt(data.getStringExtra("SCAN_RESULT"));
                    Toast.makeText(MainActivity.this,"O código é " + resultado,Toast.LENGTH_LONG).show();
                }
                break;
            default:
                //nada
                break;
        }
    }
}
