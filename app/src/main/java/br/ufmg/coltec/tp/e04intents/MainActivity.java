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

import java.security.PrivateKey;

public class MainActivity extends Activity {
    private static int IMG_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editImage = findViewById(R.id.btn_img);
        TextView userEmail = findViewById(R.id.user_email);
        TextView userNumber = findViewById(R.id.user_number);

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(editImage, 1);
            }
        });

        userEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:");
                Intent email = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(email);
            }
        });

        userNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView num = (TextView) v;
                String userNum = num.getText().toString();
                Uri uri = Uri.parse(new StringBuilder().append("tel:").append(userNum).toString());
                Intent email = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(email);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == IMG_CODE && resultCode == Activity.RESULT_OK){
            Bitmap user = (Bitmap) data.getExtras().get("data");
            ImageView userImage = findViewById(R.id.img_user);
            userImage.setImageBitmap(user);
        }

    }

}
