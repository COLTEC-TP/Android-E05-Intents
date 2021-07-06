package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private static int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton camera = findViewById(R.id.camera);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, FOTO_CODE);
            }
        });

        /*
            A tela deverá permitir que a foto de perfil seja alterada por uma da câmera do aparelho.
            Além disso, sua tela deverá abrir o discador quando o usuário tocar no número de telefone,
            e abrir o aplicativo de e-mail quando o campo de e-mail for tocado.
         */
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        ImageView camera = findViewById(R.id.image_profile);

        if(requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            camera.setImageBitmap(photo);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
