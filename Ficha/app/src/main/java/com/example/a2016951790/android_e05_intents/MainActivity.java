package com.example.a2016951790.android_e05_intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private static int FOTO_CODE = 1;

    public void onClick(View v){
        Uri uri = Uri.parse("Email: Teste");
        Intent intent = new Intent(Intent.ACTION_SEND, uri);
        intent.setType("application/octet-stream");
        startActivity(intent);
    }

    public void onClick1(View v){
        Uri uri = Uri.parse("tel: 12345678");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent ifoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(ifoto, FOTO_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView visu = findViewById(R.id.foto);

        // verifica se retorno pertence a Intent chamada anterioremente,
        // e se ela foi executada corretamente
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            visu.setImageBitmap(photo);
        }
    }


}