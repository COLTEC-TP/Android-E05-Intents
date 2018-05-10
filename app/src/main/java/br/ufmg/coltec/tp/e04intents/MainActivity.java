package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

//    Codigo da foto de perfil
    private static int FOTO_CODE = 1;
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgButton =(ImageButton)findViewById(R.id.imageButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = findViewById(R.id.imageButton);

        // verifica se retorno pertence a Intent chamada anterioremente,
        // e se ela foi executada corretamente
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
