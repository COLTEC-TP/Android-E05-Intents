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

    private static final int  FOTO_CODE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView foto = findViewById(R.id.perfil_foto);
        TextView texTelefone = findViewById(R.id.perfil_fone);
        TextView texEmail = findViewById(R.id.perfil_email);

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = findViewById(R.id.perfil_foto);

        // verifica se retorno pertence a Intent chamada anterioremente e se ela foi executada corretamente
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
