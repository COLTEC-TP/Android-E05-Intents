package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static int FOTO_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView foto = (ImageView) findViewById(R.id.imagemView);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });
        TextView email = (TextView) findViewById(R.id.emailView);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:");
                intent.setData(data);
                startActivity(intent);
            }
        });

        TextView telefone = findViewById(R.id.telefoneView);

        telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imagem = findViewById(R.id.imagemView);

        // verifica se retorno pertence a Intent chamada anterioremente,
        // e se ela foi executada corretamente
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imagem.setImageBitmap(photo);
        }
    }
}

