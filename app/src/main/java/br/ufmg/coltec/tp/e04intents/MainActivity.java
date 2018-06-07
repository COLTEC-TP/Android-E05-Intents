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

    private final int FOTO_CODE = 1;
    private final int CODIGO_CODE = 2;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pic = findViewById(R.id.picture);
        TextView email = findViewById(R.id.email);
        TextView phone = findViewById(R.id.phone);
        Button cdb = findViewById(R.id.cdb);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:31994799570");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:mtgo007@hotmail.com");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
            }
        });
        cdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, CODIGO_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            pic.setImageBitmap(photo);
        }
        if (requestCode == CODIGO_CODE && resultCode == Activity.RESULT_OK){
            String contents = data.getStringExtra("SCAN_RESULT");

            Toast.makeText(getBaseContext(), "Resultado do Scanner: "+contents, Toast.LENGTH_LONG).show();
        }
    }
}
