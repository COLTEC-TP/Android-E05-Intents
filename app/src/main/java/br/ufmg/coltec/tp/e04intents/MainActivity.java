package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView foto = findViewById(R.id.foto);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, FOTO_CODE);
            }
        });

        EditText email = findViewById(R.id.txt_email);
        final Editable txtEmail = email.getText();
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("mailto:" + txtEmail);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);

            }
        });


        EditText telefone = findViewById(R.id.txt_telefone);
        final Editable txtTelefone = telefone.getText();
        telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + txtTelefone);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case FOTO_CODE:
                    Bitmap foto = (Bitmap) data.getExtras().get("data");
                    ImageView imgFoto = findViewById(R.id.foto);
                    imgFoto.setImageBitmap(foto);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
