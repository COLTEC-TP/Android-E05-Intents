package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView ligar= findViewById(R.id.telefone);
        TextView mensagem = findViewById(R.id.email);
        ImageView foto = findViewById(R.id.foto);

        ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:0000-0000");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uriText =
                        "mailto:icaroemd18@gmail.com" +
                                "?subject=" + Uri.encode("some subject text here") +
                                "&body=" + Uri.encode("some text here");

                Uri uri = Uri.parse(uriText);

                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                startActivity(Intent.createChooser(sendIntent, "Send email"));

            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            ImageView imageView = findViewById(R.id.foto);

            if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        }
}