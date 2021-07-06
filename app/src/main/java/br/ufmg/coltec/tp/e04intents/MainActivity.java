package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton camera = findViewById(R.id.camera);
        final TextView textEmail = findViewById(R.id.email);
        final TextView textPhone = findViewById(R.id.phone_number);
        Button barCode = findViewById(R.id.bar_code);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, FOTO_CODE);
            }
        });

        textPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + textPhone.getText());
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(phoneIntent);
            }
        });

        textEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{textEmail.getText().toString()});
                email.putExtra(Intent.EXTRA_SUBJECT, "ASSUNTO DO EMAIL");
                email.putExtra(Intent.EXTRA_TEXT, "CORPO DO EMAIL");
                email.setType("message/rfc822");

                startActivity(email);
                
            }
        });

        barCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
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
